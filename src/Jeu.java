import model.personnages.Heros;

import org.newdawn.slick.*;
import vues.VueHeros;
import vues.VueLabyrinthe;

import java.util.ArrayList;

/* C'est la class principal de notre jeu
* Elle sair de boucle principal mais aussi de Controlleur pour les touche */
public class Jeu extends BasicGame {

    private GameContainer container;
    private static VueLabyrinthe laby;
    private ArrayList<VueHeros> lesHerosVue;
    private ArrayList<Heros> lesHeros;
    
    private static Jeu instance = null;
	public static Jeu getInstance() {
		if (instance == null) {
			instance = new Jeu();
		}
		return instance;
	}

    private Jeu() {
        super("Link thes Labyrinthe's Master");
        lesHeros = new ArrayList<Heros>();
        lesHerosVue = new ArrayList<VueHeros>();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        laby = VueLabyrinthe.getInstance();
        lesHeros.add(new Heros(300,280,"link"));
        lesHerosVue.add(new VueHeros(VueHeros.BLEU,lesHeros.get(0)));// car un joueur
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
        for(VueHeros h : lesHerosVue){
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
        new AppGameContainer(new Jeu(), 1680, 1050, false).start();
    }
    
    public static VueLabyrinthe getLaby() {
    	return laby;
    }
}