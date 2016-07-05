package org.academiadecodigo.superpaddle.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.screens.PlayScreen;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class Ball extends Sprite {

    public World world;
    public Body b2Body;

    public Ball(PlayScreen screen) {

        super(new Texture("ball.png"));
        this.world = screen.getWorld();

        defineBall();

    }

    public void update(float dt) {

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }

    public void defineBall() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(SuperPaddle.WIDTH / 2, SuperPaddle.HEIGHT / 2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15);
        fdef.filter.categoryBits = SuperPaddle.BALL_BIT;
        fdef.filter.maskBits = SuperPaddle.BLOCK_BIT | SuperPaddle.EDGE_BIT | SuperPaddle.PADDLE_BIT;

        fdef.shape = shape;
        fdef.restitution = 1f;
        //fdef.friction = 1000f;
        //fdef.density = 1000f;
        b2Body.createFixture(fdef);
    }
}
