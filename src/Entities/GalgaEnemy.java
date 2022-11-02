package Entities;

import Projectiles.ImageView;
import interfaces.Node;

public class GalgaEnemy extends Obstacle {

	@Override
	public Node getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collisionBehavior() {
		// TODO Auto-generated method stub
		
	}
	
	public enemy(Image image, int y, int x) {
		myView = new ImageView(image);
		imagesizex = x;
		imagesizey = y;
		return enemy;
	}
	
}
