package com.studio.sanjeev.storklight.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

/**
 * Created by sanjeev309 on 3/18/18.
 */

public class PlayState extends State  {
    private com.studio.sanjeev.storklight.sprites.Stork stork;
    private Stage stage;
    private com.studio.sanjeev.storklight.elements.CollectibleOrbs orbs;
    Array<Texture> textures = new Array<Texture>();
    private Rectangle rect;
    private ShapeRenderer shapeRenderer;
    private World world;
    private BitmapFont font;
    private Texture lifeTex;
    private int score = 0;
    private int lives = 5;
    //    private RayHandler rayHandler;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        stork = new com.studio.sanjeev.storklight.sprites.Stork(0, com.studio.sanjeev.storklight.StorkLightGameClass.HEIGHT/2);
        stage = new Stage();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        lifeTex = new Texture(Gdx.files.internal("lifes.png"));

        orbs = new com.studio.sanjeev.storklight.elements.CollectibleOrbs(cam);
        cam.setToOrtho(false,Gdx.graphics.getWidth() ,Gdx.graphics.getHeight());
        font = new BitmapFont(Gdx.files.internal("fonts/abel.fnt"),Gdx.files.internal("fonts/abel.png"),false);

        world = new World(new Vector2(0,0),true);
//        rayHandler = new RayHandler(world);
//        rayHandler.setCombinedMatrix(cam.combined);
//        rayHandler.setShadows(true);

//        new PointLight(rayHandler,1000,new Color(1,1,1,1),10000,StorkLightGameClass.WIDTH/2,StorkLightGameClass.HEIGHT/2);

        textures.add(new Texture("n0.png"));
        textures.get(textures.size - 1).setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

        for(int i = 1; i <=4;i++) {
            textures.add(new Texture(i + ".png"));
            textures.get(textures.size - 1).setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        }

        ParallaxBackground parallaxBackground = new ParallaxBackground(textures);
        parallaxBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        parallaxBackground.setSpeed(1);
        stage.addActor(parallaxBackground);
        stage.getViewport().update(com.studio.sanjeev.storklight.StorkLightGameClass.WIDTH, com.studio.sanjeev.storklight.StorkLightGameClass.HEIGHT, true);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            stork.fly();
            }
    }

    @Override
    public void update(float dt) {
        handleInput();
        stork.update(dt);
        cam.update();
        orbs.update(dt);
        CollisionCheckMain();
        checkKillCondition();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.act();
        stage.draw();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        stage.getViewport().update( Gdx.graphics.getWidth() ,Gdx.graphics.getHeight());
        sb.draw(stork.getTextureRegion(),stork.getPosition().x,stork.getPosition().y);
        orbs.render(sb);
        sb.end();
        sb.begin();
        font.draw(sb,"Score : "+ getScore(), com.studio.sanjeev.storklight.StorkLightGameClass.WIDTH - 300, com.studio.sanjeev.storklight.StorkLightGameClass.HEIGHT - 100);
        sb.end();
        sb.begin();
        for(int i =1; i <= lives; i++){
            sb.draw(lifeTex, lifeTex.getWidth()*i - lifeTex.getWidth() + 40 , com.studio.sanjeev.storklight.StorkLightGameClass.HEIGHT - lifeTex.getHeight()/2 - 100,lifeTex.getWidth(),lifeTex.getHeight());

        }
        sb.end();
        //        rayHandler.updateAndRender();
        //        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    @Override
    public void dispose() {
        stage.dispose();
        orbs.dispose();
        font.dispose();

    }

    public void CollisionCheckMain(){
        int temp;
        temp = orbs.checkCollision(stork.getBoundingRectangle());
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

//        Gdx.app.debug("LIves : ",lives + "###########");
    }

    public void checkKillCondition(){
        if (lives == 0){
            //Killed, Restart
            gsm.set(new MenuState(gsm));
            dispose();
        }
    }

    public int getScore(){
        return score;
    }
}