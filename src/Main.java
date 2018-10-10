import java.util.Scanner;

public class Main {


    public static void main(String args[]){

        Labyrinthe laby = new Labyrinthe();
        Scanner sc = new Scanner(System.in);
        System.out.print("Pour deplacer le personnage utilisez ZQSD\n");
        System.out.print("Pour quitter utilisez p\n");



        String deplacement = "";

        while(!deplacement.equals("p")){
            System.out.print(laby.toString()+"\n\n");
            deplacement = sc.nextLine();

            if(deplacement.equals("p")){
                if(deplacement.equals("q")){

                }
                if(deplacement.equals("s")){

                }
                if(deplacement.equals("d")){

                }
                if(deplacement.equals("zp")){

                }
            }
        }



    }
}
