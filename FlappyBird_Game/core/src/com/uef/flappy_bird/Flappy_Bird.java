package com.uef.flappy_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uef.flappy_bird.States.GameStateManager;
import com.uef.flappy_bird.States.MenuStates;

public class Flappy_Bird extends ApplicationAdapter {
	// Constants for the game window dimensions and title
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final float SCALE = 0.5f;
	public static final String TITLE = "FlappyBird";

	private Music music;              // Music for the game
	private SpriteBatch spriteBatch;  // Batch for rendering sprites
	private GameStateManager gameStateManager;  // Game state manager for managing different game states

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();  // Initialize the sprite batch for rendering
		gameStateManager = new GameStateManager();  // Initialize the game state manager
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));  // Load game music
		music.setLooping(true);  // Set the music to loop
		music.setVolume(0.1f);   // Set the music volume
		music.play();  // Start playing the music

		// Push the menu state onto the game state manager's stack
		gameStateManager.push(new MenuStates(gameStateManager));

		Gdx.gl.glClearColor(1, 0, 0, 1);  // Set the clear color for the screen
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  // Clear the screen with the clear color
		gameStateManager.update(Gdx.graphics.getDeltaTime());  // Update the game state manager
		gameStateManager.render(spriteBatch);  // Render the current game state
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();  // Dispose of the game music
	}
}
