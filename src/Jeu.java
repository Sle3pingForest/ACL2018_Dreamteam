import model.Labyrinthe;
import model.personnages.Heros;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import vues.VueHeros;
import vues.VueLabyrinthe;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/* C'est la class principal de notre jeu
* Elle sair de boucle principal mais aussi de Controlleur pour les touche */
public class Jeu extends BasicGame {

    private GameContainer container;
    private static VueLabyrinthe labyVue;
    private static Labyrinthe labyModel;
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
        super("Link The Labyrinthe's Master");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        labyModel =  new Labyrinthe(100,100);
        labyVue = VueLabyrinthe.getInstance();
        labyVue.setLab(labyModel);
        //lesHerosVue.add(new VueHeros(VueHeros.BLEU,lesHeros.get(0)));// car un joueur
    }
    
    
    public void init_new(ArrayList<Heros> lesHeros2) throws SlickException {
    	labyVue = VueLabyrinthe.getInstance();
		//lesHeros.clear();
		//lesHeros = lesHeros2;
		//lesHerosVue.clear();
		//System.out.println(lesHeros);
		//lesHerosVue.add(new VueHeros(VueHeros.BLEU,lesHeros.get(0)));// car un joueur
    	/*System.out.println(h.getX());
    	
    	lesHeros.get(0).setX(h.getX());
    	lesHeros.get(0).setY(h.getY());*/
    	
	}
    

	@Override
    public void render(GameContainer container, Graphics g) throws SlickException {
	    Heros h = labyModel.getHeros(0);
        int cameraX = (int)(container.getWidth() / 2 - h.getX());
        int cameraY = (int)(container.getHeight() / 2 - h.getY());
        if(cameraX < -labyVue.getLongeurCarte()+container.getWidth()){
            cameraX = -labyVue.getLongeurCarte()+container.getWidth();
        }
        if(cameraY  < -labyVue.getHauteurCarte() + container.getHeight() ){
            cameraY = -labyVue.getHauteurCarte()+ container.getHeight();
        }
        if(cameraX > 0){
            cameraX = 0;
        }
        if(cameraY > 0){
            cameraY = 0;
        }

        g.translate(cameraX, cameraY);


        labyVue.render(container,g,-cameraX,-cameraX+container.getWidth(),-cameraY,-cameraY+ container.getHeight());

    }

    public void keyPressed(int key, char c) {
        if(key == Input.KEY_ESCAPE){
            container.exit();
        }
        switch (key) {
            case Input.KEY_UP:  labyModel.goHaut();    break;
            case Input.KEY_LEFT: labyModel.goGauche();   break;
            case Input.KEY_DOWN: labyModel.goBas();   break;
            case Input.KEY_RIGHT: labyModel.goDroite();  break;
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
            case Input.KEY_UP:  labyModel.arretHaut();    break;
            case Input.KEY_LEFT: labyModel.arretGauche();   break;
            case Input.KEY_DOWN: labyModel.arretBas();   break;
            case Input.KEY_RIGHT: labyModel.arretDroite();  break;
        }
    }


    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        labyModel.update(container,delta);
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
    
   /* public static VueLabyrinthe getLaby() {
    	return laby;
    }
    
    public GameContainer getContainer() {
  		return container;
  	}

  	public ArrayList<VueHeros> getLesHerosVue() {
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

	public static void setLaby(VueLabyrinthe laby) {
		Jeu.laby = laby;
	}

	public void setLesHerosVue(ArrayList<VueHeros> lesHerosVue) {
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
	}*/

	
}