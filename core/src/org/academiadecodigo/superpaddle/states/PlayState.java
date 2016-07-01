package org.academiadecodigo.superpaddle.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.sprites.Ball;
import org.academiadecodigo.superpaddle.sprites.Paddle;

/**
 * Created by codecadet on 01/07/16.
 */
public class PlayState extends State {

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;


    public PlayState(GameStateManager gsm) {
        super(gsm);

        ball = new Ball();
        leftPaddle = new Paddle(50, 200);
        rightPaddle = new Paddle(750, 200);

        camera.setToOrtho(false, SuperPaddle.WIDTH, SuperPaddle.HEIGHT);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();

        ball.update(dt);
        leftPaddle.update(dt);
        rightPaddle.update(dt);

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.end();


    }

    @Override
    public void dispose() {
        ball.dispose();
        rightPaddle.dispose();
        leftPaddle.dispose();
    }
}
