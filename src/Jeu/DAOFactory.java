package Jeu;

import org.newdawn.slick.SlickException;

public abstract class DAOFactory {
	
	public abstract JeuDAO getJeuDAO() throws SlickException;
	
	
	public static DAOFactory getAbstractDAOFactory(int format) {
		DAOFactory d = null;
		switch (format) {
		case 1:
			d = new SerializableFactory();
			break;
		}
		return d;
	}

}
