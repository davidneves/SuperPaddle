package org.academiadecodigo.superpaddle.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class MenuScreen implements Screen {


    private SuperPaddle game;
    private OrthographicCamera cam;
    private Viewport viewport;

    private AssetManager manager;
    private Music music;


    public MenuScreen(SuperPaddle game, AssetManager manager) {
        this.game = game;
        this.manager = manager;

        cam = new OrthographicCamera();
        viewport = new FitViewport(SuperPaddle.WIDTH / SuperPaddle.PPM, SuperPaddle.HEIGHT / SuperPaddle.PPM, cam);

        cam.position.set(viewport.getWorldWidth() / 2, viewport.getScreenHeight() / 2, 0);

        music = manager.get("audio/music.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }


    public void update(float dt) {

        handleInput(dt);
    }

    public void handleInput(float dt) {

        if (Gdx.input.justTouched()) {

            game.setScreen(new PlayScreen(game, manager));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {

        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
