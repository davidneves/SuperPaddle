package org.academiadecodigo.superpaddle.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class PlayScreen implements Screen {

    private SuperPaddle game;
    private AssetManager manager;



    public PlayScreen(SuperPaddle game, AssetManager manager) {
        this.game = game;
        this.manager = manager;
        System.out.println("Gameswfgwrgr");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
