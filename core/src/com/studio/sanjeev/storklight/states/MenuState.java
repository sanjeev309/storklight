package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private OrthographicCamera cam;
    private Viewport viewport;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();
        cam = new OrthographicCamera();
        viewport = new StretchViewport(100 * aspectRatio,100,cam);
        viewport.apply();
        cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);


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
            gsm.set(new PlayState(gsm));
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
        sb.draw(background,0,0, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        sb.draw(playStork,(Gdx.graphics.getWidth()/2) - (playStork.getWidth()/2),(Gdx.graphics.getHeight()/2) - (playStork.getHeight()/2) + 120);
        sb.draw(playBtn,(Gdx.graphics.getWidth()/2) - (playBtn.getWidth()/2),(Gdx.graphics.getHeight()/2) - (playBtn.getHeight()/2) - 150);
        sb.end();
        sb.begin();
        font.draw(sb, "StorkLight", Gdx.graphics.getWidth()/2 - 200, Gdx.graphics.getHeight()/10);
        sb.end();
    }
}
