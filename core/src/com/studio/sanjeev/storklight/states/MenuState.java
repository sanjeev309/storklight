package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.StorkLightGameClass;


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

        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"),Gdx.files.internal("fonts/abel.png"),false);
        font.getData().setScale(0.2f);

        background = new Texture("n0.png");

        playStork = new Texture("storkmenu.png");
        playBtn = new Texture("play.png");

        font.getData().setScale(0.3f);
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
        sb.draw(background,0,0, StorkLightGameClass.WIDTH,StorkLightGameClass.HEIGHT);

        sb.draw(playStork,10,10,80,80);
        sb.draw(playBtn,(cam.viewportWidth/2) - (playBtn.getWidth()/2),(cam.viewportHeight/2) - (playBtn.getHeight()/2) - 150);

//        font.draw(sb, "Stork Light", 50, 50);
        sb.end();
    }
}
