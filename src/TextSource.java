import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextSource {
	private Vector<String> v = new Vector<String>();
	
	public TextSource(int lang) {
		if(lang == 0) { // 한글 선택 시
			try {
				Scanner input = new Scanner(new File("Kor.txt"));
				while(input.hasNextLine()) {
					this.v.add(input.nextLine());
				}
			}catch(FileNotFoundException e) {
				System.out.println("파일을 찾지 못했습니다");
			}
		}
		else {
			try {
				Scanner input = new Scanner(new File("Eng.txt"));
				while(input.hasNextLine()) {
					this.v.add(input.nextLine());
				}
			}catch(FileNotFoundException e) {
				System.out.println("파일을 찾지 못했습니다");
			}
		}
	}
	
	public String get() { 
		int index = (int)(Math.random() * v.size());
		return v.get(index);
	}
	
	public void add(String word) {//단어 추가
		v.add(word);
	}
	
	public void showWords(int lang) {
		new WordScreen(lang);//WordScreen 생성 함수
	}
	
	public void showWordAdd() {
		new WordAddFrame();
	}
	
	class WordAddFrame extends JFrame {
		private JPanel inputPanel = new JPanel();
		private JPanel radioPanel = new JPanel();
		public WordAddFrame() {
			setSize(200,150);
			setTitle("단어 추가");
			setLayout(new BorderLayout(5,10));
			
			JRadioButton korSlt = new JRadioButton("한글");
			JRadioButton engSlt = new JRadioButton("영어");
			ButtonGroup group = new ButtonGroup();
			group.add(korSlt);
			group.add(engSlt);
			
			radioPanel.add(korSlt);
			radioPanel.add(engSlt);
			
			JTextField tf = new JTextField(10);
	
			inputPanel.add(tf);
			
			add(radioPanel,BorderLayout.NORTH);
			add(inputPanel,BorderLayout.CENTER);
			
			tf.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String text = tf.getText();
					if(!text.isEmpty()) {
						//한글 선택되어있으면 Kor텍스트파일 영어면 Eng텍스트파일
						String fileName = korSlt.isSelected() ? "Kor.txt" : "Eng.txt";
						File file = new File(fileName);
				        
				        try (Scanner scanner = new Scanner(text)) {        
				            try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
				                writer.println(scanner.nextLine());
				                JOptionPane.showMessageDialog(tf, "저장되었습니다!");
				            }
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				        tf.setText("");
					}
				}
			});
			setVisible(true);
		}
		
	}
	
	class WordScreen extends JFrame { //단어 목록 보여주는 JFrame
		private JTextArea textArea;
		private JScrollPane scrollPane;
		private String sltLang;
		public WordScreen(int lang) {
			if(lang == 0) {//선택한 언어에 따라 JFrame 제목 선택
				sltLang = "한국어";
			}
			else {
				sltLang = "영어";
			}
			
			setTitle(sltLang);
			setSize(200,800);
			
			textArea = new JTextArea();
			textArea.setEditable(false); // 텍스트 수정 불가 설정
	        

	        //textArea 스크롤 가능하게 설정
	        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        add(scrollPane, BorderLayout.CENTER); // 스크롤 패널을 화면 중앙에 추가

	        // 파일 읽어서 JTextArea에 출력
	        Scanner wordRS;//wordReadScanner
	        if(lang == 0) { // 한글 선택 시
	        	textArea.setFont(new Font("굴림", Font.PLAIN, 14)); // 폰트 설정
				try {
					wordRS = new Scanner(new File("Kor.txt"));
					while(wordRS.hasNextLine()) {
						textArea.append(wordRS.nextLine() + "\n");
					}
				}catch(FileNotFoundException e) {
					System.out.println("파일을 찾지 못했습니다");
				}
			}
			else {
				textArea.setFont(new Font("Arial", Font.PLAIN, 14)); // 폰트 설정
				try {
					wordRS = new Scanner(new File("Eng.txt"));
					while(wordRS.hasNextLine()) {
						textArea.append(wordRS.nextLine() + "\n");
					}
				}catch(FileNotFoundException e) {
					System.out.println("파일을 찾지 못했습니다");
				}
			}
	        
			setVisible(true);
		}
	}
	
}
