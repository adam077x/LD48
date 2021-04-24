package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.BaseMenu;
import ldjam48.game.items.Item;
import ldjam48.game.screens.MainGameScreen;

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
        super("Player", TextureManager.player, 32, 64);
        camera = new OrthographicCamera(640, 480);
        camera.position.x = position.x;
        camera.position.y = position.y;

        face = Face.DOWN;
        position.y += 32;

        super.sprite.setOrigin(32/2, 32/2);
        nodeTilemap = (NodeTilemap)MainGameScreen.scene.findNode("Tilemap");
        nodeBase = (NodeBase)MainGameScreen.scene.findNode("Base");
        baseMenu = (BaseMenu) MainGameScreen.gui.addNode(new BaseMenu());

    }

    public float animateY = 0;


    private Face face;

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && position.x > 0) {
            position.x -= 32;

            super.width = 64;
            super.height = 32;
            if(face != Face.LEFT)
            {
                super.sprite.setOrigin(32/2, 32/2);
                sprite.setTexture(TextureManager.player_rotated);
                sprite.setFlip(false, false);
                face = Face.LEFT;
            }
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && position.x < (nodeTilemap.width-1) * nodeTilemap.tileSize) {
            position.x += 32;

            super.width = 64;
            super.height = 32;
            if(face != Face.RIGHT)
            {

                super.sprite.setOrigin(32/2, 32/2);
                sprite.setTexture(TextureManager.player_rotated);
                sprite.setFlip(true, false);
                face = Face.RIGHT;
            }
            mine(position.x, position.y );
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            //position.y -= 32;
            if(animateY < 0)
                return;

            position.y -= 32;

            super.width = 32;
            super.height = 64;
            if(face != Face.DOWN)
            {
                super.sprite.setOrigin(32/2, 32/2);
                sprite.setTexture(TextureManager.player);
                sprite.setFlip(false, false);
                face = Face.DOWN;
            }
            mine(position.x, position.y +16);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if(position.y >= 190) return;

            if(animateY < 0 ||animateY > 31)
                return;

            position.y += 32;

            super.width = 32;
            super.height = 64;
            if(face != Face.UP)
            {
                super.sprite.setOrigin(32/2, 32/2);
                sprite.setTexture(TextureManager.player);
                sprite.setFlip(false, true);
                face = Face.UP;
            }
            mine(position.x, position.y);
        }

        if(animateY < 0) {
            position.y -= 128 * delta;
            animateY += 128 * delta;
            if(animateY > 0)
                animateY = 0;

            System.out.println(position.y);

        }
        if(animateY > 31)
        {
            position.y += 128 * delta;
            animateY -= 128 * delta;
            if(animateY < 0)
                animateY = 0;

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

    public enum Face {
        DOWN,UP,RIGHT,LEFT;
    }
}
