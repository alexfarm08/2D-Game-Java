package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class tileManager {
    GamePanel gp;
    tile[] arrTile;
    int[][] mapTileNum;

    public tileManager(GamePanel gp) {
        this.gp = gp;

        arrTile = new tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map00.txt");
    }

    public void getTileImage() {
        try {
            arrTile[0] = new tile();
            arrTile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png.png"));

            arrTile[1] = new tile();
            arrTile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone-brick.png.png"));

            arrTile[2] = new tile();
            arrTile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum =  mapTileNum[col][row];

            g2.drawImage(arrTile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }

    }

}
