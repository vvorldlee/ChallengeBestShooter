import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class StartScreen extends JFrame {

	public StartScreen() {//게임의 시작화면
		setTitle("산성비 게임");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		JButton startBtn = new JButton("게임 시작");
		startBtn.setSize(100,40);
		startBtn.setLocation(350,400);
		
		JTextField playerName = new JTextField(10);
		playerName.setBounds(350, 450, 200, 40);
		playerName.setLocation(300, 450);
		playerName.setVisible(true);
		
		contentPane.add(playerName);
		contentPane.add(startBtn);
	
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String player = playerName.getText().trim();
				if(!player.isEmpty()) {
					savePlayerName(player);
				}
				new SettingScreen();
				setVisible(false);
			}
		});
	
		setVisible(true);
	}
	
	  private void savePlayerName(String playerName) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("playerNames.txt", true))) {
	            writer.write(playerName);
	            writer.newLine();  // 이름마다 새로운 줄에 기록
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
	        }
	    }

	public static void main(String[] args) {
		new StartScreen();
	}

}
