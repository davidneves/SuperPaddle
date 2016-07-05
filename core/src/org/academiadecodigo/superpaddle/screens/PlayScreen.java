package org.academiadecodigo.superpaddle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.superpaddle.SuperPaddle;
import org.academiadecodigo.superpaddle.scenes.Hud;
import org.academiadecodigo.superpaddle.sprites.Ball;
import org.academiadecodigo.superpaddle.sprites.Block;
import org.academiadecodigo.superpaddle.sprites.Paddle;
import org.academiadecodigo.superpaddle.tools.B2WorldCreator;
import org.academiadecodigo.superpaddle.tools.BallContactListener;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class PlayScreen implements Screen {

    private SuperPaddle game;
    private AssetManager manager;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Texture background;

    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Ball ball;
    private Paddle player1;
    private Paddle player2;
    private Array<Block> blocks;


    public PlayScreen(SuperPaddle game, AssetManager manager) {

        this.game = game;
        this.manager = manager;

        init();
    }

    private void init() {

        //TODO: add comments explaining what is going on
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(SuperPaddle.WIDTH, SuperPaddle.HEIGHT, gameCam);
        hud = new Hud(game.sb);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("limits.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        ball = new Ball(this);
        player1 = new Paddle(this, 40, 100);
        player2 = new Paddle(this, SuperPaddle.WIDTH - 40, SuperPaddle.HEIGHT - 100);


        world.setContactListener(new BallContactListener());

        background = new Texture("game_background.jpg");

    }

    public void handleInput(float dt) {

        if (Gdx.input.justTouched()) {
            //player1.b2Body.setLinearVelocity(0, 0);
            ball.b2Body.applyForce(new Vector2(MathUtils.random(-1000000, 1000000), MathUtils.random(-100000, 100000)), ball.b2Body.getWorldCenter(), true);
        }


        handlePlayer1Input();
        handlePlayer2Input();


    }

    private void handlePlayer1Input() {
        System.out.println("");
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.b2Body.setLinearVelocity(0, 1000);
            System.out.println("up");
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            System.out.println("down");
            player1.b2Body.setLinearVelocity(0, -1000);
        } else {

            Vector2 vector = player1.b2Body.getLinearVelocity();
            if(vector.x != 0 || vector.y != 0){
                setToSteady(player1);
                System.out.println("overriding velocity");
            }
        }

    }

    private void handlePlayer2Input() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2.b2Body.setLinearVelocity(0, 1000);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player2.b2Body.setLinearVelocity(0, -1000);
        } else {
            setToSteady(player2);
        }
    }

    private void setToSteady(Paddle player) {
        player.b2Body.setLinearVelocity(0, 0);
    }

    public void update(float dt) {

        handleInput(dt);
        world.step(1 / 60f, 6, 2);

        ball.update(dt);
        player1.update(dt);
        player2.update(dt);

        hud.update(dt);

        renderer.setView(gameCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {

        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gameCam.combined);

        game.sb.setProjectionMatrix(gameCam.combined);
        game.sb.begin();
        game.sb.draw(background, 0, gameCam.position.y - gameCam.viewportHeight / 2);
        ball.draw(game.sb);
        player1.draw(game.sb);
        player2.draw(game.sb);

        game.sb.end();

        game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
}
