package ldjam48.game.gui.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.TextureManager;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.components.Button;
import ldjam48.game.gui.components.ButtonEvent;
import ldjam48.game.gui.statusbars.CoalStatus;

public class BaseMenu extends Gui {
    public static boolean hidden = true;
    private BitmapFont font = new BitmapFont();
    private Button fuelButton;

    public BaseMenu() {
        super("Refuel Station");

        int x = 100;
        int y = Gdx.graphics.getHeight() / 2;
        final SlotNamed baseSlot = new SlotNamed(1,"Coal(5x)");
        baseSlot.position = new Vector2(110, 290);

        fuelButton = new Button("Fuel", "Add Fuel", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if (baseSlot.getItemInSlot().getBlockType().getBlockId() == BlockType.Coal.getBlockId() && baseSlot.getItemInSlot().getItemAmount() >= 5) {
                    CoalStatus.coalLevel += 100;

                    baseSlot.getItemInSlot().setItemAmount(baseSlot.getItemInSlot().getItemAmount() - 5);
                    if (baseSlot.getItemInSlot().getItemAmount() <= 0) {
                        baseSlot.setItemInSlot(null);
                    }
                }
            }
        });

        fuelButton.position.x = 200;
        fuelButton.position.y = 280;

        addNode(fuelButton);
        addNode(baseSlot);
    }

    public static boolean isHidden() {
        return hidden;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        GlyphLayout glyphLayout = new GlyphLayout();

        glyphLayout.setText(font, name);

        font.draw(batch,name,Gdx.graphics.getWidth() / 2  - glyphLayout.width/2, Gdx.graphics.getHeight() / 2 - 125 + 50+250);


        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);
        batch.setColor(Color.WHITE);
        //batch.draw(TextureManager.inventory, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 + 32);
        super.update(batch, delta);
    }
}
