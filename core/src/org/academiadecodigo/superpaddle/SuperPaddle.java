package org.academiadecodigo.superpaddle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.superpaddle.states.GameScreenManager;
import org.academiadecodigo.superpaddle.states.MenuScreen;

public class SuperPaddle extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final float PPM = 100;

	public static final short EDGE_BIT = 1;
	public static final short BALL_BIT = 2;
	public static final short PADDLE_BIT = 4;
	public static final short BLOCK_BIT = 8;

	public SpriteBatch sb;

	private AssetManager manager;


	@Override
	public void create () {

		sb = new SpriteBatch();

		manager = new AssetManager();
		manager.load("audio/music.mp3", Music.class);
		manager.finishLoading();

		setScreen(new MenuScreen(this, manager));
	}

	@Override
	public void render () {

		super.render();

	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		sb.dispose();
	}
}

//TODO: 1 - move paddles according to keyboard input
//TODO: 2 - add blocks with tiled
//TODO: 3 - update score according to game
//TODO: 4 - add game over screen
//wishlist: move paddles with touch thingy
//wishlist: add networking! --> implement server-client model
//wishlist: implement high score using databases
//wishlist: make it round!
