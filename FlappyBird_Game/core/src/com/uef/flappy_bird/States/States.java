package com.uef.flappy_bird.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class States {
    protected GameStateManager gsm;   // Game state manager for state transitions
    protected OrthographicCamera cam; // Camera for rendering
    protected Vector3 mouse;          // Mouse input coordinates

    // Constructor for initializing a game state
    protected States(GameStateManager gsm) {
        this.gsm = gsm;                 // Set the game state manager
        cam = new OrthographicCamera(); // Create an orthographic camera
        cam.setToOrtho(false, 240, 400); // Set the camera's view to orthographic mode with specified dimensions
    }

    // Abstract methods to be implemented by subclasses
    public abstract void handleInput();     // Method for handling user input
    public abstract void update(float dt);   // Method for updating the game state
    public abstract void render(SpriteBatch sb); // Method for rendering the game state
    public abstract void dispose();          // Method for disposing of resources
}

