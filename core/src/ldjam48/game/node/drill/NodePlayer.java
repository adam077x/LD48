package ldjam48.game.node.drill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.Game;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.base.*;
import ldjam48.game.gui.game.GameOver;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.items.Item;
import ldjam48.game.node.*;
import ldjam48.game.screens.MainEndScene;
import ldjam48.game.screens.MainGameScreen;

import java.util.ArrayList;

public class NodePlayer extends NodeSprite {
    public float force;
    public OrthographicCamera camera;
    private NodeTilemap nodeTilemap;
    private NodeBase nodeBase;
    private BaseMenu baseMenu;
    private FurnaceMenu furnaceMenu;
    private StorageMenu storageMenu;
    private BaseUpgradeMenu baseUpgradeMenu;
    private NodeUpgradeBase nodeUpgradeBase;
    private NodeStorage nodeStorage;
    private RocketMenu rocketMenu;
    private NodeRocketBase nodeRocketBase;

    private static Sprite drillSprite;

    private static Sprite drillSprite2;

    private DrillSprites[] drillSprites;

    public Rectangle getReactangle() {
        return new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
    }

    public static int drillLevel = 1;

    private ParticleEffect particleEffect = new ParticleEffect();

    public NodePlayer() {
        super("Player", TextureManager.playerBase, 32, 32);
        camera = new OrthographicCamera(640, 480);
        camera.position.x = position.x;
        camera.position.y = position.y;

        face = Face.DOWN;
        position.y += 32;

        //super.sprite.setOrigin(32/2, 32/2);
        nodeTilemap = (NodeTilemap)MainGameScreen.scene.findNode("Tilemap");
        nodeRocketBase = (NodeRocketBase)MainGameScreen.scene.findNode("Node Rocket Base");
        nodeBase = (NodeBase)MainGameScreen.scene.findNode("Base");
        nodeBase.position.y = 192;
        baseMenu = (BaseMenu) MainGameScreen.gui.addNode(new BaseMenu());
        MainGameScreen.gui.addNode(this.furnaceMenu  = new FurnaceMenu());
        storageMenu = (StorageMenu) MainGameScreen.gui.addNode(new StorageMenu());
        //TODO: move to MainGameScene
        baseUpgradeMenu = (BaseUpgradeMenu) MainGameScreen.gui.addNode(new BaseUpgradeMenu());
        nodeUpgradeBase = (NodeUpgradeBase) MainGameScreen.scene.addNode(new NodeUpgradeBase());
        nodeStorage = (NodeStorage) MainGameScreen.scene.addNode(new NodeStorage());
        rocketMenu = (RocketMenu) MainGameScreen.gui.addNode(new RocketMenu());

        nodeUpgradeBase.position.y = 192;
        nodeUpgradeBase.position.x = 190;

        nodeStorage.position.y = 192;
        nodeStorage.position.x = 425;

        drillSprites = new DrillSprites[4];

        int lastRow = 0;

        //Load animation

        particleEffect.load(Gdx.files.internal("particles/block_break"), Gdx.files.internal(""));
        particleEffect.start();


        for(int i = 0; i < 4; i++)
        {
            DrillSprites drillSprites = new DrillSprites();
            drillSprites.upSprites = new ArrayList<>();
            drillSprites.sideSprites = new ArrayList<>();

            for(int j = 0; j < 6; j ++)
            {
                drillSprites.upSprites.add(new Sprite(TextureManager.drillRegion[lastRow][j]));
                drillSprites.sideSprites.add(new Sprite(TextureManager.drillRegion[lastRow+1][j]));
            }
            lastRow += 2;
            this.drillSprites[i] = drillSprites;

        }

        drillSprite = new Sprite(drillSprites[0].upSprites.get(0));
        drillSprite2 = new Sprite(drillSprites[0].sideSprites.get(0));
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

    long current = System.currentTimeMillis();

    int sprite = 0;
    @Override
    public void update(SpriteBatch batch, float delta) {
        if(current + 400 + delta >= System.currentTimeMillis())
        {

            current = System.currentTimeMillis();
            DrillSprites drillSprite = drillSprites[drillLevel-1];
            if(drillSprite == null)
                return;

            Sprite ds1 = (drillSprite.upSprites.get(sprite));
            Sprite ds2 = (drillSprite.sideSprites.get(sprite));

            ds1.setFlip(this.drillSprite.isFlipX(), this.drillSprite.isFlipY());
            ds2.setFlip(drillSprite2.isFlipX(), drillSprite2.isFlipY());

            this.drillSprite = ds1;
            this.drillSprite2 = ds2;
            if(sprite + 1 > 5)
                sprite = 0;
            else
                sprite ++;
        }
        //super.update(batch, delta);
        particleEffect.update(delta);
        particleEffect.draw(batch);
        batch.draw(img, position.x + animationLeft - animationRight, position.y + animationDown - animationUp, 32, 32);

        MainGameScreen.upgradeDrill = false;

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

                if(!(BlockType.values()[blockId].getBlockMeta().getHardness() <= drillLevel)) {
                    MainGameScreen.upgradeDrill = true;
                    return;
                }

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

                if(position.y >= 192) return;

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
            animationDown -= delta * (50 + (drillLevel * 50));
        }
        else {
            animationDown = 0;
        }

        if(animationUp > 0) {
            animationUp -= delta * (50 + (drillLevel * 50));
        }
        else {
            animationUp = 0;
        }

        if(animationLeft > 0) {
            animationLeft -= delta * (50 + (drillLevel * 50));
        }
        else {
            animationLeft = 0;
        }

        if(animationRight > 0) {
            animationRight -= delta * (50 + (drillLevel * 50));
        }
        else {
            animationRight = 0;
        }

        MainGameScreen.hiddenMenuHint = false;
        showBaseMenu();
        showStorageMenu();
        showBaseUpgradeMenu();
        showFurnaceMenu();
        showRocketMenu();

        camera.position.x = position.x - animationRight + animationLeft;
        camera.position.y = position.y + animationDown - animationUp;

        if(camera.position.x <= 320) {
            camera.position.x = 320;
        }

        if(camera.position.x >= 1728) {
            camera.position.x = 1728;
        }

        camera.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            if(drillLevel + 1 >= 5)
            {
                drillLevel = 1;
            }else
                drillLevel ++;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            CoalStatus.coalLevel = 100000;
            CoalStatus.maxCoalLevel = 100000;
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.F3))
        {
            MainGameScreen.getInstance().getGame().setScreen(new MainEndScene());
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.F4))
        {

            NodeClickRender.setIsFlaming(!NodeClickRender.isFlaming);
        }
    }

    private void showStorageMenu() {
        if(nodeStorage.getRectangle().overlaps(getReactangle())) {
            MainGameScreen.hiddenMenuHint = true;
        }

        if(nodeStorage.getRectangle().overlaps(getReactangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E) && storageMenu.hidden) {
            storageMenu.hidden = false;
        }
        else if(!nodeStorage.getRectangle().overlaps(getReactangle()) || Gdx.input.isKeyJustPressed(Input.Keys.E) && !storageMenu.hidden){
            storageMenu.hidden = true;
        }
    }

    private void showRocketMenu() {
        if(nodeRocketBase.getRectangle().overlaps(getReactangle())) {
            MainGameScreen.hiddenMenuHint = true;
        }

        if(nodeRocketBase.getRectangle().overlaps(getReactangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E) && rocketMenu.hidden) {
            rocketMenu.hidden = false;
        }
        else if(!nodeRocketBase.getRectangle().overlaps(getReactangle()) || Gdx.input.isKeyJustPressed(Input.Keys.E) && !rocketMenu.hidden){
            rocketMenu.hidden = true;
        }
    }


    private void showFurnaceMenu() {

        NodeFurnace nodeFurnace = MainGameScreen.getInstance().nodeFurnace;
        if(nodeFurnace.getRectangle().overlaps(getReactangle())) {
            MainGameScreen.hiddenMenuHint = true;
        }

        if(nodeFurnace.getRectangle().overlaps(getReactangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E) && furnaceMenu.hidden) {
            furnaceMenu.hidden = false;
        }
        else if(!nodeFurnace.getRectangle().overlaps(getReactangle()) || Gdx.input.isKeyJustPressed(Input.Keys.E) && !furnaceMenu.hidden){
            furnaceMenu.hidden = true;
        }
    }

    private void showBaseMenu() {
        if(nodeBase.getRectangle().overlaps(getReactangle())) {
            MainGameScreen.hiddenMenuHint = true;
        }

        if(nodeBase.getRectangle().overlaps(getReactangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E) && baseMenu.hidden) {
            baseMenu.hidden = false;
        }
        else if(!nodeBase.getRectangle().overlaps(getReactangle()) || Gdx.input.isKeyJustPressed(Input.Keys.E) && !baseMenu.hidden){
            baseMenu.hidden = true;
        }
    }

    private void showBaseUpgradeMenu() {
        if(nodeUpgradeBase.getRectangle().overlaps(getReactangle())) {
            MainGameScreen.hiddenMenuHint = true;
        }

        if(nodeUpgradeBase.getRectangle().overlaps(getReactangle()) && Gdx.input.isKeyJustPressed(Input.Keys.E) && baseUpgradeMenu.hidden) {
            baseUpgradeMenu.hidden = false;
        }
        else if(!nodeUpgradeBase.getRectangle().overlaps(getReactangle()) || Gdx.input.isKeyJustPressed(Input.Keys.E) && !baseUpgradeMenu.hidden){
            baseUpgradeMenu.hidden = true;
        }
    }

    int blockMined = 0;
    int blockWalked = 0;

    public void mine(float x, float y)
    {
        blockWalked ++;
        MainGameScreen.statistics.put("blocks_walked", blockWalked);

        int blockId = nodeTilemap.getTileByGlobalPosition(new Vector2(x, y));
        CoalStatus.coalLevel -- ;
        if(blockId == 0)
            return;
        else if(blockId == BlockType.Bedrock.getBlockId())
            return;
        if(CoalStatus.coalLevel <= 0)
        {
            MainGameScreen.getInstance().getGame().setScreen(new MainEndScene());
            //GameOver.hidden = false;
            return;
        }


        for(ParticleEmitter emitter : particleEffect.getEmitters())
        {
            emitter.setPosition(x+16,y+16);
        }
        particleEffect.reset();
        blockMined ++;
        MainGameScreen.statistics.put("blocks_mined", blockWalked);



        BlockType blockType = BlockType.values()[blockId];
        Item item = new Item(blockType, 1);
        MainGameScreen.getInstance().getInventory().addItem(item);
        nodeTilemap.setTileByGlobalPosition(new Vector2(x, y), 0);
    }

    public enum Face {
        DOWN,UP,RIGHT,LEFT;
    }
}
