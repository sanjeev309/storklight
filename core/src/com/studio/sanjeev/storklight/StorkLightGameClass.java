package com.studio.sanjeev.storklight;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.studio.sanjeev.storklight.states.GameStateManager;

public class StorkLightGameClass extends ApplicationAdapter {
	public static final int WIDTH = 1920;
	public static final int HEIGHT= 1080;
	public static final float SPEED = 0.1f;

	public static final String TITLE="Stork Light";
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new com.studio.sanjeev.storklight.states.MenuState(gsm));
//		gsm.push(new PlayState(gsm));
//        Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
