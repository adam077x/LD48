package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.tool.Main;
import ldjam48.game.TextureManager;
import ldjam48.game.screens.MainGameScreen;

import javax.xml.bind.util.ValidationEventCollector;

public class NodePlayer extends NodeSprite{
    public float force;

    public NodePlayer() {
        super("Player", TextureManager.player, 32, 32);
        nodeTilemap = MainGameScreen.tilemap;
    }

    private NodeTilemap nodeTilemap;
    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            position.x -= 32;
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            position.x += 32;
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            position.y -= 32;
            mine(position.x, position.y + 16);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if(position.y >= 190) return;
            position.y += 32;
            mine(position.x, position.y );
        }


    }

    public void mine(float x, float y)
    {
        nodeTilemap.setTileByGlobalPosition(new Vector2(x, y), 0);
    }
}
