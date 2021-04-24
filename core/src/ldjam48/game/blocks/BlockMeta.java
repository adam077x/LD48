package ldjam48.game.blocks;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class BlockMeta {
    private boolean isCollidable;
    private boolean isBackground;
    private Texture texture;
    private int hardness;
    private HashMap<Object, Object> customMetaValues = new HashMap<>();

    public BlockMeta(boolean isCollidable, boolean isBackground, Texture texture, int hardness) {
        this.isCollidable = isCollidable;
        this.isBackground = isBackground;
        this.texture = texture;
        this.hardness = hardness;
    }
    public BlockMeta addCustomMeta(Object o, Object o2)
    {
        customMetaValues.put(o, o2);
        return this;
    }
    public boolean hasMeta(Object o)
    {
        return customMetaValues.containsKey(o);
    }
    public Object getMeta(Object o)
    {
        return customMetaValues.getOrDefault(o, null);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getHardness() {
        return hardness;
    }
}
