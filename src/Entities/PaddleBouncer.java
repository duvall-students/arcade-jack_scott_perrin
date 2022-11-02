package Entities;

import java.nio.file.Paths;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
public class PaddleBouncer extends Player
{
	public static final String PONG_SOUND = "resources/pong_beep.wav";
	public Media pongSound = new Media(Paths.get(PONG_SOUND).toUri().toString());
	public MediaPlayer sound  = new MediaPlayer(pongSound);
	
	private ImageView myView;
	private int health;
	
	public PaddleBouncer (Image image, double screenWidth, double screenHeight) {
		myView = new ImageView(image);
		// make sure it stays a circle
//		double playerWidth = screenWidth/20;
		double playerWidth = screenWidth/10;
		
		double playerHeight = screenHeight/20;
		myView.setFitWidth(playerWidth);
		myView.setFitHeight(playerHeight);
		// make sure it stays within the bounds
		setPlayerPosition(screenWidth, screenHeight);
	}
	
	public Node getView () {
		return myView;
	}
	
	public ImageView getImageView () {
		return myView;
	}
	
	public void setPlayerPosition(double screenWidth, double screenHeight) 
	{
		myView.setX(screenWidth/2);
		myView.setY((18.65*screenHeight)/20);
	}
	
	public double getXPosition() {
		return myView.getX();
	}
	
	@Override
	public void collisionBehavior()
	{
		sound.play();
		sound.seek(Duration.ZERO);
	}



}
