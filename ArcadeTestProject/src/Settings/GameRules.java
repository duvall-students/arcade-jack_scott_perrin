package Settings;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class GameRules {

	private int playerlives = 3;
	
	public static int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight();
	public static int width = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight();
	
	public void setScreenSize(GraphicsDevice gd[]) 
	{
		width = gd[0].getDisplayMode().getHeight();
	}
	
	public void setScreenHeight(GraphicsDevice gd[]) 
	{
		height = gd[0].getDisplayMode().getHeight();
	}
	
	public int getScreenWidth() {
		return width;
	}
	
	public int getScreenHeight() {
		return height;
	}
	
	public int getPlayerlives() {
		return playerlives;
	}
	
	public void setPlayerlives(int newplayerlives) {
		playerlives = newplayerlives;
	}
}
