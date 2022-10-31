package Projectiles;

import interfaces.Collidable;
import interfaces.GameObject;
import interfaces.Updatable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Projectile implements Updatable, Collidable
{
	protected ImageView myView;
	public Point2D myVelocity;

	public Projectile (Image image, int screenWidth, int screenHeight, int projectileSpeed) 
	{
		myView = new ImageView(image);
		// make sure it stays a circle
		int projectileSize = screenWidth/38;
		myView.setFitWidth(projectileSize);
		myView.setFitHeight(projectileSize);
		// make sure it stays within the bounds
		setProjectilePosition(screenWidth, screenHeight);
		// turn speed into velocity that can be updated on bounces
		myVelocity = new Point2D(projectileSpeed, projectileSpeed);
	}
	
	@Override
	public void update(double elapsedTime)
	{
		move(elapsedTime);
		projectileBehavior();
	}
	
	/**
	 * Implement any additional behavior this projectile performs each frame
	 */
	protected abstract void projectileBehavior();
	
	/**
	 * define what this projectile does when it interacts with a collidable object
	 */
	@Override
	public abstract void collisionBehavior(); 
	

	/**
	 * Define what this projectile does when it leaves the game boundaries
	 */
	//public abstract void outOfBoundsBheavior();
	
	protected void move (double elapsedTime) 
	{
		myView.setX(myView.getX() + myVelocity.getX() * elapsedTime);
		myView.setY(myView.getY() + myVelocity.getY() * elapsedTime);
	}
	
	public Node getView () {
		return myView;
	}
	
	/**
	 * Determine the starting position for any new projectile
	 */
	protected abstract void setProjectilePosition(double screenWidth, double screenHeight);
}
