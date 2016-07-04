package org.academiadecodigo.superpaddle.screens;

import com.badlogic.gdx.Screen;

import java.util.Stack;

/**
 * Created by vi.KING David Neves on 04/07/16.
 */
public class GameScreenManager {

    private Stack<Screen> screens;

    public GameScreenManager() {
        screens = new Stack<Screen>();
    }

    public void push(Screen screen){
        screens.push(screen);
    }

    public void pop(){
        screens.pop().dispose();
    }

    public void set(Screen screen){
        screens.pop().dispose();
        screens.push(screen);
    }

    /*public void update(float dt){
        screens.peek().update(dt);
    }*/

    public void render(float dt){
        screens.peek().render(dt);
    }
}
