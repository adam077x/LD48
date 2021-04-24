package ldjam48.game.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.node.Node;

public class Slot extends Node{
    public String slotName;

    public Slot(String slotName) {
        super("Slot");

        this.slotName = slotName;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.draw(TextureManager.inventory, position.x, position.y);
    }
}
