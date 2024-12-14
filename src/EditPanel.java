import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditPanel extends JPanel {
	private JTextField tf = new JTextField(10);
	private TextSource textSource = null;
	public EditPanel(TextSource textSource) {
		this.textSource = textSource;
		this.setBackground(Color.CYAN);
		add(tf);
		JButton btn = new JButton("추가");
		add(btn);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = tf.getText();
				if(word.length() == 0)
					return;
				textSource.add(word);
			}
		});
	}
}
