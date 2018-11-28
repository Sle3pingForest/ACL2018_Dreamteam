import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

	public static final int ID = 1;
	private Image background, press;
	private StateBasedGame game;
	private GameContainer container;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int width = (int) screenSize.getWidth();
	private static int height = (int) screenSize.getHeight();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		this.game = game;
		this.container = container;
		this.background = new Image("main/resources/background/wind_waker.png");
		this.press = new Image("main/resources/background/press_any_button.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawImage(press, width/2, (float) (height/2.5));
		// g.drawString("Appuyez sur entree", width/2, height/3);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key, char c) {
		game.enterState(Jeu.ID);
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE: container.exit() ; break;
		case Input.KEY_P: break;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
