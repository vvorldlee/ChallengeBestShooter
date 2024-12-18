import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class StartScreen extends JFrame {
	private String player;
	private ImageIcon icon;
	private Image img;
	
	public StartScreen() {//게임의 시작화면
		setTitle("산성비 게임");
		setSize(800,600);
		icon = new ImageIcon("img/StartScreen.png");
		img = icon.getImage();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		StartScreenPanel panel = new StartScreenPanel();
		setContentPane(panel);
		
//		Container contentPane = getContentPane();
//		contentPane.setLayout(null);
//		contentPane.add(panel);
		

		
		setVisible(true);
	}

	public static void main(String[] args) {
		new StartScreen();
	}
	
	private class StartScreenPanel extends JPanel {
		public StartScreenPanel() {
			setLayout(null);
			
			JButton startBtn = new JButton("게임 시작");
			startBtn.setSize(100,40);
			startBtn.setLocation(240,450);
			
			JButton rankBtn = new JButton("랭킹");
			rankBtn.setSize(100,40);
			rankBtn.setLocation(350,450);
			
			JButton exitBtn = new JButton("종료");
			exitBtn.setSize(100,40);
			exitBtn.setLocation(460,450);
			
			JTextField playerName = new JTextField(10);
			playerName.setBounds(350, 450, 200, 40);
			playerName.setLocation(300, 500);
			playerName.setVisible(true);
			
			add(playerName);
			add(startBtn);
			add(rankBtn);
			add(exitBtn);
		
			startBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					player = playerName.getText().trim();
					new SettingScreen(player);
					setVisible(false);
					dispose();
				}
			});
			
			rankBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new RankScreen();
				}
			});
			
			exitBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		}
		
	}

}
