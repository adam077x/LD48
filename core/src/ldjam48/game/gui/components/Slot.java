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

        batch.draw(slotTexture, position.x, position.y, width, height);
        batch.draw(itemInSlot != null? itemInSlot.getBlockType().getBlockMeta().getTexture() : TextureManager.blank, position.x +8, position.y + 8);

        if(itemInSlot == null)
            return;
        font.setColor(itemInSlot.getItemAmount() == 0 ? Color.RED : Color.BLACK);
        font.draw(batch, itemInSlot.getItemAmount() + "", position.x, position.y + 5);
    }


    public void onClick()
    {
        System.out.println("Clicked");
        if(NodeClickRender.itemOnMouse != null)
        {
            if(itemInSlot != null)
                return;

            itemInSlot = NodeClickRender.itemOnMouse;
            NodeClickRender.itemOnMouse = null;
        }else
        {
            if(itemInSlot == null)
                return;
            NodeClickRender.itemOnMouse = itemInSlot;
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
