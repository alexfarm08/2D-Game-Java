package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JFrame {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // constructor to make new screen
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets screen size
        this.setBackground(Color.black);
        //this.setDoubleBuffered(true); // FIX ME!!!!!
    }

}
