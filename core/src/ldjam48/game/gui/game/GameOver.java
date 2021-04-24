package ldjam48.game.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.gui.Gui;
import ldjam48.game.gui.base.BaseMenu;
import ldjam48.game.gui.statusbars.CoalStatus;

public class GameOver extends Gui {
    public GameOver() {
        super("Game Over");
    }
    BitmapFont font = new BitmapFont();

    public static boolean hidden = true;

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);
            if(hidden)
                return;


            font.getData().setScale(2.5f);
            font.setColor(Color.RED);
            font.draw(batch, "Game Over!",  Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
            font.getData().setScale(1.5f);
            font.setColor(Color.RED);
            font.draw(batch,"You have no fuel!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 100);
    }
}
