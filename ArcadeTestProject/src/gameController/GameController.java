package gameController;

import Map.BreakoutLevel;
import Projectiles.BreakoutBall;
import Projectiles.Projectile;
import Settings.GameRules;
import Settings.HighScore;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import Entities.Brick;
import Entities.PaddleBouncer;
import GameLoop.*;
import Map.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController extends Application
{
	public static final String TITLE = "Breakout";
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
	public static final String BOUNCER_IMAGE = "resources/ball.gif";
	public static final String POINTS_IMAGE = "resources/pointspower.gif";
	public static final int NUM_BOUNCERS = 1;
	public static final int NUM_BRICKS_COLUMNS = 20;
	public static final int NUM_BRICKS_ROWS = 5;
	public static final int NUM_BRICKS = NUM_BRICKS_COLUMNS * NUM_BRICKS_ROWS;
	public static final int NUM_PADDLES = 1;
	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final String PONG_SOUND = "resources/pong_beep.wav";
	public int screenHeight;
	public int screenWidth;

	// some things we need to remember during our game
	private Scene myScene;
	private BreakoutBall myBall;
	private PaddleBouncer myPaddle;
	private List<Brick> myBricks;


	private int totalScore = 0;
	GameRules gamerule = new GameRules();
	HighScore highscore = new HighScore();
	Timeline animation = new Timeline();
	ArrayList<Integer> doubleBricks;
	
	@Override
	public void start (Stage stage) 
	{
		Level testLevel = new BreakoutLevel();
		testLevel.setupGame();
		stage.setScene(testLevel.getScene());
		stage.setTitle(testLevel.getTitle());
		stage.show();
		
		GameLoop gameLoop = new BreakoutGameLoop(testLevel);
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY, gameLoop, testLevel.getLevelEntities()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step (double elapsedTime, GameLoop gameLoop, EntityCollection levelEntities) 
	{
		/*
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
		*/
		gameLoop.runLoop(elapsedTime, levelEntities); 
	}
	
	public static void main () {
		launch("");
	}
}
