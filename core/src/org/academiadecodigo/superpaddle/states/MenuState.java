package org.academiadecodigo.superpaddle.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by codecadet on 30/06/16.
 */
public class MenuState extends State {

    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SuperPaddle.WIDTH / 2, SuperPaddle.HEIGHT / 2);
        playBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }
}
