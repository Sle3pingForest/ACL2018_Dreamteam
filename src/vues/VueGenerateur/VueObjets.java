package vues.VueGenerateur;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.nio.ByteBuffer;

public class VueObjets extends JPanel{

    private ImageIcon mur;
    private ImageIcon dragon;
    private ImageIcon soldat;
    private ImageIcon heros;
    private static int NBOBJET = 4;

    private JLabel labelMur;
    private JLabel labelSoldat;
    private JLabel labelDragon;
    private JLabel labelHeros;


    public VueObjets() {

        mur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/ArbrePetit.png"));
        dragon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/dragon.png"));;
        soldat = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/soldat.png"));;
        heros = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/heros.png"));;

        this.setLayout(new GridLayout(NBOBJET/2,2));

        labelMur = new JLabel(mur);
        labelDragon = new JLabel(dragon);
        labelSoldat = new JLabel(soldat);
        labelHeros = new JLabel(heros);

        labelMur.setMinimumSize(new java.awt.Dimension(40,40));
        labelMur.setPreferredSize(new java.awt.Dimension(40,40));


        this.dragAndDrop();

        this.add(labelMur);
        this.add(labelDragon);
        this.add(labelSoldat);
        this.add(labelHeros);
        this.setVisible(true);
    }

    private void dragAndDrop(){
        final String propertyName = "icon";
        labelMur.setTransferHandler(new TransferHandler(propertyName));
        labelMur.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });


        labelSoldat.setTransferHandler(new TransferHandler(propertyName));
        labelSoldat.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });



        labelDragon.setTransferHandler(new TransferHandler(propertyName));
        labelDragon.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });

        labelHeros.setTransferHandler(new TransferHandler(propertyName));
        labelHeros.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JComponent comp = (JComponent) evt.getSource();
                TransferHandler th = comp.getTransferHandler();

                th.exportAsDrag(comp, evt, TransferHandler.COPY);
            }
        });
    }


}
