package fishes;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JRootPane;
import javax.swing.Timer;

public class MainForm extends javax.swing.JFrame {

    private ArrayList<Image> fishes = new ArrayList<>();
    private Random r = new Random();
    private int w;
    private int h;

    public MainForm() {

        initComponents();
        //загрузка фона
        Image background = new Image("background.jpg", false);
        w = background.getW();
        h = background.getH();
        //добавление рыб
        addFish("fish1.png");
        addFish("fish2.png");
        addFish("fish3.png");
        addFish("fish4.png");
        addFish("fish5.png");
        addFish("fish1.png");
        addFish("fish2.png");
        addFish("fish3.png");
        addFish("fish4.png");
        addFish("fish5.png");
        //добавление фона
        add(background);
        Dimension d = background.getSize();
        JRootPane pane = getRootPane();
        pane.setPreferredSize(d);
        pack(); // обновляем размеры
        Timer timer = new Timer(40, (e) -> {
            ArrayList<Image> toRemove = new ArrayList<>();
            for (Image fish : fishes) {
                fish.setPosX(fish.getPosX() + fish.getSpeed() * Math.cos(fish.getAngle()));
                fish.setPosY(fish.getPosY() + fish.getSpeed() * Math.sin(fish.getAngle()));
                //проверить коордианты и сбросить рыбу в аквариум
                Rectangle bg = new Rectangle(w, h);
                Rectangle f = new Rectangle((int) fish.getPosX(), (int) fish.getPosY(), fish.getSize().width, fish.getSize().height);
                if (!bg.intersects(f)) {
                    toRemove.add(fish);
                }
                fish.repaint();
            }
            for (Image fish : toRemove) {
                remove(fish);
                fishes.remove(fish);
                int random = r.nextInt(5) + 1;
                addFish("fish" + random + ".png");
            }
        });
        timer.start();
    }

    private void addFish(String path) {
        Image fish = new Image(path, true);
        if (r.nextBoolean()) {
            fish.setPosX(-fish.getSize().width + 1);
            fish.setAngle((r.nextInt(60) - 30) / 180.0 * Math.PI);
        } else {
            fish.setPosX(w - 1);
            fish.setAngle((r.nextInt(60) - 30) / 180.0 * Math.PI + Math.PI);
            fish.reverse();
        }
        fish.setPosY(r.nextInt((int) (h - fish.getH())));
        fish.setSpeed(1.0 + r.nextInt(5));
        fishes.add(fish);
        add(fish, 0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Аквариум с рыбами");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm frame = new MainForm();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
