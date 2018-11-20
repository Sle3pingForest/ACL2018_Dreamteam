package model.Item;

import model.personnages.Personnage;

public class Projectile extends Item {

	public final static float VITESSE = 0.3f;
    protected int vertical = 0;
    protected int horizontal = 0;
    //Position static
    public final static int BAS = 0;
    public final static int DROITE = 1;
    public final static int HAUT = 2;
    public final static int GAUCHE = 3;
    
    protected float x,y;
    

    protected int directionActu = BAS;
	protected boolean collision = true;
	protected int porte = 3;
	protected float xInit, yInit;
    
	
	public Projectile(float posX, float posY) {
		super(0,0);
		this.x = posX;
		this.y = posY;
		this.xInit = posX;
		this.yInit = posY;
		
		// TODO Auto-generated constructor stub
	}
	
    public float getxInit() {
		return xInit;
	}

	public void setxInit(float xInit) {
		this.xInit = xInit;
	}

	public float getyInit() {
		return yInit;
	}

	public void setyInit(float yInit) {
		this.yInit = yInit;
	}

	public void setDirectionActu(int directionActu) {
        this.directionActu = directionActu;
    }
    public int getDirectionActu(){
        return directionActu;
    }
    public void goDroite(){
        directionActu = DROITE;
        this.horizontal = 1;
    }

    public void goGauche(){
    	directionActu = GAUCHE;
        this.horizontal = -11;
    }
    public void goBas(){
        directionActu = BAS;
        this.vertical = 1;
    }
    public void goHaut(){
    	this.vertical = -1;
        directionActu = HAUT;
    }
	
	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	public boolean getCollision(){
		return collision;
	}
	
	public void setCollision(boolean b){
		collision = b;
	}


	public void deplacer(int i) {
		if(Personnage.ATTAQUER_BAS == i){
			goBas();
		}
		else if(Personnage.ATTAQUER_GAUCHE == i){
			goGauche();
		}
		else if(Personnage.ATTAQUER_DROITE == i){
			goDroite();
		}
		else if(Personnage.ATTAQUER_HAUT == i){
			goHaut();
		}
	}
	public void setPosX(float posX) {
        this.x = posX;
    }

    public void setPosY(float posY) {
        this.y = posY;
    }
    
    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }


}
