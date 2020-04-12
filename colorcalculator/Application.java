//Application to build/connect components in the colorCalculator package
//http://www.cs.cmu.edu/~pattis/15-1XX/common/classes/edu/cmu/cs/pattis/cs151xx/
//https://www.cs.cmu.edu/~pattis/15-1XX/15-200/lectures/modelinmvc/index.html


import colorCalculator.Model;
import colorCalculator.View;
import colorCalculator.Controller;
//import colorCalculator.*;

public class Application
{
	public static void main(String[] args)
	{
    //Construct all the components
	  Model      model      = new Model();
	  Controller controller = new Controller();
	  View       view       = new View();
	  
	  //Notify each component of the other components it needs
	  model.addView(view);
	  controller.addModel(model);
	  view.addModel(model);
	  view.addController(controller);
	  
	  //Build the application, then show it on the screen
	  view.build();
		view.show();
	}
}