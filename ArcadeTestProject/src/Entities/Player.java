package Entities;

import interfaces.Collidable;
import interfaces.GameObject;

public abstract class Player implements Collidable, GameObject
{
	//TODO: Convert this into player superclass
	public abstract void collisionBehavior();
}
