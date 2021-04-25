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
    Blackstone(14, "Blackstone", new BlockMeta(false, false, TextureManager.blackstone, 3)),
    LavaBlackstone(15, "LavaBlackstone", new BlockMeta(false, false, TextureManager.lavaBlackstone, 3)),
    Diamond_Ore(16, "Diamond_Ore", new BlockMeta(false, false, TextureManager.diamondOre, 3).addCustomMeta("drop", 17)),
    Diamond(17, "Diamond", new BlockMeta(false, false, TextureManager.diamond, 3)),
    IronIngot(18, "Iron Ingot", new BlockMeta(false, false, TextureManager.iron_ingot, 0)),
    GoldIngot(19,"Gold Ingot", new BlockMeta(false, false, TextureManager.gold_ingot, 0)),
    SilverIngot(20, "Silver Ingot", new BlockMeta(false, false, TextureManager.silver_ingot,0));



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
