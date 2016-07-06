package org.academiadecodigo.superpaddle.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.screens.PlayScreen;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class Ball extends Sprite {

    public World world;
    public Body b2Body;
    public Paddle player;
    public Fixture fixture;

    public Ball(PlayScreen screen) {

        super(new Texture("ball.png"));
        this.world = screen.getWorld();
        //setSize(this.getWidth() / SuperPaddle.PPM, this.getHeight() / SuperPaddle.PPM);
        setSize(SuperPaddle.BALL_RADIUS*2 / SuperPaddle.PPM, SuperPaddle.BALL_RADIUS*2 / SuperPaddle.PPM);

        defineBall();

    }

    public void update(float dt) {

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }

    public boolean isGoalPlayer2() {
        return (b2Body.getPosition().x < 0);
    }

    public boolean isGoalPlayer1() {
        return (b2Body.getPosition().x > SuperPaddle.WIDTH / SuperPaddle.PPM);
    }

    private void defineBall() {

        player = null;
        BodyDef bdef = new BodyDef();
        bdef.position.set(SuperPaddle.WIDTH / 2 / SuperPaddle.PPM, SuperPaddle.HEIGHT / 2 / SuperPaddle.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(SuperPaddle.BALL_RADIUS / SuperPaddle.PPM);
        fdef.filter.categoryBits = SuperPaddle.BALL_BIT;
        fdef.filter.maskBits = SuperPaddle.BLOCK_BIT | SuperPaddle.EDGE_BIT | SuperPaddle.PADDLE_BIT;

        fdef.shape = shape;
        fdef.restitution = 1f;
        fdef.friction = 0f;
        //fdef.density = 1000f;
        fixture = b2Body.createFixture(fdef);
        fixture.setUserData(this);
    }

    public void resetBall(){
        defineBall();
        b2Body.setLinearVelocity(SuperPaddle.BALL_SPEED * MathUtils.randomSign(), SuperPaddle.BALL_SPEED * MathUtils.randomSign());

    }

}
