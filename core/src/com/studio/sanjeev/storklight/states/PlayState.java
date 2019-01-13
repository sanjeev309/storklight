package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.studio.sanjeev.storklight.elements.CollectibleOrbs;
import com.studio.sanjeev.storklight.sprites.Stork;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public class PlayState extends State {
    private Stork stork;
    private CollectibleOrbs orbs;
    Array<Texture> textures = new Array<Texture>();
    private BitmapFont font;
    private Texture lifeTex;
    private int score = 0;
    private int lives = 5;

    public PlayState(GameStateManager gsm, OrthographicCamera cam, Viewport viewport,Stage  stage) {
        super(gsm, cam, viewport, stage);

        stork = new Stork(0, 50);
        lifeTex = new Texture(Gdx.files.internal("lifes.png"));
        orbs = new CollectibleOrbs(cam);
        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"), Gdx.files.internal("fonts/abel.png"), false);
        font.getData().setScale(0.15f);
        textures.add(new Texture("n0.png"));
        textures.get(textures.size - 1).setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

        for (int i = 1; i <= 4; i++) {
            textures.add(new Texture(i + ".png"));
            textures.get(textures.size - 1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }
        Gdx.app.debug("Stage:", stage.getActors().size + "");

        if (stage.getActors().size == 0) {
            ParallaxBackground parallaxBackground = new ParallaxBackground(textures, cam);
            parallaxBackground.setSize(cam.viewportWidth, cam.viewportHeight);
            parallaxBackground.setSpeed(1);
            stage.addActor(parallaxBackground);
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            stork.fly();
            }
    }

    @Override
    public void update(float dt) {
        cam.update();
        handleInput();
        stork.update(dt);
        orbs.update(dt);
        CollisionCheckMain();
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
        font.draw(sb," S c o r e : "+ getScore(), 55 , 95);
        sb.end();

        sb.begin();
        for(int i =1; i <= lives; i++){
            sb.draw(lifeTex, 4*i - 4 + 2 , 100 - 10,4,6);

        }
        sb.end();
        }

    @Override
    public void dispose() {
        orbs.dispose();
        font.dispose();
    }

    public void CollisionCheckMain(){
        int temp;
        temp = orbs.checkCollision(stork.getBoundingRectangle());
//        Gdx.app.debug("collision",temp + ":" + stork.getBoundingRectangle());
        if(temp==1){
            score +=temp;
            if (score%20==0){
                lives+=1;
            }
        }
        else
            if(temp == -1){
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

    public int getScore(){
        return score;
    }


}
