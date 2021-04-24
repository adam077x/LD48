package ldjam48.game.blocks;

import ldjam48.game.TextureManager;

public enum BlockType {
    Mars_Soil(1, "Soil", new BlockMeta(true, false, TextureManager.img)),
    Coal(2,"Coal", new BlockMeta(true, false, TextureManager.img));
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
