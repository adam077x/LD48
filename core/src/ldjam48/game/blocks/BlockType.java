package ldjam48.game.blocks;

import ldjam48.game.TextureManager;

public enum BlockType {

    Air(0, "Air", new BlockMeta(false, false, TextureManager.blank, 0)),
    Dirt(1, "Dirt", new BlockMeta(true, false, TextureManager.dirt, 1)),
    Grass(2, "Grass", new BlockMeta(true, false, TextureManager.grass, 1)),
    Stone(3, "Stone", new BlockMeta(true, false, TextureManager.stone, 2)),
    Coal_Ore(4,"Coal", new BlockMeta(true, false, TextureManager.coal_ore, 2).addCustomMeta("drop", 13)),
    Bedrock(5, "Bedrock", new BlockMeta(true, false, TextureManager.bedrock, 1000)),
    Iron(6,"Iron", new BlockMeta(true, false, TextureManager.iron, 2)),
    Sandstone(7, "Sandstone", new BlockMeta(true, false, TextureManager.sandstone, 1)),
    Sand(8, "Sand", new BlockMeta(true, false, TextureManager.sand, 1)),
    Sandiron(9, "Sandiron", new BlockMeta(true, false, TextureManager.sandIron, 1)),
    Sandcoal(10, "Sandcoal", new BlockMeta(true, false, TextureManager.sandCoal, 1).addCustomMeta("drop", 13)),
    Silver(11, "Silver", new BlockMeta(true, false, TextureManager.silver, 2)),
    Gold(12, "Gold", new BlockMeta(true, false, TextureManager.gold, 2)),
    Coal(13, "Coal", new BlockMeta(false, false, TextureManager.coal, 0)),
    Iron_Ingot(14, "Iron Ingot", new BlockMeta(false, false, TextureManager.iron_ingot, 0));

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
