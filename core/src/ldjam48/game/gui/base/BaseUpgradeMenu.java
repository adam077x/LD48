package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.node.drill.NodePlayer;

public class BaseUpgradeMenu extends Gui {
    private Button upgrade;
    private Button upgradeFuelStorage;

    public boolean hidden = true;

    SlotNamed slot1;
    SlotNamed slot2;

    private BitmapFont font = new BitmapFont();

    public BaseUpgradeMenu() {
        super("Upgrade Garage");

        upgrade = new Button("upgrade", "Upgrade Drill", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if(slot1.getItemInSlot() != null) {
                    System.out.println(slot1.getItemInSlot().getBlockType().getBlockId());
                    if (slot1.getItemInSlot().getBlockType().getBlockId() == BlockType.Sandiron.getBlockId() && slot1.getItemInSlot().getItemAmount() >= 8) {
                        NodePlayer.drillLevel++;
                        //slot.setItemInSlot(null);
                        slot1.getItemInSlot().setItemAmount(slot1.getItemInSlot().getItemAmount() - 8);
                        if(slot1.getItemInSlot().getItemAmount() <= 0) {
                            slot1.setItemInSlot(null);
                        }
                    }
                }
            }
        });

        upgrade.position.x = Gdx.graphics.getWidth() / 2 - 150;
        upgrade.position.y = Gdx.graphics.getHeight() / 2 + 50;
        addNode(upgrade);

        upgradeFuelStorage = new Button("upgrade", "Upgrade fuel storage", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if(slot2.getItemInSlot() != null) {
                    if (slot2.getItemInSlot().getBlockType().getBlockId() == BlockType.Sandiron.getBlockId() && slot2.getItemInSlot().getItemAmount() >= 10) {
                        CoalStatus.maxCoalLevel += 100;

                        slot2.getItemInSlot().setItemAmount(slot2.getItemInSlot().getItemAmount() - 10);
                        if(slot2.getItemInSlot().getItemAmount() <= 0) {
                            slot2.setItemInSlot(null);
                        }
                    }
                }
            }
        });

        upgradeFuelStorage.position.x = Gdx.graphics.getWidth() / 2 - 150;
        upgradeFuelStorage.position.y = Gdx.graphics.getHeight() / 2 - 30;
        addNode(upgradeFuelStorage);

        slot1 = new SlotNamed(0, "Irons (8x)");
        slot1.position.x = 100;
        slot1.position.y = 300;
        addNode(slot1);

        slot2 = new SlotNamed(1, "Irons (10x)");
        slot2.position.x = Gdx.graphics.getWidth() / 2 - 220;
        slot2.position.y = Gdx.graphics.getHeight() / 2 - 15;
        addNode(slot2);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        GlyphLayout glyphLayout = new GlyphLayout();

        glyphLayout.setText(font, name);

        font.draw(batch,name,Gdx.graphics.getWidth() / 2  - glyphLayout.width/2, Gdx.graphics.getHeight() / 2 - 125 + 50+250);
        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);

        if(slot1.getItemInSlot() != null) {
            System.out.println(slot1.getItemInSlot().getBlockType().getBlockId());
        }

        super.update(batch, delta);
    }
}
