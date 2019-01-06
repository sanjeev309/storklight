package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio.sanjeev.storklight.states.GameStateManager;

/**
 * Created by sanjeev309 on 4/9/18.
 */

public class LevelState extends State {

    public LevelState(GameStateManager gsm){
        super(gsm);

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

    @Override
    public void resize(int width, int height) {
        cam.position.set(width,height,0);
        cam.update();
    }
}
