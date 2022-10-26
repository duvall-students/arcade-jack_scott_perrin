package Entities;

// Spencer Buehlman 
public class Brick {

	int brickWidth = -1;

	int brickHeight = -1;

	int pointValue = -1;

	

	public Brick() {

		

	}

	

	public void setBrickDimensions(int width, int height) {

		brickWidth = width;

		brickHeight = height;

	}

	

	public void setPointValue(int value) {

		pointValue = value;

	}

	

	public int getBrickWidth() {

		return brickWidth;

	}

	public int getBrickHeight() {

		return brickHeight;

	}

	public int getPointValue() {

		return pointValue;

	}

	

	public static void main(String []args) {

		Brick myBrick = new Brick();

		myBrick.setBrickDimensions(5, 7);

		myBrick.setPointValue(10);

		System.out.println(myBrick.getPointValue());

		System.out.println(myBrick.getBrickHeight() + ", " + myBrick.getBrickWidth());

	}

}