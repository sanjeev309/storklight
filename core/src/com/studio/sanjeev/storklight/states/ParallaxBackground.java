package com.studio.sanjeev.storklight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.studio.sanjeev.storklight.StorkLightGameClass;

/**
 * Created by sanjeev309 on 3/20/18.
 */
public class ParallaxBackground extends Actor {

    private int scroll;
    private Array<Texture> layers;
    private OrthographicCamera cam;
    private final int LAYER_SPEED_DIFFERENCE = 2;

    float x,y,width,heigth,scaleX,scaleY;
    int originX, originY,rotation,srcX,srcY;
    boolean flipX,flipY;

    private int speed;

    public ParallaxBackground(Array<Texture> textures){
        layers = textures;
        layers.get(0).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        for(int i = 1; i <textures.size;i++){
            layers.get(i).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }
        scroll = 0;
        speed = 0;

        x = y = originX = originY = rotation = srcY = 0;
        width = Gdx.graphics.getWidth();
        heigth = Gdx.graphics.getHeight();
        scaleX = scaleY = 1;
        flipX = flipY = false;

        this.cam = cam;
    }

    public void setSpeed(int newSpeed){
        this.speed = newSpeed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        scroll+=speed;
        srcX = scroll/5; //+ i*this.LAYER_SPEED_DIFFERENCE *scroll;
        batch.draw(layers.get(0), x, y, originX, originY, width, heigth,scaleX,scaleY,rotation,srcX,srcY,layers.get(0).getWidth(),layers.get(0).getHeight(),flipX,flipY);
        for(int i = 1;i<layers.size;i++) {
            srcX = scroll + i*this.LAYER_SPEED_DIFFERENCE *scroll;
            batch.draw(layers.get(i), x, y, originX, originY, width, heigth,scaleX,scaleY,rotation,srcX,srcY,layers.get(i).getWidth(),layers.get(i).getHeight(),flipX,flipY);
        }
    }
}
