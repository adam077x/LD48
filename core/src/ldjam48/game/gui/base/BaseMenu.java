package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.items.Item;
import ldjam48.game.node.Node;

import java.awt.*;

public class BaseMenu extends Gui {
    public static boolean hidden = true;

    private Button fuelButton;

    public BaseMenu() {
        super("Base Menu");

        int x = Gdx.graphics.getWidth() / 2 - 250;
        int y = Gdx.graphics.getHeight() / 2 - 125;
        final BaseSlot baseSlot = new BaseSlot(1,"Coal");
        baseSlot.position = new Vector2(x  + 5+10, y + 5);
        BaseSlot baseSlot2 = new BaseSlot(2,"Irons");
        baseSlot2.position = new Vector2(x+32+65, y + 5);

        fuelButton = new Button("Fuel", "Add Fuel", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if (baseSlot.getItemInSlot().getBlockType().getBlockId() == BlockType.Coal.getBlockId() && baseSlot.getItemInSlot().getItemAmount() >= 10) {
                    CoalStatus.coalLevel += 100;

                    baseSlot.getItemInSlot().setItemAmount(baseSlot.getItemInSlot().getItemAmount() - 10);
                    if (baseSlot.getItemInSlot().getItemAmount() <= 0) {
                        baseSlot.setItemInSlot(null);
                    }
                }
            }
        });

        fuelButton.position.x = 200;
        fuelButton.position.y = 200;

        addNode(fuelButton);
        addNode(baseSlot);
        addNode(baseSlot2);
    }

    public static boolean isHidden() {
        return hidden;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);
        batch.setColor(Color.WHITE);
        //batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);
        super.update(batch, delta);
    }
}
