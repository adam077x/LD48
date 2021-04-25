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
import ldjam48.game.gui.components.OneWaySlot;
import ldjam48.game.items.Item;

public class FurnaceMenu extends Gui {
    public static boolean hidden = true;
    private BitmapFont font = new BitmapFont();
    private Button smeltButton;

    public FurnaceMenu() {
        super("Furnace");

        int x = Gdx.graphics.getWidth() / 2 - 250;
        int y = Gdx.graphics.getHeight() / 2 - 125;
        final SlotNamed baseSlot = new SlotNamed(1,"Coal(1x)");
        baseSlot.position = new Vector2(x  + 5+10+10, y + 5);

        final SlotNamed ironSlot = new SlotNamed(2, "Iron(1x)");
        ironSlot.position = new Vector2(x + 25, y + 5 + 50);


        final OneWaySlot oneWaySlot = new OneWaySlot(3);
        oneWaySlot.position = new Vector2(x + 275, y + 5);

        this.addNode(ironSlot);
        this.addNode(oneWaySlot);


        smeltButton = new Button("Smelt", "Smelt it!", 128, 64, new ButtonEvent() {
            @Override
            public void onClick() {
                if(baseSlot.getItemInSlot() == null || ironSlot.getItemInSlot() == null)
                    return;

                        baseSlot.getItemInSlot().setItemAmount(baseSlot.getItemInSlot().getItemAmount() - 1);
                        ironSlot.getItemInSlot().setItemAmount(ironSlot.getItemInSlot().getItemAmount() - 1);

                        if(oneWaySlot.getItemInSlot() == null)
                        {
                            oneWaySlot.setItemInSlot(new Item(getSmeltResult(ironSlot.getItemInSlot().getBlockType()),1));
                        }else
                        {
                            if(getSmeltResult(ironSlot.getItemInSlot().getBlockType()) == oneWaySlot.getItemInSlot().getBlockType())
                            {
                                oneWaySlot.getItemInSlot().setItemAmount(oneWaySlot.getItemInSlot().getItemAmount() + 1);
                            }
                            else
                            {
                                baseSlot.getItemInSlot().setItemAmount(baseSlot.getItemInSlot().getItemAmount() + 1);
                                ironSlot.getItemInSlot().setItemAmount(ironSlot.getItemInSlot().getItemAmount() + 1);
                            }
                        }
                        if (baseSlot.getItemInSlot().getItemAmount() <= 0) {
                            baseSlot.setItemInSlot(null);
                        }
                        if (ironSlot.getItemInSlot().getItemAmount() <= 0) {
                            ironSlot.setItemInSlot(null);
                        }




            }
        });

        smeltButton.position.x = 200;
        smeltButton.position.y = 200;

        addNode(smeltButton);
        addNode(baseSlot);
    }

    public BlockType getSmeltResult(BlockType ore)
    {
        switch (ore)
        {
            case Iron:
                return BlockType.IronIngot;
            case Sandiron:
                return BlockType.IronIngot;
            case Silver:
                return BlockType.SilverIngot;
            case Gold:
                return BlockType.GoldIngot;
        }
        return BlockType.Air;
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
