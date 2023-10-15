package com.uef.flappy_bird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.uef.flappy_bird.Flappy_Bird;

public class MenuStates extends States {
    // Textures for background and play button
    Texture background;
    Texture playBtn;

    // Constructor to initialize the menu state
    public MenuStates(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        // Check for a touch input to transition to the play state
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayStates(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        // Draw the background and play button on the screen
        sb.draw(background, 0, 0, Flappy_Bird.WIDTH, Flappy_Bird.HEIGHT);
        sb.draw(playBtn, (Flappy_Bird.WIDTH / 2) - (playBtn.getWidth() / 2), Flappy_Bird.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        // Dispose of resources when the menu state is no longer needed
        background.dispose();
        playBtn.dispose();
        // Log a message when the state is disposed
        // System.out.println("Menu State Disposed");
    }
}
