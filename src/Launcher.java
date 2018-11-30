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
        super("Launcher");
        setSize(382, 553);
        setLocation((w-this.getSize().width)/2,(h-this.getSize().height)/2);
        setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/background/launcher.jpg"))));

        setLayout(new BorderLayout());

        jeu = new JButton("Lancer le jeu");
        editeur = new JButton("Ouvrir l'editeur de niveau");

        jeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AppGameContainer(new StateGame(),   w, h, false).start();
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

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,2));
        jp.add(jeu);
        jp.add(editeur);

        this.add(jp,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }


}
