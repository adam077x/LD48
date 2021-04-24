package ldjam48.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ldjam48.game.TextureManager;
import ldjam48.game.node.Node;

public class BaseMenu extends Node {
    public boolean hidden = true;

    //private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public BaseMenu() {
        super("Base Menu");
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 100, 200, 200);
        batch.setColor(Color.WHITE);
        batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);
    }
}
