package org.academiadecodigo.superpaddle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import org.academiadecodigo.superpaddle.states.GameStateManager;
import org.academiadecodigo.superpaddle.states.MenuState;

public class SuperPaddle extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final int xCenter = WIDTH / 2;
	public static final int yCenter = HEIGHT / 2;

	private SpriteBatch sb;

	private GameStateManager gsm;

	@Override
	public void create () {
		sb = new SpriteBatch();
		gsm = new GameStateManager();

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);

		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
}
