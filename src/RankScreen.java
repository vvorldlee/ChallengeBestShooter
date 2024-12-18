import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class RankScreen extends JFrame{//랭킹 화면
	private JLabel title;
	private JPanel rankPanel;
	ArrayList<PlayerInfo> playerList = new ArrayList<>();
	File rankList = new File("playerRanking.txt");
	
	public RankScreen() {
		setSize(500,800);
		setResizable(false);
		setTitle("랭킹");
		setLayout(new BorderLayout());
		
		title = new JLabel("- 랭킹 -");
		title.setFont(new Font("굴림",Font.BOLD,40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);
		
		rankPanel = new JPanel();
		rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS)); //수직 일자 정렬 레이아웃
		
		JScrollPane scrollPane = new JScrollPane(rankPanel); //랭크패널에 스크롤 달아줌
		add(scrollPane, BorderLayout.CENTER);
		
		try(Scanner scanner = new Scanner(rankList)){
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[]data = line.split(" ");
				String name = data[0];
				int playerScore = Integer.parseInt(data[1]);
				playerList.add(new PlayerInfo(name,playerScore));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		playerList.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        // 랭킹 정보를 JLabel 배열에 추가
        for (int i = 0; i < playerList.size(); i++) {
            PlayerInfo player = playerList.get(i);
            JLabel playerRank = new JLabel((i + 1) + ". " + player.getName() + " - " + player.getScore() + "점");
            playerRank.setFont(new Font("굴림",Font.PLAIN,30));
            playerRank.setHorizontalAlignment(SwingConstants.CENTER);
            rankPanel.add(playerRank);
        }
		
		setVisible(true);
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
