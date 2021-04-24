package ldjam48.game.node;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.screens.MainGameScreen;

import java.util.Random;

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
                    setTileByPosition(j, i, BlockType.Grass.getBlockId());
                }
                else if(i-1 >= height-4){
                    setTileByPosition(j, i, BlockType.Dirt.getBlockId());
                }
                else if(i == 0) {
                    setTileByPosition(j, i, BlockType.Bedrock.getBlockId());
                }
                else {
                    //Random gen
                    int r = MainGameScreen.getInstance().random.nextInt(100);

                    if(r < 85)
                    {
                        setTileByPosition(j, i, BlockType.Stone.getBlockId());
                    }
                    if(r >= 85 && r < 95)
                    {
                        setTileByPosition(j, i, BlockType.Coal.getBlockId());
                    }
                    if(r >= 95)
                    {
                        setTileByPosition(j, i, BlockType.Iron.getBlockId());
                    }
                }
            }
        }
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                BlockType blockType = BlockType.values()[getTileByPosition(j,i)];
                batch.draw(blockType.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
            }
        }
    }

    public int getTileByPosition(int x, int y) {
        return arrayMap[y * width + x];
    }

    public int getTileByGlobalPosition(Vector2 position) {
        if((int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize)) >= arrayMap.length) return 0;
        return arrayMap[(int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize))];
    }

    public void setTileByGlobalPosition(Vector2 position, int id) {
        if((int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize)) >= arrayMap.length) return;
        arrayMap[(int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize))] = id;
    }

    public void setTileByPosition(int x, int y, int id) {
        arrayMap[y * width + x] = id;
    }
}
