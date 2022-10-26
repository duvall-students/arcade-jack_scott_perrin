package Projectiles;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Projectile 
{
	protected ImageView myView;
	protected Point2D myVelocity;

	public Projectile (Image image, int screenWidth, int screenHeight, int projectileSpeed) 
	{
		myView = new ImageView(image);
		// make sure it stays a circle
		int ballSize = screenWidth/38;
		myView.setFitWidth(ballSize);
		myView.setFitHeight(ballSize);
		// make sure it stays within the bounds
		setProjectilePosition(screenWidth, screenHeight);
		// turn speed into velocity that can be updated on bounces
		myVelocity = new Point2D(projectileSpeed, projectileSpeed);
	}
	
	public void move (double elapsedTime) 
	{
		myView.setX(myView.getX() + myVelocity.getX() * elapsedTime);
		myView.setY(myView.getY() + myVelocity.getY() * elapsedTime);
	}
	
	protected abstract void setProjectilePosition(double screenWidth, double screenHeight);
}
