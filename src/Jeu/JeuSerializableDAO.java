package Jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import model.personnages.Heros;

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
			oos.writeObject(j.getLesheros());
			oos.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Fichier non trouve");
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
				a = ((ArrayList<Heros>) ois.readObject());
				//jeu_charge.setContainer((GameContainer) ois.readObject());
				//jeu_charge.setLesHerosVue( (ArrayList<VueHeros>) ois.readObject());
				//jeu_charge.setLaby((VueLabyrinthe) ois.readObject());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			j.init_new(a);
			//Jeu.Jeu.load(jeu_charge);
			
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
