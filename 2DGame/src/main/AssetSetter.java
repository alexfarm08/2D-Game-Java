package main;

import object.OBJ_Key;
import object.OBJ_chest;
import object.OBJ_door;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 56 * gp.tileSize;
        gp.obj[0].worldY = 14 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 52 * gp.tileSize;
        gp.obj[1].worldY = 47 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 30 * gp.tileSize;
        gp.obj[2].worldY = 47 * gp.tileSize;

        gp.obj[3] = new OBJ_door();
        gp.obj[3].worldX = 46 * gp.tileSize;
        gp.obj[3].worldY = 42 * gp.tileSize;

        gp.obj[4] = new OBJ_door();
        gp.obj[4].worldX = 42 * gp.tileSize;
        gp.obj[4].worldY = 7 * gp.tileSize;

        gp.obj[5] = new OBJ_chest();
        gp.obj[5].worldX = 40 * gp.tileSize;
        gp.obj[5].worldY = 5 * gp.tileSize;

    }

}
