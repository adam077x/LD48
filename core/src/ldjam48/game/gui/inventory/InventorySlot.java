package ldjam48.game.gui.inventory;

import com.badlogic.gdx.graphics.Texture;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.items.Item;

public class InventorySlot extends Slot {

    private int capacity = 999;
    public InventorySlot(int slotId) {
        super("Inventory_"+slotId, TextureManager.inventory);
    }
    public boolean isEmpty()
    {
        return itemInSlot == null;
    }
    public boolean canAdd(int num)
    {
        return itemInSlot != null? (capacity - itemInSlot.getItemAmount()) >= num : true;
    }
    public boolean canAdd(Item item)
    {
        return itemInSlot == null || (itemInSlot.getBlockType() == item.getBlockType() && canAdd(item.getItemAmount()));
    }
    public void addItem(Item item)
    {
        if (itemInSlot == null) {
            itemInSlot = item;
        } else {
            itemInSlot.setItemAmount(itemInSlot.getItemAmount() + item.getItemAmount());
        }
    }
    public void removeItem(int amount)
    {
        if(itemInSlot == null)
            return;
        itemInSlot.setItemAmount(itemInSlot.getItemAmount() - amount);
    }

}
