package com.uef.flappy_bird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    // Width of tube texture (used for spacing tubes on play state)
    public static final int TUBE_WIDTH = 52;

    // Gap between the top and bottom tubes
    private static final int TUBE_GAP = 100;

    // Lowest position the top of the bottom tube can be (above ground level)
    private static final int LOWEST_OPENING = 120;

    // Fluctuation in tube placement to keep top tube in view
    private static final int FLUCTUATION = 130;

    // Textures for top and bottom tubes
    private Texture topTube, bottomTube;

    // Positions of top and bottom tubes
    private Vector2 posTopTube, posBottomTube;

    // Bounding rectangles for collision detection
    private Rectangle boundsTop, boundsBottom;

    // Random number generator for tube placement
    private Random rand;

    // Constructor to initialize the tubes
    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        // Randomly position the top and bottom tubes within the specified limits
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        // Create bounding rectangles for collision detection
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    // Reposition the tubes when they move off-screen
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(x, posTopTube.y);
        boundsBottom.setPosition(x, posBottomTube.y);
    }

    // Check if the player collides with either the top or bottom tube
    public boolean collides(Rectangle player) {
        return player.overlaps(boundsBottom) || player.overlaps(boundsTop);
    }

    // Getters for various tube attributes
    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public Rectangle getBoundsBottom() {
        return boundsBottom;
    }

    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    // Dispose of resources when no longer needed
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
