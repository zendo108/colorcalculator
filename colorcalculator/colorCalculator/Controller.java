//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Class          : colorCalculator.Controller
//
// Author         : Richard E. Pattis
//                  Computer Science Department
//                  Carnegie Mellon University
//                  5000 Forbes Avenue
//                  Pittsburgh, PA 15213-3891
//                  e-mail: pattis@cs.cmu.edu
//
// Maintainer     : Author
//
//
// Description:
//
//   The Controller for the colorCalculator package creates JButtons,
// which when pressed call methods in the the Model (sometimes with an
// appropriate parameter) to implement the semantics of their actions.
//
//   For debugging purposes, when each button is pressed it displays
// some information on the console. These statements can be commented-out
// in the working program.
//
//   Note that "no access modifier" means that the method is package
// friendly: this means the member is public to all other classes in
// the color machine package, but private elsewhere.
//
// Future Plans   : More Comments
//
// Program History:
//   10/10/01: R. Pattis - Operational for 15-100
//
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


package colorCalculator;


import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;



public class Controller
{

  //Trivial constructor (could be automatically supplied by Java)
  public Controller()
  {}


  
  //Refer to the model (used in all the button methods, to call
  //  methods in the model) 
  public void addModel(Model m)
  {model = m;}

  
  //Build/Return a Color button: it calls the changeColor method in model
  JButton getColorButton(final String color, final int amount)
  {
	  JButton b = new JButton();
	  
	  b.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
		  {
		     System.out.println("Debug-Controller: " + "Color +10/-10 button pressed (" +
		                        color+","+amount+")");
         model.changeColorViaButton(color,amount);}
	  });
	  
	  return b;
  }
    
     
  //Build/Return a JTextField: for entering Numeric Values: it calls the changeColor
  //  method in model
  JTextField getColorField(final String color)
  {
	  final String     errorMessage = "Enter [0,255]";
	  final JTextField tf           = new JTextField(errorMessage,5);
	  
	  tf.addActionListener(new ActionListener()
	  {
	    public void actionPerformed(ActionEvent action)
	    {
		    System.out.println("Debug-Controller: " + "Color text field activated (" +
		                        color+",\""+tf.getText()+"\")");
	      model.changeColorViaTextField(color,tf.getText());
	    }
	  });
	  
	  tf.addFocusListener(new FocusAdapter()
	  {
	    public void focusLost(FocusEvent event)
	    {
		    System.out.println("Debug-Controller: " + "Color text field loses focus (" +
		                        color+",\""+tf.getText()+"\")");
	      model.changeColorViaTextField(color,tf.getText());
	    }
	  });

    /*
	  tf.addInputMethodListener(new InputMethodListener()
	  {
      public void caretPositionChanged(InputMethodEvent event)
	    {}
	    
      public void inputMethodTextChanged(InputMethodEvent event)
	    {
	      //problem: tf is text without new entry
	      if (model.validate(tf.getText()))
	        old = tf.getText();
	      else
	        tf.setText(old);
	    }
	    
	    String old = "";
	  });
	  */
	  return tf;
  }
    
    
   
    
  private Model model;  //Controller must tell Model when buttons are pressed
}


