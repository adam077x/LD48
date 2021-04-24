package ldjam48.game.gui.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.trashbin.TrashSlot;
import ldjam48.game.items.Item;
import ldjam48.game.node.Node;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.LinkedList;

public class Inventory extends Gui {


    private int invSize = 10;



    private int tileSize = 32;


    private BitmapFont font = new BitmapFont();

    private LinkedList<InventorySlot> slots = new LinkedList<>();

    private Texture texture;
    public Inventory() {
        super("Inventory");
        texture = TextureManager.inventory;
        for(int i = 0; i < invSize; i++)
        {
            InventorySlot inventorySlot = new InventorySlot(i);
            inventorySlot.position.x =
            Gdx.graphics.getWidth() -tileSize;
            inventorySlot.position.y = 150+(tileSize*i)+ 5;
            slots.add(inventorySlot);
            this.addNode(inventorySlot);
        }

        TrashSlot trashSlot = (TrashSlot)this.addNode(new TrashSlot("Trash", TextureManager.inventory));
        trashSlot.position.y = 0;
        trashSlot.position.x = Gdx.graphics.getWidth() - tileSize;
    }

    public void addItem(Item item)
    {
        if(item.getBlockType().getBlockMeta().hasMeta("drop"))
        {
            int drop = (int) item.getBlockType().getBlockMeta().getMeta("drop");
            BlockType blockType = BlockType.values()[drop];
            item.setBlockType(blockType);
        }
        for(InventorySlot slot : slots)
        {
            if(slot.canAdd(item))
            {
                slot.addItem(item);
                break;
            }
        }

    }
    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.draw(TextureManager.arrow, Gdx.graphics.getWidth() - (tileSize * 2), (150+(tileSize+5)*9)/2 + 16);

    }
}
