package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.items.Item;

public class NodeClickRender extends Node {
    private BitmapFont font = new BitmapFont();
    //private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static Item itemOnMouse = null;
    public NodeClickRender() {
        super("ClickRender");
      //  itemOnMouse = new Item(BlockType.Dirt, 5);
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(itemOnMouse != null)
        {
            batch.draw(itemOnMouse.getBlockType().getBlockMeta().getTexture(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 32, 32);
            font.setColor(Color.WHITE);
            font.draw(batch, itemOnMouse.getItemAmount() + "", Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() + 5);
        }
    }
}
