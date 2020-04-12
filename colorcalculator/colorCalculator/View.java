//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Class          : colorCalculator.View
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
//   This View for the colorCalculator package shows a color swatch (and
// its hexidecimal value); we can change the red/green/blue components of a
// color by entering information (in a text field), or incrementing/decrementing
// the components (with buttons); both are created in the Controller, to call
// methods in the Model).
//
//   This class uses many Swing classes in fairly typical ways. The
// entire View itself extends a JFrame (holding all the displays/buttons).
//
//   Note that "no access modifier" means that the method is package
// friendly: this means the member is public to all other classes in
// the color machine package, but private elsewhere.
//
// Future Plans   : More Comments
//
// Program History:
//  10/10/01: R. Pattis - Operational for 15-100
//
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////



package colorCalculator;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class View extends JFrame
{

  //Trivial constructor (could be automatically supplied by Java)
	public View()
	{}
	
  //Refer to the model (used in the update method, to call the getDisplay
  //  method in the model) 
	public void addModel(Model m)
	{model = m;}

	
  //Refer to the controller (used to build the buttons the view will
  //  place in the view) 
	public void addController(Controller c)
	{controller = c;}
	
	
  //build does the heavy lifting; it builds the view, populating it
  //  with the appropriate display and buttons
  //GUI applications have lots of little details to specify to make
  //  them look nice, and this method is in charge of them all
	public void build()
	{
	  //When a window close icon is pressed, exit the entire program
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		//Size the frame to a reasobnable size and label it
		setSize(500, 250);
		setTitle("Color Calculator");
		

		//Create a font for all the buttons (save it in a field, so it can be
		//  referred to in methods called subsequently: buttonSetup)
		buttonFont = new Font("Monospaced", Font.BOLD, 24);

    //Create a panel to store all the GUI components:
    //  colors has textfields and buttons to enter the color values
    //  show displays the color palette and the hexidecimal value
    JPanel sideBySide = new JPanel();
    sideBySide.setLayout(new GridLayout(1,2));
    
    //Create a panel to store the textfields and buttons to enter the
    //  color values, and put it in on the left of sideBySide.
    JPanel colors = new JPanel();
    colors.setLayout(new GridLayout(3,1));
    
    red   = controller.getColorField("Red");
    green = controller.getColorField("Green");
    blue  = controller.getColorField("Blue");
    
    colors.add(createColorPalette("Red",red));
    colors.add(createColorPalette("Green",green));
    colors.add(createColorPalette("Blue",blue));

    sideBySide.add(colors);
    
    
    //Create a panel to show displays the color palette and the
    //  hexidecimal value, and put it in on the right of sideBySide.
    JPanel show = new JPanel();
    show.setLayout(new GridLayout(2,1));
    show.add(new JPanel(){
      public void paintComponent(Graphics g)
      {
        if (colorSwatch == null)
          return;
        Dimension size = getSize();
        g.setColor(colorSwatch);
        g.fillRect(size.width/4,size.height/4,size.width/2,size.height/2);
      }},"Center");
      
    hex = new JTextField(30);
    hex.setEditable(false);
    hex.setBackground(Color.cyan);
    hex.setFont(buttonFont);
    show.add(hex);
    
    sideBySide.add(show);
    
    //Put the side by side panel at the center of the main JFrame's
    //  content panel
		Container contentPane = getContentPane();
		contentPane.add(sideBySide, "Center");
		
		update();
	}


  //Called only in createButtonPanel below, to set the attributes of
  //  the buttons (their label, font) and add them to the GUI
  private void buttonSetup(JPanel  panelForButtons,
                           JButton b,
                           String  bLabel)
  {
		b.setText(bLabel);
		b.setFont(buttonFont);
		panelForButtons.add(b);
  }
  
  
  //Creates the entire panel of textfields and buttons (using GridLayout)
  //It both creates the buttons (from the controller) and
  //  places them in the grid (for the view)
  private JPanel createColorPalette(String color,JTextField colorField)
	{
		JPanel colorChoose = new JPanel();
		colorChoose.setLayout(new GridLayout(2,2));
		
		colorChoose.add(new JLabel(color));
		JButton colorUp = controller.getColorButton(color,+10);
		buttonSetup(colorChoose,colorUp,"+10");
		colorChoose.add(colorField);
		JButton colorDown = controller.getColorButton(color,-10);
		buttonSetup(colorChoose,colorDown,"-10");
		
		return colorChoose;
  }
		
		
  //When the model changes, it calls update, which determines how to
  //  view the model by calling its getRed/getGreen/getBlue methods.
  //This seems a bit circular, but it isn't (you need to know
  //   more about the MVC pattern to understand better)
	void update()
	{
     int r = model.getRed();
     int g = model.getGreen();
     int b = model.getBlue();

     colorSwatch = null;
     if (r!=-1 && g!=-1 && b!=-1)
       colorSwatch = new Color(r,g,b);
     
     red.setText  (r != -1 ? ""+r : "Enter [0,255]");
     green.setText(g != -1 ? ""+g : "Enter [0,255]");
     blue.setText (b != -1 ? ""+b : "Enter [0,255]");
     hex.setText  ("Hex: "+ (colorSwatch != null ? ""+model.getHex()
                                                 : "Unknown"   ) );
		 repaint();
	}





  //Instance Variables
  

  private Controller controller;  //Controller creates button in View
	private Model      model;       //Model tells update what to display
	
	Font       buttonFont;          //Information shared by multiple methods
	JTextField red, green, blue, hex;
	Color      colorSwatch;
 }
