package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.studio.sanjeev.storklight.StorkLightGameClass;
//import com.studio.sanjeev.storklight.states.GameStateManager;
//import com.studio.sanjeev.storklight.states.PlayState;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playStork;
    private Texture playBtn;
    private BitmapFont font;
    private Skin skin;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false,Gdx.graphics.getWidth() ,Gdx.graphics.getHeight());
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
        sb.draw(background,0,0, StorkLightGameClass.WIDTH,StorkLightGameClass.HEIGHT);

//      sb.draw(stork,(StorkLightGameClass.WIDTH/2) - (playBtn.getWidth()/2),StorkLightGameClass.HEIGHT/2- (playBtn.getHeight()/2));
        sb.draw(playStork,(StorkLightGameClass.WIDTH/2) - (playStork.getWidth()/2),(StorkLightGameClass.HEIGHT/2) - (playStork.getHeight()/2) + 120);
        sb.draw(playBtn,(StorkLightGameClass.WIDTH/2) - (playBtn.getWidth()/2),(StorkLightGameClass.HEIGHT/2) - (playBtn.getHeight()/2) - 150);
        sb.end();
        sb.begin();
        font.draw(sb, "StorkLight", StorkLightGameClass.WIDTH/2 -200, 100);
        sb.end();
    }
}
