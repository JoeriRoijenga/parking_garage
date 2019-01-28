package nl.hanze.st.mvc;

import java.util.ArrayList;

/**
 * Abstract Class Model
 * 
 * This class is the model class that we will use for every child model.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public abstract class Model {

	/**
	 * @param views This param will contain all the views that are added to the ArrayList.
	 */
	private ArrayList<View> views = new ArrayList<>();

	/**
	 * Add views to model to be notified by model.
	 * @param view View to be added to list of models that will be notified.
	 */
	public void addView(View view) {
		views.add(view);
	}

	/**
	 * Notify views. Called by subclasses (concrete models)
	 */
	public void notifyView() {
		for (View view : views) {
			view.notify(this);
		}
	}

}
