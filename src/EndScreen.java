import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class EndScreen extends JFrame {//게임 종료 화면
	JLabel over,playerInfo,rankSaveMessage;
	JButton rankYes,rankNo;
	private String name;
	private int playerScore;
	
	public EndScreen(String playerName, int score) {
		this.playerScore = score;
		this.name = playerName;
        // 창 크기 및 설정
        setTitle("Game Over");
        setSize(600, 500);
        setResizable(false);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(null);
        //setLocationRelativeTo(null); // 화면 가운데에 창 배치
        
        EndScreenPanel panel = new EndScreenPanel(name,playerScore);
		setContentPane(panel);
        
        
        setVisible(true);
    }
	
	class EndScreenPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("img/EndScreen.png");
		private Image img = icon.getImage();
		public EndScreenPanel(String playerName, int score) {
			setSize(600,500);
			setLayout(null);
			// 게임 오버 메시지
	        over = new JLabel("-GAME OVER-", JLabel.CENTER);
	        over.setFont(new Font("Arial", Font.BOLD, 30)); // 글씨 크기와 두께 조정
	        over.setForeground(Color.WHITE);
	        over.setHorizontalAlignment(SwingConstants.CENTER);
	        over.setBounds(47, 50, 500, 60); // 넉넉한 너비와 중앙 상단 배치
	        add(over);
	        
	        
	        playerInfo = new JLabel(playerName + "님의 점수는 " + score + "점 입니다.", SwingConstants.CENTER);
	        playerInfo.setFont(new Font("굴림", Font.BOLD, 25)); // 폰트 크기 조정
	        playerInfo.setForeground(Color.WHITE);
	        playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
	        playerInfo.setBounds(45, 280, 500, 30); // 위치와 크기 조정
	        add(playerInfo);
	        
	        //랭킹저장메세지
	        rankSaveMessage = new JLabel("랭킹에 저장하시겠습니까?",SwingConstants.CENTER);
	        rankSaveMessage.setFont(new Font("굴림", Font.BOLD, 20));
	        rankSaveMessage.setForeground(Color.WHITE);
	        rankSaveMessage.setBounds(50, 320, 500, 30);
	        add(rankSaveMessage);
	        
	        // "YES" 버튼 - 순위 저장
	        rankYes = new JButton("YES");
	        rankYes.setFont(new Font("Arial", Font.PLAIN, 15)); // 버튼 글씨 크기 증가
	        rankYes.setBounds(190, 350, 80, 40); // YES 버튼 위치 및 크기 조정
	        rankYes.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		JOptionPane.showMessageDialog(rankYes,"저장되었습니다. 초기화면으로 돌아갑니다.");
	        		
	        		savePlayer(name,playerScore);
	        		
	        		new StartScreen();
	        		dispose(); // 창 닫기
	        	}
	        });
	        add(rankYes);

	        // "NO" 버튼 - 순위 저장하지 않음
	        rankNo = new JButton("NO");
	        rankNo.setFont(new Font("Arial", Font.PLAIN, 15)); // 버튼 글씨 크기 증가
	        rankNo.setBounds(330, 350, 80, 40); // NO 버튼 위치 및 크기 조정
	        rankNo.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		JOptionPane.showMessageDialog(rankNo,"저장하지 않고 초기화면으로 돌아갑니다.");
	        		new StartScreen();
	        		dispose(); // 창 닫기
	        	}
	        });
	        add(rankNo);
	        
			setVisible(true);
			
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}
	
	private void savePlayer(String playerName, int score) {//랭킹파일에 플레이어 저장하는 함수
		ArrayList<PlayerInfo> playerList = new ArrayList<>(); //플레이어 랭킹 기록을 위한 배열
		File ranking = new File("playerRanking.txt");
		
		try (Scanner scanner = new Scanner(ranking)){ //파일의 플레이어 정보들을 불러와서 playerList에 저장
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[]data = line.split(" ");
				String name = data[0];
				int playerScore = Integer.parseInt(data[1]);
				playerList.add(new PlayerInfo(name,playerScore));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"파일을 읽을 수 없습니다.");
		}
		
		playerList.add(new PlayerInfo(playerName,score));//새로운 플레이어 추가
		playerList.sort((p1,p2) -> Integer.compare(p2.getScore(),p1.getScore()));//점수 높은 순으로 정렬
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("playerRanking.txt"))){//정렬된 플레이어리스트를 다시 파일에 저장
			for(PlayerInfo player : playerList) {
				writer.write(player.getName() + " " + player.getScore()+"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "저장 중 오류가 발생했습니다.");
		}
		
		
	}
	
	class PlayerInfo {
		private String name;
		private int score;
		
		public PlayerInfo (String name, int score) {
			this.name = name;
			this.score = score;
		}
		
		public String getName() {
			return this.name;
		}
		public int getScore() {
			return this.score;
		}
		
	}
}
