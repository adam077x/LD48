package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.screens.MainGameScreen;

public class NodePlayer extends NodeSprite{
    public NodePlayer() {
        super("Player", TextureManager.player, 32, 32);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= 200 * delta;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += 200 * delta;
        }
    }
}
