package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.states.GameStateManager;

/**
 * Created by sanjeev309 on 4/9/18.
 */

public class LevelState extends State {

    public LevelState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport, Stage stage){
        super(gsm, cam, viewport,stage);

    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
