package ldjam48.game.node;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;

public class NodeUpgradeBase extends NodeSprite {
    public NodeUpgradeBase() {
        super("Upgrade Base", TextureManager.garage, 96, 96);
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
