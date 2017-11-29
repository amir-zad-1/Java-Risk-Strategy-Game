
package view;

import javafx.scene.Scene;

/**
 *
 * <p>To enforce every view to return its {@link Scene} object </p>
 * @author SA
 */
public interface IView {
	public Scene getView(boolean isResume);
}
