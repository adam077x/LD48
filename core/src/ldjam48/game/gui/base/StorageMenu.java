package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.inventory.InventorySlot;
import ldjam48.game.gui.statusbars.CoalStatus;

public class StorageMenu extends Gui {
    public static boolean hidden = true;
    private BitmapFont font = new BitmapFont();
    public StorageMenu() {
        super("Storage");

        int x = Gdx.graphics.getWidth() / 2 - 250;
        int y = Gdx.graphics.getHeight() / 2 - 125;
        for(int i = 0; i< 10; i++)
        {
            for(int j = 0; j < 5; j ++)
            {
                InventorySlot inventorySlot = new InventorySlot(i);
                inventorySlot.position.y = y + ((j*32)+(18*j)) + 9;
                inventorySlot.position.x = x + ((i*32)+(18*i)) + 9;
                inventorySlot.setWidth(32);
                inventorySlot.setHeight(32);

                this.addNode(inventorySlot);

            }
        }

    }

    public static boolean isHidden() {
        return hidden;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        GlyphLayout glyphLayout = new GlyphLayout();

        glyphLayout.setText(font, name);

        font.draw(batch,name,Gdx.graphics.getWidth() / 2  - glyphLayout.width/2, Gdx.graphics.getHeight() / 2 - 125 + 50+250);


        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);
        batch.setColor(Color.WHITE);
        //batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);
        super.update(batch, delta);
    }
}
