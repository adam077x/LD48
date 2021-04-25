package ldjam48.game.gui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ldjam48.game.TextureManager;
import ldjam48.game.gui.GuiComponent;
import ldjam48.game.items.Item;
import ldjam48.game.node.NodeClickRender;

import static ldjam48.game.node.NodeClickRender.itemOnMouse;

public class Slot extends GuiComponent {

    private BitmapFont font = new BitmapFont();
    private Texture slotTexture;
    public Slot(String slotId, Texture slotTexture) {
        super("Slot_" + slotId);
        this.slotTexture = slotTexture;
    }
    protected int width = 32,height=32;

    protected Item itemInSlot;
    @Override
    public void update(SpriteBatch batch, float delta) {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            if(Gdx.input.getX() > position.x && Gdx.input.getX() < (position.x + width))
            {
                if((Gdx.graphics.getHeight() - Gdx.input.getY()) > position.y && (Gdx.graphics.getHeight() - Gdx.input.getY()) < (position.y + height))
                {
                    onClick();
                }
            }
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
        {
            if(Gdx.input.getX() > position.x && Gdx.input.getX() < (position.x + width))
            {
                if((Gdx.graphics.getHeight() - Gdx.input.getY()) > position.y && (Gdx.graphics.getHeight() - Gdx.input.getY()) < (position.y + height))
                {
                    onRightClick();
                }
            }
        }

        if(Gdx.input.getX() > position.x && Gdx.input.getX() < (position.x + width))
        {
            if((Gdx.graphics.getHeight() - Gdx.input.getY()) > position.y && (Gdx.graphics.getHeight() - Gdx.input.getY()) < (position.y + height))
            {
                if(itemInSlot != null)
                {
                    Hint.show(itemInSlot.getBlockType().name());
                }else
                {
                    Hint.hide();
                }
            }
        }else
        {
            Hint.hide();
        }

        batch.draw(slotTexture, position.x, position.y, width, height);
        batch.draw(itemInSlot != null? itemInSlot.getBlockType().getBlockMeta().getTexture() : TextureManager.blank, position.x +8, position.y + 8);

        if(itemInSlot == null)
            return;
        font.setColor(itemInSlot.getItemAmount() == 0 ? Color.RED : Color.BLACK);
        font.draw(batch, itemInSlot.getItemAmount() + "", position.x, position.y + 5);
    }

    private void onRightClick() {
        if(itemOnMouse != null)
        {
            if(itemInSlot != null)
            {
                if(itemInSlot.getBlockType() == itemOnMouse.getBlockType())
                {
                    if(itemOnMouse.getItemAmount() + 1 <= 999)
                    {
                        itemOnMouse.setItemAmount(itemOnMouse.getItemAmount() + 1);
                        itemInSlot.setItemAmount(itemInSlot.getItemAmount() - 1);
                        if(itemInSlot.getItemAmount() <= 0)
                        {
                            itemInSlot = null;
                        }
                        return;
                    }
                }
            }else {
                return;
            }
        }else
        {
            if(itemInSlot == null)
                return;

            itemOnMouse = new Item(itemInSlot.getBlockType(), 1);
            itemInSlot.setItemAmount(itemInSlot.getItemAmount() - 1);
            if(itemInSlot.getItemAmount() <= 0)
            {
                itemInSlot = null;
            }
        }
    }


    public void onClick()
    {
        if(itemOnMouse != null)
        {
            if(itemInSlot != null)
            {
                if(itemInSlot.getBlockType() == itemOnMouse.getBlockType())
                {
                    if(itemInSlot.getItemAmount() + itemOnMouse.getItemAmount() <= 999)
                    {
                        itemInSlot.setItemAmount(itemInSlot.getItemAmount() + itemOnMouse.getItemAmount());
                        itemOnMouse = null;
                        return;
                    }
                }
            }else {
                itemInSlot = itemOnMouse;
                itemOnMouse = null;
            }
        }else
        {
            if(itemInSlot == null)
                return;
            itemOnMouse = itemInSlot;
            itemInSlot = null;
        }
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public Item getItemInSlot() {
        return itemInSlot;
    }

    public void setItemInSlot(Item itemInSlot) {
        this.itemInSlot = itemInSlot;
    }




}
