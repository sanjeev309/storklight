package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public abstract class State {
    protected GameStateManager gsm;

    protected OrthographicCamera cam;
    protected Viewport viewport;
    protected State(GameStateManager gsm){
        this.gsm = gsm;

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    public abstract void resize(int width, int height);
}
