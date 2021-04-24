package ldjam48.game.blocks;

public enum BlockType {
    Mars_Soil(0, "Soil", new BlockMeta(true, false)),
    Coal(1,"Coal", new BlockMeta(true, false));
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
