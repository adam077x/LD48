package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.tool.Main;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.BaseMenu;
import ldjam48.game.items.Item;
import ldjam48.game.screens.MainGameScreen;

import javax.xml.bind.util.ValidationEventCollector;

public class NodePlayer extends NodeSprite{
    public float force;
    public OrthographicCamera camera;
    private NodeTilemap nodeTilemap;
    private NodeBase nodeBase;
    private BaseMenu baseMenu;

    public Rectangle getReactangle() {
        return new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
    }

    public NodePlayer() {
        super("Player", TextureManager.player, 32, 32);
        camera = new OrthographicCamera(640, 480);
        camera.position.x = position.x;
        camera.position.y = position.y;

        nodeTilemap = (NodeTilemap)MainGameScreen.scene.findNode("Tilemap");
        nodeBase = (NodeBase)MainGameScreen.scene.findNode("Base");
        baseMenu = (BaseMenu) MainGameScreen.gui.addNode(new BaseMenu());
    }

    public float animateY = 0;

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && position.x > 0) {
            position.x -= 32;
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && position.x < (nodeTilemap.width-1) * nodeTilemap.tileSize) {
            position.x += 32;
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            //position.y -= 32;
            animateY = -32;
            mine(position.x, position.y - 16);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if(position.y >= 190) return;
            position.y += 32;
            mine(position.x, position.y + 32);
        }

        if(animateY < 0) {
            position.y -= 128 * delta;
            animateY += 128 * delta;
        }

        showBaseMenu();

        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.update();
    }

    private void showBaseMenu() {
        if(nodeBase.getRectangle().overlaps(getReactangle())) {
            baseMenu.hidden = false;
        }
        else {
            baseMenu.hidden = true;
        }
    }

    public void mine(float x, float y)
    {
        int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(x, y));
        if(blockId == 0)
            return;
        else if(blockId == BlockType.Bedrock.getBlockId())
            return;
        BlockType blockType = BlockType.values()[blockId];
        Item item = new Item(blockType, 1);
        MainGameScreen.getInstance().getInventory().addItem(item);
        nodeTilemap.setTileByGlobalPosition(new Vector2(x, y), 0);
    }
}
