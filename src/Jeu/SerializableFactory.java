package Jeu;

import org.newdawn.slick.SlickException;

public class SerializableFactory extends DAOFactory {

	@Override
	public JeuDAO getJeuDAO() throws SlickException {
			return JeuSerializableDAO.getInstance();
	}

}
