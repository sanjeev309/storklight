package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.utility.Prefs;


/**
 * Created by sanjeev309 on 3/18/18.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playStork;
    private Texture stop;
    private BitmapFont font;
    private Prefs prefs;

    private static Boolean PAUSED = false;


    public MenuState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport, Stage stage) {
        super(gsm,cam, viewport, stage);


        prefs = new Prefs();
        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"), Gdx.files.internal("fonts/abel.png"), false);
        font.getData().setScale(0.15f);
//        font.getData().setColor(1.0f,1.0f,1.0f,0.0f);
        background = new Texture("artwork/main_landing_screen.png");
        playStork = new Texture("artwork/storkmenu.png");
        stop = new Texture("artwork/stop.png");

    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {
        background.dispose();
        playStork.dispose();
    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched() ) {
            Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            v = cam.unproject(v);
//            Gdx.app.debug("Input: ",v.x + " , " + v.y);

            if(v.y < 20){
                if(v.x > 10 && v.x < 20 ) {

                }

                else if(v.x > 30 && v.x < 40 ){

                }

                else if(v.x > 45 && v.x < 55 ){
                    gsm.set(new PlayState(gsm, cam, viewport, stage));
                    dispose();
                }

                else if(v.x > 65 && v.x < 70 ){
                    prefs.setSound(!prefs.getSound());
                }

                else if(v.x > 80 && v.x < 90 ){

                }
            }
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
        sb.draw(background,0,0, 100,100);
        sb.draw(playStork,10 ,20 ,80,80);
        if( !prefs.getSound()){
         sb.draw(stop,63,4, 12,17);
        }
        font.draw(sb,"Press Play to Start", 30,30);
        sb.end();

    }
}
