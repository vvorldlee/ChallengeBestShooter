import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
	private int sltLang; //선택 언어
	private int sltLv; // 선택 난이도
	private String playerName;
	
	private TextSource textSource;
	private ScorePanel scorePanel;
	private EditPanel editPanel;
	private GamePanel gamePanel;
	
	public GameFrame(int lang, int level, String player) {
		playerName = player;
		sltLang = lang;
		sltLv = level;
		
		textSource = new TextSource(sltLang); // 생성자에서 초기화 -> null참조 방지
        editPanel = new EditPanel(textSource);
        scorePanel = new ScorePanel(player);
        gamePanel = new GamePanel(scorePanel, textSource, sltLv, playerName, editPanel);
        
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
		mb.setLayout(new BoxLayout(mb, BoxLayout.X_AXIS));
	
		
		JMenu fileMenu = new JMenu("단어");
		fileMenu.setMaximumSize(new Dimension(35,50));
		mb.add(fileMenu);
		
		JMenuItem wordList = new JMenuItem("단어목록");
		wordList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textSource.showWords(sltLang);
			}
		});
		
		JMenuItem wordAdd = new JMenuItem("단어추가");
		wordAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textSource.showWordAdd();
			}
		});
		fileMenu.add(wordList);
		fileMenu.add(wordAdd);
		
		mb.add(Box.createHorizontalStrut(-5));
		
		JMenuItem editMenu = new JMenuItem("설정");
		editMenu.setMaximumSize(new Dimension(35,50));
		editMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SettingScreen(playerName);
				gamePanel.stopGame();
				dispose();
			}
		});
		mb.add(editMenu);
		
		JMenuItem backToStart = new JMenuItem("초기화면");
		backToStart.setMaximumSize(new Dimension(55,50));
		backToStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StartScreen();
				setVisible(false);
			}
		});
		mb.add(backToStart);
		
		this.setJMenuBar(mb);
		
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
				gamePanel.stopGame();
				new GameFrame(sltLang,sltLv,playerName);
				dispose();
			}
		});
		tBar.setFloatable(false);	
	}
}

