package fishes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Image extends JLabel {

    private double posX;
    private double posY;
    private double angle;
    private double speed;

    public void reverse() {
        ImageIcon mg = (ImageIcon) getIcon();
        BufferedImage image = toBufferedImage(mg.getImage());
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        setIcon(new ImageIcon(image));
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Image(String path, boolean isFish) {
        super(new ImageIcon(path));
        if (isFish) {
            double size = Math.max(getW(), getH());
            setSize((int) (size * 1.5), (int) (size * 1.5));
        } else {
            setSize(getW(), getH());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (angle < -Math.PI / 2 || angle > Math.PI / 2) {
            g2d.rotate(angle - Math.PI, getWidth() / 2, getHeight() / 2);
        } else {
            g2d.rotate(angle, getWidth() / 2, getHeight() / 2);
        }
        setLocation((int) posX, (int) posY);
        super.paintComponent(g);
    }

    public int getW() {
        return getIcon().getIconWidth();
    }

    public int getH() {
        return getIcon().getIconHeight();
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    private static BufferedImage toBufferedImage(java.awt.Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
