package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;

public class NodeRocketBase extends NodeSprite {
    public NodeRocketBase() {
        super("Node Rocket Base", TextureManager.factory, 156, 128);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
    }

    public Rectangle getRectangle() {
        return new Rectangle(position.x, position.y, width, height);
    }
}
