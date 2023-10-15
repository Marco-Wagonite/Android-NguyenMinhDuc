package com.uef.flappy_bird.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;

    // Constructor for creating an animation
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;

        // Create individual frames for the animation
        for (int i = 0; i < frameCount; i++) {
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount; // Calculate the time for each frame
        frame = 0; // Initialize the current frame index
    }

    // Method to update the animation frame
    public void update(float dt) {
        currentFrameTime += dt;

        // Switch to the next frame when the time exceeds the maximum frame time
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }

        // Loop back to the first frame if we've reached the end
        if (frame >= frameCount)
            frame = 0;
    }

    // Method to flip all frames horizontally
    public void flip() {
        for (TextureRegion region : frames)
            region.flip(true, false);
    }

    // Method to get the current frame of the animation
    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}

