package ldjam48.game.node;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.nashorn.internal.ir.Block;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.screens.MainGameScreen;

import java.util.Random;

public class NodeTilemap extends Node {
    public int width, height;
    public int arrayMap[];
    public int arrayMapBackground[];

    public int tileSize;

    public NodeTilemap(String name, int width, int height, int tileSize) {
        super(name);

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;

        arrayMap = new int[width * height];
        arrayMapBackground = new int[width * height];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == height-1) {
                    setTileByPosition(j, i, BlockType.Sand.getBlockId());
                }
                else if(i-1 >= height-4){
                    setTileByPosition(j, i, BlockType.Sand.getBlockId());
                }
                else if(i == 0) {
                    setTileByPosition(j, i, BlockType.Bedrock.getBlockId());
                }
                else if(i > height-25)
                {
                    setTileByPosition(j, i, BlockType.Sandstone.getBlockId());

                    int r = MainGameScreen.getInstance().random.nextInt(100);

                    if(r >= 85 && r < 96)
                    {
                        setTileByPosition(j, i, BlockType.Sandcoal.getBlockId());
                    }
                    if(r >= 97)
                    {
                        setTileByPosition(j, i, BlockType.Sandiron.getBlockId());
                    }
                }
                else if(i <= height-25 && i > height-50)
                {
                    setTileByPosition(j, i, BlockType.Stone.getBlockId());

                    int r = MainGameScreen.getInstance().random.nextInt(100);

                    if(r >= 94 && r < 97)
                    {
                        setTileByPosition(j, i, BlockType.Silver.getBlockId());
                    }
                    if(r >= 98)
                    {
                        setTileByPosition(j, i, BlockType.Gold.getBlockId());
                    }

                    if(r >= 88 && r < 93)
                    {
                        setTileByPosition(j, i, BlockType.Coal_Ore.getBlockId());
                    }
                    if(r >= 88 && r < 90)
                    {
                        setTileByPosition(j, i, BlockType.Iron.getBlockId());
                    }
                }
                else if(i <= height-50 && i > height-100)
                {
                    setTileByPosition(j, i, BlockType.Blackstone.getBlockId());

                    int r = MainGameScreen.getInstance().random.nextInt(1000);

                    if(r >= 990)
                    {
                        setTileByPosition(j, i, BlockType.Diamond_Ore.getBlockId());
                    }
                }
                else if(i <= height-100)
                {
                    int r = MainGameScreen.getInstance().random.nextInt(100);
                    //int r2 = MainGameScreen.getInstance().random.nextInt(3);

                    setTileByPosition(j, i, BlockType.LavaBlackstone.getBlockId());

                    if(r >= 99) {
                        setTileByPosition(j, i, BlockType.MagmaBlock.getBlockId());
                    }
                }
            }
        }

        //arrayMapBackground = arrayMap;
        for(int i = 0; i < arrayMap.length; i++) {
            arrayMapBackground[i] = arrayMap[i];
            if(arrayMapBackground[i] == BlockType.Sandiron.getBlockId())
                arrayMapBackground[i] = BlockType.Sandstone.getBlockId();
            if(arrayMapBackground[i] == BlockType.Sandcoal.getBlockId())
                arrayMapBackground[i] = BlockType.Sandstone.getBlockId();
            if(arrayMapBackground[i] == BlockType.Iron.getBlockId())
                arrayMapBackground[i] = BlockType.Stone.getBlockId();
            if(arrayMapBackground[i] == BlockType.Gold.getBlockId())
                arrayMapBackground[i] = BlockType.Stone.getBlockId();
            if(arrayMapBackground[i] == BlockType.Silver.getBlockId())
                arrayMapBackground[i] = BlockType.Stone.getBlockId();
            if(arrayMapBackground[i] == BlockType.Diamond_Ore.getBlockId())
                arrayMapBackground[i] = BlockType.Blackstone.getBlockId();
        }
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.setColor(new Color(0.5f, 0.5f, 0.5f, 1.0f));
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                BlockType blockType = BlockType.values()[getTileByPosition2(j,i)];
                batch.draw(blockType.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
            }
        }

        batch.setColor(Color.WHITE);

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BlockType blockType = BlockType.values()[getTileByPosition(j, i)];
                batch.draw(blockType.getBlockMeta().getTexture(), j * tileSize + position.x, i * tileSize + position.y, tileSize, tileSize);
            }
        }
    }

    public int getTileByPosition(int x, int y) {
        return arrayMap[y * width + x];
    }

    public int getTileByPosition2(int x, int y) {
        return arrayMapBackground[y * width + x];
    }

    public int getTileByGlobalPosition(Vector2 position) {
        if((int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize)) >= arrayMap.length) return 0;
        if((int) (Math.round((position.y - this.position.y) / tileSize)) * width + (Math.round((position.x - this.position.x) / tileSize)) < 0) return 0;
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
