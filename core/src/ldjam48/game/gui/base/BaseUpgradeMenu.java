package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.node.Node;
import ldjam48.game.node.NodePlayer;
import ldjam48.game.node.NodeUpgradeBase;

public class BaseUpgradeMenu extends Gui {
    private Button upgrade;
    private Button upgradeFuelStorage;

    public boolean hidden = true;

    Slot slot;

    public BaseUpgradeMenu() {
        super("Base Upgrading Menu");

        upgrade = new Button("upgrade", "Upgrade Drill", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if(slot.getItemInSlot() != null) {
                    if (slot.getItemInSlot().getBlockType().getBlockId() == BlockType.Sandiron.getBlockId() && slot.getItemInSlot().getItemAmount() >= 8) {
                        NodePlayer.drillLevel++;
                        NodePlayer.updateDrill();
                        //slot.setItemInSlot(null);
                        slot.getItemInSlot().setItemAmount(slot.getItemInSlot().getItemAmount() - 8);
                        if(slot.getItemInSlot().getItemAmount() <= 0) {
                            slot.setItemInSlot(null);
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
                if(slot.getItemInSlot() != null) {
                    if (slot.getItemInSlot().getBlockType().getBlockId() == BlockType.Sandiron.getBlockId() && slot.getItemInSlot().getItemAmount() >= 10) {
                        CoalStatus.maxCoalLevel += 100;

                        slot.getItemInSlot().setItemAmount(slot.getItemInSlot().getItemAmount() - 10);
                        if(slot.getItemInSlot().getItemAmount() <= 0) {
                            slot.setItemInSlot(null);
                        }
                    }
                }
            }
        });

        upgradeFuelStorage.position.x = Gdx.graphics.getWidth() / 2 - 150;
        upgradeFuelStorage.position.y = Gdx.graphics.getHeight() / 2 - 30;
        addNode(upgradeFuelStorage);

        slot = new Slot("Upgrade_Table", TextureManager.inventory);
        slot.position.x = 100;
        slot.position.y = 300;
        addNode(slot);

        slot = new Slot("Upgrade_Table", TextureManager.inventory);
        slot.position.x = Gdx.graphics.getWidth() / 2 - 220;
        slot.position.y = Gdx.graphics.getHeight() / 2 - 15;
        addNode(slot);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);

        if(slot.getItemInSlot() != null) {
            System.out.println(slot.getItemInSlot().getBlockType().getBlockId());
        }

        super.update(batch, delta);
    }
}
