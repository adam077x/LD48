package ldjam48.game.gui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.Gui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Hint extends Gui {
    private BitmapFont font = new BitmapFont();
    private int height = 250, width = 500;
    public static boolean hidden;
    public Hint() {
        super("Hint Box");
    }

    public static void hide()
    {
        hidden = true;
    }
    private static List<String> t;
    public static void show(String ...text)
    {
        hidden = false;
        t = Arrays.asList(text);
    }
    @Override
    public void update(SpriteBatch batch, float delta) {
        if(hidden) return;

        batch.draw(TextureManager.backgroundGui, Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 - 125, 500, 250);
        batch.setColor(Color.WHITE);



        for(int i = 0; i < t.size(); i++ )
        {

            GlyphLayout glyphLayout = new GlyphLayout();
            glyphLayout.setText(font, t.get(i));
            font.getData().setScale(2,2);
            font.draw(batch,t.get(i), position.x+width/2 - (glyphLayout.width/2), position.y + height/2 + 100 - (glyphLayout.height - 25));
        }

        super.update(batch, delta);
    }
}
