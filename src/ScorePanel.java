import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
	private int score = 0;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel playerName = new JLabel("Name");
	
	public ScorePanel() {
	    this.setBackground(Color.YELLOW);
	    this.setLayout(new GridLayout(6, 1));

	    // 점수 표시
	    JLabel scoreTitle = new JLabel("-SCORE- ", JLabel.CENTER);
	    scoreTitle.setFont(new Font("Arial", Font.BOLD, 20));
	    add(scoreTitle);

	    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
	    add(scoreLabel);

	    // 플레이어 표시
	    JLabel player = new JLabel("-PLAYER-", JLabel.CENTER);
	    player.setFont(new Font("Arial", Font.BOLD, 20));
	    add(player);

	    playerName.setHorizontalAlignment(SwingConstants.CENTER);
	    playerName.setFont(new Font("Arial", Font.PLAIN, 20));
	    add(playerName);
	    
	    //목숨 표시
	    JLabel lifeCount = new JLabel("-LIFE-", JLabel.CENTER);
	    lifeCount.setFont(new Font("Arial", Font.BOLD, 20));
	    add(lifeCount);
	    
	    JPanel lifePanel = new JPanel();
	    lifePanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
	    lifePanel.setBackground(Color.yellow);
	    
	    JPanel life1 = new JPanel();
        life1.setBackground(Color.RED);
        life1.setPreferredSize(new Dimension(30, 30));
        lifePanel.add(life1);
        
        JPanel life2 = new JPanel();
        life2.setBackground(Color.RED);
        life2.setPreferredSize(new Dimension(30, 30));
        lifePanel.add(life2);
        
        JPanel life3 = new JPanel();
        life3.setBackground(Color.RED);
        life3.setPreferredSize(new Dimension(30, 30));
        lifePanel.add(life3);
        
        add(lifePanel);
	}
	public void increase() {//점수 증가 함수
		score += 10;
		scoreLabel.setText(Integer.toString(score));
	}
	public void reset() {
		score = 0;
		scoreLabel.setText(Integer.toString(score));
	}
}
