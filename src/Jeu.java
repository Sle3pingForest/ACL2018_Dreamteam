import model.personnages.Heros;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import vues.aVueHeros;
import vues.aVueLabyrinthe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/* C'est la class principal de notre jeu
* Elle sair de boucle principal mais aussi de Controlleur pour les touche */
public class Jeu extends BasicGame {

    private GameContainer container;
    private static aVueLabyrinthe laby;
    private ArrayList<aVueHeros> lesHerosVue;
    private ArrayList<Heros> lesHeros;
    private DAOFactory d;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();
    
    private static Jeu instance = null;
	public static Jeu getInstance() {
		if (instance == null) {
			instance = new Jeu();
		}
		return instance;
	}

    private Jeu() {
        super("Link the Labyrinthe's Master");
        lesHeros = new ArrayList<Heros>();
        lesHerosVue = new ArrayList<aVueHeros>();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        laby = aVueLabyrinthe.getInstance();
        lesHeros.add(new Heros(300,280,"link"));
        lesHerosVue.add(new aVueHeros(aVueHeros.BLEU,lesHeros.get(0)));// car un joueur
    }
    
    
    public void init_new(ArrayList<Heros> lesHeros2) throws SlickException {
    	laby = aVueLabyrinthe.getInstance();
		lesHeros.clear();
		lesHeros = lesHeros2;
		lesHerosVue.clear();
		System.out.println(lesHeros);
		lesHerosVue.add(new aVueHeros(aVueHeros.BLEU,lesHeros.get(0)));// car un joueur
    	/*System.out.println(h.getX());
    	
    	lesHeros.get(0).setX(h.getX());
    	lesHeros.get(0).setY(h.getY());*/
    	
	}
    

	@Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        int cameraX = (int)(container.getWidth() / 2 - lesHeros.get(0).getX());
        int cameraY = (int)(container.getHeight() / 2 - lesHeros.get(0).getY());
        if(cameraX < -laby.getLongeurCarte()+container.getWidth()){
            cameraX = -laby.getLongeurCarte()+container.getWidth();
        }
        if(cameraY  < -laby.getHauteurCarte() + container.getHeight() ){
            cameraY = -laby.getHauteurCarte()+ container.getHeight();
        }
        if(cameraX > 0){
            cameraX = 0;
        }
        if(cameraY > 0){
            cameraY = 0;
        }

        g.translate(cameraX, cameraY);


        laby.render(container,g,-cameraX,-cameraX+container.getWidth(),-cameraY,-cameraY+ container.getHeight());
        for(aVueHeros h : lesHerosVue){
            h.render(container,g);
        }
    }

    public void keyPressed(int key, char c) {
        if(key == Input.KEY_ESCAPE){
            container.exit();
        }
        switch (key) {
            case Input.KEY_UP:  lesHeros.get(0).goHaut();    break;
            case Input.KEY_LEFT: lesHeros.get(0).goGauche();   break;
            case Input.KEY_DOWN: lesHeros.get(0).goBas();   break;
            case Input.KEY_RIGHT: lesHeros.get(0).goDroite();  break;
            case Input.KEY_0:	try {
				save();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            	break;
            case Input.KEY_1: try {
				this.charger();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            	break;
        }
    }

    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_UP:  lesHeros.get(0).arretHaut();    break;
            case Input.KEY_LEFT: lesHeros.get(0).arretGauche();   break;
            case Input.KEY_DOWN: lesHeros.get(0).arretBas();   break;
            case Input.KEY_RIGHT: lesHeros.get(0).arretDroite();  break;
        }
    }


    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        lesHeros.get(0).update(container,delta);
    }


    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Jeu(), width, height, true).start();
    }
    
    public void save() throws SlickException {
    	DAOFactory.getAbstractDAOFactory(1).getJeuDAO().save(this);
    }
    
    
    public void charger() throws SlickException {
    	DAOFactory.getAbstractDAOFactory(1).getJeuDAO().load(this);
    }
    
    public static aVueLabyrinthe getLaby() {
    	return laby;
    }
    
    public GameContainer getContainer() {
  		return container;
  	}

  	public ArrayList<aVueHeros> getLesHerosVue() {
  		return lesHerosVue;
  	}

  	public ArrayList<Heros> getLesHeros() {
  		return lesHeros;
  	}

  	public static Dimension getScreenSize() {
  		return screenSize;
  	}

  	public static int getWidth() {
  		return width;
  	}

  	public static int getHeight() {
  		return height;
  	}

	public void setContainer(GameContainer container) {
		this.container = container;
	}

	public static void setLaby(aVueLabyrinthe laby) {
		Jeu.laby = laby;
	}

	public void setLesHerosVue(ArrayList<aVueHeros> lesHerosVue) {
		this.lesHerosVue = lesHerosVue;
	}

	public void setLesHeros(ArrayList<Heros> lesHeros) {
		this.lesHeros = lesHeros;
	}

	public static void setScreenSize(Dimension screenSize) {
		Jeu.screenSize = screenSize;
	}

	public static void setWidth(int width) {
		Jeu.width = width;
	}

	public static void setHeight(int height) {
		Jeu.height = height;
	}

	public static void setInstance(Jeu instance) {
		Jeu.instance = instance;
	}

	
}