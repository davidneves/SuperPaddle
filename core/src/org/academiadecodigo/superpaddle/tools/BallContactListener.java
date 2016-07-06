package org.academiadecodigo.superpaddle.tools;

import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.scenes.Hud;
import org.academiadecodigo.superpaddle.sprites.Ball;
import org.academiadecodigo.superpaddle.sprites.Block;
import org.academiadecodigo.superpaddle.sprites.Paddle;

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
                    if ((((Ball) fixA.getUserData()).player) != null) {
                        Hud.addScore(SuperPaddle.BLOCK_SCORE, ((Ball) fixA.getUserData()).player.playerNumber);
                    }
                } else {
                    ((Block) fixA.getUserData()).onHit((Ball) fixB.getUserData());
                    if (((Ball) fixB.getUserData()).player != null) {
                        Hud.addScore(SuperPaddle.BLOCK_SCORE, ((Ball) fixB.getUserData()).player.playerNumber);
                    }
                }

                break;

            case SuperPaddle.BALL_BIT | SuperPaddle.PADDLE_BIT:
                if (fixA.getFilterData().categoryBits == SuperPaddle.BALL_BIT) {
                    ((Ball) fixA.getUserData()).player = (Paddle) fixB.getUserData();
                } else {
                    ((Ball) fixB.getUserData()).player = (Paddle) fixA.getUserData();
                }
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
