package Entities;

import interfaces.Collidable;
import interfaces.GameObject;

public abstract class Player implements Collidable
{
	private int health = 3;
	//TODO: Convert this into player superclass
	public abstract void collisionBehavior();
	
	public void damagePlayer()
	{
		health -= health;
	}
	
	public int getHealth()
	{
		return health;
	}
	
}
