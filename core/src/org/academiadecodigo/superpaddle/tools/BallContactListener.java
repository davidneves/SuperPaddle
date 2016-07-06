package org.academiadecodigo.superpaddle.tools;

import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.sprites.Ball;
import org.academiadecodigo.superpaddle.sprites.Block;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class BallContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case SuperPaddle.BALL_BIT | SuperPaddle.BLOCK_BIT:
                if (fixA.getFilterData().categoryBits == SuperPaddle.BALL_BIT) {
                    ((Block) fixB.getUserData()).onHit((Ball) fixA.getUserData());
                } else {
                    ((Block) fixA.getUserData()).onHit((Ball) fixB.getUserData());
                }
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
