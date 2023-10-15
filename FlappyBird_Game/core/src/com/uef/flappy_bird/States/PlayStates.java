package com.uef.flappy_bird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.uef.flappy_bird.Sprites.Bird;
import com.uef.flappy_bird.Sprites.Tube;

public class PlayStates extends States {
    // Constants for game parameters
    private static final int GROUND_Y_OFFSET = -30;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background, ground, gameoverImg;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;
    private boolean gameover;

    // Constructor to initialize the play state
    public PlayStates(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(40, 200); // Create the player character
        background = new Texture("bg.png"); // Load the background texture
        ground = new Texture("ground.png"); // Load the ground texture
        gameoverImg = new Texture("gameover.png"); // Load the game over image

        tubes = new Array<Tube>(); // Create an array to store tubes (obstacles)
        for (int i = 1; i <= TUBE_COUNT; i++) {
            // Add a specified number of tubes with appropriate spacing
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(groundPos1.x + ground.getWidth(), GROUND_Y_OFFSET);
        gameover = false; // Initialize the game state as not over
    }

    @Override
    public void handleInput() {
        // Check for touch input to make the bird jump or restart the game
        if (Gdx.input.isTouched()) {
            if (gameover) gsm.set(new PlayStates(gsm));
            else bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); // Handle user input
        updateGround(); // Update the ground scrolling
        bird.update(dt); // Update the player character
        cam.position.set(bird.getX() + 80, cam.viewportHeight / 2, 0); // Update the camera position

        // Loop through tubes to manage their positions and collisions
        for (Tube tube : tubes) {
            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())) {
                bird.colliding = true; // Set a collision flag on the bird
                gameover = true; // Mark the game as over
            }
        }
        if (bird.getY() <= ground.getHeight() + GROUND_Y_OFFSET) {
            gameover = true; // Mark the game as over
            bird.colliding = true; // Set a collision flag on the bird
        }
        cam.update(); // Update the camera
    }

    @Override
    public void dispose() {
        background.dispose(); // Dispose of background texture
        bird.dispose(); // Dispose of player character resources
        ground.dispose(); // Dispose of ground texture

        // Loop through tubes to dispose of their resources
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }

    public void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0); // Update ground position 1
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0); // Update ground position 2
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0); // Draw background

        // Loop through tubes to draw them
        for (Tube tube : tubes) {
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y); // Draw ground 1
        sb.draw(ground, groundPos2.x, groundPos2.y); // Draw ground 2
        sb.draw(bird.getTexture(), bird.getX(), bird.getY()); // Draw the player character

        if (gameover) {
            sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth() / 2, cam.position.y); // Draw game over image
        }
        sb.end();
    }
}
