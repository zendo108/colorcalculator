//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Class          : colorCalculator.Model
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
//   The Model for the colorCalculator package implements its guts: it
// responds to entered text and presses of buttons created by its
// Conroller (changeColorViaTextField, changeColorViaButton), and tells
// the View when it needs to update its display (calling update in view,
// which calls this classes getRed/Green/Blue and getHex methods). For
// debugging purposes, each time before calling view.update, the model
// prints on the console the state of its instance variables. 
// 
//   Note that "no access modifier" means that the method is package
// friendly: this means the member is public to all other classes in
// the color machine package, but private elsewhere.
//
//   This class includes a main method so Model can be tested
// independenlty from the View and Controller. For this to work, we
// cannot assume setView is called (if there is no view!), so we check
// to see if view stores null before each call to update. To run main,
// inside Java Target set the Main Class to colorCalculator.Model
//
// Future Plans   : Inactivate +10/-10 buttons when no number to change
//
// Program History:
//   9/20/01: R. Pattis - Operational for 15-100/
//   4/12/04: R. Pattis - Now using modular counters
//   8/25/04: R. Pattis - Added main for independent testing
//
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////



package colorCalculator;


import java.lang.Integer;
import java.awt.Color;
import com.ivanaranda.ModularCounter;
import com.ivanaranda.Prompt;            //For main only


public class Model {

  public Model ()
  {
    //All colors initialized to null in their declarations; if I omitted
    //  this constructor, Java would write it for me anyway!
  }

   
    
  //Refer to the view (used to call update after each button press)
  public void addView(View v)
  {view = v;}
 
 
 
  //Implement method called by controller (or by main) 
  void changeColorViaTextField(String color, String intensity)
  {
    //Find right color, but assume illegal value by setting it to null
    if (color.equals("Red"))
      red = null;
    else if (color.equals("Green"))
      green = null;
    else if (color.equals("Blue"))
      blue = null;
    else
      return;  //Not a good color!
      
    //Compute the real value for the color
    //Possible exceptions if: not a number or not in range [0..255] 
    try {
      int i = Integer.parseInt(intensity);
	    if (color.equals("Red"))
	      red = new ModularCounter(i,256);
	    if (color.equals("Green"))
	      green = new ModularCounter(i,256);;
	    if (color.equals("Blue"))
	      blue = new ModularCounter(i,256);;
    }catch (Exception e) {/*don't set value; use null one*/} 
    
    //Always display state in the console and update GUI
    System.out.println("State: " + this +"\n");
    if (view != null)    //Checked in case main (not application) running
      view.update();
   }  



  //Implement method called by controller (or by main)
  void changeColorViaButton(String color, int amount)
  {
    //Increment correct color, only if it has a correct value
    if (color.equals("Red")        && red   != null)
      red.update(amount);
    else if (color.equals("Green") && green != null)
      green.update(amount);
    else if (color.equals("Blue")  && blue  != null)
      blue.update(amount);
    else
      return;  //Not a good color!

    //Always display state in the console and update GUI
    System.out.println("State: " + this +"\n");
    if (view != null)    //Checked in case main (not application) running
      view.update();
  }  
      


  //The view calls these accessors
  int getRed()
  {return (red == null ? -1 : red.getValue());}
  
  
  int getGreen()
  {return (green == null ? -1 : green.getValue());}
  
  
  int getBlue()
  {return (blue == null ? -1 : blue.getValue());}
  
  
  String getHex()
  {return gh(getRed()) + gh(getGreen()) + gh(getBlue());}
 
 
  
  //Primarily for debugging purposes
  public String toString()
  {return "Model[red=" + red + ", green=" + green + ", blue=" + blue +"]";}
  
  
  
  //Helper method
  private String gh (int i)
  {
    String hexDigits = "0123456789ABCDEF";
    return ""+hexDigits.charAt(i/16)+hexDigits.charAt(i%16);
  }
  


  //Fields (all instance variablesd)
  private View view;         // Model must tell View when to update itself
  
  private ModularCounter red,green,blue;
    
    
    
    
    
  /////////////////////////////////////////////////////////////
  //
  //Driver Program
  //
  //For testing Model independently from View and Controller
  //Inside Java Target set the Main Class to colorMachine.Model
  //
  /////////////////////////////////////////////////////////////
  
 
	public static void main(String[] args)
	{
	  //Prompt for construction arguments; get object to test; Don't use constructor directly
	  Model m = new Model();
	  System.out.println("State: "+ m +"\n");
	  
	  for (;;)
	    try {
	      System.out.println("Menu");
        System.out.println("  t - changeColorViaTextField");
        System.out.println("  b - changeColorViaButton");
        System.out.println("  ? - view all accessors");
        System.out.println("  q - quit");
        char selection = Prompt.forChar("Enter Command","tb?q");

        if (selection == 't') {
          String color     = Prompt.forString("  Enter color    ");
          String intensity = Prompt.forString("  Enter intensity");
          m.changeColorViaTextField(color,intensity);
          
        }else if (selection == 'b') {
          String color  = Prompt.forString("  Enter color ");
          int    amount = Prompt.forInt   ("  Enter amount");
          m.changeColorViaButton(color,amount);
       
        }else if (selection == '?') {
           System.out.println("  getRed   = " + m.getRed());
           System.out.println("  getGreen = " + m.getGreen());
           System.out.println("  getBlue  = " + m.getBlue());
           if (m.getRed() == -1 || m.getGreen() == -1 || m.getBlue() == -1)
             System.out.println("  No getHex because some colors missing");
           else
             System.out.println("  getHex   = " + m.getHex());
           System.out.println();

        }else if (selection == 'q')
          break;
        
        else
          System.out.println("\""+selection+"\" is unknown command");

      }catch(Exception e) {
        System.out.println("  Exception Caught/Handled: "+e.getMessage());
      }
  }
}


