package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NodeSprite extends Node {
    public Texture img;

    public NodeSprite(String name, Texture img) {
        super(name);

        this.img = img;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.draw(img, position.x, position.y);
    }
}
