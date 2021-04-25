package ldjam48.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    public static final Sound sound = Gdx.audio.newSound(Gdx.files.internal("BreakSound1.wav"));
    public static final Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("BreakSound2.wav"));
    public static final Sound sound3 = Gdx.audio.newSound(Gdx.files.internal("Explosion70.wav"));
}
