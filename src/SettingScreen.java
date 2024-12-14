import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingScreen extends JFrame { //게임 설정 화면
	private String[] lang = {"한글", "ENG"};      //한,영 타입 선택
    private String[] level = {"상","중","하"};     //난이도 선택
    private JLabel gmLang, gmLevel;
    private JButton startBtn, endBtn;
    private JComboBox<String> lvBox, lngBox;
    private int sltLang, sltLv;
    
	public SettingScreen(){
		setTitle("게임 설정");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		Container contentPane = getContentPane();
		
		GridLayout grid = new GridLayout(3,2);//레이아웃 설정
		grid.setVgap(15);
		contentPane.setLayout(grid);
		
		//언어 선택
		gmLang = new JLabel("언 어");
		gmLang.setHorizontalAlignment(SwingConstants.CENTER);
		gmLang.setFont(new Font("굴림", Font.BOLD, 30));
		contentPane.add(gmLang);
		
		lngBox = new JComboBox<String>(lang); //언어 선택 박스 생성
		contentPane.add(lngBox);
		lngBox.addActionListener(new ActionListener() {   //선택한 언어 반환
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                sltLang = cb.getSelectedIndex();
            }
        });
		//난이도 선택
		gmLevel = new JLabel("난이도");
		gmLevel.setHorizontalAlignment(SwingConstants.CENTER);
		gmLevel.setFont(new Font("굴림", Font.BOLD, 30));
		contentPane.add(gmLevel);
		lvBox = new JComboBox<String>(level); //난이도 선택 박스 생성
		contentPane.add(lvBox);
		lvBox.addActionListener(new ActionListener() {   //선택한 난이도 반환
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> cb = (JComboBox<String>) e.getSource();
                sltLv = cb.getSelectedIndex();
            }
        });
		
		JPanel button = new JPanel();
		button.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton backBtn = new JButton("시작!");
		backBtn.setFont(new Font("굴림",Font.BOLD,30));
		button.add(backBtn);
		contentPane.add(button);		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GameFrame gameFrame = new GameFrame(sltLang,sltLv);
				gameFrame.setVisible(true);
			}
		});
		
		
		setVisible(true);
	}
}
