package gameController;

import java.util.ArrayList;
import java.util.List;

import Entities.Obstacle;
import Entities.Player;
import Projectiles.Projectile;
import interfaces.Collidable;
import interfaces.Updatable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EntityCollection 
{
	//collidable will likely be removed at a future point
	private ObservableList<Collidable> collidableEntities = FXCollections.observableArrayList();
	
	private ObservableList<Projectile> projectiles = FXCollections.observableArrayList();
	private List<Updatable> updatableEntities = new ArrayList<Updatable>();
	private ObservableList<Obstacle> obstacles = FXCollections.observableArrayList();
	private Player player;
	
	public EntityCollection() {}
	
	
	public void removeCollidable(ArrayList<Collidable> collidedEntities)
	{
		/*
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
		*/
		for (Collidable collidedEntity : collidedEntities)
		{
			collidableEntities.remove(collidedEntity);
		}
	}
	
	
	public ObservableList<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public void removeObstacles(ArrayList<Obstacle> collidedObstacles)
	{
		for (Obstacle collidedObstacle : collidedObstacles)
		{
			obstacles.remove(collidedObstacle);
		}
	}
	
	public void removeProjectiles(ArrayList<Projectile> collidedProjectiles)
	{
		for (Projectile collidedProjectile : collidedProjectiles)
		{
			projectiles.remove(collidedProjectile);
		}
	}
	
	public void addObstacle(Obstacle obstacle) {
		this.obstacles.add(obstacle);
	}

	public ObservableList<Projectile> getProjectiles() {
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

	public ObservableList<Collidable> getCollidableEntities() {
		return collidableEntities;
	}
	
	
	public void addCollidableEntity(Collidable collidableEntity) {
		this.collidableEntities.add(collidableEntity);
		/*;
		this.collidableEntities.addAll(obstacles);
		this.collidableEntities.addAll(projectiles);
		this.collidableEntities.add(player);
		*/
	}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	
	
}
