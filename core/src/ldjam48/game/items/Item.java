package ldjam48.game.items;

import ldjam48.game.blocks.BlockType;

public class Item {
    private BlockType blockType;
    private int itemAmount;

    public Item(BlockType blockType, int itemAmount) {
        this.blockType = blockType;
        this.itemAmount = itemAmount;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }
}
