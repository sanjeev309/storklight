package com.studio.sanjeev.storklight;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.states.GameStateManager;
import com.studio.sanjeev.storklight.states.MenuState;
import com.studio.sanjeev.storklight.states.PlayState;

public class StorkLightGameClass extends ApplicationAdapter {
	public static final int HEIGHT = 100;
	public static final int WIDTH = 100;
	public static final float SPEED = 0.1f;

	public static final String TITLE="Stork Light";
	private GameStateManager gsm;
	private SpriteBatch batch;
	OrthographicCamera cam;
	Viewport viewport;
	Stage stage;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		batch = new SpriteBatch();
		stage = new Stage();

		cam = new OrthographicCamera();
		viewport = new StretchViewport(100 ,100,cam);
		viewport.apply();
		cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);


		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm,cam, viewport,stage));

	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		batch.setProjectionMatrix(cam.projection);
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);

		cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);

		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		stage.setViewport(viewport);

	}
}
