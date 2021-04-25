package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.math.Vector2;
import ldjam48.game.blocks.BlockType;
import ldjam48.game.items.Item;

public class NodeClickRender extends Node {
    private BitmapFont font = new BitmapFont();
    //private ShapeRenderer shapeRenderer = new ShapeRenderer();
    public static Item itemOnMouse = null;
    public NodeClickRender() {
        super("ClickRender");

        particleEffect.load(Gdx.files.internal("particles/rocket_particle"),Gdx.files.internal(""));
        particleEffect.start();
      //  itemOnMouse = new Item(BlockType.Dirt, 5);
    }

    static ParticleEffect particleEffect = new ParticleEffect();
    public static boolean isFlaming = false;

    public static void setIsFlaming(boolean isFlaming) {
        NodeClickRender.isFlaming = isFlaming;
        if(isFlaming)
            particleEffect.start();
        else
            particleEffect.reset();
    }

    @Override
    public void update(SpriteBatch batch, float delta) {
        super.update(batch, delta);

        if(isFlaming)
        {
            particleEffect.getEmitters().first().setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            particleEffect.update(delta);
            particleEffect.draw(batch);

            if(particleEffect.isComplete())
                particleEffect.reset();

        }


        if(itemOnMouse != null)
        {
            batch.draw(itemOnMouse.getBlockType().getBlockMeta().getTexture(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 32, 32);
            font.setColor(Color.WHITE);
            font.draw(batch, itemOnMouse.getItemAmount() + "", Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY() + 5);
        }
    }
}
