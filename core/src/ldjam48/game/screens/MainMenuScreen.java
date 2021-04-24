package ldjam48.game.screens;

import ldjam48.game.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainMenuScreen implements Screen {
    private BitmapFont font = new BitmapFont();
    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private String[] options = new String[4];

    int selection = options.length-1;

    Game game;

    public MainMenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        options[0] = "Play";
        options[1] = "Settings";
        options[2] = "Help";
        options[3] = "Exit";
    }

    @Override
    public void render(float delta) {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer.end();
        batch.begin();

        for(int i = 0; i < options.length; i++) {
            if(i == selection) {
                font.draw(batch, "< "+ options[options.length - i - 1] + " >", Gdx.graphics.getWidth() / 2 - 30, Gdx.graphics.getHeight() / 2 + (i * 32));
            }
            else {
                font.draw(batch, options[options.length - i - 1], Gdx.graphics.getWidth() / 2 - 25, Gdx.graphics.getHeight() / 2 + (i * 32));
            }
        }

        if(Gdx.app.getInput().isKeyJustPressed(Input.Keys.ENTER) || Gdx.app.getInput().isKeyJustPressed(Input.Keys.SPACE)) {
            if(selection == 3) {
                game.setScreen(new MainGameScreen(game));
            }
            else if(selection == 0) {
                System.exit(0);
            }
        }

        if(Gdx.app.getInput().isKeyJustPressed(Input.Keys.DOWN) && selection > 0) {
            selection--;
        }
        if(Gdx.app.getInput().isKeyJustPressed(Input.Keys.UP) && selection < options.length-1) {
            selection++;
        }

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
