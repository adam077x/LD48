package ldjam48.game.node;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == height-1) {
                    setTileByPosition(j, i, 2);
                }
                else if(i-1 >= height-4){
                    setTileByPosition(j, i, 1);
                }
                else {
                    setTileByPosition(j, i, 3);
                }
            }
        }
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(getTileByPosition(j, i) == BlockType.Dirt.getBlockId()) {
                    batch.draw(BlockType.Dirt.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
                }
                else if(getTileByPosition(j, i) == BlockType.Grass.getBlockId()) {
                    batch.draw(BlockType.Grass.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
                }
                else if(getTileByPosition(j, i) == BlockType.Stone.getBlockId()) {
                    batch.draw(BlockType.Stone.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
                }
            }
        }
    }

    public int getTileByPosition(int x, int y) {
        return arrayMap[y * width + x];
    }

    public int getTileByGlobalPosition(Vector2 position) {
        return 0;
    }

    public void setTileByPosition(int x, int y, int id) {
        arrayMap[y * width + x] = id;
    }
}
