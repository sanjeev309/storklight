package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by sanjeev309 on 3/18/18.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playStork;
    private Texture playBtn;
    private BitmapFont font;


    public MenuState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport, Stage stage) {
        super(gsm,cam, viewport, stage);

//        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
//        cam = new OrthographicCamera();
//        viewport = new StretchViewport(100 * aspectRatio,100,cam);
//        viewport.apply();
//        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);


        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"),Gdx.files.internal("fonts/abel.png"),false);
        background = new Texture("n0.png");

        playStork = new Texture("storkmenu.png");
        playBtn = new Texture("play.png");

        font.getData().setScale(3);
        font.setColor(0,0,1,0);

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void dispose() {
        background.dispose();
        playStork.dispose();
        playBtn.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        cam.position.set(width,height,0);
        cam.update();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() ) {
            gsm.set(new PlayState(gsm,cam,viewport,stage));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, cam.viewportWidth/2,cam.viewportHeight/2);

        sb.draw(playStork,(cam.viewportWidth/2) - (playStork.getWidth()/2),(cam.viewportHeight/2) - (playStork.getHeight()/2) + 120);
        sb.draw(playBtn,(cam.viewportWidth/2) - (playBtn.getWidth()/2),(cam.viewportHeight/2) - (playBtn.getHeight()/2) - 150);
        sb.end();
        sb.begin();
        font.draw(sb, "StorkLight", cam.viewportWidth/2 - 200, cam.viewportHeight/10);
        sb.end();
    }
}
