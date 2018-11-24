package vues.VueGenerateur;

import javax.swing.*;
import java.awt.*;

public class VueGenerateur extends JFrame {

    private VueObjets vo;

    public VueGenerateur(){
        setTitle("Generateur de Monde");
        setSize(1000, 500);
        vo = new VueObjets();
        this.setLayout(new BorderLayout());
        //this.add(vo,BorderLayout.LINE_START);
        this.setVisible(true);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 5;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( new JButton("grid"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        add(new JButton("out"), gbc);
    }

}
