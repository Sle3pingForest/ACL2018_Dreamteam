package vues.VueItem;

import java.util.ArrayList;

import model.Item.Epee;
import model.Item.Projectile;
import model.personnages.Heros;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VueProjectile extends VueItem {

	private final static  String CHEMIN_PROJECTILE = "main/resources/Personnages/Heros/Bleu.png";
	protected Projectile projectile;
	private ArrayList<Projectile> lprojectile;

	public VueProjectile(Projectile projectile) throws SlickException {
		super(projectile);
		this.projectile = projectile;
		animations = new Animation[4];
		largeur_Sprite = 30;
		chargerAnimation(CHEMIN_PROJECTILE);
	}


	public VueProjectile(ArrayList<Projectile> projectile) throws SlickException {
		super(null);
		this.lprojectile = projectile;
		animations = new Animation[4];
		largeur_Sprite = 30;
		chargerAnimation(CHEMIN_PROJECTILE);
	}

	private void chargerMarcheBas(String chemin) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(9, 21), 150);
		this.animations[Projectile.BAS] = animation;
	}

	private void chargerMarcheDroite(String chemin) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		Image img = spriteSheet.getSprite(9, 71);
		img = img.getFlippedCopy(true,false);
		animation.addFrame(img, 100);
		this.animations[Projectile.DROITE] = animation;
	}

	private void chargerMarcheGauche(String chemin) throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(9, 71), 150);
		this.animations[Projectile.GAUCHE] = animation;
	}

	private void chargerMarcheHaut(String chemin)throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet(chemin, 30, 30);
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(9, 46), 50);

		this.animations[Projectile.HAUT] = animation;
	}


	private void chargerAnimation(String chemin) throws SlickException {
		chargerMarcheBas(chemin);
		chargerMarcheDroite(chemin);
		chargerMarcheGauche(chemin);
		chargerMarcheHaut(chemin);


	}

	public void render(GameContainer container, Graphics g)  {
		// liste des indices des projectiles a supprimer dans l'ordre decroissant
		ArrayList<Integer> listeSuppr = new ArrayList<>();
		int i = 0;

		// dessine tous les projectiles qui nont pas touché d obstacle ou qui nont 
		// pas atteint la portee max
		if (lprojectile.size() > 0) {
			for (Projectile p : lprojectile) {
				if(!p.distanceMax() && !p.isTouche() ){
					float x = p.getX();
					float y = p.getY();
					g.drawAnimation(animations[p.getDirectionActu()],(int)x,(int)y);
				}
				else if (p.distanceMax() || p.isTouche()) listeSuppr.add(0, i);
				i++;
			}
			for (Integer suppr : listeSuppr) lprojectile.remove(suppr);
		}
	}

	public String toString() {
		return this.projectile.getPosX() + "  " + this.projectile.getPosY();
	}
}
