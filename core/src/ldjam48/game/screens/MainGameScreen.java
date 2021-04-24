package ldjam48.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import ldjam48.game.Game;
import ldjam48.game.gui.Inventory;
import ldjam48.game.node.Node;
import ldjam48.game.node.NodePlayer;
import ldjam48.game.node.NodeSprite;
import ldjam48.game.node.NodeTilemap;

public class MainGameScreen implements Screen {
    Texture img;
    BitmapFont font = new BitmapFont();

    Game game;

    public Node scene;
    public Node gui;
    public NodePlayer nodePlayer;
    public static NodeTilemap tilemap;

    private Inventory inventory;

    private SpriteBatch guiBatch;

    public MainGameScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
        scene = new Node("Game Scene");
        gui = new Node("Gui");
        scene.position = new Vector2(0, 0);
        guiBatch = new SpriteBatch();

        tilemap = new NodeTilemap("Tilemap", 64, 256, 32);
        tilemap.position.y += -8000;

        nodePlayer = new NodePlayer();
        nodePlayer.position.y = 190;
        nodePlayer.position.x = 16*32;

        scene.addNode(tilemap);
        scene.addNode(nodePlayer);

        gui.addNode(inventory = new Inventory());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 1, 1);
        game.batch.setProjectionMatrix(nodePlayer.camera.combined);
        game.batch.begin();

        font.draw(game.batch, "Hello World", 300, 300);

        scene.update(game.batch, delta);

        game.batch.end();

        guiBatch.begin();

        gui.update(guiBatch, delta);

        guiBatch.end();
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
        img.dispose();
        font.dispose();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
