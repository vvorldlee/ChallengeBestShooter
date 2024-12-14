import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel{
	private ArrayList<JLabel> fallingLabel = new ArrayList<>();
	private GameGroundPanel ground = new GameGroundPanel();
	private InputPanel input = new InputPanel();
	private ScorePanel scorePanel = null;
	private TextSource textSource = null;
	private GenerateThread gThread = new GenerateThread();
	private int sltLv;
	private int fallSpeed; //단어 떨어지는 주기
	private int generateSpeed; //단어 생성 주기
	
	public GamePanel(ScorePanel scorePanel,TextSource textSource, int level) {
		this.scorePanel = scorePanel;
		this.textSource = textSource;
		this.sltLv = level;
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
	
	public void startGame() { //게임 시작 함수
		gThread.start();
	}
	
	public void stopGame() { //게임 중단 함수 
		if(gThread != null && gThread.isAlive()) {
			gThread.interrupt();
		}
		for(Thread thread : Thread.getAllStackTraces().keySet()) {
			if(thread instanceof FallingThread)
				thread.interrupt();
		}
		rmLabels();
	}
	
	public void rmLabels() {
		for(JLabel label : fallingLabel) {
			ground.remove(label);
		}
		fallingLabel.clear();
		ground.repaint();
	}
	
	private JLabel newWord() {
		JLabel label = new JLabel(textSource.get());
		label.setSize(100,20);
		label.setLocation((int)(Math.random()*(ground.getWidth()-100)), 0);
		fallingLabel.add(label);
		ground.add(label);
		ground.repaint();
		return label;
	}
	
	class GameGroundPanel extends JPanel {
		public GameGroundPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
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
					label.setLocation(label.getX(),label.getY()+10);
					Thread.sleep(fallSpeed);
				}
//				if(label.getY() >= ground.getHeight()) { //목숨 감소 구현
//					
//				}
				ground.remove(label);
				fallingLabel.remove(label);
				ground.repaint();
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	
	class GenerateThread extends Thread{
		@Override
		public void run() {
			try {
				while(!Thread.currentThread().isInterrupted()) {
					JLabel label = newWord();
					new FallingThread(label).start();
					Thread.sleep(generateSpeed); //단어 생성 주기 - 난이도별 조절
				}
			}catch(InterruptedException e) {
				return;
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

	                    for (JLabel label : fallingLabel) {
	                        if (text.equals(label.getText())) {
	                            scorePanel.increase();
	                            ground.remove(label);
	                            fallingLabel.remove(label);
	                            ground.repaint();
	                            break;
	                        }
	                    }
	                    tf.setText("");
	                }
	            });
	        }
		public void reset() {
			tf.setText("");
		}
	}
}
