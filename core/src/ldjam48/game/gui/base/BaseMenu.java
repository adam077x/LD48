package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.items.Item;
import ldjam48.game.node.Node;

public class BaseMenu extends Gui {
    public boolean hidden = true;

    public BaseMenu() {
        super("Base Menu");

        int x = Gdx.graphics.getWidth() / 2 - 250;
        int y = Gdx.graphics.getHeight() / 2 - 125;
        BaseSlot baseSlot = new BaseSlot(1,"Coal");
        baseSlot.position = new Vector2(x  + 5+10, y + 5);
        BaseSlot baseSlot2 = new BaseSlot(2,"Irons");
        baseSlot2.position = new Vector2(x+32+65, y + 5);

        addNode(baseSlot);
        addNode(baseSlot2);

    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);
        batch.setColor(Color.WHITE);
        //batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);
        super.update(batch, delta);
    }
}
