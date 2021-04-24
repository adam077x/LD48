package ldjam48.game.node;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;

public class NodeTilemap extends Node {
    public int width, height;
    public int arrayMap[];

    public int tileSize;

    public NodeTilemap(String name, int width, int height, int tileSize) {
        super(name);

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;

        arrayMap = new int[width * height];

        for(int i = 0; i < width * height; i++) {
            arrayMap[i] = 1;
        }
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(getTileByPosition(j, i) == BlockType.Mars_Soil.getBlockId()) {
                    batch.draw(BlockType.Mars_Soil.getBlockMeta().getTexture(), i * tileSize, j * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    public int getTileByPosition(int x, int y) {
        return arrayMap[y * width + x];
    }

    public void setTileByPosition(int x, int y, int id) {
        arrayMap[y * width + x] = id;
    }
}
