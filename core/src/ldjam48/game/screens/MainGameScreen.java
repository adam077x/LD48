package ldjam48.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import ldjam48.game.Game;
import ldjam48.game.gui.components.Hint;
import ldjam48.game.gui.game.GameOver;
import ldjam48.game.gui.inventory.Inventory;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.node.*;
import ldjam48.game.node.drill.NodePlayer;

import java.util.Random;

public class MainGameScreen implements Screen {
    Texture img;
    BitmapFont font = new BitmapFont();

    public Random random;

    Game game;

    public static Node scene;
    public static Node background;
    public static Node gui;

    public NodePlayer nodePlayer;
    public NodeBase nodeBase;
    public NodeTilemap tilemap;
    public NodeFurnace nodeFurnace;

    private Inventory inventory;

    private SpriteBatch guiBatch;
    private SpriteBatch backgroundBatch = new SpriteBatch();

    public static boolean hiddenMenuHint = true;
    public static boolean upgradeDrill = false;

    private static MainGameScreen instance;
    public MainGameScreen(Game game) {
        this.game = game;
        random = new Random();
        instance = this;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
        scene = new Node("Game Scene");
        gui = new Node("Gui");
        background = new Node("Background");

        scene.position = new Vector2(0, 0);
        guiBatch = new SpriteBatch();

        tilemap = new NodeTilemap("Tilemap", 64, 256, 32);
        tilemap.position.y += -8000;

        scene.addNode(tilemap);

        for(int i = 0; i < (tilemap.width * tilemap.tileSize) / 320; i++) {
            NodeBackground b = (NodeBackground)background.addNode(new NodeBackground());
            if(i % 2 > 0) {
                b.sprite.setFlip(true, false);
            }
            b.position.x = i * 320;
        }

        nodeBase = new NodeBase();
        nodeBase.position.y = 190;

        nodeFurnace  = new NodeFurnace();
        nodeFurnace.position.y = 191;
        nodeFurnace.position.x = 680;
        scene.addNode(nodeBase);
        scene.addNode(nodeFurnace);

        nodePlayer = new NodePlayer();
        nodePlayer.position.y = 192;
        nodePlayer.position.x = 16*32;

        scene.addNode(nodePlayer);
        gui.addNode(inventory = new Inventory());
        gui.addNode(new NodeClickRender());

        gui.addNode(new CoalStatus());
        gui.addNode(new Hint()) ;
        gui.addNode(new NodeTimer());
        gui.addNode(new GameOver());
    }

    @Override
    public void render(float delta) {
        System.out.println(Gdx.graphics.getFramesPerSecond());

        ScreenUtils.clear(0.8f, 0.8f, 1, 1);

        OrthographicCamera tmpCamera = nodePlayer.camera;
        //tmpCamera.position.x = 1000;
        backgroundBatch.setProjectionMatrix(tmpCamera.combined);
        backgroundBatch.begin();
        background.update(backgroundBatch, delta);
        backgroundBatch.end();

        game.batch.setProjectionMatrix(nodePlayer.camera.combined);
        game.batch.begin();

        scene.update(game.batch, delta);

        game.batch.end();

        guiBatch.begin();

        gui.update(guiBatch, delta);
        if(hiddenMenuHint) font.draw(guiBatch, "Press E to open menu", 100, 100);
        if(upgradeDrill) font.draw(guiBatch, "You need to upgrade your drill", 100, 100);

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

    public static MainGameScreen getInstance() {
        return instance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public SpriteBatch getGuiBatch() {
        return guiBatch;
    }
}
