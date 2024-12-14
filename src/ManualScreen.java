import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ManualScreen extends JFrame { //게임 설명 화면
	public ManualScreen(){
		setTitle("게임 설명");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		
		JButton backBtn = new JButton("돌아가기");
		backBtn.setSize(100,50);
		backBtn.setLocation(350,400);
		
		contentPane.add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StartScreen();
				setVisible(false);
			}
		});
		setVisible(true);
	}
}
