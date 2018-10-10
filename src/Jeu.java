
public class Jeu {
	protected Labyrinthe l;
	
	
	public Jeu(String nomFichier){
		this.l = new Labyrinthe(nomFichier);
	}
	
	public void afficheJeu(){
		this.l.afficher();
	}
	
	public static void main(String[] args) {
			Jeu j= new Jeu("src/murLvl1.txt");
			j.afficheJeu();
	}

}
