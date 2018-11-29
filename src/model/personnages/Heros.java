package model.personnages;

import model.Item.Item;
import model.Item.Projectile;
import model.Item.Tresor;
import model.Labyrinthe;
import model.personnages.monstres.Monstre;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;


public class Heros extends Personnage {

    private Tresor tresorDeMap = null;
    private ArrayList<Item> inventaire;
    private Projectile projectile;
    private ArrayList<Projectile> lprojectile;
    private final static float VITESSE = 0.2f;



    protected int tailleInventaire = 10;


    public Heros(float x, float y, String nom){
    	super(x,y);
		largeur=21;
		hauteur=23;
		decalage_largeur=5;
		decalage_hauteur=20;
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 3;
    	this.defense = 0;
    	inventaire = new ArrayList<Item>();
    	tailleInventaire = 10;
    	this.projectile = new Projectile(x,y);
    	lprojectile = new ArrayList<>();
        boxCollider = new Rectangle(x+decalage_largeur,y+decalage_hauteur,largeur-decalage_largeur,hauteur-decalage_hauteur);
		boxColliderDegat =  new Rectangle(x+decalage_largeur,y,largeur,hauteur);
	}
    
    public void charge(Heros h) {
    	this.x = h.getX();
    	this.y = h.getY();
    	this.pointVie = h.getPointVie();
    	this.nom = h.getNom();
    	this.attaque = h.getAttaque();
    	this.defense = h.getDefense();
    	inventaire = h.getInventaire();
    }

    public ArrayList<Item> getInventaire() {
		return inventaire;
	}

	public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
    

    public String getNom() {
		return nom;
	}

    public int getTailleInventaire() {
        return tailleInventaire;
    }

    public void setTailleInventaire(int tailleInventaire) {
    	if (tailleInventaire >= 0 &&  tailleInventaire >= this.inventaire.size())
    		this.tailleInventaire = tailleInventaire;
    }
    
    public int getDirectionActu(){
    	return directionActu;
    }
    
    public Tresor getTresorDeMap(){
        return  tresorDeMap;
    }

    
    
    public Projectile getProjectile() {
		return projectile;
	}

	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}

	 public void tirer(Labyrinthe lab){
		 this.lprojectile.add(new Projectile(this.x, this.y));
		 int direction = directionActu%4;
		 if(direction == BAS){
	   		 	this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_BAS);
	    	}
	    	else if(direction == HAUT){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_HAUT);
	    	}
	    	else if(direction == GAUCHE){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_GAUCHE);
	    	}
	    	else if(direction == DROITE){
	    		this.lprojectile.get(lprojectile.size() -1).deplacer(ATTAQUER_DROITE);
	    	}
	 }
	
	public ArrayList<Projectile> getLprojectile() {
		return lprojectile;
	}

	public void setLprojectile(ArrayList<Projectile> lprojectile) {
		this.lprojectile = lprojectile;
	}


    public int getPointVie() {
        return pointVie;
    }


    public void ajouterAInventaire(Item i){
        if(!i.getClass().getName().equals("model.Item.Tresor")) {
            if(!i.isRamasser() && i.isRamassable()) {
                if (inventaire.size() < tailleInventaire) {
                    inventaire.add(i);
                    i.ramasser();
                }
            }
            else if(i.getClass().getName().equals("model.Item.Piege")){
                if(!i.isRamasser()){
					System.out.println("Avant " + getPointVie());
					i.blesser(this);
					System.out.println("Après " + getPointVie());
                    i.ramasser();
                }
            }
        }else{
            tresorDeMap = (Tresor)i;
        }
    }

    /**
     * fonction de mise a jour du Heros
     * @param lab labyrinthe
     * @param delta
     * @throws SlickException
     */
    public void updateHeros(Labyrinthe lab, int delta) throws SlickException{
        entrainDAttaque = false;
        float vitesseActu = delta*VITESSE;

        float futureX = x + horizontal * vitesseActu;
        float futureY = y + vertical * vitesseActu;

        boxCollider.setY(futureY+decalage_hauteur);
        boxCollider.setX(futureX+decalage_largeur);
        boxColliderDegat.setX(futureX+decalage_largeur);
        boxColliderDegat.setY(futureY);


        if(futureX > 0 && futureX < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR ){
            if (getCollision()) {


                if(horizontal == 1){
                    if(!collisionHorizontale(lab, x+largeur, y)){
                        setX(futureX);
                    }else{
						setX(x-1);
                        boxCollider.setX(x+decalage_largeur-1);
                        boxCollider.setY(y+decalage_hauteur-1);
						boxColliderDegat.setX(x+decalage_largeur-1);
						boxColliderDegat.setY(y-1);
                    }
                }
                if(horizontal == -1){
                    if(!collisionHorizontale(lab, futureX, futureY)){
                        setX(futureX);
                    }else{
                        boxCollider.setX(x+decalage_largeur);
                        boxCollider.setY(y+decalage_hauteur);
						boxColliderDegat.setX(x+decalage_largeur);
						boxColliderDegat.setY(y);
                    }
                }
            } else {
                setX(futureX);

            }



            if(futureY > 0 && futureY < lab.getHauteurCarte()- Labyrinthe.HAUTEUR_MUR){
                if (getCollision()) {
                    if(vertical == -1){
                        if(!collisionVetical(lab, futureX, futureY)){
                            setY(futureY);
                        }else{
                            boxCollider.setX(x+decalage_largeur);
                            boxCollider.setY(y+decalage_hauteur);
							boxColliderDegat.setX(x+decalage_largeur);
							boxColliderDegat.setY(y);
                        }
                    }
                    if(vertical == 1){
                        if(!collisionVetical( lab,futureX, futureY+hauteur)){
                            setY(futureY);
                        }else{
                            boxCollider.setX(x+decalage_largeur);
                            boxCollider.setY(y+decalage_hauteur);
							boxColliderDegat.setX(x+decalage_largeur);
							boxColliderDegat.setY(y);
                        }
                    }
                }else {
                    setY(futureY);
                }
            }
        }


		float vitesseProjectille = delta*Projectile.VITESSE;
		ArrayList<Projectile> lp = this.getLprojectile();
		for (Projectile p : lp ) {
	        toucherProjetile(lab,p);
			float xP = p.getX();
			float yP = p.getY();
			int horizontalP = p.getHorizontal();
			int verticalP = p.getVertical();
			float futureXP = xP + horizontalP * vitesseProjectille;
			float futureYP = yP + verticalP * vitesseProjectille;
			if(futureXP > 0 && futureXP < lab.getLongeurCarte() - Labyrinthe.LARGEUR_MUR && futureYP > 0 && futureYP < lab.getHauteurCarte()-Labyrinthe.HAUTEUR_MUR){
				if (p.getCollision()  ) {
					if(verticalP == -1){
						if (!collisionProjectile(p, xP, futureYP, lab))
							p.setY(futureYP);
					}
					else if(verticalP == 1){
						if (!collisionProjectile(p, xP, futureYP, lab))
							p.setY(futureYP);
					}

					else if(horizontalP == -1){
						if (!collisionProjectile(p, futureXP, yP, lab))
							p.setX(futureXP);
					}
					else if(horizontalP == 1){
						if (!collisionProjectile(p, futureXP, yP, lab))
							p.setX(futureXP);
					}
				} else {
					p.setX(futureXP);
					p.setY(futureYP);
				}
			}
		}

		if(estInvulnerable()){
			tempsRestantInvulnerable -= delta*0.001;
		}
    }
    
	public void toucherProjetile(Labyrinthe lab, Projectile project){
		float xP = project.getX()/Labyrinthe.LARGEUR_MUR;
		float yP = project.getY()/Labyrinthe.HAUTEUR_MUR;
		for(Monstre m : lab.getListeMonstres()){
			float xM = m.getX()/Labyrinthe.LARGEUR_MUR;
			float yM = m.getY()/Labyrinthe.HAUTEUR_MUR;
			boolean estToucher = false;
			if(Math.abs(yP - yM) <= (float)1/2 && Math.abs(xP-xM) <= (float)1/2  ){
				estToucher = true;
			}

			if(estToucher){
				//lesHeros.get(0).setPointVie(m.getAttaque());
				m.perdrePointDeVie(this.getAttaque()*5);
				if(m.getPointVie() <= 0){
					m.mortMonstres(); 
				}
			}
			if(this.getPointVie() <= 0){
                Labyrinthe.MORT_HEROS = true;
				this.mort();
			}
		}
	}

	public boolean collisionProjectile(Projectile p,float futureX, float futureY, Labyrinthe lab) {
		boolean check = false;
		int xCaseFuture,yCaseFuture;
		switch (p.getDirectionActu()) {
		case 0:
			xCaseFuture = (int)((futureX+13)/Labyrinthe.LARGEUR_MUR);
			yCaseFuture = (int)((futureY+18)/Labyrinthe.HAUTEUR_MUR);
			if(lab.getTabMur()[xCaseFuture][yCaseFuture] != null) {
				check = true; 
				p.setTouche(true);
			}
			break;
		case 1:
			xCaseFuture = (int)((futureX+23)/Labyrinthe.LARGEUR_MUR);
			yCaseFuture = (int)((futureY+20)/Labyrinthe.HAUTEUR_MUR);
				if(lab.getTabMur()[xCaseFuture][yCaseFuture] != null) {
				check = true;
				p.setTouche(true);
			}
			break;
		case 2:
			xCaseFuture = (int)((futureX+13)/Labyrinthe.LARGEUR_MUR);
			yCaseFuture = (int)((futureY+5)/Labyrinthe.HAUTEUR_MUR);
			if(lab.getTabMur()[xCaseFuture][yCaseFuture] != null) {
				check = true;
				p.setTouche(true);
			}
			break;
		case 3:
			xCaseFuture = (int)((futureX+7)/Labyrinthe.LARGEUR_MUR);
			yCaseFuture = (int)((futureY+20)/Labyrinthe.HAUTEUR_MUR);
			if(lab.getTabMur()[xCaseFuture][yCaseFuture] != null) {
				check = true;
				p.setTouche(true);
			}
			break;
		}
		return check;
	}
    /**
     * Fonction permettant au heros d'attaquer
     * @param lab
     */
    public void attaquer(Labyrinthe lab){

        attaquer();
        float xH = getX()/Labyrinthe.LARGEUR_MUR;
        float yH = getY()/Labyrinthe.HAUTEUR_MUR;
        for(Monstre m : lab.getListeMonstres()){
            float xM = m.getX()/Labyrinthe.LARGEUR_MUR;
            float yM = m.getY()/Labyrinthe.HAUTEUR_MUR;
            boolean estToucher = false;
            if(Math.abs(yH -yM) < 1 && Math.abs(xH-xM) < 1  ){
                estToucher = true;
            }

            if(estToucher){
               // setPointVie(m.getAttaque());
                m.perdrePointDeVie(getAttaque());
                if(m.getPointVie() <= 0){
                    m.mortMonstres();
                }
            }
            if(getPointVie() <= 0){
                Labyrinthe.MORT_HEROS = true;
                mort();
            }
        }
    }


}
