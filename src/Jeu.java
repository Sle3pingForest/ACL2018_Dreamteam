import java.util.Scanner;

public class Jeu {


    public static void main(String args[]) {

        Labyrinthe laby = new Labyrinthe("src/murLvl1.txt", "SleepingForest",5);
        Scanner sc = new Scanner(System.in);
        System.out.print("Pour deplacer le personnage utilisez ZQSD\n");
        System.out.print("Pour quitter utilisez p\n");


        String deplacement = "";

        while (!deplacement.equals("p") && !Labyrinthe.MORT_HEROS) {
        	laby.afficher();
            deplacement = sc.nextLine();

            if (!deplacement.equals("p")) {
                if (deplacement.equals("q")) {
                	laby.deplacerHerosGauche();

                }
                if (deplacement.equals("s")) {
                	laby.deplacerHerosBas();

                }
                if (deplacement.equals("d")) {
                	laby.deplacerHerosDroite();

                }
                if (deplacement.equals("z")) {
                	laby.deplacerHerosHaut();
                }

                laby.deplacerMonstres();

                laby.collison();
                //System.out.println(laby.getHeros().getX() + "**" + laby.getHeros().getY());
            }
            
        }


    }
}
