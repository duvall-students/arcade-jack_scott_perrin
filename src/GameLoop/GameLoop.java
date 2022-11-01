package GameLoop;

import java.util.ArrayList;
import java.util.List;

import Entities.Obstacle;
import Map.Level;
import Projectiles.Projectile;
import gameController.EntityCollection;
import interfaces.Collidable;
import interfaces.GameObject;
import interfaces.Updatable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.util.Duration;

public abstract class GameLoop 
{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	Timeline animation = new Timeline();
	protected double elapsedTime;
	protected EntityCollection levelEntities;
	
	public GameLoop(Level gameLevel) 
	{
		
	}

	
	public void runLoop(double elapsedTime, EntityCollection levelEntities)
	{
		this.elapsedTime = elapsedTime;
		this.levelEntities = levelEntities;
		//checkWinConditions
		//
		checkCollision();
		iterateUpdatables();

		
		//if player health = 0 throw end game event
	}
	
	//check out of bounds projectiles
	
	protected void iterateUpdatables()
	{
		for (Updatable entity : levelEntities.getUpdatableEntities())
		{
			entity.update(elapsedTime);
		}
	}
	
	/**
	 * Level specific functionality for check which entities are colliding
	 */
	protected void checkCollision() 
	{
		checkCollisionAgainstBricks();
		playerOnlyCollision();
	}
	
	protected void checkCollisionAgainstObstacles()
	{
		//List<Collidable>
		
		//include projectiles in collidable
		//if projectile collides with collidable that is not itself
		
			//projectile.collisionBehavior
				//ball just bounces
				//bullet gets destroyed
			
			//collidable.collisionbehavior
				//breakout player plays noise
				//galaga player loses health
		//trigger collision event
		//
		for (Projectile projectile: levelEntities.getProjectiles())
		{
			for(Collidable collidableEntity : levelEntities.getCollidableEntities())
			{
				if(projectile.getView().getBoundsInParent().intersects(collidableEntity.getView().getBoundsInParent()))
				{
					projectile.collisionBehavior();
					collidableEntity.collisionBehavior();
					//if health is now 0 remove from list, triggering change listener
					
					

					projectile.update(elapsedTime);
					projectile.update(elapsedTime);
					projectile.update(elapsedTime);
				}
			}
		}
	}
	
	protected void checkCollisionAgainstBricks()
	{
	
		for (Projectile projectile: levelEntities.getProjectiles())
		{
			for(Obstacle obstacle : levelEntities.getObstacles())
			{
				Bounds brickview = obstacle.getView().getBoundsInParent();
				Bounds projectileview = projectile.getView().getBoundsInParent();
				
				if(projectile.getView().getBoundsInParent().intersects(obstacle.getView().getBoundsInParent()))
				{
					
					projectile.collisionBehavior();

					//Temporary fix since bricks arent being removed
					projectile.update(elapsedTime);
					projectile.update(elapsedTime);
					projectile.update(elapsedTime);


				}
			}
		}

	}
	/**
	 * TESTING PURPOSES ONLY
	 */
	protected void playerOnlyCollision()
	{
		for (Projectile projectile: levelEntities.getProjectiles())
		{
			if(projectile.getView().getBoundsInParent().intersects(levelEntities.getPlayer().getView().getBoundsInParent()))
			{
				projectile.collisionBehavior();
				levelEntities.getPlayer().collisionBehavior();
			}
		}
	}


}
