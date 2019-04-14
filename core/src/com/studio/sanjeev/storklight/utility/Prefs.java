package com.studio.sanjeev.storklight.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {

    private Preferences pref ;
    private boolean hasSound;

    public Prefs(){
        pref = Gdx.app.getPreferences("Storklight");
        hasSound = pref.getBoolean("hasSound",true);
    }

    public void setSound(boolean hasSound){
        this.hasSound=hasSound;
        pref.putBoolean("hasSound",hasSound);
        pref.flush();
    }

    public boolean getSound(){
        return hasSound;
    }


}