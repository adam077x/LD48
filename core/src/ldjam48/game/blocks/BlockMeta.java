package ldjam48.game.blocks;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class BlockMeta {
    private boolean isCollidable;
    private boolean isBackground;
    private Texture texture;
    private int hardness;
    public HashMap<Object, Object> customMetaValues = new HashMap<>();

    public BlockMeta(boolean isCollidable, boolean isBackground, Texture texture, int hardness) {
        this.isCollidable = isCollidable;
        this.isBackground = isBackground;
        this.texture = texture;
        this.hardness = hardness;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHardness() {
        return hardness;
    }
}
