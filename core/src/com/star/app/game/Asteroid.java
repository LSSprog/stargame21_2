package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private Vector2 velocity;
    //private float angel;
    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Asteroid() {
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        //this.angel = 0.0f; //MathUtils.random(0.0f, 360.0f);
//        this.velocity = new Vector2(MathUtils.cosDeg(angel) * 60, MathUtils.sinDeg(angel) * 60);
        this.active = false;
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y) { //, float vx, float vy) {
        position.set(x, y);
        float angel = MathUtils.random(0.0f, 360.0f);
        velocity.set(MathUtils.cosDeg(angel) * 60, MathUtils.sinDeg(angel) * 60);
        active = true;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < -200 || position.x > ScreenManager.SCREEN_WIDTH + 200 ||
                position.y < -200 || position.y > ScreenManager.SCREEN_HEIGHT + 200) {
            deactivate();
        }
    }
}
