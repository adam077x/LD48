package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.nashorn.internal.ir.Block;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.GuiComponent;
import ldjam48.game.gui.components.BackgroundSlot;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.components.CraftingSlots;
import ldjam48.game.gui.inventory.InventorySlot;
import ldjam48.game.items.Item;
import ldjam48.game.node.NodeRocketBase;
import ldjam48.game.screens.MainEndScene;
import ldjam48.game.screens.MainGameScreen;

public class RocketMenu extends Gui {
    public static boolean hidden = true;

    private BitmapFont font = new BitmapFont();
    private CraftingSlots craftingSlots;
    private CraftingSlots magmaCraftingSlots;
    private CraftingSlots metalCraftingSlots;

    BackgroundSlot slot1;
    BackgroundSlot slot2;
    BackgroundSlot slot3;

    public RocketMenu() {
        super("Rocket Menu");

        int[][] blocksIds = new int[3][2];
        blocksIds[0][0] = BlockType.SilverIngot.getBlockId();
        blocksIds[0][1] = 4;
        blocksIds[1][0] = BlockType.IronIngot.getBlockId();
        blocksIds[1][1] = 4;
        blocksIds[2][0] = 0;
        blocksIds[2][1] = 0;

        CraftingSlots.Recipe recipe = new CraftingSlots.Recipe(blocksIds, new Item(BlockType.MechanicalPart, 1));

        craftingSlots = new CraftingSlots("Craft_Machine_Parts", this, "Create mechanical parts", recipe, new Vector2(0, 0));

        craftingSlots.button.offset = 3;

        int[][] blocksIds2 = new int[3][2];
        blocksIds2[0][0] = BlockType.MagmaBlock.getBlockId();
        blocksIds2[0][1] = 4;
        blocksIds2[1][0] = BlockType.IronIngot.getBlockId();
        blocksIds2[1][1] = 4;
        blocksIds2[2][0] = BlockType.Coal.getBlockId();
        blocksIds2[2][1] = 4;

        CraftingSlots.Recipe recipe2 = new CraftingSlots.Recipe(blocksIds2, new Item(BlockType.MagmaIngot, 1));

        magmaCraftingSlots = new CraftingSlots("Craft_Machine_Parts", this, "Create magma fuel", recipe2, new Vector2(0, -50));

        int[][] blocksIds3 = new int[3][2];
        blocksIds2[0][0] = BlockType.IronIngot.getBlockId();
        blocksIds2[0][1] = 4;
        blocksIds2[1][0] = BlockType.SilverIngot.getBlockId();
        blocksIds2[1][1] = 4;
        blocksIds2[2][0] = BlockType.GoldIngot.getBlockId();
        blocksIds2[2][1] = 4;

        CraftingSlots.Recipe recipe3 = new CraftingSlots.Recipe(blocksIds2, new Item(BlockType.MetalParts, 1));

        metalCraftingSlots = new CraftingSlots("Craft_Metal_Parts", this, "Create metal parts", recipe3, new Vector2(0, -100));

        slot1 = new BackgroundSlot("5555", TextureManager.mechanicalPart, 1);
        slot1.position.x = 79;
        slot1.position.y = 150;

        addNode(slot1);

        slot2 = new BackgroundSlot("6666", TextureManager.metalParts, 1);
        slot2.position.x = 79 + 50;
        slot2.position.y = 150;

        addNode(slot2);

        slot3 = new BackgroundSlot("7777", TextureManager.magmaIngot, 1);
        slot3.position.x = 79 + 100;
        slot3.position.y = 150;

        addNode(slot3);

        Button button = new Button("Flyaway", "Fly away with rocket", 175, 32, new ButtonEvent() {
            @Override
            public void onClick() {
                if(assembled) {
                    NodeRocketBase nodeRocketBase = (NodeRocketBase) MainGameScreen.getInstance().scene.findNode("Node Rocket Base");

                    nodeRocketBase.sprite = new Sprite(TextureManager.rocket);
                    NodeRocketBase.flyAway = true;
                }
            }
        });

        button.position.x = 79 + 240;
        button.position.y = 145;

        addNode(button);
    }

    public static boolean assembled = false;

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        GlyphLayout glyphLayout = new GlyphLayout();

        glyphLayout.setText(font, name);

        font.draw(batch,name,Gdx.graphics.getWidth() / 2  - glyphLayout.width/2, Gdx.graphics.getHeight() / 2 - 125 + 50+250);

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);

        if(slot1.getItemInSlot() != null && slot2.getItemInSlot() != null && slot3.getItemInSlot() != null && !assembled) {
            System.out.println("PASSED");
            if(slot1.getItemInSlot().getBlockType().getBlockId() == BlockType.MechanicalPart.getBlockId() && slot2.getItemInSlot().getBlockType().getBlockId() == BlockType.MetalParts.getBlockId() && slot3.getItemInSlot().getBlockType().getBlockId() == BlockType.MagmaIngot.getBlockId()) {
                NodeRocketBase nodeRocketBase = (NodeRocketBase) MainGameScreen.getInstance().scene.findNode("Node Rocket Base");
                //MainGameScreen.getInstance().getGame().setScreen(new MainEndScene());
                nodeRocketBase.sprite = new Sprite(TextureManager.rocket_ramp2);
                assembled = true;
            }
        }

        super.update(batch, delta);
     }
}
