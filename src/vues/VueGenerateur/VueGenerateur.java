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

    public VueGenerateur() throws SlickException {

        setTitle("Generateur de Monde");
        setSize(1000, 500);

        vo = new VueObjets();
        vc = new VueCreaLaby();

        //this.setLayout(new BoxLayout());
        //this.add(vo,BorderLayout.LINE_START);

       // BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        //this.setLayout(boxlayout);

        JSplitPane js = new JSplitPane(SwingConstants.VERTICAL,vo,vc);
        this.add(js);


        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }


}
