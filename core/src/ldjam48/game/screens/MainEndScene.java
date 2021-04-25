package ldjam48.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ldjam48.game.node.NodeTimer;

public class MainEndScene implements Screen {

    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();


    @Override
    public void show() {

        NodeTimer.counting = false;

    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        batch.begin();

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, "Blocks Walked: " + (int)MainGameScreen.statistics.get("blocks_walked"));
        font.draw(batch, "Blocks Walked: " + (int)MainGameScreen.statistics.get("blocks_walked"), Gdx.graphics.getWidth()/2 - glyphLayout.width/2, Gdx.graphics.getHeight()/2 + 100);
        glyphLayout.setText(font, "Blocks Mined: " + (int)MainGameScreen.statistics.get("blocks_mined"));
        font.draw(batch, "Blocks Mined: " + (int)MainGameScreen.statistics.get("blocks_mined"), Gdx.graphics.getWidth()/2 - glyphLayout.width/2, Gdx.graphics.getHeight()/2 + 150);
        glyphLayout.setText(font, "Finished in: " + (String)MainGameScreen.statistics.get("time"));
        font.draw(batch, "Finished in:" + (String)MainGameScreen.statistics.get("time"), Gdx.graphics.getWidth()/2 - glyphLayout.width/2, Gdx.graphics.getHeight()/2 + 200);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
