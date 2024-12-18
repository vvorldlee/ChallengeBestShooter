import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditPanel extends JPanel {
	private JLabel activatingItemText = new JLabel("아이템 : ");
	private JLabel remainTimeText = new JLabel("남은 시간 : ");
	private JLabel activatingItem = new JLabel("");
	private JLabel remainTime = new JLabel("");
	private Font font = new Font("굴림",Font.BOLD,15);
	
	public EditPanel(TextSource textSource) {
		this.setBackground(Color.CYAN);
		setLayout(new GridLayout(2,2,10,10));
		
		this.add(activatingItemText);
		this.add(activatingItem);
		this.add(remainTimeText);
		this.add(remainTime);
		setFont();
		centerAligned();
	
	}
	
	public void setFont() {
		activatingItemText.setFont(font);
		activatingItem.setFont(font);
		remainTimeText.setFont(font);
		remainTime.setFont(font);
	}
	
	public void centerAligned() {
		activatingItemText.setHorizontalAlignment(SwingConstants.CENTER);
		activatingItem.setHorizontalAlignment(SwingConstants.CENTER);
		remainTimeText.setHorizontalAlignment(SwingConstants.CENTER);
		remainTime.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void itemStatus(String item, int time) {
		activatingItem.setText(item);
		remainTime.setText(time + "s");
	}
}
