package nl.hanze.st.mvc;

/**
 * Abstract Class Controller
 * 
 * This class is the controller class that we will use for every child controller.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public abstract class Controller {
	/**
	 * @param model This param contains the current model that's used for the controller.
	 */
	protected final Model model;

	/**
	 * Constructor
	 * @param model Model to be connected with the controller
	 */
	public Controller(Model model) {
		this.model = model;
	}

	/**
	 * Called from class View to notify controller of events
	 * @param view View where event comes from
	 * @param event_id id of event
	 */
	void notify(View view, int event_id) {
		if (!event(view, event_id)) {
			throw new IllegalStateException("Event (event_id="+event_id+") not handled.");
		}
	}

	/**
	 * Eventhandler of controller
	 * @param view View where event comes from
	 * @param event_id id of event
	 * @return true if event is handled
	 */
	protected abstract boolean event(View view, int event_id);


}
