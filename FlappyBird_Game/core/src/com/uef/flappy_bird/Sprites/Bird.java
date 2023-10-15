package com.uef.flappy_bird.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    // Constants for gravity and movement
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    // Position and velocity vectors
    private Vector3 position;
    private Vector3 velocity;

    // Rectangle to represent the bird's bounds
    private Rectangle bounds;

    // Texture for the bird's appearance
    private Texture texture;

    // Animation for bird animation frames
    private Animation birdAnimation;

    // Sound for flapping
    private Sound flap;

    // Flag to indicate collision
    public boolean colliding;

    // Constructor to initialize the bird
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        // Load bird texture and create animation
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);

        // Set up the bounding rectangle
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

        colliding = false;

        // Load flap sound
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    // Update the bird's position and animation
    public void update(float dt) {
        birdAnimation.update(dt);

        // Apply gravity to the bird's velocity
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        // Move the bird horizontally if not colliding
        if (!colliding)
            position.add(MOVEMENT * dt, velocity.y, 0);

        // Ensure the bird stays above a certain y-coordinate
        if (position.y < 82)
            position.y = 82;

        velocity.scl(1 / dt);

        // Update the bounding rectangle position
        updateBounds();
    }

    // Make the bird jump and play the flap sound
    public void jump() {
        velocity.y = 250;
        flap.play(0.5f);
    }

    // Update the position of the bounding rectangle
    public void updateBounds() {
        bounds.setPosition(position.x, position.y);
    }

    // Getters for bird's attributes
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return texture.getWidth();
    }

    public float getHeight() {
        return texture.getHeight();
    }

    // Get the current frame of the bird's animation
    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    // Get the bounding rectangle
    public Rectangle getBounds() {
        return bounds;
    }

    // Dispose of resources when no longer needed
    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
