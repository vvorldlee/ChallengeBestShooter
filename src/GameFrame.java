import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
	private int sltLang; //선택 언어
	private int sltLv; // 선택 난이도
	
//	private TextSource textSource = new TextSource(sltLang);
//	private ScorePanel scorePanel = new ScorePanel();
//	private EditPanel editPanel = new EditPanel(textSource);
//	private GamePanel gamePanel = new GamePanel(scorePanel,textSource);
	
	private TextSource textSource;
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel editPanel;
	private GamePanel gamePanel;
	
	public GameFrame(int lang, int level) {
		sltLang = lang;
		sltLv = level;
		
		textSource = new TextSource(sltLang); // 생성자에서 초기화 -> null참조 방지
        editPanel = new EditPanel(textSource);
        gamePanel = new GamePanel(scorePanel, textSource, sltLv);
        
		setTitle("게임");  
		setSize(800,600);
		setResizable(false);//프레임 사이즈 고정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x키 누르면 종료
		makeMenu();
		makeToolBar();
		makeSplit();
		setVisible(true);
	}
	
	private void makeMenu(){
		JMenuBar mb = new JMenuBar();
		this.setJMenuBar(mb);
		
		JMenu fileMenu = new JMenu("단어");
		mb.add(fileMenu);
		JMenu editMenu = new JMenu("설정");
		mb.add(editMenu);
		
		
		
		JMenuItem startItem = new JMenuItem("재시작");
		fileMenu.add(startItem);
		startItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.startGame();
			}
			
		});
		
		JMenuItem stopItem = new JMenuItem("STOP");
		fileMenu.add(stopItem);
		stopItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.stopGame();
			}
		});
		
		JMenuItem exitItem = new JMenuItem("EXIT");
		fileMenu.add(exitItem);
	}
	
	private void makeSplit() {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(550);
		getContentPane().add(hPane, BorderLayout.CENTER);
		
		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		vPane.setDividerLocation(300);
		hPane.setRightComponent(vPane);
		hPane.setLeftComponent(gamePanel);
		vPane.setTopComponent(scorePanel);
		vPane.setBottomComponent(editPanel);
	}
	
	private void makeToolBar() {
		JToolBar tBar = new JToolBar();
		getContentPane().add(tBar,BorderLayout.NORTH);
		
		JButton startBtn = new JButton("시작");
		tBar.add(startBtn);
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.startGame();
			}
			
		});
		
		JButton stopBtn = new JButton("중단");
		tBar.add(stopBtn);
		stopBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.stopGame();
			}
		});
		
		JButton restartBtn = new JButton("재시작");
		tBar.add(restartBtn);
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameFrame(sltLang,sltLv);
				dispose();
			}
		});
		tBar.setFloatable(false);	
	}
}

