package vues.VueGenerateur;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueCreaLaby extends JPanel {

    private int nbLigne,nbColonne;
    private JLabel[][] tabLab;

    public VueCreaLaby(int nbLigne, int nbColonne){
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

       // this.setLayout(new GridLayout(nbLigne,nbColonne));
        this.setLayout(new BorderLayout());

        JPanel jp = new JPanel();
        jp.setLayout(null);


        tabLab = new JLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));

        for(int i =0;i<nbLigne;i++){
            for(int j =0;j<nbColonne;j++){

                tabLab[i][j] = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/generateur/herbe.png")));
                tabLab[i][j].setTransferHandler(new TransferHandler("icon"));
                tabLab[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        JComponent comp = (JComponent) evt.getSource();
                        TransferHandler th = comp.getTransferHandler();

                        th.exportAsDrag(comp, evt, TransferHandler.COPY);
                    }
                });
                tabLab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tabLab[i][j].setBounds(i*50,j*50,50,50);
                jp.add(tabLab[i][j]);
            }
        }
        JScrollPane js = new JScrollPane(jp);
        js.createHorizontalScrollBar();
        js.createVerticalScrollBar();
        this.add(js);
    }

    public Dimension getTailleLaby(){
        return new Dimension(50*nbLigne,50*nbColonne);
    }
}
