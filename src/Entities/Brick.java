package Entities;

import interfaces.Collidable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Brick extends Obstacle
{
	public static final int BOUNCER_SPEED = 200;
	// Need to figure out this class, why different from bouncer, give it better name or just merge it into a different class

	protected ImageView myView;
	private int score = 0;

	
	/**
	 * Create a bouncer from a given image with random attributes.
	 */
	public Brick (Image image, int screenWidth, int screenHeight, int numBrickRows, int numBrickColumns) {
		myView = new ImageView(image);
		// make sure it stays a circle
		int brickWidth = screenWidth/20;
		int brickHeight = screenHeight/19;
		myView.setFitWidth(brickWidth);
		myView.setFitHeight(brickHeight);
		// make sure it stays within the bounds
		myView.setX(screenWidth - brickWidth*(numBrickColumns+1));
		myView.setY(brickHeight*(numBrickRows+1) + brickWidth);
	}
	
	@Override
	public Node getView () {
		return myView;
	}

	
	public void setScore(int rowNumber) {
		score = rowNumber+1;
	}
	
	public int getScore() {
		return score;
	}
	
	public void collisionBehavior()
	{
		//set up some sort of health system here, will help for "special" bricks
	}
	
}
