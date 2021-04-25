package ldjam48.game.node;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;

public class NodeBase extends NodeSprite {
    public NodeBase() {
        super("Base", TextureManager.factory, 234, 192);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        batch.setColor(new Color(0.5f, 0.5f, 0.5f, 1.0f));
        super.update(batch, delta);
        batch.setColor(Color.WHITE);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)position.x, (int)position.y, width, height);
    }
}
