package ldjam48.game.gui.base;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.components.NamedSlot;
import ldjam48.game.gui.components.Slot;
import ldjam48.game.node.NodeClickRender;

public class SlotNamed extends Slot implements NamedSlot {
    int id = 0;

    private String name;
    public SlotNamed(int id, String name) {
        super("Base_" + id, TextureManager.inventory);
        this.id = id;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
    private BitmapFont font = new BitmapFont();

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, name);
        font.draw(batch,name, position.x+height/2 - (glyphLayout.width/2), position.y + height+15);

    }
}
