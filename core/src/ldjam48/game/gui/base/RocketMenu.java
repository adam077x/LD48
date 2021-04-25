package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.nashorn.internal.ir.Block;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.GuiComponent;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.components.CraftingSlots;
import ldjam48.game.gui.inventory.InventorySlot;
import ldjam48.game.items.Item;

public class RocketMenu extends Gui {
    public static boolean hidden = true;

    private BitmapFont font = new BitmapFont();
    private CraftingSlots craftingSlots;
    private CraftingSlots magmaCraftingSlots;
    private CraftingSlots metalCraftingSlots;

    public RocketMenu() {
        super("Rocket Menu");

        int[][] blocksIds = new int[3][2];
        blocksIds[0][0] = BlockType.SilverIngot.getBlockId();
        blocksIds[0][1] = 8;
        blocksIds[1][0] = BlockType.IronIngot.getBlockId();
        blocksIds[1][1] = 8;
        blocksIds[2][0] = 0;
        blocksIds[2][1] = 0;

        CraftingSlots.Recipe recipe = new CraftingSlots.Recipe(blocksIds, new Item(BlockType.MechanicalPart, 1));

        craftingSlots = new CraftingSlots("Craft_Machine_Parts", this, "Create mechanical parts", recipe, new Vector2(0, 0));

        craftingSlots.button.offset = 3;

        int[][] blocksIds2 = new int[3][2];
        blocksIds2[0][0] = BlockType.MagmaBlock.getBlockId();
        blocksIds2[0][1] = 8;
        blocksIds2[1][0] = BlockType.IronIngot.getBlockId();
        blocksIds2[1][1] = 8;
        blocksIds2[2][0] = BlockType.Coal.getBlockId();
        blocksIds2[2][1] = 8;

        CraftingSlots.Recipe recipe2 = new CraftingSlots.Recipe(blocksIds2, new Item(BlockType.MagmaIngot, 1));

        magmaCraftingSlots = new CraftingSlots("Craft_Machine_Parts", this, "Create magma fuel", recipe2, new Vector2(0, -50));

        int[][] blocksIds3 = new int[3][2];
        blocksIds2[0][0] = BlockType.IronIngot.getBlockId();
        blocksIds2[0][1] = 8;
        blocksIds2[1][0] = BlockType.SilverIngot.getBlockId();
        blocksIds2[1][1] = 8;
        blocksIds2[2][0] = BlockType.GoldIngot.getBlockId();
        blocksIds2[2][1] = 8;

        CraftingSlots.Recipe recipe3 = new CraftingSlots.Recipe(blocksIds2, new Item(BlockType.MetalParts, 1));

        metalCraftingSlots = new CraftingSlots("Craft_Metal_Parts", this, "Create metal parts", recipe3, new Vector2(0, -100));
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        GlyphLayout glyphLayout = new GlyphLayout();

        glyphLayout.setText(font, name);

        font.draw(batch,name,Gdx.graphics.getWidth() / 2  - glyphLayout.width/2, Gdx.graphics.getHeight() / 2 - 125 + 50+250);

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);

        super.update(batch, delta);
     }
}
