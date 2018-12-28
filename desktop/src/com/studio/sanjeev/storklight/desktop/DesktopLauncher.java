package com.studio.sanjeev.storklight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.studio.sanjeev.storklight.StorkLightGameClass;
import com.studios.storklight.storklight.StorkLightGameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = StorkLightGameClass.WIDTH;
		config.height = StorkLightGameClass.HEIGHT;
		config.title = StorkLightGameClass.TITLE;
		new LwjglApplication(new StorkLightGameClass(), config);
	}
}
