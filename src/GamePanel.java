import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class GamePanel extends JPanel{
	private ArrayList<WordLabel> fallingLabel = new ArrayList<>();//떨어지는 단어 리스트
	private ArrayList<FallingThread> fallingThreads = new ArrayList<>();//단어에 추가되는 스레드 리스트
	
	private GameGroundPanel ground = new GameGroundPanel();
	private InputPanel input = new InputPanel();
	private ScorePanel scorePanel = null;
	private TextSource textSource = null;
	private EditPanel editPanel = null;
	
	private GenerateThread gThread = new GenerateThread();//단어 생성 스레드 생성
	private LifeCountThread lThread = new LifeCountThread();//목숨 카운트 스레드 생성
	
	private int sltLv;
	private String player;
	private int fallSpeed; //단어 떨어지는 주기
	private int generateSpeed; //단어 생성 주기
	
	private volatile int life = 3;// 디폴트 목숨 개수 3개, volatile로 스레드간 상태 공유
	private boolean sItemFlag = false; //시간정지 아이템 플레그 아이템 효과 적용중일때 true
	private boolean dItemFlag = false; //점수두배 아이템 플레그 아이템 효과 적용중일때 true

	private ImageIcon icon = new ImageIcon("img/GameBackGround.jpg");//배경 이미지
	private Image img = icon.getImage();
	
	private ImageIcon targetIcon = new ImageIcon("img/Word.png");//단어 이미지
	private ImageIcon wordIcon = new ImageIcon(targetIcon.getImage().getScaledInstance(80, 20, Image.SCALE_SMOOTH));//단어 이미지 크기조정
	
		
	
	
	public GamePanel(ScorePanel scorePanel,TextSource textSource, int level, String playerName, EditPanel editPanel) {
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;
		this.textSource = textSource;
		this.sltLv = level;
		this.player = playerName;
		if(sltLv == 0){//난이도에 따른 속도 - 0 = 상, 1 = 중, 2 = 하
			this.fallSpeed = 200;
			this.generateSpeed = 800;
		}
		else if(sltLv == 1) {
			this.fallSpeed = 300;
			this.generateSpeed = 1200;
		}
		else if(sltLv == 2) {
			this.fallSpeed = 400;
			this.generateSpeed = 1600;
		}
		
		setLayout(new BorderLayout());
		add(ground,BorderLayout.CENTER);
		add(input,BorderLayout.SOUTH);
	}
	
	public void setTextSource(TextSource textSource) {
		this.textSource = textSource;
	}
	
	public void startGame() { //게임 시작 함수 게임 시작 시 단어 생성, 목숨 카운트 스레드 실행
		gThread = new GenerateThread();
		lThread = new LifeCountThread();
		gThread.start();
		lThread.start();
		
		synchronized (fallingLabel) {//중단된 레이블에 스레드 다시 연결 후 실행
			for(JLabel label : fallingLabel) {
				if(label.getY()<ground.getHeight()) {
					FallingThread newThread = new FallingThread(label);
					fallingThreads.add(newThread);
					newThread.start();
				}
			}
		}
	}
	
	public void stopGame() { //게임 중단 함수 
		if(gThread != null && gThread.isAlive()) {
			gThread.interrupt();
		}
		if(lThread != null && lThread.isAlive()) {
			lThread.interrupt();
		}
		for(Thread thread : Thread.getAllStackTraces().keySet()) {
			if(thread instanceof FallingThread)
				thread.interrupt();
		}
	}
	
	private JLabel newWord() {
		//떨어지는 단어 생성
		WordLabel label = new WordLabel(textSource.get());
		label.setSize(120,40);
		label.setLocation((int)(Math.random()*(ground.getWidth()-100)), 0);
		
		label.setIcon(wordIcon);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		fallingLabel.add(label);
		ground.add(label);
		ground.repaint();
		
		//생성한 단어에 스레드 부여
		FallingThread fallingThread = new FallingThread(label);
		fallingThreads.add(fallingThread);
		fallingThread.start();
		return label;
	}
	
	class GameGroundPanel extends JPanel {
		private ImageIcon playerIcon = new ImageIcon("img/player.png");
		private ImageIcon player = new ImageIcon(playerIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)); 
		private ImageIcon shoot = new ImageIcon("img/shootingEffect.png");
		private ImageIcon shootIcon = new ImageIcon(shoot.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		private	JLabel fireEffect = new JLabel(shootIcon);
		public GameGroundPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
			
			JPanel end_line = new JPanel(); //산성비 바닥라인 설정
	        end_line.setBackground(Color.DARK_GRAY);
	        end_line.setBounds(0, 425, 550, 10);
	        
	        
	        JLabel player = new JLabel(this.player);
	        player.setHorizontalTextPosition(JLabel.CENTER);
	        player.setVerticalTextPosition(JLabel.CENTER);
	        player.setBounds(100,325,100,100);
	        
	        
	        fireEffect.setBounds(180,290,50,50);
            fireEffect.setHorizontalTextPosition(JLabel.CENTER);
	        fireEffect.setVerticalTextPosition(JLabel.CENTER);
	        fireEffect.setVisible(false);
	        add(fireEffect);
	        add(player);
	        add(end_line);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img,0,0,this.getWidth(),this.getHeight(),null);
		}
		public void setEffectOff() {
			fireEffect.setVisible(false);
		}
		public void setEffectOn() {
			fireEffect.setVisible(true);
		}
	}
	
	
	class FallingThread extends Thread{
		private JLabel label;
		
		public FallingThread(JLabel label) {
			this.label = label;
		}
		@Override
		public void run() {
			try {
				while(label.getY()<ground.getHeight() && !Thread.currentThread().isInterrupted()) {
					if(sItemFlag) {
						Thread.sleep(2000);
					}
					else
						Thread.sleep(fallSpeed);
					ground.setEffectOff();
					label.setLocation(label.getX(),label.getY()+10);
					if(label.getY() >= 405) { //endline에 닿으면
						ground.remove(label);//레이블 지우고
						fallingLabel.remove(label);
						ground.repaint();
						life--;//목숨 차감
						ground.setEffectOn();
						Thread.currentThread().interrupt();//닿은 레이블의 스레드 삭제
					}
				}
			}catch(InterruptedException e) {
				Thread.currentThread().interrupt(); //인터럽트 상태 업데이트
			}
		}
	}
	
	class GenerateThread extends Thread{
		@Override
		public void run() {
			try {
				while(!Thread.currentThread().isInterrupted()) {
					if(sItemFlag) {
						Thread.sleep(2000);
					}
					JLabel label;					
					label = newWord();
					Thread.sleep(generateSpeed); //단어 생성 주기 - 난이도별 조절
				}
			}catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	class LifeCountThread extends Thread {
	    @Override
	    public void run() {
	        while (true) {
	            if (life <= 0) {
	                JOptionPane.showMessageDialog(GamePanel.this, "Game Over!"); // Game Over 메시지
	                stopGame(); // GamePanel의 stopGame() 메서드를 호출하여 게임 종료
	                new EndScreen(player ,scorePanel.getScore());
	                Window window = SwingUtilities.getWindowAncestor(GamePanel.this);//GamePanel의 상위 JFrame 가져옴
	                if (window instanceof JFrame) {
	                    ((JFrame) window).dispose(); //JFrame 닫기
	                }
	                break; // 스레드 종료
	            }
	            scorePanel.decreaseLife(life);
	            try {
	                Thread.sleep(100); // 너무 빠른 체크를 방지
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt(); // 스레드가 중단될 경우
	                break;
	            }
	        }
	    }
	}
	
	 class InputPanel extends JPanel {
	        private JTextField tf = new JTextField(10);
	        
	        public InputPanel() {
	            this.setBackground(Color.LIGHT_GRAY);
	            add(tf);
	            
	            tf.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    String text = tf.getText();
	                    if (text.isEmpty()) return;

	                    // fallingLabel과 fallingThreads을 안전하게 처리
	                    synchronized (fallingLabel) {
	                    	Iterator<WordLabel> labelIterator = fallingLabel.iterator();
	                    	Iterator<FallingThread> threadIterator = fallingThreads.iterator();
	                        while (labelIterator.hasNext() && threadIterator.hasNext()) {
	                            WordLabel label = labelIterator.next();
	                            if (text.equals(label.getText())) {
	                                scorePanel.increase();
	                                ground.remove(label);
	                                runItem(label);
	                                labelIterator.remove();
	                                threadIterator.next().interrupt();
	                                threadIterator.remove();
	                                ground.fireEffect.setVisible(true);
	                                ground.repaint();
	                                break;
	                            }
	                        }
	                    }

	                    tf.setText("");  // 텍스트 필드 초기화
	                }
	            });

	        }
		public void reset() {
			tf.setText("");
		}
	}
	 
	 class Item{//아이템 정보 클래스
		 private String name;
		 private int effect; //0 = 목숨 회복, 1 = 2초간 정지, 3 = 5초간 점수 두배
		 
		 public Item(String name,int effect) {
			 this.name = name;
			 this.effect = effect;
		 }
		 
		 public String getName() {
			 return name;
		 }
		 public int getEffect() {
			 return effect;
		 }
	 }
	  
	 public void runItem(WordLabel label) { // 아이템 작동 함수
		        switch (label.getItem()) {
		            case 0:
		            	break;
		            case 1: // 목숨 회복
		            	if(!sItemFlag) {		            		
		            		if(life < 3) {
			            		life++;
			            		scorePanel.healLife(life);
			            		editPanel.itemStatus("체력 회복!",0);//시간 필요 없음
			            	}
		            	}
		            	break;
		            case 2: // 2초간 정지
		            	if(!sItemFlag) {
		            		sItemFlag = true;
		            		new ItemThread("시간 정지!",2).start();
		            	}
			            break;
		            case 3:// 5초간 점수 2배
		            	if(!dItemFlag) {
		            		scorePanel.doubleMode(true);
		            		dItemFlag = true;
		            		new ItemThread("점수 두배!",5).start();
		            	}
		                break;
		            default:
		                break;
		    }
		}
	 
	 class ItemThread extends Thread {
		 private String status;
		 private int remainTime;
		 
		 public ItemThread(String status, int remainTime) {
			 this.status = status;
			 this.remainTime = remainTime;
		 }
		 
		 @Override
		 public void run() {
			 try {
				 for(int time = remainTime; time>0; time--) {
					 editPanel.itemStatus(status, time);
					 Thread.sleep(1000);
				 }
			 }catch(InterruptedException e) {
				 Thread.currentThread().interrupt();
			 }
			 
			 if(status.equals("시간 정지!")) {
				 sItemFlag = false;
				 editPanel.itemStatus("", 0);
			 }
			 else if (status.equals("점수 두배!")) {
		            scorePanel.doubleMode(false); // 점수 두배 해제
		            dItemFlag = false; // 점수 두배 효과 종료
		            editPanel.itemStatus("", 0); // 아이템 상태 갱신
		        }
			 
		 }
		 
	 }
}
