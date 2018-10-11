package model.personnages;

public abstract class Personnage {
	protected int x, y;
	protected int pointVie;
	protected int defense, attaque;
	public Personnage(int x, int y) {
		this.pointVie = 1; 
		this.attaque = 1;
		this.defense = 0;
		this.x = x;
		this.y = y;
		
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
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	

}
