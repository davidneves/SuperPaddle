package org.academiadecodigo.superpaddle.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.screens.PlayScreen;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class Paddle extends Sprite {

    public World world;
    public Body b2Body;

    public Paddle(PlayScreen screen, float x, float y) {

        super(new Texture("paddle.png"));
        this.world = screen.getWorld();
        setSize(this.getWidth() / SuperPaddle.PPM, this.getHeight() / SuperPaddle.PPM);

        definePaddle(x, y);

    }

    public void update(float dt) {

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }

    public void definePaddle(float x, float y) {

        BodyDef bdef = new BodyDef();
        bdef.position.set(x / SuperPaddle.PPM, y / SuperPaddle.PPM);
        bdef.type = BodyDef.BodyType.KinematicBody;
        b2Body = world.createBody(bdef);

        //b2Body.setGravityScale(10f);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-7.5f, 57).scl(1 / SuperPaddle.PPM);
        vertice[1] = new Vector2(-7.5f, -57).scl(1 / SuperPaddle.PPM);
        vertice[2] = new Vector2(7.5f, -57).scl(1 / SuperPaddle.PPM);
        vertice[3] = new Vector2(7.5f, 57).scl(1 / SuperPaddle.PPM);
        shape.set(vertice);

        fdef.filter.categoryBits = SuperPaddle.PADDLE_BIT;
        fdef.filter.maskBits = SuperPaddle.EDGE_BIT | SuperPaddle.BALL_BIT;

        fdef.shape = shape;
        fdef.restitution = 1f;
        //fdef.density = 10f;
        fdef.friction = 0f;
        b2Body.createFixture(fdef);
    }
}
