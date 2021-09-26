package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.screen.ScreenManager;

public class AsteroidController extends ObjectPool<Asteroid> {
    private Texture textureAsteroid;
    private final int numAsteroid = 3;

    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidController() {
        this.textureAsteroid = new Texture("asteroid.png");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid as = activeList.get(i);
            batch.draw(textureAsteroid, as.getPosition().x - 128, as.getPosition().y - 128);
        }
    }

    public void setup(float x, float y) { //, float vx, float vy) {
        getActiveElement().activate(x, y); // , vx, vy);
    }

    public void update(float dt) {

        while (activeList.size() < numAsteroid) {
            int xy = MathUtils.random(0, 4);
            float x = 600.0f;
            float y = -100.0f;
            switch (xy) {
                case 0:
                    x = -100.0f;
                    y = MathUtils.random(0.0f, ScreenManager.SCREEN_HEIGHT);
                    break;
                case 1:
                    x = (float) ScreenManager.SCREEN_WIDTH + 100.0f;
                    y = MathUtils.random(0.0f, ScreenManager.SCREEN_HEIGHT);
                    break;
                case 2:
                    x = MathUtils.random(0.0f, ScreenManager.SCREEN_WIDTH);
                    y = -100.0f;
                    break;
                case 3:
                    x = MathUtils.random(0.0f, ScreenManager.SCREEN_WIDTH);
                    y = (float) ScreenManager.SCREEN_HEIGHT + 100.0f;
                    break;
            }
            setup(x, y);
        }
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }


}
