package ldjam48.game.gui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.GuiComponent;
import ldjam48.game.gui.base.SlotNamed;
import ldjam48.game.gui.inventory.InventorySlot;
import ldjam48.game.items.Item;

import java.util.ArrayList;

public class CraftingSlots extends GuiComponent {
    private ArrayList<BackgroundSlot> inventorySlots;

    private OneWaySlot outputSlot;

    public Button button;

    public static class Recipe {
        public int[][] blockIds = new int[3][3];
        public Item outputItem;

        public Recipe(int[][] blockIds, Item outputItem) {
            this.blockIds = blockIds;
            this.outputItem = outputItem;
        }
    }

    private Recipe recipe;

    public CraftingSlots(String name, Gui parent, String craftText, Recipe recipe, Vector2 position) {
        super(name);
        this.position = position;

        this.recipe = recipe;

        inventorySlots = new ArrayList<BackgroundSlot>();

        int x = Gdx.graphics.getWidth() / 2 - 250;
        int y = Gdx.graphics.getHeight() / 2 - 125;
        for(int i = 0; i < 3; i++)
        {
            BackgroundSlot inventorySlot = new BackgroundSlot(String.valueOf(i), BlockType.values()[recipe.blockIds[i][0]].getBlockMeta().getTexture());
            inventorySlot.position.y = y + 32*6 + 9 + position.y;
            inventorySlot.position.x = x + ((i*32)+(18*i)) + 9 + position.x;
            inventorySlot.setWidth(32);
            inventorySlot.setHeight(32);

            parent.addNode(inventorySlot);
            inventorySlots.add(inventorySlot);
        }

        outputSlot = new OneWaySlot(999);
        outputSlot.position.y = y + 32*6 + 9 + position.y;
        outputSlot.position.x = x + ((4*32)+(18*4)) + 9 + position.x;
        outputSlot.setWidth(32);
        outputSlot.setHeight(32);

        parent.addNode(outputSlot);

        button = new Button("Crafting Button", craftText, 180, 32, new ButtonEvent() {
            @Override
            public void onClick() {
                craft();
            }
        });

        button.position.y = y + 32*6 + 9 + position.y;
        button.position.x = 256+64 + position.x;

        parent.addNode(button);
    }

    public void craft() {
        int a = 0;

        for(BackgroundSlot slot : inventorySlots) {
            if(slot != null) {

                for(int i = 0; i < 3; i++) {
                    if(slot.getItemInSlot() == null) continue;
                    if(slot.getItemInSlot().getBlockType().getBlockId() == recipe.blockIds[i][0] && slot.getItemInSlot().getItemAmount() >= recipe.blockIds[i][1]) {
                        slot.getItemInSlot().setItemAmount(slot.getItemInSlot().getItemAmount() - recipe.blockIds[i][1]);

                        if(slot.getItemInSlot().getItemAmount() <= 0) {
                            slot.setItemInSlot(null);
                        }
                        a++;
                    }
                }
            }
        }

        if(a >= 2) {
            outputSlot.addItem(recipe.outputItem);
        }
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
    }
}
