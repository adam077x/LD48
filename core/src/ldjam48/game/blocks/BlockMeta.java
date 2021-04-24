package ldjam48.game.blocks;

import java.util.HashMap;

public class BlockMeta {
    private boolean isCollidable;
    private boolean isBackground;
    public HashMap<Object, Object> customMetaValues = new HashMap<>();

    public BlockMeta(boolean isCollidable, boolean isBackground) {
        this.isCollidable = isCollidable;
        this.isBackground = isBackground;
    }
}
