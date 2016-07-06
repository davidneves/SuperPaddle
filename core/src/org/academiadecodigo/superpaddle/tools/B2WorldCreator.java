package org.academiadecodigo.superpaddle.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.screens.PlayScreen;
import org.academiadecodigo.superpaddle.sprites.Block;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class B2WorldCreator {

    //private static Array<Block> blocks;

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;



        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SuperPaddle.PPM, (rect.getY() + rect.getHeight() / 2) / SuperPaddle.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / SuperPaddle.PPM, rect.getHeight() / 2 / SuperPaddle.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = SuperPaddle.EDGE_BIT;
            body.createFixture(fdef);
        }

        //blocks = new Array<Block>();

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {

            new Block(screen, object);

            //blocks.add(new Block(screen, rect.getX()/SuperPaddle.PPM, rect.getY()/SuperPaddle.PPM));
/*
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2)/ SuperPaddle.PPM, (rect.getY() + rect.getHeight() / 2)/ SuperPaddle.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2/ SuperPaddle.PPM, rect.getHeight() / 2/ SuperPaddle.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = SuperPaddle.BLOCK_BIT;
            body.createFixture(fdef);*/
        }
    }

    public static void removeBlock(Block block){
        //blocks.removeValue(block, true);
    }

    public Array<Block> getBlocks(){
        //return blocks;
        return null;
    }
}
