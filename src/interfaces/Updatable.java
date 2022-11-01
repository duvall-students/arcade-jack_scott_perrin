package interfaces;

import javafx.scene.Node;
/**
 * Any entity that must be updated on a frame by frame basis
 * @author snsum
 *
 */
public interface Updatable 
{	

	public void update(double elapsedTime);
}
