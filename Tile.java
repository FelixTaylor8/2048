
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Tile {
	
	int value;
	JLabel box;
	
    public Tile() {
    	value = 0;
    	box = new JLabel();
    	box.setPreferredSize(new Dimension(120, 120));
    	box.setHorizontalAlignment(SwingConstants.CENTER);
    	box.setVerticalAlignment(SwingConstants.CENTER);
    	box.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
    	box.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
    	box.setOpaque(true);
    }
    
    public void setValue(int value) {
    	this.value = value;
    	if (value > 0) box.setText("" + value);
    	else box.setText(null);
    	switch (value) {
    	case 0:
    		box.setBackground(null);
    	//	box.setForeground(Color.BLACK);
    		break;
    	case 2:
    		box.setBackground(Board.COLOR_2);
    		box.setForeground(Color.BLACK);
    		break;
    	case 4:
    		box.setBackground(Board.COLOR_4);
    		box.setForeground(Color.BLACK);
    		break;
    	case 8:
    		box.setBackground(Board.COLOR_8);
    		box.setForeground(Color.BLACK);
    		break;
    	case 16:
    		box.setBackground(Board.COLOR_16);
    		box.setForeground(Color.BLACK);
    		break;
    	case 32:
    		box.setBackground(Board.COLOR_32);
    		box.setForeground(Color.BLACK);
    		break;
    	case 64:
    		box.setBackground(Board.COLOR_64);
    		box.setForeground(Color.BLACK);
    		break;
    	case 128:
    		box.setBackground(Board.COLOR_128);
    		box.setForeground(Color.BLACK);
    		break;
    	case 256:
    		box.setBackground(Board.COLOR_256);
    		box.setForeground(Color.BLACK);
    		break;
    	case 512:
    		box.setBackground(Board.COLOR_512);
    		box.setForeground(Color.BLACK);
    		break;
    	case 1024:
    		box.setBackground(Board.COLOR_1024);
    		box.setForeground(Color.BLACK);
    		break;
    	default:
    		box.setBackground(Color.BLACK);
    		box.setForeground(Color.BLACK);
    		break;
    	}
    }


    public int getValue() {
    	return value;
    }
    
    public JLabel getBox() {
    	return box;
    }

}
