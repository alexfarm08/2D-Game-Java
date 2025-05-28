package main;

import entity.Player;
import object.OBJ_Key;
import object.SuperObject;
import tile.tileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    tileManager tileM = new tileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public collisionChecker cChecker = new collisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject[] obj = new SuperObject[10];

    // constructor to make new screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets screen size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / (double)FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // 1 UPDATE: update info such as char position
            update();
            // 2 DRAW: draw the screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileM.draw(g2);

        // OBJECTS
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }

        // PLAYER
        player.draw(g2);

        g2.dispose();
    }

}
