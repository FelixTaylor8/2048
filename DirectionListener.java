import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DirectionListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) Board.move(Direction.UP);
		else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) Board.move(Direction.DOWN);
		else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) Board.move(Direction.LEFT);
		else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) Board.move(Direction.RIGHT);
	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }
}
