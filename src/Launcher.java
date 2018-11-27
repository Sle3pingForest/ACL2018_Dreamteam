import model.generateur.Niveau;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import vues.VueGenerateur.VueGenerateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {


    private JButton jeu;
    private JButton editeur;


    public Launcher(final int w,final int h) {

        setTitle("Launcher");
        setSize(100, 100);

        JPanel jp = new JPanel();

        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));

        jeu = new JButton("Lancer le jeu");
        editeur = new JButton("Ouvrir l'editeur de niveau");

        jeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AppGameContainer(new StateGame(), w, h, true).start();
                } catch (SlickException e1) {
                    e1.printStackTrace();
                }
            }
        });

        editeur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new VueGenerateur(new Niveau("niv"));
               dispose();
            }
        });

        this.add(jeu);
        this.add(editeur);

        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
}
