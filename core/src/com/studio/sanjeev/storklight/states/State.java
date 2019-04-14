package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public abstract class State{
    protected GameStateManager gsm;

    protected OrthographicCamera cam;
    protected Viewport viewport;
    protected Stage stage;
    protected State(GameStateManager gsm, OrthographicCamera cam, Viewport viewport, Stage stage){
        this.gsm = gsm;
        this.cam = cam;
        this.viewport = viewport;
        this.stage = stage;
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void pause();
    public abstract void dispose();

}
