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

    public Paddle(PlayScreen screen, int x, int y) {

        super(new Texture("paddle.png"));
        this.world = screen.getWorld();

        definePaddle(x, y);

    }

    public void update(float dt) {

        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }

    public void definePaddle(int x, int y) {

        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-7.5f, 57);
        vertice[1] = new Vector2(-7.5f, -57);
        vertice[2] = new Vector2(7.5f, -57);
        vertice[3] = new Vector2(7.5f, 57);
        shape.set(vertice);

        fdef.filter.categoryBits = SuperPaddle.PADDLE_BIT;
        fdef.filter.maskBits = SuperPaddle.EDGE_BIT | SuperPaddle.BALL_BIT;

        fdef.shape = shape;
        fdef.restitution = 1f;
        fdef.density = 1f;
        fdef.friction = 1f;
        b2Body.createFixture(fdef);
    }
}
