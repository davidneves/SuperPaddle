package org.academiadecodigo.superpaddle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by Cadavre Exquis on 05-07-2016.
 */
public class GameOverScreen implements Screen{

    private SuperPaddle game;
    private OrthographicCamera cam;
    private Viewport viewport;

    private AssetManager manager;

    private Texture texture;


    public GameOverScreen(SuperPaddle game, AssetManager manager) {
        this.game = game;
        this.manager = manager;

        init();
    }

    private void init(){
        cam = new OrthographicCamera();
        viewport = new FitViewport(SuperPaddle.WIDTH, SuperPaddle.HEIGHT, cam);

        cam.position.set(viewport.getWorldWidth() / 2, viewport.getScreenHeight() / 2, 0);

        texture = new Texture("texture.jpg");
    }


    public void update(float dt) {

        handleInput(dt);
    }

    public void handleInput(float dt) {

        if (Gdx.input.justTouched()) {

            game.setScreen(new MenuScreen(game, manager));
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

        game.sb.setProjectionMatrix(cam.combined);
        game.sb.begin();
        game.sb.draw(texture, 0, cam.position.y - cam.viewportHeight / 2);

        game.sb.end();
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
