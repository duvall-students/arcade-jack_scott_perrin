package GameLoop;

import java.nio.file.Paths;
import java.util.ArrayList;

import Entities.Player;
import Map.Level;
import Projectiles.Projectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BreakoutGameLoop extends GameLoop
{
	public static final String PADDLE_IMAGE = "resources/paddle.gif";
	public static final String PONG_SOUND = "resources/pong_beep.wav";
	public Media pongSound = new Media(Paths.get(PONG_SOUND).toUri().toString());
	public MediaPlayer sound  = new MediaPlayer(pongSound);

	public BreakoutGameLoop()
	{
		super();
	}

	@Override
	protected void handleProjectileEntityCollision(ArrayList<Projectile> collidedProjectiles) 
	{
		for (Projectile projectile : collidedProjectiles)
		{
			projectile.collisionBehavior();
		}
		
	}

	@Override
	protected void handleProjectilePlayerCollision(ArrayList<Player> collidedPlayers) 
	{
		for(Player player : collidedPlayers)
		{
			player.collisionBehavior();
		}
		
	}
	
	//check out of bounds projectiles
		//if out of bounds, lower player health
		//

	
}
