package model.Item;

public class Tresor extends Item{

    public Tresor(int posX,int posY,Item item){
        super(posX,posY);
        setRamassable(true);
    }
}
