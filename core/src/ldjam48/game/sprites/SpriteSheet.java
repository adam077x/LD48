package ldjam48.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {

    private Texture texture;
    private TextureRegion[][] splitTexture;
    private TextureRegion spriteSheet;

    private int width, height;
    public SpriteSheet(Texture texture) {
        this(texture, 16);
    }

    public SpriteSheet(Texture texture, int tilesize)
    {
        this(texture, tilesize,tilesize);
    }
    public SpriteSheet(Texture texture, int width, int height)
    {
        this.texture = texture;
        this.height = height;
        this.width = width;
        spriteSheet = new TextureRegion(texture, width, height);
        splitTexture = spriteSheet.split(width, height);
    }

    public Sprite grabImage(int col, int row)
    {
        return new Sprite(splitTexture[row][col]);
    }
    public Sprite[] getRowAsArray(int row)
    {
        Sprite[] sprites = new Sprite[splitTexture[row].length];
        for (int i = 0; i < splitTexture[row].length; i++) {
            sprites[i] = new Sprite(splitTexture[row][i]);
        }
        return sprites;
    }
}
