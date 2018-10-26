package model.personnages;

public abstract class Personnage {
	
    protected float x ,y;
    protected int vertical = 0;
    protected int horizontal = 0;
    
	protected int pointVie;
	protected int defense, attaque, vitesse;
	public Personnage(int x, int y) {
		this.pointVie = 1; 
		this.attaque = 1;
		this.defense = 0;
		this.x = x;
		this.y = y;
		this.vitesse = 1;
		
	}
    public void goGauche(){
        x--;
    }

    public void goDroite(){
        x++;
    }

    public void goHaut(){
        y--;
    }

    public void goBas(){
        y++;
    }
    
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public int getVertical(){
    	return vertical;
    }
	
    public int getHorizontal(){
    	return horizontal;
    }

}
