import java.util.*;
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
	
}
