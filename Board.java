import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Board {
	
	static final Color COLOR_128 = new Color(173, 221, 255); //light blue	
	static final Color COLOR_1024 = new Color(255, 173, 216); //pink	
	static final Color COLOR_32 = new Color(193, 251, 164); //green	
	static final Color COLOR_16 = new Color(255, 239, 159); //yellow	
	static final Color COLOR_4 = new Color(240, 151, 151); //red	
	static final Color COLOR_512 = new Color(212, 202, 227); //purple	
	static final Color COLOR_8 = new Color(255, 210, 158); //orange
	static final Color COLOR_256 = new Color(191, 208, 217); //another blue
	static final Color COLOR_2 = new Color(255, 209, 209); //light red	
	static final Color COLOR_64 = new Color(164, 222, 176); //teal


	static int highScore, tileCount;
	static JFrame frame;
	static JPanel panel, tilePanel, upper;
	static Tile[][] tiles = new Tile[4][4];
	static JLabel scoreText;
	
	public static void main (String[] args) {
		new Board();
	}
	
	public Board() {
		if (frame != null) frame.dispose();
		highScore = 0;
		frame = new JFrame("2048");
		
		upper = new JPanel(new GridLayout(0, 2));
		scoreText = new JLabel("Score: 0");
		scoreText.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setVerticalAlignment(SwingConstants.CENTER);
		
		upper.add(scoreText);
		
        JButton b = new JButton ("Restart");
        b.setBackground(Color.WHITE);
        //b.setForeground(Color.BLUE);
        b.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        b.setFocusable(false);
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                new Board();
            }  
        });
        
        upper.add(b);
		
		tilePanel = new JPanel(new GridLayout(4, 4));
		tileCount = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				tiles[x][y] = new Tile();
				tilePanel.add(tiles[x][y].getBox());
			}
		}
		addTile();
		addTile();
		
        panel = new JPanel(new BorderLayout());
        panel.add(upper, BorderLayout.NORTH);
        panel.add(tilePanel, BorderLayout.SOUTH);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(new DirectionListener());
		
	}
	
	public static void addTile() {
		Random r = new Random();
		while (tileCount < 16) {
			int x = r.nextInt(4);
			int y = r.nextInt(4);
			if (tiles[x][y].getValue() == 0) {
				int chance = r.nextInt(10);
				if (chance == 0) tiles[x][y].setValue(4);
				else tiles[x][y].setValue(2);
				tileCount++;
				break;
			}
		}
	}
	
	public static void move(Direction direction) {
		switch (direction) {
		case UP:
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					int search = x + 1;
					while (search < 4) {
						if (tiles[search][y].getValue() == 0) search++;
						else if (tiles[search][y].getValue() != tiles[x][y].getValue()) break;
						else {
							tiles[x][y].setValue(tiles[search][y].getValue() * 2);
							if (tiles[x][y].getValue() > highScore) {
								highScore = tiles[x][y].getValue();
								scoreText.setText("Score: " + highScore);
							}
							tiles[search][y].setValue(0);
							tileCount--;
							break;
						}
					}
				}
			}
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					int search = x + 1;
					while (tiles[x][y].getValue() == 0 && search < 4) {
						tiles[x][y].setValue(tiles[search][y].getValue());
						tiles[search][y].setValue(0);
						search++;
					}
				}
			}
			break;
		case DOWN:
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					int search = x - 1;
					while (search >= 0) {
						if (tiles[search][y].getValue() == 0) search--;
						else if (tiles[search][y].getValue() != tiles[x][y].getValue()) break;
						else {
							tiles[x][y].setValue(tiles[search][y].getValue() * 2);
							tiles[search][y].setValue(0);
							if (tiles[x][y].getValue() > highScore) {
								highScore = tiles[x][y].getValue();
								scoreText.setText("Score: " + highScore);
							}							
							tileCount--;
							break;
						}
					}
				}
			}
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					int search = x - 1;
					while (tiles[x][y].getValue() == 0 && search >= 0) {
						tiles[x][y].setValue(tiles[search][y].getValue());
						tiles[search][y].setValue(0);
						search--;
					}
				}
			}
			break;
		case LEFT:
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					int search = y + 1;
					while (search < 4) {
						if (tiles[x][search].getValue() == 0) search++;
						else if (tiles[x][search].getValue() != tiles[x][y].getValue()) break;
						else {
							tiles[x][y].setValue(tiles[x][search].getValue() * 2);
							tiles[x][search].setValue(0);
							if (tiles[x][y].getValue() > highScore) {
								highScore = tiles[x][y].getValue();
								scoreText.setText("Score: " + highScore);
							}							
							tileCount--;
							break;
						}
					}
				}
			}
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					int search = y + 1;
					while (tiles[x][y].getValue() == 0 && search < 4) {
						tiles[x][y].setValue(tiles[x][search].getValue());
						tiles[x][search].setValue(0);
						search++;
					}
				}
			}
			break;
		case RIGHT:
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					int search = y - 1;
					while (search >= 0) {
						if (tiles[x][search].getValue() == 0) search--;
						else if (tiles[x][search].getValue() != tiles[x][y].getValue()) break;
						else {
							tiles[x][y].setValue(tiles[x][search].getValue() * 2);
							tiles[x][search].setValue(0);
							if (tiles[x][y].getValue() > highScore) {
								highScore = tiles[x][y].getValue();
								scoreText.setText("Score: " + highScore);
							}							
							tileCount--;
							break;
						}
					}
				}
			}
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					int search = y - 1;
					while (tiles[x][y].getValue() == 0 && search >= 0) {
						tiles[x][y].setValue(tiles[x][search].getValue());
						tiles[x][search].setValue(0);
						search--;
					}
				}
			}
			break;		
		default:
			break;
		}
		addTile();		
	}
}
