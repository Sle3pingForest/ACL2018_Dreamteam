package Jeu;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {
    
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();

    
    public static void main(String[] args) throws SlickException {
		new Launcher(width,height);

	}

	public StateGame() {
		super("Link The Labyrinthe's Master");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		addState(new MainScreenGameState());
		addState(Jeu.getInstance());
	}
	
}
