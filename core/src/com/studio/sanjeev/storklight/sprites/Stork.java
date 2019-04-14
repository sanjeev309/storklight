package com.studio.sanjeev.storklight.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
/**
 * Created by sanjeev309 on 3/19/18.
 */

public class Stork {
    private static final int GRAVITY = -5;
    private static final int MOVEMENT = 0;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int STORK_SPEED = 8;


    private Boolean AudioStarted = false;
    private Vector3 position;
    private Vector3 velocity;
    private Animation storkAnimation;
    private Rectangle storkRectangle;
    private Sound wings;
    private Sound orb_sound;
    private Sound dark_sound;
    private long wings_audio_id;
    private long orb_audio_id;
    private long dark_audio_id;
    private AssetManager assetManager;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTextureRegion() {
        return storkAnimation.getTextureRegion();
    }

    public Stork(int x, int y) {

        assetManager = new AssetManager();
        assetManager.load("audio/wings.wav", Sound.class);
        assetManager.load("audio/orb.wav",Sound.class);
        assetManager.load("audio/dark.wav",Sound.class);
        assetManager.finishLoading();

        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        Texture texture = new Texture("artwork/storkanim.png");
        storkAnimation = new Animation(texture, 6, 0.3f);
        storkRectangle = new Rectangle(x, y, WIDTH, HEIGHT);

        if (assetManager.isLoaded("audio/orb.wav")) {
            orb_sound = assetManager.get("audio/orb.wav", Sound.class);
        }

        if (assetManager.isLoaded("audio/dark.wav")) {
            dark_sound = assetManager.get("audio/dark.wav", Sound.class);
        }
    }

    public void update(float dt){

        if (assetManager.isLoaded("audio/wings.wav") && ! AudioStarted) {

            wings = assetManager.get("audio/wings.wav", Sound.class);
            wings_audio_id = wings.loop(1.0f);
            wings.setPan(wings_audio_id,-0.3f,0.3f);
            wings.setPitch(wings_audio_id,1.2f);
            AudioStarted = true;
        }

        storkAnimation.update(dt);

        if (position.y > 0)
            velocity.add(0,GRAVITY,0);
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y,0);
            velocity.scl(1/dt);

        if(position.y < 0 ){
            velocity.y = 0;
            position.y = 0;
        }

        if(position.y > 92 ){
            position.y = 92 ;
            velocity.y = 0;
        }

        storkRectangle = new Rectangle(position.x ,position.y,WIDTH,HEIGHT);
    }

    public void fly(){
        velocity.y = velocity.y + STORK_SPEED;
        wings.setPitch(wings_audio_id,1.4f);
    }

    public void descend(){
        if (wings !=null){
            wings.setPitch(wings_audio_id,1.2f);
        }
    }

    public Rectangle getBoundingRectangle(){
        return storkRectangle;
    }


    public void reward(){
        if (assetManager.isLoaded("audio/orb.wav")){
            orb_sound.play(0.2f,0.8f,-0.4f);
        }
    }

    public void punish(){
        if (assetManager.isLoaded("audio/dark.wav")){
            dark_sound.play(0.2f,1.0f,-0.4f);
        }
    }
    public void dispose(){
        wings.stop();
        wings.dispose();
        orb_sound.stop();
        orb_sound.dispose();
        dark_sound.stop();
        dark_sound.dispose();
    }

}

