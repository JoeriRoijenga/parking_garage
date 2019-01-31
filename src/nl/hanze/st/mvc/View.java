package nl.hanze.st.mvc;

import javax.swing.JPanel;

/**
 * Abstract Class View
 * 
 * This class is the view class that we will use for every child view.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public abstract class View extends JPanel {	
	/**
	 * @param controller This param contains the controller that's used with the view.
	 */
	private Controller controller;

	/**
	 * Attach controller to view
	 * @param controller
	 */
	public void setController(Controller controller) {
		if (this.controller!=null) {
			throw new IllegalStateException("Controller already set.");
		}
		this.controller = controller;
	}

	/**
	 * Notify controller of event, called by subclasses (concrete views)
	 * @param event_id id of event
	 */
	protected void notifyController(int event_id) {
		if (controller==null) {
			throw new IllegalStateException("View does not have a controller.");
		}
		controller.notify(this, event_id);
	}

	/**
	 * Notify from model. Called by class Model
	 * @param model Model
	 */
	protected void notify(Model model) {
		update(model);
	}
	
	

	/**
	 * Updatehandler
	 * @param model Model
	 */
	protected abstract void update(Model model);

}
