package ldjam48.game.gui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.GuiComponent;
import ldjam48.game.node.NodeClickRender;

public class Button extends GuiComponent {
    public int width, height;
    public Texture texture1;
    public Texture texture2;
    public String text;
    private ButtonEvent event;

    private BitmapFont font = new BitmapFont();

    private int clicked = 0;

    public Button(String id, String text, int width, int height, ButtonEvent event) {
        super("Button_" + id);
        this.event = event;

        texture1 = TextureManager.button;
        texture2 = TextureManager.buttonFlash;

        this.text = text;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            if(Gdx.input.getX() > position.x && Gdx.input.getX() < (position.x + width))
            {
                if((Gdx.graphics.getHeight() - Gdx.input.getY()) > position.y && (Gdx.graphics.getHeight() - Gdx.input.getY()) < (position.y + height))
                {
                    clicked = 1;
                    event.onClick();
                }
            }
        }
        else {
            clicked = 0;
        }

        if(clicked == 0) {
            batch.draw(texture1, position.x, position.y, width, height);
        }
        else {
            batch.draw(texture2, position.x, position.y, width, height);
        }
        font.draw(batch, text, position.x + width/2 - text.length() * 4, position.y + height/2 + 5);
    }
}
