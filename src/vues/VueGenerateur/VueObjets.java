package vues.VueGenerateur;

import model.personnages.Heros;
import model.personnages.monstres.Dragon;
import model.personnages.monstres.Soldat;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import vues.VueElementDecor;
import vues.VueHeros;
import vues.VueMonstres.VueDragon;
import vues.VueMonstres.VueSoldat;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.nio.ByteBuffer;

public class VueObjets extends JPanel{

    private ImageIcon mur;
    private ImageIcon dragon;
    private ImageIcon soldat;
    private ImageIcon heros;
    private static int NBOBJET = 4;

    public VueObjets() throws SlickException {
        mur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/map/tuiles/Mur/ArbrePetit.png"));
        mur = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./src/main/resources/map/tuiles/Mur/ArbrePetit.png"));

        dragon = new ImageIcon(toAwtImage(VueDragon.getImageGenerateur(),false));
        soldat = new ImageIcon(toAwtImage(VueSoldat.getImageGenerateur(),false));
        heros = new ImageIcon(toAwtImage(VueHeros.getImageGenerateur(),false));

        this.setLayout(new GridLayout(NBOBJET/2,2));
        this.add(new JLabel(mur));
        this.add(new JLabel(dragon));
        this.add(new JLabel(soldat));
        this.add(new JLabel(heros));
        this.setVisible(true);
    }

    private java.awt.Image toAwtImage(org.newdawn.slick.Image image, boolean hasAlpha) {
        // conver the image into a byte buffer by reading each pixel in turn
        int len = 4 * image.getWidth() * image.getHeight();
        if (!hasAlpha) {
            len = 3 * image.getWidth() * image.getHeight();
        }

        ByteBuffer out = ByteBuffer.allocate(len);
        org.newdawn.slick.Color c;

        for (int y = image.getHeight()-1; y >= 0; y--) {
            for (int x = 0; x < image.getWidth(); x++) {
                c = image.getColor(x, y);

                out.put((byte) (c.r * 255.0f));
                out.put((byte) (c.g * 255.0f));
                out.put((byte) (c.b * 255.0f));
                if (hasAlpha) {
                    out.put((byte) (c.a * 255.0f));
                }
            }
        }

        // create a raster of the correct format and fill it with our buffer
        DataBufferByte dataBuffer = new DataBufferByte(out.array(), len);

        PixelInterleavedSampleModel sampleModel;

        ColorModel cm;

        if (hasAlpha) {
            int[] offsets = { 0, 1, 2, 3 };
            sampleModel = new PixelInterleavedSampleModel(
                    DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 4,
                    4 * image.getWidth(), offsets);

            cm = new ComponentColorModel(ColorSpace
                    .getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 },
                    true, false, ComponentColorModel.TRANSLUCENT,
                    DataBuffer.TYPE_BYTE);
        } else {
            int[] offsets = { 0, 1, 2};
            sampleModel = new PixelInterleavedSampleModel(
                    DataBuffer.TYPE_BYTE, image.getWidth(), image.getHeight(), 3,
                    3 * image.getWidth(), offsets);

            cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
                    new int[] {8,8,8,0},
                    false,
                    false,
                    ComponentColorModel.OPAQUE,
                    DataBuffer.TYPE_BYTE);
        }
        WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));

        // finally create the buffered image based on the data from the texture
        // and spit it through to ImageIO
        return new BufferedImage(cm, raster, false, null);
    }
}
