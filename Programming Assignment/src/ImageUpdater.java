import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.List;

public class ImageUpdater{

    //Method to take the input png, read it, and then make a new image by painting over it using the coordinates from the leaf components, then assignment the painting to the output file.
    public void drawBoxes(File inputFile, List<Leaf> leaves, File outputFile) {
        try {
            BufferedImage img = ImageIO.read(inputFile);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.YELLOW);
            g2d.setStroke(new BasicStroke(5));

            //For loop will go through each leaf component and draw a box for its respective coordinates.
            for (int i = 0; i < leaves.size(); i++) {
                int[] coors = leaves.get(i).coordinates();
                g2d.drawRect(coors[0], coors[1], coors[2]-coors[0], coors[3]-coors[1]);
            }
            g2d.dispose();

            //Adding the boxes to the output file.
            ImageIO.write(img, "png", outputFile);

        //Preventing the program from failing if something is wrong with a png file, so we can still update the others.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
