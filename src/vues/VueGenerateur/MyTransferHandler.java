package vues.VueGenerateur;

import org.hamcrest.Description;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MyTransferHandler extends TransferHandler implements Transferable{

    //private Image image;
    private String str;
    //private final DataFlavor flavors[] = { DataFlavor.imageFlavor };
    private final DataFlavor flavors[] = { DataFlavor.imageFlavor };

    /**
     * Méthode permettant à l'objet de savoir si les données reçues
     * via un drop sont autorisées à être importées
     * @param info
     * @return boolean
     */
    public boolean canImport(TransferHandler.TransferSupport info) {
        if (!(info.getComponent() instanceof JLabel)) {
            return false;
        }
        return  true;
    }

    /**
     * C'est ici que l'insertion des données dans notre composant est réalisée
     * @param support
     * @return boolean
     */
    public boolean importData(TransferHandler.TransferSupport support){
        //Nous contrôlons si les données reçues sont d'un type autorisé

        //On récupère notre objet Transferable, celui qui contient les données en transit
        Transferable data = support.getTransferable();
        String s = null;

        try {
            //Nous récupérons nos données en spécifiant ce que nous attendons
            //i = (Image) data.getTransferData(DataFlavor.imageFlavor);
            s = (String) data.getTransferData(DataFlavor.stringFlavor);

        } catch (UnsupportedFlavorException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //Via le TRansferSupport, nous pouvons récupérer notre composant
        Case c = (Case)support.getComponent();
        //Afin de lui affecter sa nouvelle valeur
        c.setType(s);
        //c.setIcon(new ImageIcon(i));
        c.ajouterElement();

        return true;
    }

    @Override
    public Transferable createTransferable(JComponent comp) {
        //image = null;
        //image = (Image)((ImageIcon) (((JLabel)comp).getIcon())).getImage(); //We transfer image //image stays null
        return this;
    }


    /**
     * Cette méthode est invoquée à la fin de l'action DROP
     * Si des actions sont à faire ensuite, c'est ici qu'il faudra coder le comportement désiré
     * @param c
     * @param t
     * @param action
     */
    protected void exportDone(JComponent c, Transferable t, int action){

    }

    /**
     * Cette méthode est utilisée afin de déterminer le comportement
     * du composant vis-à-vis du drag'n drop : nous retrouverons
     * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE
     * @param c
     * @return int
     */
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavors[0].equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor){

        if (isDataFlavorSupported(flavor)) {
           // return image;
        }
        return null;
    }
}