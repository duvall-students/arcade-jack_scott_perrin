package Settings;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class GameRules {

	private int playerlives = 3;
	
	public static int height = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight() * 0.7);
	public static int width = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight() * 0.7);
	
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
