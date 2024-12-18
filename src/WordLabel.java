import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

public class WordLabel extends JLabel{//단어 레이블 클래스
	private String word; //단어
	private int item; //아이템 기능 0 = 기본 단어, 1 = 회복, 2 = 정지, 3 = 점수2배
	private Font font = new Font("굴림", Font.PLAIN, 12);
	private ImageIcon icon = new ImageIcon("img/Word.png");
	private JLabel textLabel;
	
	public WordLabel(String word) {
		this.word = word;
		this.setFont(font);
		
		//아이템 설정
		this.item = Math.random() < 0.8 ? 0 : -1; //80퍼센트로 0, 20퍼센트로 -1
		if(item == 0){
			item = 0;
		}
		else if (item == -1) {
			int persent = (int)(Math.random() * 3);
			if(persent == 0) {
				item = 1;//회복
				this.setForeground(Color.CYAN);
			}
			else if(persent == 1) {
				item = 2;//시간정지
				this.setForeground(Color.BLUE);
			}
			else if(persent == 2) {
				item = 3;//점수두배
				this.setForeground(Color.GREEN);
			}
			
		}

		this.setText(word);
	}
	
	public int getItem() {
		return this.item;
	}
}