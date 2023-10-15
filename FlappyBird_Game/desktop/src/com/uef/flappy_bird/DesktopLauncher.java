package com.uef.flappy_bird;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle(Flappy_Bird.TITLE);
		config.setWindowedMode(Flappy_Bird.WIDTH, Flappy_Bird.HEIGHT);

		new Lwjgl3Application(new Flappy_Bird(), config);
	}
}