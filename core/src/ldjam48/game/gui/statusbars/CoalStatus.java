package ldjam48.game.gui.statusbars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.Gui;

public class CoalStatus extends Gui {

    public static int coalLevel = 500;
    public static int maxCoalLevel = 500;
    public CoalStatus() {
        super("Coal_Status");
    }
    private BitmapFont font = new BitmapFont();


    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        batch.setColor(Color.BLACK);
        batch.draw(TextureManager.blankWhite,0+20, Gdx.graphics.getHeight()-40, 104, 22);
        batch.setColor(Color.WHITE);
        int percents =100 - ((maxCoalLevel- coalLevel) * 100)/maxCoalLevel;
        batch.draw(TextureManager.blankWhite,0+22, Gdx.graphics.getHeight()-38, percents, 18);

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, coalLevel + "/" +maxCoalLevel);
        font.setColor(Color.BLACK);
        font.draw(batch, coalLevel + "/" +maxCoalLevel,21, Gdx.graphics.getHeight()-43);
        font.draw(batch, "Fuel",21, Gdx.graphics.getHeight()-2);

        if(coalLevel > maxCoalLevel) {
            coalLevel = maxCoalLevel;
        }
    }
}
