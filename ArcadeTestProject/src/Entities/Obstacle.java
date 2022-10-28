package Entities;

import interfaces.GameObject;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

//place holder class, will be super class for bricks and galaga enemies
public class Obstacle implements GameObject
{
	protected ImageView myView;
	
	public Node getView () {
		return myView;
	}
}
