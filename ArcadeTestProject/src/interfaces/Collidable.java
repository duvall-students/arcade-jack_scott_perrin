package interfaces;

/**
 * Any entity that a projectile is able to collide with (may be removed, not sure if this is actually usefull)
 * @author snsum
 *
 */
public interface Collidable extends GameObject
{
	public void collisionBehavior();
}
