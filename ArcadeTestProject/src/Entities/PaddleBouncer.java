package Entities;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class PaddleBouncer {

	private ImageView myView;

	public PaddleBouncer (Image image, double screenWidth, double screenHeight) {
		myView = new ImageView(image);
		// make sure it stays a circle
		double playerWidth = screenWidth/20;
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
	
	public double getXPosition() {
		return myView.getX();
	}
	
	public void setPlayerPosition(double screenWidth, double screenHeight) {
		myView.setX(screenWidth/2);
		myView.setY((18.65*screenHeight)/20);
	}

}
