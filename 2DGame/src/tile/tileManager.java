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
    public tile[] arrTile;
    public int[][] mapTileNum;

    public tileManager(GamePanel gp) {
        this.gp = gp;

        arrTile = new tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            arrTile[0] = new tile();
            arrTile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png.png"));

            arrTile[1] = new tile();
            arrTile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone-brick.png.png"));
            arrTile[1].collision = true;

            arrTile[2] = new tile();
            arrTile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png.png"));
            arrTile[2].collision = true;

            arrTile[3] = new tile();
            arrTile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            arrTile[4] = new tile();
            arrTile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            arrTile[4].collision = true;

            arrTile[5] = new tile();
            arrTile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/road00.png"));


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

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum =  mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {

                g2.drawImage(arrTile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
