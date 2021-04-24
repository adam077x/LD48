package ldjam48.game.node;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NodeSprite extends Node {
    public Texture img;

    public int width, height;

    public NodeSprite(String name, Texture img, int width, int height) {
        super(name);

        this.width = width;
        this.height = height;
        this.img = img;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.draw(img, position.x, position.y, width, height);
    }
}
