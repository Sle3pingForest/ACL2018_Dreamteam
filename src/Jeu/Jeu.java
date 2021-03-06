package Jeu;

import model.Item.Item;
import model.Labyrinthe;
import model.mur.Mur;
import model.personnages.Heros;

import model.personnages.monstres.Monstre;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import vues.HudBarreDeVie;
import vues.HudEndGame;
import vues.HudVictoire;
import vues.Song;
import vues.VueLabyrinthe;

import java.awt.*;
import java.util.ArrayList;

/* C'est la class principal de notre jeu
* Elle sair de boucle principal mais aussi de Controlleur pour les touche */
public class Jeu extends BasicGameState {

	public static final int ID = 2;
	
	private StateBasedGame game;
    private GameContainer container;
    private static VueLabyrinthe labyVue;
    private static Labyrinthe labyModel;
    private DAOFactory d;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int width = (int) screenSize.getWidth();
    private static int height = (int) screenSize.getHeight();
    
    private HudBarreDeVie hud;
    private HudEndGame hud_fin_du_jeu;
    private HudVictoire hudVictory;

    public static Mur[][] LESMURS = null;
    public static Item[][] LESITEMS;
    public static ArrayList<Monstre> LESMONSTRES;
    public static int XDEBUT;
    public static  int YDEBUT;


    
    private static Jeu instance = null;
	public static Jeu getInstance() {
		if (instance == null) {
			instance = new Jeu();
		}	
		return instance;
	}

    private Jeu() {
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.game = game;
        if(LESMURS == null){
            labyModel =  new Labyrinthe(50,50,   10);
        }
        else{
            labyModel =  new Labyrinthe(LESMURS,LESITEMS,LESMONSTRES,XDEBUT,YDEBUT);
        }
        labyVue = VueLabyrinthe.getInstance();
        labyVue.setLab(labyModel);
        Song.chargerForet();
        Song.jouerBackground();
        float x = labyModel.getHeros(0).getX();
        float y = labyModel.getHeros(0).getY();
    	hud = new HudBarreDeVie(labyModel);
    	hudVictory = new HudVictoire();
    	hudVictory.init(x,y);
        this.hud.init(x,y);
        this.hud_fin_du_jeu = new HudEndGame();
    }
    
    
    public void init_new(ArrayList<Heros> lesHeros2) throws SlickException {
    	labyVue = VueLabyrinthe.getInstance();
        labyModel.getHeros(0).charge(lesHeros2.get(0));
	}
    

	@Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	    //g.scale(1.2f,1.2f);
	    Heros h = labyModel.getHeros(0);
        int cameraX = (int)(container.getWidth() / 2 - h.getX());
        int cameraY = (int)(container.getHeight() / 2 - h.getY());
        if(cameraX < -labyModel.getLongeurCarte()+container.getWidth()){
            cameraX = -labyModel.getLongeurCarte()+container.getWidth();
        }
        if(cameraY  < -labyModel.getHauteurCarte() + container.getHeight() ){
            cameraY = -labyModel.getHauteurCarte()+ container.getHeight();
        }
        if(cameraX > 0){
            cameraX = 0;
        }
        if(cameraY > 0){
            cameraY = 0;
        }

        g.translate(cameraX, cameraY);
        

        labyVue.render(container,g,-cameraX,-cameraX+container.getWidth(),-cameraY,-cameraY+ container.getHeight());
        this.hud.render(-cameraX, -cameraY,g, container);
        if (h.getPointVie() <= 0) {
        	this.hud_fin_du_jeu.init(h.getX(),h.getY() ); 
        	this.hud_fin_du_jeu.render(-cameraX, -cameraY,g, container);
        }
        if (h.getTresorDeMap() != null) this.hudVictory.render(-cameraX, -cameraY,g, container);
    }

    public void keyPressed(int key, char c) {
        switch (key) {
        	case Input.KEY_ESCAPE: container.exit(); break;
            case Input.KEY_UP:  labyModel.goHaut();    break;
            case Input.KEY_LEFT: labyModel.goGauche();   break;
            case Input.KEY_DOWN: labyModel.goBas();   break;
            case Input.KEY_RIGHT: labyModel.goDroite();  break;
            case Input.KEY_SPACE: for (Item item : labyModel.getLink().getInventaire()){
                if (item.getClass().getName().equals("model.Item.Epee")){
                    labyModel.attaquer();
                }
            }  break;
            case Input.KEY_P: labyModel.tirer() ; break;
            case Input.KEY_B:	try {
				save();
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            	break;
            case Input.KEY_L: try {
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
            case Input.KEY_ENTER: game.enterState(MainScreenGameState.ID); break;
           // case Input.KEY_SPACE: labyModel.attaquerStop();   break;
        }
    }


    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        labyModel.update(container,delta);
        /*if(labyModel.isTresorTrouver()){
            //container.exit();
        }*/
    }

    /*
    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new Jeu.Jeu(), width, height, true).start();
    }*/
    
    public void save() throws SlickException {
    	DAOFactory.getAbstractDAOFactory(1).getJeuDAO().save(this);
    }
    
    
    public void charger() throws SlickException {
    	DAOFactory.getAbstractDAOFactory(1).getJeuDAO().load(this);
    }
    
    public ArrayList<Heros> getLesheros() {
    	return labyModel.getLesHeros();
    }
   
    @Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	
}