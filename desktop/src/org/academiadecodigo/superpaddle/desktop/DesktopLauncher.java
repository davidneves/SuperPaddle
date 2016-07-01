package org.academiadecodigo.superpaddle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.superpaddle.SuperPaddle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SuperPaddle.WIDTH;
		config.height = SuperPaddle.HEIGHT;
		config.title = "Supper Paddle";
		new LwjglApplication(new SuperPaddle(), config);
	}
}
