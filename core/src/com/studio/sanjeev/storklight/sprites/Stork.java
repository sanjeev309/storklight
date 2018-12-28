package com.studio.sanjeev.storklight.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.studio.sanjeev.storklight.sprites.Animation;
/**
 * Created by sanjeev309 on 3/19/18.
 */

public class Stork {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Animation storkAnimation;
    private Rectangle storkRectangle;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTextureRegion() {
        return storkAnimation.getTextureRegion();
    }

    public Stork(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
//        stork = new Texture("stork.png");
        Texture texture = new Texture("storkanim.png");
        storkRectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
        storkAnimation = new Animation( texture, 6, 0.4f);
        storkRectangle = new Rectangle(x,y,getTextureRegion().getRegionWidth(),getTextureRegion().getRegionHeight());
         }
    public void update(float dt){
        storkAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 0 ){
            velocity.y = 0;
            position.y = 0;
        }
        if(position.y > Gdx.graphics.getHeight() - storkAnimation.getTextureRegion().getRegionHeight()/2){
            position.y = Gdx.graphics.getHeight() - storkAnimation.getTextureRegion().getRegionHeight()/2;
            velocity.y = 0;
        }
        storkRectangle = new Rectangle(position.x ,position.y,storkAnimation.getTextureRegion().getRegionWidth(),storkAnimation.getTextureRegion().getRegionHeight());
    }
    public void fly(){
        velocity.y = velocity.y + 20;
    }

    public Rectangle getBoundingRectangle(){
        return storkRectangle;
    }

}
