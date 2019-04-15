package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.elements.CollectibleOrbs;
import com.studio.sanjeev.storklight.sprites.Stork;
import com.studio.sanjeev.storklight.utility.Prefs;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public class PlayState extends State {
    private Stork stork;
    private CollectibleOrbs orbs;
    Array<Texture> textures = new Array<Texture>();
    private BitmapFont font;
    private Texture lifeTex, pauseTex;
    private static int score = 0;
    private static int lives = 5;
    private long startTime;
    public Prefs prefs;

    private static boolean PAUSED = false;

    public PlayState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport,Stage  stage) {
        super(gsm, cam, viewport, stage);

        prefs = new Prefs();
        stork = new Stork(0, 50);
        lifeTex = new Texture(Gdx.files.internal("artwork/lifes.png"));
        pauseTex = new Texture(Gdx.files.internal("artwork/pause.png"));
        orbs = new CollectibleOrbs(cam);
        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"), Gdx.files.internal("fonts/abel.png"), false);
        font.getData().setScale(0.15f);
        textures.add(new Texture("artwork/night_bg.png"));
        textures.get(textures.size - 1).setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

        for (int i = 1; i <= 4; i++) {
            textures.add(new Texture("artwork/"+i + ".png"));
            textures.get(textures.size - 1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }
//        Gdx.app.debug("Stage:", stage.getActors().size + "");

        if (stage.getActors().size == 0) {
            ParallaxBackground parallaxBackground = new ParallaxBackground(textures, cam);
            parallaxBackground.setSize(cam.viewportWidth, cam.viewportHeight);
            parallaxBackground.setSpeed(1);
            stage.addActor(parallaxBackground);
        }

        startTime = System.currentTimeMillis();
    }

    @Override
    protected void handleInput() {


        if(Gdx.input.isTouched() ) {

            Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            v = cam.unproject(v);

            if (!PAUSED) {
                stork.fly();
            }

            else {
                PAUSED = false;
            }

            if (v.y > 90 && v.x > 90) {
                PAUSED = true;
            }
        }


        else
        {
            stork.descend();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        if (!PAUSED) {
            cam.update();
            stork.update(dt);
            orbs.update(dt);
            CollisionCheckMain();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        stage.draw();

        sb.begin();
        sb.draw(stork.getTextureRegion(),stork.getPosition().x,stork.getPosition().y,10,10);
        orbs.render(sb);
        sb.end();

        sb.begin();
        font.draw(sb,"Score: "+ getScore(), 40 , 95);
        sb.end();

        sb.begin();
        for(int i =1; i <= lives; i++){
            sb.draw(lifeTex, 4*i - 4 + 2 , 100 - 10,4,6);

        }

        sb.draw(pauseTex,92,90,5,5);

        sb.end();

        if (PAUSED){

            sb.begin();
            font.draw(sb,"Touch Anywhere to Continue", 30,50);
            sb.end();
        }
        else{
            sb.begin();
            font.draw(sb," | Time: "+ getPlayTime(), 60 , 95);
            sb.end();
        }
    }

    @Override
    public void pause() {
        PAUSED = true;

    }

    @Override
    public void dispose() {
        orbs.dispose();
        font.dispose();
        stork.dispose();

    }

    public void CollisionCheckMain(){
        int temp;
        temp = orbs.checkCollision(stork.getBoundingRectangle());

        if(temp==1){
            stork.reward();
            score +=temp;
            if (score%20==0 && lives < 9){
                lives+=1;
            }
        }
        else
            if(temp == -1){
                stork.punish();
                lives -=1;
        }
        checkKillCondition();
    }

    public void checkKillCondition(){
        if (lives == 0){
            //Killed, Restart
            gsm.set(new MenuState(gsm, cam, viewport,stage));
            dispose();
        }
    }

    private int getScore(){
        return score;
    }

    private String getPlayTime(){
        long minutes=0, seconds=0, units;
        String timeString;
        units = (System.currentTimeMillis() - startTime)/1000 ;

//        hours = units / 3600;
        minutes = (units % 3600) / 60;
        seconds = units % 60;

        timeString = String.format("%02d:%02d", minutes, seconds);
        return timeString;
    }


}
