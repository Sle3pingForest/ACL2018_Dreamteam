package model.Item;

public class Tresor extends Item{

    Item tresor;
    public Tresor(float posX,float posY,Item tresor){
        super(posX,posY);
        this.tresor = tresor;
    }

    @Override
    public void ramasser() {
        super.ramasser();
    }
}
