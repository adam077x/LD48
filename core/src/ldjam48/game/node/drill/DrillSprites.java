package ldjam48.game.node.drill;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

public class DrillSprites {
    public List<Sprite> upSprites;
    public List<Sprite> sideSprites;

    public List<Sprite> getUpSprites() {
        return upSprites;
    }

    public void setUpSprites(List<Sprite> upSprites) {
        this.upSprites = upSprites;
    }

    public List<Sprite> getSideSprites() {
        return sideSprites;
    }

    public void setSideSprites(List<Sprite> sideSprites) {
        this.sideSprites = sideSprites;
    }
}
