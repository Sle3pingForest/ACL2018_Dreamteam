import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import model.personnages.Heros;
import vues.VueHeros;
import vues.VueLabyrinthe;

public class JeuSerializableDAO implements JeuDAO{


	private static JeuDAO instance = null;
	public static JeuDAO getInstance() throws SlickException {
		if (instance == null) {
			instance = new JeuSerializableDAO();
		}
		return instance;
	}

	@Override
	public void save(Jeu j) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("zeldiablo.sav"));
			//oos.writeObject(MoteurGraphique.getJ());
			//oos.writeObject(j.getContainer());
			oos.writeObject(j.getLesHeros());
			//oos.writeObject(j.getLesHerosVue());
			//oos.writeObject(j.getLaby());
			oos.close();
			System.out.println("SAVE");
		} catch (FileNotFoundException e1) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e1) {
			System.out.println("erreur IO");
		}

	}
	@Override
	public Jeu load(Jeu j) {
		try {
			Jeu jeu_charge = null;
			ArrayList<Heros> a = new ArrayList<>();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("zeldiablo.sav"));
			try {
				//jeu_charge.setContainer((GameContainer) ois.readObject());
				a = ((ArrayList<Heros>) ois.readObject());
				//jeu_charge.setLesHerosVue( (ArrayList<VueHeros>) ois.readObject());
				//jeu_charge.setLaby((VueLabyrinthe) ois.readObject());
				System.out.println("LOAD");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			j.init_new(a);
			//Jeu.load(jeu_charge);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return null;
	}

}
