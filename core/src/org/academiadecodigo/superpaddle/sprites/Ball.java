package org.academiadecodigo.superpaddle.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by codecadet on 01/07/16.
 */
public class Ball {

    private final int MOVEMENT = 100;
    private final int RADIUS = 25;

    private Texture texture;
    private Circle bounds;
    private Vector3 position;
    private Vector3 velocity;

    public Ball() {
        texture = new Texture("ball.png");
        position = new Vector3(SuperPaddle.xCenter - (texture.getWidth() / 2), SuperPaddle.yCenter - (texture.getHeight() /2), 0);
        velocity = new Vector3(0, 0, 0);

        bounds = new Circle(SuperPaddle.xCenter, SuperPaddle.yCenter, RADIUS);

        position.add(MathUtils.random(), MathUtils.random(), 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void update(float dt) {
        if (position.y == 0 || position.y == SuperPaddle.HEIGHT){
            bounce();
        }

        velocity.add(MOVEMENT * dt, MOVEMENT * dt, 0);
        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public void bounce(){
    }
}
