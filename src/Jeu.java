import java.util.Scanner;

public class Jeu {


    public static void main(String args[]) {

        Labyrinthe laby = new Labyrinthe("src/murLvl1.txt", "SleepingForest",10);
        Scanner sc = new Scanner(System.in);
        System.out.print("Pour deplacer le personnage utilisez ZQSD\n");
        System.out.print("Pour quitter utilisez p\n");


        String deplacement = "";

        while (!deplacement.equals("p")) {
        	laby.afficher();
            deplacement = sc.nextLine();

            if (!deplacement.equals("p")) {
                if (deplacement.equals("q")) {
                	laby.deplacerGauche();

                }
                if (deplacement.equals("s")) {
                	laby.deplacerBas();

                }
                if (deplacement.equals("d")) {
                	laby.deplacerDroite();

                }
                if (deplacement.equals("z")) {
                	laby.deplacerHaut();
                }
                System.out.println(laby.getHeros().getX() + "**" + laby.getHeros().getY());
            }
            
        }


    }
}
