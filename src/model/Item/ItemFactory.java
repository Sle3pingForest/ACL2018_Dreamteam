package model.Item;

public class ItemFactory {

    //Type d'objet
    //public final static int TRESOR = 0;
    public final static int PIEGE = 0;
    public final static int EPEE = 1;

    public Item creerItems(int type, int x, int y){
        Item item = null;
        switch (type){

            //case TRESOR : item = new Tresor(x,y,);
            case PIEGE : item = new Piege(x,y);break;
            case EPEE : item = new Epee(x,y); break;
        }
        return item;
    }
}