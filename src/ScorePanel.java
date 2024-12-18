import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
	private int score = 0;
	private String playerName;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel playerLabel;
	private JPanel life1 = new JPanel();//목숨 표기 JPanel
	private JPanel life2 = new JPanel();
	private JPanel life3 = new JPanel();
	private boolean setDouble = false; //점수 2배 on/off
	
	private ImageIcon lifeIcon;
	
	public ScorePanel(String player) {
	    this.setBackground(Color.YELLOW);
	    this.setLayout(new GridLayout(6, 1));
	    playerName = player;
	    
	    lifeIcon = new ImageIcon("img/life.png");
	    Image image = lifeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);//이미지 크기 수정
        lifeIcon = new ImageIcon(image);

	    // 점수 표시
	    JLabel scoreTitle = new JLabel("-SCORE- ", JLabel.CENTER);
	    scoreTitle.setFont(new Font("Arial", Font.BOLD, 20));
	    add(scoreTitle);

	    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
	    add(scoreLabel);

	    // 플레이어 표시
	    JLabel playerTitle = new JLabel("-PLAYER-", JLabel.CENTER);
	    playerTitle.setFont(new Font("Arial", Font.BOLD, 20));
	    add(playerTitle);
	    
	    playerLabel = new JLabel(playerName);
	    playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    playerLabel.setFont(new Font("굴림", Font.BOLD, 20));
	    add(playerLabel);
	    
	    //목숨 표시
	    JLabel lifeCount = new JLabel("-LIFE-", JLabel.CENTER);
	    lifeCount.setFont(new Font("Arial", Font.BOLD, 20));
	    add(lifeCount);
	    
	    JPanel lifePanel = new JPanel();
	    lifePanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
	    lifePanel.setBackground(Color.yellow);
	    
	    life1.setPreferredSize(new Dimension(30, 35));
        life1.setOpaque(false); // 배경을 투명하게 설정
        JLabel life1Label = new JLabel(lifeIcon);
        life1.add(life1Label);
        lifePanel.add(life1);
        
        life2.setPreferredSize(new Dimension(30, 35));
        life2.setOpaque(false); // 배경을 투명하게 설정
        JLabel life1Label2 = new JLabel(lifeIcon);
        life2.add(life1Label2);
        lifePanel.add(life2);
        
        life3.setPreferredSize(new Dimension(30, 35));
        life3.setOpaque(false); // 배경을 투명하게 설정
        JLabel life1Label3 = new JLabel(lifeIcon);
        life3.add(life1Label3);
        lifePanel.add(life3);
        
        add(lifePanel);
	}
	
	public void decreaseLife(int life) {
		if(life == 2) {
			life3.setVisible(false);
		}
		else if(life == 1) {
			life2.setVisible(false);
		}
		else if(life == 0) {
			life1.setVisible(false);
		}
	}
	
	public void increase() {//점수 증가 함수
		if(!setDouble)
			score += 10;
		else
			score += 20;
		
		scoreLabel.setText(Integer.toString(score));
	}
	public void reset() {
		score = 0;
		scoreLabel.setText(Integer.toString(score));
		setDouble = false;
	}
	public int getScore() {
		return score;
	}
	public void healLife(int life) {// "회복"이지 "증가"가 아니므로 남은 목숨이 3개라면 변화 없음
		if(life == 1) {
			add(life2);
			life2.setVisible(true);
		}
		else if(life == 2) {
			add(life3);
			life3.setVisible(true);
		}
		else {
			return;
		}
	}
	public void doubleMode(boolean onoff) {
		setDouble = onoff;
	}
}
