package ldjam48.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.node.Node;

public class BaseMenu extends Node {
    public boolean hidden = true;

    //private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public BaseMenu() {
        super("Base Menu");

        Slot slot = new Slot("testSlot1");
        slot.position = new Vector2(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 64);
        addNode(slot);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 128, Gdx.graphics.getHeight() / 2 - 128, 256, 256);
        batch.setColor(Color.WHITE);
        //batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);

        super.update(batch, delta);
    }
}
