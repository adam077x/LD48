package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import jdk.jfr.internal.tool.Main;
import ldjam48.game.gui.game.GameOver;
import ldjam48.game.gui.statusbars.CoalStatus;
import ldjam48.game.node.drill.NodePlayer;
import ldjam48.game.screens.MainGameScreen;
import ldjam48.game.screens.MainMenuScreen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NodeTimer extends Node {

    private BitmapFont font = new BitmapFont();

    public NodeTimer () {
        super("Timer");
    }

    public static boolean counting = true;
    private long current = System.currentTimeMillis();
    private long currentStart = System.currentTimeMillis();
    private long startMillies = 0;

    private long endMillies = 1000*60*20;
    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(NodePlayer.stop) return;

        if(System.currentTimeMillis() >=  currentStart + endMillies)
        {
            counting = false;
            GameOver.hidden = false;
            CoalStatus.coalLevel = 0;
        }
        if(!counting)
            return;
        startMillies += (System.currentTimeMillis() - current);
        current = System.currentTimeMillis();


        MainGameScreen.statistics.put("time", milliesToText(startMillies));

        if(endMillies - startMillies <= 1000*60) {
            font.setColor(Color.RED);
        }
        font.draw(batch, "Time left:" +milliesToText(endMillies - startMillies), 21, Gdx.graphics.getHeight()-73);
    }
    Date date;
    DateFormat formatter = new SimpleDateFormat("mm:ss");

    public String milliesToText(long millies)
    {
        date = new Date(millies);
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }
}
