package vues.VueGenerateur;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import javax.swing.*;
import java.awt.*;

public class VueGenerateur extends JFrame   {

    private VueObjets vo;
    private VueCreaLaby vc;
    private final static int HAUTEUR = 1000;
    private final static int LARGEUR = HAUTEUR+130;

    public VueGenerateur() throws SlickException {

        setTitle("Generateur de Monde");
        setSize(LARGEUR, HAUTEUR);

        vo = new VueObjets();
        vc = new VueCreaLaby(100,100);

        //this.setLayout(new BoxLayout());
        //this.add(vo,BorderLayout.LINE_START);

       // BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        //this.setLayout(boxlayout);

        JSplitPane js = new JSplitPane(SwingConstants.VERTICAL,vo,vc);
        this.add(js);
        js.setEnabled(false);

       // this.setSize(vc.getTailleLaby().width+130,vc.getTailleLaby().height+45);
       // this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }


}
