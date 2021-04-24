package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;

public class NodeBase extends NodeSprite {
    public NodeBase() {
        super("Base", TextureManager.base, 256, 128);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)position.x, (int)position.y, width, height);
    }
}
