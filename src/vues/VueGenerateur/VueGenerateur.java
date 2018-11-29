package vues.VueGenerateur;
import model.generateur.Niveau;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueGenerateur extends JFrame {

    private Niveau niv;
    private VueObjets vo;
    private VueCreaLaby vc;
    private final static int HAUTEUR = 1000;
    private final static int LARGEUR = HAUTEUR+130;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public VueGenerateur(Niveau niv) {

        this.niv = niv;

        setTitle("Generateur de Monde");
        setSize(LARGEUR, HAUTEUR);
        setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);

        vo = new VueObjets(niv);
        vc = new VueCreaLaby(niv,0, 0);

        //this.setLayout(new BoxLayout());
        //this.add(vo,BorderLayout.LINE_START);

        // BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        //this.setLayout(boxlayout);

        JSplitPane js = new JSplitPane(SwingConstants.VERTICAL, vo, vc);
        this.add(js);
        js.setEnabled(false);

        // this.setSize(vc.getTailleLaby().width+130,vc.getTailleLaby().height+45);
        // this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    public VueCreaLaby getCreaLaby(){
        return vc;
    }
}
