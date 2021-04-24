package ldjam48.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import ldjam48.game.screens.MainGameScreen;
import ldjam48.game.screens.MainMenuScreen;

public class Game extends com.badlogic.gdx.Game {
	public SpriteBatch batch;

	@Override
	public void create () {
		//this.create();
		batch = new SpriteBatch();
		//this.setScreen(new MainGameScreen(this));
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
