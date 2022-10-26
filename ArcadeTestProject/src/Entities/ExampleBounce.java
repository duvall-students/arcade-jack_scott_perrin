package Entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Map.BreakoutLevel;
import Map.Level;
import Projectiles.BreakoutBall;
import Settings.GameRules;
import Settings.HighScore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

// Name: Christopher Boyette

/**
 * A basic example JavaFX program for the first lab.
 * 
 * @author Shannon Duvall
 * 
 * This program animates two squares.  The top is the "mover" and 
 * the bottom is the "grower".
 * 
 * Changes for you to make in the code:
 * 
 * 1. Currently the mover is rotating counter-clockwise.  Add code so the grower
 * 	  rotates in the opposite direction (clockwise).
 * 2. If the mover and grower ever intersect, the mover should change color to be HIGHLIGHT,
 *    and change back to the MOVER_COLOR if not intersecting.
 * 3. If any of the bouncing blue balls intersect the grower, the grower should change color
 *    to be HIGHLIGHT, and change back when not intersecting.
 * 4. The mover can currently only move up and down.  Enable left and right movement with arrow keys.
 * 5. The grower currently only grows horizontally, changing the square to a rectangle.  Change
 *    this to grow in both directions equally so the square shape is preserved.
 */
public class ExampleBounce extends Application {
	public static final String TITLE = "Breakout";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
	public static final String BOUNCER_IMAGE = "resources/ball.gif";
	public static final String POINTS_IMAGE = "resources/pointspower.gif";
	public static final int NUM_BOUNCERS = 1;
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
	public static final int NUM_PADDLES = 1;
	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final String PONG_SOUND = "resources/pong_beep.wav";
	public Media pongSound = new Media(Paths.get(PONG_SOUND).toUri().toString());
	public MediaPlayer sound  = new MediaPlayer(pongSound);
	public int screenHeight;
	public int screenWidth;

	// some things we need to remember during our game
	private Scene myScene;
	private BreakoutBall myBall;
	private PaddleBouncer myPaddle;
	private List<Bouncerw> myBricks;
	Group root = new Group();
	Text textScore = new Text();
	Text textTitle = new Text();
	Text textlives = new Text();
	Text textHighScore = new Text();
	Text textGameOver = new Text();
	Text textGameWin = new Text();
	private int totalScore = 0;
	GameRules gamerule = new GameRules();
	HighScore highscore = new HighScore();
	Timeline animation = new Timeline();
	ArrayList<Integer> doubleBricks;

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {

		
		setScreenSize();
		myScene = setupGame(screenWidth, screenHeight, BACKGROUND);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		
		
		
		 // MY CODE:
		/*
		BreakoutLevel testLevel = new BreakoutLevel();
		testLevel.setupGame();
		stage.setScene(testLevel.getScene());
		stage.setTitle(testLevel.getTitle());
		stage.show();
		*/
		
		// attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}

	// Create the game's "scene": what shapes will be in the game and their starting properties
	private Scene setupGame (int width, int height, Paint background) {
		// create one top level collection to organize the things in the scene
		// make some shapes and set their properties
		int doubleBrick = setRandomBrick();
		createCurrentScoreDisplay();
		createHighScoreDisplay();
		createGameTitle();
		createlivesDisplay();
		createPlayer(width, height);
		createBall(width, height);
		createBricks(width, height, doubleBrick);
		Scene scene = new Scene(root, width, height, background);
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		return scene;
	}

	// Change properties of shapes in small ways to animate them over time
	private void step (double elapsedTime) {
		myBall.move(elapsedTime);
		playerMinuslive();
		myBall.bounce(myScene.getWidth(), myScene.getHeight());
		playerHitsPaddle();
		ArrayList<Bouncerw> destroyBricks = new ArrayList<Bouncerw>();
		destroyBricks = playerHitsBrick(destroyBricks);
		removeBricksView (destroyBricks, doubleBricks);
		textScore.setText("Score: " + totalScore);
		checkPlayerLives();
		checkAllBricksGone();
	}

	//Spencer Buehlman
	private void checkAllBricksGone() {
		if(myBricks.size() == 0) {
			winGameSteps();
		}
	}
	//Spencer Buehlman
	private void checkPlayerLives() {
		if (gamerule.getPlayerlives() == 0) {
			endGameSteps();
		}

	}
	
	private void endGameSteps() {
		if (totalScore > highscore.getCurrentHighScore()) {
			highscore.setNewHighScore(totalScore);
		}
		root.getChildren().remove(myBall.getView());
		root.getChildren().remove(myPaddle.getView());
		animation.stop();
		createEndGameDisplay();
	}
	
	private void winGameSteps() {
		if (totalScore > highscore.getCurrentHighScore()) {
			highscore.setNewHighScore(totalScore);
		}
		root.getChildren().remove(myBall.getView());
		root.getChildren().remove(myPaddle.getView());
		animation.stop();
		createWinGameDisplay();
	}

	// What to do each time a key is pressed
	private void handleKeyInput (KeyCode code) {
		double paddleSpeed = screenWidth/20;
		if (code == KeyCode.RIGHT && myPaddle.getXPosition() < (19*screenWidth)/20) {
			myPaddle.getImageView().setX(myPaddle.getXPosition() + paddleSpeed);
		}
		else if (code == KeyCode.LEFT && myPaddle.getXPosition() > 0) {
			myPaddle.getImageView().setX(myPaddle.getXPosition() - paddleSpeed);
		}
	}
	
	//Display Class
	private void createGameTitle() {
		textTitle.setFont(new Font("Arial", 75));
		textTitle.setX(0.4*screenWidth);
		textTitle.setY(screenHeight/15);
		textTitle.setText("BREAKOUT");
		textTitle.setStroke(Color.WHITE);
		root.getChildren().add(textTitle);
	}
	//Display Class
	private void createCurrentScoreDisplay() {
		textScore.setFont(new Font("Arial", 20));
		textScore.setX((15.5*screenWidth)/20);
		textScore.setY(screenHeight/20);
		textScore.setText("Score: 0");
		textScore.setStroke(Color.WHITE);
		root.getChildren().add(textScore);
	}
	//Display Class
	private void createHighScoreDisplay() {
		textHighScore.setFont(new Font("Arial", 20));
		textHighScore.setX((17.5*screenWidth)/20);
		textHighScore.setY(screenHeight/20);
		textHighScore.setText("High Score: " + highscore.getCurrentHighScore());
		textHighScore.setStroke(Color.WHITE);
		root.getChildren().add(textHighScore);
	}
	//Display Class
	private void createlivesDisplay() {
		textlives.setFont(new Font("Arial", 20));
		textlives.setX(screenWidth/20);
		textlives.setY(screenHeight/20);
		textlives.setText("Player lives: " + gamerule.getPlayerlives());
		textlives.setStroke(Color.WHITE);
		root.getChildren().add(textlives);
	}
	//Display Class
	private void createEndGameDisplay() {
		textGameOver.setFont(new Font("Arial", 150));
		textGameOver.setX(0.3*screenWidth);
		textGameOver.setY((16*screenHeight)/20);
		textGameOver.setText("GAME OVER!");
		textGameOver.setStroke(Color.WHITE);
		root.getChildren().add(textGameOver);
	}
	//Display Class
	private void createWinGameDisplay() {
		textGameWin.setFont(new Font("Arial", 150));
		textGameWin.setX(0.3*screenWidth);
		textGameWin.setY((16*screenHeight)/20);
		textGameWin.setText("YOU WIN!");
		textGameWin.setStroke(Color.WHITE);
		root.getChildren().add(textGameWin);
	}
	// Sub class of general entities, Player Class
	private void createPlayer(int width, int height) {
		try {
			Image paddleImage = new Image(new FileInputStream(PADDLE_IMAGE));
			PaddleBouncer p = new PaddleBouncer(paddleImage, width, height);
			myPaddle = p;
			root.getChildren().add(p.getView());
		}
		catch (FileNotFoundException e) {
		}   
	}
	// Sub class of general entities, Ball Class
	private void createBall(int width, int height) {
		try {
			Image ballImage = new Image(new FileInputStream(BOUNCER_IMAGE));
			BreakoutBall b = new BreakoutBall(ballImage, width, height);
			myBall = b;
			root.getChildren().add(b.getView());
		}
		catch (FileNotFoundException e) {
		}
	}
	// Sub class of general entities, Brick Class
	private void createBricks(int width, int height, int doubleBrick) {
		myBricks = new ArrayList<>();
		doubleBricks = new ArrayList<>();
		try {
			for (int i = 0; i < NUM_BRICKS_ROWS; i++) {
				for (int k = 0; k < NUM_BRICKS_COLUMNS; k++) {
					Image image2 = new Image(new FileInputStream("resources/brick" + Integer.toString(i+1) + ".gif"));
					Bouncerw br = new Bouncerw(image2, width, height, i, k);
					myBricks.add(br);
					if (myBricks.size()-1 == doubleBrick) {
						doubleBricks.add(1);
					}
					else {
						doubleBricks.add(0);
					}
					br.setScore(9-i);
					root.getChildren().add(br.getView());
				}
			}
		}
		catch (FileNotFoundException e) {
		}
	}
	// Used code from the following link to help get the screen size width and height https://stackoverflow.com/questions/27419390/how-to-get-the-width-and-height-of-current-monitor-in-java
	// GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
	// int width = gd[n].getDisplayMode().getWidth();
	// int height = gd[n].getDisplayMode().getHeight();
	// Maybe some type of Scene class
	public void setScreenSize() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		screenHeight = (int) (gd[0].getDisplayMode().getHeight() * 0.8);
		screenWidth = (int) (gd[0].getDisplayMode().getWidth() * 0.8);
	}
	// Player Class
	public void playerMinuslive() {
		if (((ImageView)(myBall).getView()).getY() > screenHeight) 
		{
			myBall.setProjectilePosition(screenWidth, screenHeight);
			myPaddle.setPlayerPosition(screenWidth, screenHeight);
			gamerule.setPlayerlives(gamerule.getPlayerlives()-1);
			myBall.myVelocity = new Point2D(200, 200);
			textlives.setText("Player lives: " + gamerule.getPlayerlives());
		}
	}
	//Scene Class
	public int getScreenWidth() {
		return screenWidth;
	}
	//Scene Class
	public int getScreenHeight() {
		return screenHeight;
	}
	// Bricks Class
	// generating integer
	public int setRandomBrick() {
		Random ran = new Random();
		return ran.nextInt(NUM_BRICKS);
	}

	public void setDoublePoints() {
		for (Bouncerw br : myBricks) {
			br.setScore(br.getScore()*2-1);
		}
		try {
			root.getChildren().remove(myBall.getView());
			Image ballImage = new Image(new FileInputStream(POINTS_IMAGE));
			BreakoutBall b = new BreakoutBall(ballImage, screenWidth, screenHeight);
			myBall = b;
			root.getChildren().add(b.getView());
		}
		catch (FileNotFoundException e) {
		}
	}
	
	public void playerHitsPaddle() {
		if (myBall.getView().getBoundsInParent().intersects(myPaddle.getView().getBoundsInParent())) 
		{
			myBall.bouncePaddle();
			sound.play();
			sound.seek(Duration.ZERO);
		}
	}
	
	public ArrayList<Bouncerw> playerHitsBrick(ArrayList<Bouncerw> destroyBricks) 
	{
		for (Bouncerw br : myBricks) 
		{
			if (myBall.getView().getBoundsInParent().intersects(br.getView().getBoundsInParent())) 
			{       
				int indexBrick = myBricks.indexOf(br);
				if (doubleBricks.get(indexBrick) == 1) 
				{
					setDoublePoints();
				}
				myBall.bounceBrick();
				totalScore = totalScore + br.getScore();
				destroyBricks.add(br);
			}
		}
		return destroyBricks;
	}
	
	//now in breakout level
	public void removeBricksView (ArrayList<Bouncerw> destroyBricks, ArrayList<Integer> doubleBricks) {
		if (destroyBricks.size() > 0) 
		{
			root.getChildren().remove((destroyBricks.get(destroyBricks.size()-1)).getView());
			myBricks.remove(destroyBricks.get(destroyBricks.size()-1)); 
			doubleBricks.remove(doubleBricks.get(doubleBricks.size()-1));
		}
	}

	/**
	 * Start the program.
	 */
	public static void main () {
		launch("");
	}
	/**
	 * Start the program.
	 */
	//    public static void main (String[] args) {
	//        launch(args);
	//    }
}
