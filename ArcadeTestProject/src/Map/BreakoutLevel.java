package Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.Brick;
import Entities.Obstacle;
import Entities.PaddleBouncer;
import Projectiles.BreakoutBall;
import Projectiles.Projectile;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

public class BreakoutLevel extends Level
{


	public static final String BOUNCER_IMAGE = "resources/ball.gif";
	public static final String POINTS_IMAGE = "resources/pointspower.gif";

	public static final String BRICK_IMAGE_1 = "resources/brick1.gif";
	public static final String BRICK_IMAGE_2 = "resources/brick2.gif";
	public static final String BRICK_IMAGE_3 = "resources/brick3.gif";
	public static final String BRICK_IMAGE_4 = "resources/brick4.gif";
	public static final String BRICK_IMAGE_5 = "resources/brick5.gif";
	public static final String BRICK_IMAGE_6 = "resources/brick6.gif";
	public static final String BRICK_IMAGE_7 = "resources/brick7.gif";
	public static final String BRICK_IMAGE_8 = "resources/brick8.gif";
	public static final String BRICK_IMAGE_9 = "resources/brick9.gif";
	public static final String BRICK_IMAGE_10 = "resources/brick10.gif";
	
	public static final int NUM_BRICKS_COLUMNS = 20;
	public static final int NUM_BRICKS_ROWS = 5;
	public static final int NUM_BRICKS = NUM_BRICKS_COLUMNS * NUM_BRICKS_ROWS;

	
	private static final String TITLE = "Breakout";
	
	ArrayList<Integer> doubleBricks;
	private List<Brick> myBricks;
	private PaddleBouncer myPaddle;
	
	public BreakoutLevel() 
	{
		super(TITLE);
;
	}
	
	// Bricks Class
	// generating integer
	public int setRandomBrick() {
		Random ran = new Random();
		return ran.nextInt(NUM_BRICKS);
	}
	
	//functionality unique to breakout level type
	@Override
	protected void generateLevel(int width, int height, Paint background)
	{
		

		createProjectile(width, height);
		
		createBricks(width, height);
	}
	
	@Override
	protected void createProjectile(int width, int height) 
	{
		try {
			Image ballImage = new Image(new FileInputStream(BOUNCER_IMAGE));
			Projectile b = new BreakoutBall(ballImage, width, height);
			levelEntities.addProjectile(b);
			levelEntities.addUpdatableEntity(b);
			root.getChildren().add(b.getView());
		}
		catch (FileNotFoundException e) {
		}
	}
	
	//projectile destroyed event here will
	@Override
	protected void createPlayer(int width, int height)
	{
		try {
			Image paddleImage = new Image(new FileInputStream(PADDLE_IMAGE));
			PaddleBouncer p = new PaddleBouncer(paddleImage, width, height);
			myPaddle = p;
			levelEntities.setPlayer(p);
			levelEntities.addCollidableEntity(p);
			root.getChildren().add(p.getView());
		}
		catch (FileNotFoundException e) {
		}   
	}
	
	// What to do each time a key is pressed
	@Override
	protected void handleKeyInput (KeyCode code) {
		double paddleSpeed = screenWidth/20;
		if (code == KeyCode.RIGHT && myPaddle.getXPosition() < (19*screenWidth)/20) {
			myPaddle.getImageView().setX(myPaddle.getXPosition() + paddleSpeed);
		}
		else if (code == KeyCode.LEFT && myPaddle.getXPosition() > 0) {
			myPaddle.getImageView().setX(myPaddle.getXPosition() - paddleSpeed);
		}
	}
	
	// Sub class of general entities, Brick Class
	private void createBricks(int width, int height) 
	{
		myBricks = new ArrayList<>();
		try 
		{
			for (int i = 0; i < NUM_BRICKS_ROWS; i++) 
			{
				for (int k = 0; k < NUM_BRICKS_COLUMNS; k++) 
				{
					Image image2 = new Image(new FileInputStream("resources/brick" + Integer.toString(i+1) + ".gif"));
					Brick br = new Brick(image2, width, height, i, k);
					levelEntities.addObstacle(br);
					levelEntities.addCollidableEntity(br);
					br.setScore(9-i);
					root.getChildren().add(br.getView());
				}
			}
			System.out.println("Finished Run");
		}
		catch (FileNotFoundException e) {
		}
	}
	
	public void removeBricksView (ArrayList<Brick> destroyBricks) {
		if (destroyBricks.size() > 0) 
		{
			root.getChildren().remove((destroyBricks.get(destroyBricks.size()-1)).getView());
			myBricks.remove(destroyBricks.get(destroyBricks.size()-1)); 
		}
	}
}
