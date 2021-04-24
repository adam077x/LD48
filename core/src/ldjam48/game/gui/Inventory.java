package ldjam48.game.gui;

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
import ldjam48.game.items.Item;
import ldjam48.game.node.Node;

import javax.xml.soap.Text;
import java.util.HashMap;

public class Inventory extends Node {


    private int invSize = 10;



    private int tileSize = 32;


    private BitmapFont font = new BitmapFont();

    private HashMap<Integer, Item> inventory = new HashMap<>();
    private HashMap<BlockType, Integer> itemBackwardMap = new HashMap<>();

    private Texture texture;
    public Inventory() {
        super("Inventory");
        texture = TextureManager.inventory;
        addItem(new Item(BlockType.Dirt, 30));
    }

    public void addItem(Item item)
    {
        if(itemBackwardMap.containsKey(item.getBlockType()))
        {
            int itemId = itemBackwardMap.get(item.getBlockType());
            Item itemInMap = inventory.get(itemId);

            itemInMap.setItemAmount(itemInMap.getItemAmount() + item.getItemAmount());
        }else
        {
            int id = inventory.size();
            inventory.put(id, item);
            itemBackwardMap.put(item.getBlockType(), id);
        }
    }
    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
        for(int i = 0; i < invSize; i++)
        {
            Item item = inventory.getOrDefault(i, new Item(BlockType.Air,0));
            Texture texture = item.getBlockType().getBlockMeta().getTexture();

            batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() -tileSize, 150+(tileSize*i)+ 5);

            batch.draw(texture, Gdx.graphics.getWidth()-tileSize+8,150+(tileSize*(i+1)) - 16);

            batch.setColor(item.getItemAmount() == 0 ? Color.RED : Color.BLACK);
            font.draw(batch, item.getItemAmount() + "", Gdx.graphics.getWidth() - tileSize, 160 + (tileSize*i) + 5);
            batch.setColor(Color.WHITE);
        }

       // batch
        batch.draw(TextureManager.arrow, Gdx.graphics.getWidth() - (tileSize * 2), (150+(tileSize+5)*9)/2 + 16);

    }
}
