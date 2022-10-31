package Entities;

import interfaces.Collidable;
import interfaces.GameObject;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

//place holder class, will be super class for bricks and galaga enemies
public abstract class Obstacle implements GameObject, Collidable
{
	protected ImageView myView;
	
	@Override
	public Node getView () 
	{
		return myView;
	}
	
	@Override
	public abstract void collisionBehavior();

}
