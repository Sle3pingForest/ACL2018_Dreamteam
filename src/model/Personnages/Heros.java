package model.Personnages;

public class Heros {

    protected int x;
    protected int y;


    public Heros(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public String toString(){
        String donnee = "Le Heros est en  x :"+x+" , +y : "+y;

        return donnee;
    }
}
