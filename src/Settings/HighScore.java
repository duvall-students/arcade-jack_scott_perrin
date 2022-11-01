package Settings;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// Spencer Buehlman

public class HighScore {

	private static final String HIGH_SCORE_FILE_NAME = "PlayerHighScore.txt";

	public void setNewHighScore(int score) {
		// This code was adapted from: https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java#:~:text=Invoke%20the%20replaceAll()%20method,(new%20line)%20as%20parameters.&text=Instantiate%20the%20FileWriter%20class.,using%20the%20append()%20method.
		//		public static void main(String args[]) throws IOException {
		//		      //Instantiating the File class
		//		      String filePath = "D://input.txt";
		//		      //Instantiating the Scanner class to read the file
		//		      Scanner sc = new Scanner(new File(filePath));
		//		      //instantiating the StringBuffer class
		//		      StringBuffer buffer = new StringBuffer();
		//		      //Reading lines of the file and appending them to StringBuffer
		//		      while (sc.hasNextLine()) {
		//		         buffer.append(sc.nextLine()+System.lineSeparator());
		//		      }
		//		      String fileContents = buffer.toString();
		//		      System.out.println("Contents of the file: "+fileContents);
		//		      //closing the Scanner object
		//		      sc.close();
		//		      String oldLine = "No preconditions and no impediments. Simply Easy Learning!";
		//		      String newLine = "Enjoy the free content";
		//		      //Replacing the old line with new line
		//		      fileContents = fileContents.replaceAll(oldLine, newLine);
		//		      //instantiating the FileWriter class
		//		      FileWriter writer = new FileWriter(filePath);
		//		      System.out.println("");
		//		      System.out.println("new data: "+fileContents);
		//		      writer.append(fileContents);
		//		      writer.flush();
		//		   }
		//		}
		try {
			//Instantiating the Scanner class to read the file
			Scanner sc = new Scanner(new File(HIGH_SCORE_FILE_NAME));
			//instantiating the StringBuffer class
			StringBuffer buffer = new StringBuffer();
			//Reading lines of the file and appending them to StringBuffer
			String oldHighScore = sc.nextLine();
			buffer.append(oldHighScore+System.lineSeparator());
			String fileContents = buffer.toString();
			//closing the Scanner object
			sc.close();
			String newHighScore = Integer.toString(score);
			//Replacing the old line with new line
			fileContents = fileContents.replaceAll(oldHighScore, newHighScore);
			//instantiating the FileWriter class
			FileWriter writer = new FileWriter(HIGH_SCORE_FILE_NAME);
			writer.append(fileContents);
			writer.flush();
			writer.close();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

	public int getCurrentHighScore() {
		int currentHighScore = 0;
		try {
			Scanner scan = new Scanner(new File(HIGH_SCORE_FILE_NAME));
			currentHighScore = Integer.parseInt(scan.next());
			scan.close();

		} catch (IOException exp) {
			exp.printStackTrace();
		} 
		return currentHighScore;

	}
}
