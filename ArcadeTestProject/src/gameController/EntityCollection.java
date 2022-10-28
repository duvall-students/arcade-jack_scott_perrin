package gameController;

import java.util.ArrayList;
import java.util.List;

import Entities.Obstacle;
import Entities.Player;
import Projectiles.Projectile;
import interfaces.Collidable;
import interfaces.Updatable;

public class EntityCollection 
{
	//collidable will likely be removed at a future point
	private List<Collidable> collidableEntities = new ArrayList<Collidable>();
	
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Updatable> updatableEntities = new ArrayList<Updatable>();
	private List<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Player player;
	
	public EntityCollection() {}
	
	
	public void removeCollidable(Collidable object)
	{
		if(player != object)
		{
			for (Obstacle obstacle : obstacles)
			{
				if (obstacle == object)
				{
					obstacles.remove(object);
				}
			}
		}
		collidableEntities.remove(object);
	}
	
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void addObstacle(Obstacle obstacle) {
		this.obstacles.add(obstacle);
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void addProjectile(Projectile projectiles) {
		this.projectiles.add(projectiles);
	}

	public List<Updatable> getUpdatableEntities() {
		return updatableEntities;
	}

	public void addUpdatableEntity(Updatable updatableEntity) {
		this.updatableEntities.add(updatableEntity);
	}

	public List<Collidable> getCollidableEntities() {
		return collidableEntities;
	}

	public void addCollidableEntity(Collidable collidableEntity) {
		this.collidableEntities.add(collidableEntity);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	
	
}
