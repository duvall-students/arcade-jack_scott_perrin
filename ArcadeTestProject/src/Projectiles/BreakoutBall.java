package Projectiles;

import Settings.GameRules;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

import Entities.ExampleBounce;
import Entities.PaddleBouncer;
// Possibly make this class into super for entities
public class BreakoutBall extends Projectile
{
	public static final int BALL_SPEED = 200;
	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	//public static final int RANDOM_SPEED_UPPER_RANGE = 5;
	//public static final int RANDOM_SPEED_UPPER_RANGE = 5;


	private Random dice = new Random();
	private PaddleBouncer myPaddle;
	private GameRules gamerule = new GameRules();
	private ExampleBounce example = new ExampleBounce();

	/**
	 * Create a bouncer from a given image with random attributes.
	 */
	public BreakoutBall (Image image, int screenWidth, int screenHeight) 
	{
		super(image, screenWidth, screenHeight, BALL_SPEED);
	}

	/**
	 * Move by taking one step based on its velocity.
	 *
	 * Note, elapsedTime is used to ensure consistent speed across different machines.
	 */


	/**
	 * Bounce off the walls represented by the edges of the screen.
	 */
	public void bounce (double screenWidth, double screenHeight) {
		// collide all bouncers against the walls
		if (myView.getX() < 0 || myView.getX() > screenWidth - myView.getBoundsInLocal().getWidth()) 
		{
			myVelocity = new Point2D(-myVelocity.getX(), myVelocity.getY()+getRandomInRange (-20, 20));
		}
		else if (myView.getY() < screenHeight/10) 
		{
			myVelocity = new Point2D(myVelocity.getX()+getRandomInRange (-20, 20), -myVelocity.getY());
		}
	}
	
	public void bounceBrick () {
			myVelocity = new Point2D(myVelocity.getX()+getRandomInRange (-20, 20), -myVelocity.getY());
	}
	
	public void bouncePaddle () {
		myVelocity = new Point2D(myVelocity.getX()+getRandomInRange (-20, 20), -myVelocity.getY());
		myView.setY(myView.getY()-10);
	}
	
	/*
	 * redundant 
	public ImageView getImageView () {
		return myView;
	}
	*/
	
	
	public Node getView () {
		return myView;
	}
	
	@Override
	public void setProjectilePosition(double screenWidth, double screenHeight) {
		myView.setX(screenWidth/2);
		myView.setY((8*screenHeight)/10);
	}
	
	private int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }
	
	
}
