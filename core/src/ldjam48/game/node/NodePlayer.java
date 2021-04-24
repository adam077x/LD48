package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.compression.lzma.Base;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.base.BaseMenu;
import ldjam48.game.gui.base.BaseUpgradeMenu;
import ldjam48.game.gui.game.GameOver;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.items.Item;
import ldjam48.game.screens.MainGameScreen;

public class NodePlayer extends NodeSprite{
    public float force;
    public OrthographicCamera camera;
    private NodeTilemap nodeTilemap;
    private NodeBase nodeBase;
    private BaseMenu baseMenu;
    private BaseUpgradeMenu baseUpgradeMenu;
    private NodeUpgradeBase nodeUpgradeBase;

    private Texture drill = TextureManager.drill;
    private Sprite drillSprite = new Sprite(drill);

    private Texture drill2 = TextureManager.drill2;
    private Sprite drillSprite2 = new Sprite(drill2);

    public Rectangle getReactangle() {
        return new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
    }

    public static int drillLevel = 1;

    public NodePlayer() {
        super("Player", TextureManager.playerBase, 32, 32);
        camera = new OrthographicCamera(640, 480);
        camera.position.x = position.x;
        camera.position.y = position.y;

        face = Face.DOWN;
        position.y += 32;

        //super.sprite.setOrigin(32/2, 32/2);
        nodeTilemap = (NodeTilemap)MainGameScreen.scene.findNode("Tilemap");
        nodeBase = (NodeBase)MainGameScreen.scene.findNode("Base");
        baseMenu = (BaseMenu) MainGameScreen.gui.addNode(new BaseMenu());
        baseUpgradeMenu = (BaseUpgradeMenu) MainGameScreen.gui.addNode(new BaseUpgradeMenu());
        nodeUpgradeBase = (NodeUpgradeBase) MainGameScreen.scene.addNode(new NodeUpgradeBase());
        nodeUpgradeBase.position.y = 190;
        nodeUpgradeBase.position.x = 190;

    }

    private Face face;

    private float animationDown = 0;
    private float animationUp = 0;
    private float animationLeft = 0;
    private float animationRight = 0;

    private boolean isAnimationRunning() {
        if(animationDown > 0 || animationUp > 0 || animationLeft > 0 || animationRight > 0) return true;
        return false;
    }

    private BitmapFont font = new BitmapFont();
    @Override
    public void update(SpriteBatch batch, float delta) {
        //super.update(batch, delta);
        batch.draw(img, position.x + animationLeft - animationRight, position.y + animationDown - animationUp, 32, 32);

        if(face == Face.DOWN) {
            batch.draw(drillSprite, position.x, position.y - 32 + animationDown, 32, 32);
        }
        else if(face == Face.UP){
            batch.draw(drillSprite, position.x, position.y + 32 - animationUp, 32, 32);
        }
        else if(face == Face.LEFT) {
            batch.draw(drillSprite2, position.x - 32 + animationLeft, position.y, 32, 32);
        }
        else {
            batch.draw(drillSprite2, position.x + 32 - animationRight, position.y, 32, 32);
        }
        if(CoalStatus.coalLevel != 0)
        {
            if(Gdx.input.isKeyPressed(Input.Keys.A) && position.x > 0 && !isAnimationRunning()) {
                int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(position.x - 32, position.y));
                if(!(BlockType.values()[blockId].getBlockMeta().getHardness() <= drillLevel)) return;

                position.x -= 32;
                drillSprite2.setFlip(true, false);
                face = Face.LEFT;
                animationLeft += 32;
                animationRight = 0;
                animationDown = 0;
                animationRight = 0;
                mine(position.x, position.y );
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D) && position.x < (nodeTilemap.width-1) * nodeTilemap.tileSize && animationRight <= 0 && !isAnimationRunning()) {
                int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(position.x + 32, position.y));
                if(!(BlockType.values()[blockId].getBlockMeta().getHardness() <= drillLevel)) return;

                position.x += 32;
                drillSprite2.setFlip(false, false);
                face = Face.RIGHT;
                animationRight += 32;
                animationLeft = 0;
                animationDown = 0;
                animationLeft = 0;
                mine(position.x, position.y );
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S) && animationDown <= 0 && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !isAnimationRunning()) {
                int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(position.x, position.y - 32));
                if(blockId == BlockType.Bedrock.getBlockId()) return;

                if(!(BlockType.values()[blockId].getBlockMeta().getHardness() <= drillLevel)) return;

                position.y -= 32;
                drillSprite.setFlip(false, false);
                face = Face.DOWN;
                animationDown += 32;
                animationUp = 0;
                animationRight = 0;
                animationLeft = 0;
                mine(position.x, position.y);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.W) && animationUp <= 0 && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !isAnimationRunning()) {
                int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(position.x, position.y + 32));
                if(!(BlockType.values()[blockId].getBlockMeta().getHardness() <= drillLevel)) return;

                position.y += 32;
                drillSprite.setFlip(false, true);
                animationUp += 32;
                animationDown = 0;
                animationRight = 0;
                animationLeft = 0;
                face = Face.UP;
                mine(position.x, position.y);
            }
        }


        if(animationDown > 0) {
            animationDown -= delta * 100;
        }
        else {
            animationDown = 0;
        }

        if(animationUp > 0) {
            animationUp -= delta * 100;
        }
        else {
            animationUp = 0;
        }

        if(animationLeft > 0) {
            animationLeft -= delta * 100;
        }
        else {
            animationLeft = 0;
        }

        if(animationRight > 0) {
            animationRight -= delta * 100;
        }
        else {
            animationRight = 0;
        }

        showBaseMenu();
        showBaseUpgradeMenu();

        camera.position.x = position.x - animationRight + animationLeft;
        camera.position.y = position.y + animationDown - animationUp;

        if(camera.position.x <= 320) {
            camera.position.x = 320;
        }

        if(camera.position.x >= 1728) {
            camera.position.x = 1728;
        }

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

    private void showBaseUpgradeMenu() {
        if(nodeUpgradeBase.getRectangle().overlaps(getReactangle())) {
            baseUpgradeMenu.hidden = false;
        }
        else {
            baseUpgradeMenu.hidden = true;
        }
    }

    public void mine(float x, float y)
    {
        int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(x, y));
        CoalStatus.coalLevel -- ;
        if(blockId == 0)
            return;
        else if(blockId == BlockType.Bedrock.getBlockId())
            return;
        if(CoalStatus.coalLevel == 0)
        {
            GameOver.hidden = false;
            return;
        }




        BlockType blockType = BlockType.values()[blockId];
        Item item = new Item(blockType, 1);
        MainGameScreen.getInstance().getInventory().addItem(item);
        nodeTilemap.setTileByGlobalPosition(new Vector2(x, y), 0);
    }

    public enum Face {
        DOWN,UP,RIGHT,LEFT;
    }
}
