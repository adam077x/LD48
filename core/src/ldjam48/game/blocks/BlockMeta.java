package ldjam48.game.blocks;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class BlockMeta {
    private boolean isCollidable;
    private boolean isBackground;
    private Texture texture;
    public HashMap<Object, Object> customMetaValues = new HashMap<>();

    public BlockMeta(boolean isCollidable, boolean isBackground, Texture texture) {
        this.isCollidable = isCollidable;
        this.isBackground = isBackground;
        this.texture = texture;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public boolean isBackground() {
        return isBackground;
    }

    public Texture getTexture() {
        return texture;
    }
}
