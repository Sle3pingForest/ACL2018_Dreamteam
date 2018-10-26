package model.personnages;

public class Heros extends Personnage {

    private String nom;
    
    //Les Direction


    //Position static
    public final static int BAS = 0;
    public final static int DROITE = 1;
    public final static int HAUT = 2;
    public final static int GAUCHE = 3;

    // Avancer
    public final static int AVANCER_BAS = 4;
    public final static int AVANCER_DROITE = 5;
    public final static int AVANCER_HAUT = 6;
    public final static int AVANCER_GAUCHE = 7;

    
    public final static  float VITESSE = 3;
    
    private int directionActu = BAS;


    public Heros(int x, int y, String nom){
    	super(x,y);
    	this.pointVie = 5;
    	this.nom = nom;
    	this.attaque = 2;
    	this.defense = 0;
    }

    public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
    
    
    public void goDroite(){
        horizontal = 1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT && directionActu != AVANCER_BAS) {
                directionActu = AVANCER_DROITE;
            }
        }else{
            directionActu = AVANCER_DROITE;
        }
    }
    public void goGauche(){
        horizontal = -1;
        if(vertical != 0) {
            if (directionActu != AVANCER_HAUT && directionActu != AVANCER_BAS) {
                directionActu = AVANCER_GAUCHE;
            }
        }else{
            directionActu = AVANCER_GAUCHE;
        }
    }
    public void goBas(){
        vertical = 1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE && directionActu != AVANCER_DROITE) {
                directionActu = AVANCER_BAS;
            }
        }else{
            directionActu = AVANCER_BAS;
        }
    }
    public void goHaut(){

        vertical = -1;
        if(horizontal != 0) {
            if (directionActu != AVANCER_GAUCHE && directionActu != AVANCER_DROITE) {
                directionActu = AVANCER_HAUT;
            }
        }else{
            directionActu = AVANCER_HAUT;
        }
    }

    public void arretGauche(){

        if(horizontal == -1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = GAUCHE;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT;
            }else{
                directionActu = AVANCER_BAS;
            }
        }
    }

    public  void arretDroite(){

        if(horizontal == 1) {
            horizontal = 0;

            if(vertical == 0){
                directionActu = DROITE;
            }else  if(vertical == -1){
                directionActu = AVANCER_HAUT;
            }else{
                directionActu = AVANCER_BAS;
            }
        }
    }

    public void arretBas(){

        if(vertical == 1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = BAS;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE;
            }else{
                directionActu = AVANCER_DROITE;
            }
        }
    }

    public void arretHaut(){

        if(vertical == -1) {
            vertical = 0;

            if(horizontal == 0){
                directionActu = HAUT;
            }else  if(horizontal == -1){
                directionActu = AVANCER_GAUCHE;
            }else{
                directionActu = AVANCER_DROITE;
            }
        }
    }
    
    public int getDirectionActu(){
    	return directionActu;
    }
}
