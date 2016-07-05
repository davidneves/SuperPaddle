package org.academiadecodigo.superpaddle.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.academiadecodigo.superpaddle.SuperPaddle;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class Hud implements Disposable {

    public Stage stage;
    private Label countdownLabel;
    private static Label scoreLabel1;
    private static Label scoreLabel2;
    private Label timeLabel;


    private Label player1Label;
    private Label player2Label;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private static Integer scorePlayer1;
    private static Integer scorePlayer2;

    public Hud(SpriteBatch sb) {

        worldTimer = 300;
        timeCount = 0;
        scorePlayer1 = 0;
        scorePlayer2 = 0;

        viewport = new FitViewport(SuperPaddle.WIDTH, SuperPaddle.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel1 = new Label(String.format("%06d", scorePlayer1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel2 = new Label(String.format("%06d", scorePlayer2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player1Label = new Label("PLAYER 1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player2Label = new Label("PLAYER 2", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(player1Label).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(player2Label).expandX().padTop(10);
        table.row();
        table.add(scoreLabel1).expandX();
        table.add(countdownLabel).expandX();
        table.add(scoreLabel2).expandX();

        stage.addActor(table);
    }

    public void update(float dt) {

        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value, int player) {
        switch (player) {
            case 1:
                scorePlayer1 += value;
                scoreLabel1.setText(String.format("%06d", scorePlayer1));
                break;
            case 2:
                scorePlayer2 += value;
                scoreLabel2.setText(String.format("%06d", scorePlayer2));
                break;
            default:
                break;
        }
    }

    @Override
    public void dispose() {

        stage.dispose();
    }
}
