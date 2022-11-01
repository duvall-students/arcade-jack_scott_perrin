package Map;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Entities.Brick;
import Entities.Obstacle;
import Entities.PaddleBouncer;
import Projectiles.BreakoutBall;
import Projectiles.Projectile;
import Settings.GameRules;
import Settings.HighScore;
import gameController.EntityCollection;
import interfaces.Collidable;
import interfaces.Updatable;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Level
{
	private String title;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final Paint HIGHLIGHT = Color.OLIVEDRAB;

	public static final int NUM_PADDLES = 1;
	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final String PONG_SOUND = "resources/pong_beep.wav";
	public Media pongSound = new Media(Paths.get(PONG_SOUND).toUri().toString());
	public MediaPlayer sound  = new MediaPlayer(pongSound);
	public int screenHeight;
	public int screenWidth;
	

	
	// some things we need to remember during our game
	private Scene myScene;



	protected Group root = new Group();
	protected EntityCollection levelEntities = new EntityCollection();



	private int totalScore = 0;
	GameRules gamerule = new GameRules();
	HighScore highscore = new HighScore();
	Timeline animation = new Timeline();

	public Scene getScene()
	{
		return myScene;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Level(String title) 
	{
		this.title = title;
	}

	public void setupGame()
	{
		//Common functionality between level types
		setScreenSize();
		createCurrentScoreDisplay();
		createHighScoreDisplay();
		createGameTitle();
		createlivesDisplay();
		createPlayer(screenWidth, screenHeight);
		

		
		
		//Unique functionality defined in sub-classes
		generateLevel(screenWidth, screenHeight, BACKGROUND);
		myScene = new Scene(root, screenWidth, screenHeight, BACKGROUND);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
		initializeCollisionListener();
		//start event to listen for changes to game entities
		//initializeListener();

	}
	
	public void initializeCollisionListener()
	{
		/*
		ObservableList<Collidable> list = levelEntities.getCollidableEntities();
		
		list.addListener(new ListChangeListener<Collidable>() 
		{

			@Override
			public void onChanged(Change<? extends Collidable> c) 
			{
				while(c.next())
				{
					if (c.wasRemoved())
					{
						System.out.println("BRICK REMOVED");
						Collidable entity = c.getFrom();
						removeCollidableEntity((Collidable)c.getRemoved());
					}
				}
				
			}
			
		});
		*/
		levelEntities.getObstacles().addListener((ListChangeListener<Obstacle>) change -> {
            while (change.next()) 
            {
                if (change.wasRemoved()) 
                {
                    root.getChildren().removeAll
                    (
                            change.getRemoved().stream()
                                    .map(Collidable::getView)
                                    .collect(Collectors.toList())
                    );
                }
            }
        });

	}
	
	protected void removeCollidableEntity(Collidable removedBrick)
	{
		root.getChildren().remove(removedBrick.getView());
	}

	
	
	public EntityCollection getLevelEntities()
	{
		return levelEntities;
	}
	/**
	 * Implement any additional behavior involved in this level type
	 */
	protected abstract void generateLevel (int width, int height, Paint background);
	
	/**
	 * Create the object that will be controlled by the player
	 */
	protected abstract void createPlayer(int width, int height);
	
	/**
	 * Determine what to do with key inputs for the given game type
	 */
	protected abstract void handleKeyInput (KeyCode code);
	

	protected abstract void createProjectile(int width, int height);
	
	public void setScreenSize() {
		GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		screenHeight = (int) (gd[0].getDisplayMode().getHeight() * 0.8);
		screenWidth = (int) (gd[0].getDisplayMode().getWidth() * 0.8);
	}
	
	
	//Display Class
	private void createGameTitle() 
	{
		Text textTitle = new Text();
		textTitle.setFont(new Font("Arial", 75));
		textTitle.setX(0.4*screenWidth);
		textTitle.setY(screenHeight/15);
		textTitle.setText("BREAKOUT");
		textTitle.setStroke(Color.WHITE);
		root.getChildren().add(textTitle);
	}
	//Display Class
	private void createCurrentScoreDisplay() 
	{
		Text textScore = new Text();
		textScore.setFont(new Font("Arial", 20));
		textScore.setX((15.5*screenWidth)/20);
		textScore.setY(screenHeight/20);
		textScore.setText("Score: 0");
		textScore.setStroke(Color.WHITE);
		root.getChildren().add(textScore);
	}
	//Display Class
	private void createHighScoreDisplay() 
	{
		Text textHighScore = new Text();
		textHighScore.setFont(new Font("Arial", 20));
		textHighScore.setX((17.5*screenWidth)/20);
		textHighScore.setY(screenHeight/20);
		textHighScore.setText("High Score: " + highscore.getCurrentHighScore());
		textHighScore.setStroke(Color.WHITE);
		root.getChildren().add(textHighScore);
	}
	//Display Class
	private void createlivesDisplay() 
	{
		Text textlives = new Text();
		textlives.setFont(new Font("Arial", 20));
		textlives.setX(screenWidth/20);
		textlives.setY(screenHeight/20);
		textlives.setText("Player lives: " + gamerule.getPlayerlives());
		textlives.setStroke(Color.WHITE);
		root.getChildren().add(textlives);
	}
	
	//Display Class
	private void createEndGameDisplay() 
	{
		Text textGameOver = new Text();
		textGameOver.setFont(new Font("Arial", 150));
		textGameOver.setX(0.3*screenWidth);
		textGameOver.setY((16*screenHeight)/20);
		textGameOver.setText("GAME OVER!");
		textGameOver.setStroke(Color.WHITE);
		root.getChildren().add(textGameOver);
	}
	//Display Class
	private void createWinGameDisplay() 
	{
		Text textGameWin = new Text();
		textGameWin.setFont(new Font("Arial", 150));
		textGameWin.setX(0.3*screenWidth);
		textGameWin.setY((16*screenHeight)/20);
		textGameWin.setText("YOU WIN!");
		textGameWin.setStroke(Color.WHITE);
		root.getChildren().add(textGameWin);
	}

	//move this to a higher level class
	// Sub class of general entities, Ball Class


}
