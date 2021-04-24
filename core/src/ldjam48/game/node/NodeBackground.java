package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;

public class NodeBackground extends NodeSprite {
    public NodeBackground() {
        super("Hills", TextureManager.hills, 320, 160);

        position.y = 190;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);


    }
}
