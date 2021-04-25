package ldjam48.game.gui.components;

import com.badlogic.gdx.graphics.Texture;
import ldjam48.game.TextureManager;
import ldjam48.game.items.Item;
import ldjam48.game.node.NodeClickRender;

public class OneWaySlot extends Slot{
    public OneWaySlot(int id) {
        super("OWS_"+id, TextureManager.inventory);
    }

    @Override
    public void onClick() {
        if(NodeClickRender.itemOnMouse != null)
        {
            return;
        }else
        {
            if(itemInSlot == null)
                return;
            NodeClickRender.itemOnMouse = itemInSlot;
            itemInSlot = null;
        }
    }

    public void addItem(Item item)
    {
        if (itemInSlot == null) {
            itemInSlot = item;
        } else {
            itemInSlot.setItemAmount(itemInSlot.getItemAmount() + item.getItemAmount());
        }
    }
}
