package ldjam48.game.node;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ldjam48.game.TextureManager;

public class NodeFurnace  extends NodeSprite{

    public NodeFurnace() {
        super("Furnace", TextureManager.furnace, 128, 256);
        particleEffect.load(Gdx.files.internal("particles/furnace_particles"), Gdx.files.internal(""));
        particleEffect.start();
        particleEffect2.load(Gdx.files.internal("particles/furnace_particles"), Gdx.files.internal(""));
        particleEffect2.start();

        particleEffect3.load(Gdx.files.internal("particles/rocket_particle"), Gdx.files.internal(""));

    }

    ParticleEffect particleEffect = new ParticleEffect();
    ParticleEffect particleEffect2 = new ParticleEffect();
    ParticleEffect particleEffect3 = new ParticleEffect();

    @Override
    public void update(SpriteBatch batch, float delta) {
        batch.setColor(new Color(0.5f, 0.5f, 0.5f, 1.0f));
        super.update(batch, delta);
        batch.setColor(Color.WHITE);


        particleEffect.getEmitters().first().setPosition(position.x + sprite.getWidth()/2 + 40 ,position.y + sprite.getHeight()/2 +15 );
        particleEffect.draw(batch);
        particleEffect.update(delta);
        if (particleEffect.isComplete())
            particleEffect.reset();

        particleEffect2.getEmitters().first().setPosition(position.x + sprite.getWidth()/2 + 60 ,position.y + sprite.getHeight()/2 +15 );
        particleEffect2.draw(batch);
        particleEffect2.update(delta);
        if (particleEffect2.isComplete())
            particleEffect2.reset();
        particleEffect3.getEmitters().first().setPosition(position.x + sprite.getWidth()/2 + 50 ,position.y + sprite.getHeight()+85 );
        particleEffect3.draw(batch);
        particleEffect3.update(delta);
        if (particleEffect3.isComplete())
            particleEffect3.reset();
    }

    public Rectangle getRectangle() {
        return new Rectangle((int)position.x, (int)position.y, width, height);
    }
}
