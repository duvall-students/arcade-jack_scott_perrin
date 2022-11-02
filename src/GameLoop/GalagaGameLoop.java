package GameLoop;

import java.util.ArrayList;

import Entities.Player;
import Projectiles.Projectile;

public class GalagaGameLoop extends GameLoop
{
	public GalagaGameLoop()
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
		levelEntities.removeProjectiles(collidedProjectiles);
	}

	@Override
	protected void handleProjectilePlayerCollision(ArrayList<Player> collidedPlayers) 
	{

		levelEntities.getPlayer().damagePlayer();

	}
}
