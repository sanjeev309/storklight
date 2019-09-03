package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InfoState extends State {
    private Texture background;
    private Texture playStork;


    private BitmapFont font;
    static int scrollY = 0;
    static Boolean interpolator = false;

    public InfoState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport, Stage stage) {
        super(gsm,cam, viewport, stage);

        playStork = new Texture("artwork/storkmenu.png");

        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"), Gdx.files.internal("fonts/abel.png"), false);
        font.getData().setScale(0.2f);
        font.setColor(Color.BLACK);
        font.setUseIntegerPositions(false);
//        font.getData().setColor(1.0f,1.0f,1.0f,0.0f);
        background = new Texture("artwork/info_screen.png");

    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() ) {
            gsm.set(new MenuState(gsm, cam, viewport, stage));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        interpolator=!interpolator;

        if(interpolator)
            scrollY += 1;

        if(scrollY >= 220){
            scrollY = -85;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        sb.draw(background,0,0, 100,100);

//        font.draw(sb,"|", 50,scrollY);
//        font.draw(sb,"|", 50,scrollY - 10);
//        font.draw(sb,"|", 50,scrollY - 20);
//        font.draw(sb,"|", 50,scrollY - 30);
//        font.draw(sb,"|", 50,scrollY - 40);
//        font.draw(sb,"|", 50,scrollY - 50);
//        font.draw(sb,"|", 50,scrollY - 60);
//        font.draw(sb,"|", 50,scrollY - 70);

        sb.draw(playStork,30 ,scrollY ,40,40);

        font.draw(sb,"STORK LIGHT", 32,scrollY - 20);
        font.draw(sb,"Open-Source Side Scroller", 16,scrollY - 30);
        font.draw(sb,"by", 47,scrollY - 40);
        font.draw(sb,"CliqueRaft Studio", 27,scrollY - 50);

        font.draw(sb,"Source Code Available on Github", 10,scrollY - 70);

        font.draw(sb,"Please donate to support us!", 12,scrollY - 90);

        font.draw(sb,"Thank You!", 35,scrollY - 110);

        sb.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        playStork.dispose();
    }
}
