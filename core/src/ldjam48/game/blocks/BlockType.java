package ldjam48.game.blocks;

import jdk.nashorn.internal.ir.Block;
import ldjam48.game.TextureManager;

public enum BlockType {

    Air(0, "Air", new BlockMeta(false, false, TextureManager.blank)),
    Dirt(1, "Dirt", new BlockMeta(true, false, TextureManager.dirt)),
    Grass(2, "Grass", new BlockMeta(true, false, TextureManager.grass)),
    Stone(3, "Stone", new BlockMeta(true, false, TextureManager.stone)),
    Coal(4,"Coal", new BlockMeta(true, false, TextureManager.img));
    private int blockId;
    private String blockName;
    private BlockMeta blockMeta;

    BlockType(int blockId, String blockName, BlockMeta blockMeta) {
        this.blockId = blockId;
        this.blockName = blockName;
        this.blockMeta= blockMeta;
    }

    public BlockMeta getBlockMeta() {
        return blockMeta;
    }

    public void setBlockMeta(BlockMeta blockMeta) {
        this.blockMeta = blockMeta;
    }

    public int getBlockId() {
        return blockId;
    }

    public String getBlockName() {
        return blockName;
    }

}
