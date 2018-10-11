package model.personnages.monstres;

public class Dragon extends Monstre{

	public Dragon(int x, int y) {
		super(x, y);
		this.attaque = 10;
		this.pointVie = 10;
		this.defense = 5;
	}
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

}
