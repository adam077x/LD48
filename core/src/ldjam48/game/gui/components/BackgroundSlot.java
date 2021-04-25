package ldjam48.game.gui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;

public class BackgroundSlot extends Slot {
    private Texture backgroundTexture;
    private BitmapFont font = new BitmapFont();

    private int numberOfItems;

    public BackgroundSlot(String slotId, Texture backgroundTexture, int numberOfItems) {
        super(slotId, TextureManager.inventory);

        this.numberOfItems = numberOfItems;

        this.backgroundTexture = backgroundTexture;
    }


    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.setColor(new Color(1, 1, 1, 0.5f));
        batch.draw(backgroundTexture, position.x + 8, position.y + 8);
        batch.setColor(Color.WHITE);

        font.setColor(new Color(1, 0, 0, 0.5f));

        if(itemInSlot == null)
            if(numberOfItems != 0) {
                font.draw(batch, String.valueOf(numberOfItems), position.x, position.y);
            }
        font.setColor(Color.WHITE);
    }
}
