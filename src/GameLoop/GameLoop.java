package GameLoop;

import java.util.ArrayList;
import java.util.List;

import Entities.Obstacle;
import Entities.Player;
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
	
	public GameLoop() 
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
		checkEntityCollision();
	}
	

	
	protected void checkEntityCollision()
	{
		
		ArrayList<Obstacle> collidedObstacles = new ArrayList<Obstacle>();
		ArrayList<Projectile> collidedProjectiles = new ArrayList<Projectile>();
		ArrayList<Player> collidedPlayers = new ArrayList<Player>();
		
		for (Projectile projectile: levelEntities.getProjectiles())
		{
			for(Obstacle obstacle : levelEntities.getObstacles())
			{
				if(projectile.getView().getBoundsInParent().intersects(obstacle.getView().getBoundsInParent()))
				{
					collidedObstacles.add(obstacle);
					collidedProjectiles.add(projectile);

				}
			}
			for(Projectile otherProjectile : levelEntities.getProjectiles())
			{
				if(projectile != otherProjectile && projectile.getView().getBoundsInParent().intersects(otherProjectile.getView().getBoundsInParent()))
				{
					collidedProjectiles.add(otherProjectile);
				}
			}
			if(projectile.getView().getBoundsInParent().intersects(levelEntities.getPlayer().getView().getBoundsInParent()))
			{
				collidedPlayers.add(levelEntities.getPlayer());
				collidedProjectiles.add(projectile);
			}
			
		}
		

			handleProjectileObstacleCollision(collidedObstacles);
			handleProjectileEntityCollision(collidedProjectiles);
			handleProjectilePlayerCollision(collidedPlayers);
	}
	
	protected void handleProjectileObstacleCollision(ArrayList<Obstacle> collidedObstacles)
	{
		for (Obstacle obstacle : collidedObstacles)
		{
			obstacle.collisionBehavior();
		}
		levelEntities.removeObstacles(collidedObstacles);
	}
	
	/**
	 * Handle what action the projectile performs when hitting an entity
	 * @param collidedProjectiles
	 */
	protected abstract void handleProjectileEntityCollision(ArrayList<Projectile> collidedProjectiles);
		//bounce
		//destroy projectile
	
	/**
	 * Handle what action the player performs when hit by a projectile
	 * @param collidedPlayers
	 */
	protected abstract void handleProjectilePlayerCollision(ArrayList<Player> collidedPlayers);
		//bounce
		//destroy projectile and damage player
	
	
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
