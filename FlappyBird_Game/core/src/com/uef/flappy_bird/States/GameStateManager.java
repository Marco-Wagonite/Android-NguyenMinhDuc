package com.uef.flappy_bird.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import java.util.Stack;

public class GameStateManager {
    // Stack to manage game states
    private Stack<States> states;

    // Constructor to initialize the game state manager
    public GameStateManager() {
        states = new Stack<States>();
    }

    // Push a new game state onto the stack
    public void push(States state) {
        states.push(state);
    }

    // Pop the current game state from the stack
    public void pop() {
        states.pop();
    }

    // Set the current game state by popping the previous state and pushing a new one
    public void set(States state) {
        states.pop();
        states.push(state);
    }

    // Update the current game state with a time delta
    public void update(float dt) {
        states.peek().update(dt);
    }

    // Render the current game state using a SpriteBatch
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
