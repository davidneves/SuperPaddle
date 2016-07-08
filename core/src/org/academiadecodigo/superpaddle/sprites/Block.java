package org.academiadecodigo.superpaddle.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.screens.PlayScreen;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class Block {

    private final int BLOCK_SIZE = 16;
    private Body body;
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private PlayScreen screen;
    private Fixture fixture;
    private MapObject object;
    private boolean destroyed;

    public boolean isDestroyed() {
        return destroyed;
    }

    private TiledMapTileSet tileSet;



    public Block(PlayScreen screen, MapObject object) {


        this.object = object;
        this.screen = screen;


        defineBlock();

    }

    private void defineBlock() {

        world = screen.getWorld();
        map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / SuperPaddle.PPM, (bounds.getY() + bounds.getHeight() / 2) / SuperPaddle.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth() / 2 / SuperPaddle.PPM, bounds.getHeight() / 2 / SuperPaddle.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

        fixture.setUserData(this);
        Filter filter = new Filter();
        filter.categoryBits = SuperPaddle.BLOCK_BIT;
        fixture.setFilterData(filter);

    }

    public void onHit(Ball ball) {

        Filter filter = new Filter();
        filter.categoryBits = SuperPaddle.DESTROYED_BLOCK_BIT;
        fixture.setFilterData(filter);

        getCell().setTile(null);
        destroyed = true;


    }


    public TiledMapTileLayer.Cell getCell() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(6);

        return layer.getCell((int)(body.getPosition().x * SuperPaddle.PPM / 16), (int)(body.getPosition().y * SuperPaddle.PPM / 16));
    }
}
