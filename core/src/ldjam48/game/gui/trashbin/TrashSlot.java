package ldjam48.game.gui.trashbin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.components.BackgroundSlot;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.gui.inventory.Inventory;

public class TrashSlot extends BackgroundSlot {
    public TrashSlot(String slotId, Texture slotTexture) {
        super(slotId, TextureManager.trashbin, 0);
    }

    @Override
    public void onClick() {
        super.onClick();
        itemInSlot = null;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        batch.setColor(Color.RED);
        super.update(batch, delta);
        batch.setColor(Color.WHITE);
    }
}
