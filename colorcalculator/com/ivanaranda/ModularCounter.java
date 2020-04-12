//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////
//
// Class          : ModularCounter
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
//   ModularCounter objects represent values in some modulus. They can be
// incremented, decremented, and tested (various ways).
//
//
// Known Bugs (if any): None
//
// Future Plans       : none
//
// Program History:
//   9/18/01: R. Pattis - Operational for 15-100
//   8/22/04: R. Pattis - Document via Javadoc
//
//
//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


package com.ivanaranda;

/** 
 * Objects constructed from the ModularCounter class represent integral
 *   (non-negative) values under a modulus: they always lie in the range
 *   <code>[0,modulus-1]</code>.
 * Such counters can be incremented and decremented; incrementing a
 *   value to the modulus resets its value to zero; decrementing a
 *   value from 0 resets its value to <code>modulus-1</code>.
 * Each <code>ModularCounter</code> obeys the following class invariants
 *   <ul>
 *     <li>The modulus must be &gt; 0
 *     <li>The value must be in the range <code>[0,modulus-1]</code>
 *   </ul>
*/
public class ModularCounter { 


	/** 
	 * Constructs a <code>ModularCounter</code> object, specifying the
	 *   initial value and modulus.
	 * It establishes the following class invariants
	 *   <ul>
	 *     <li>The modulus must be &gt 0
	 *     <li>The value must be in the range <code>[0,modulus-1]</code>
	 *   </ul>
	 *
	 * @param  value specifies the value of the modular number
	 * @param  modulus specifies the modulus of the modular number
	 *
	 * @throws IllegalArgumentException if <code>modulus</b> is &lt= 0 or
	 *           <code>value</code> is outside the appropriate range
	*/
  public ModularCounter (int value, int modulus)
    throws IllegalArgumentException
  {
    if (modulus < 1)
      throw new IllegalArgumentException("ModularCounter - modulus("+modulus+") non-positive");
    if (value < 0 || value >= modulus)
      throw new IllegalArgumentException("ModularCounter - value("+value+") not in range [0,"+(modulus-1)+"]");

    this.value   = value;
    this.modulus = modulus;
  }
  
  
  
	/** 
	 * Constructs a <code>ModularCounter</code> object, specifying the
	 *   modulus only (the value is zero).
	 *
	 * @param  modulus specifies the modulus of the modular number
	*/
  public ModularCounter (int modulus)
  {this(0,modulus);}
  
  
  
	/** 
	 * Returns the value of this <code>ModularCounter</code>.
	 *
	 * @return the value of this <code>ModularCounter</code>
	*/
  public int getValue()
  {return value;}
  
  
  
	/** 
	 * Returns the modulus of this <code>ModularCounter</code>.
	 *
	 * @return the modulus of this <code>ModularCounter</code>
	*/
  public int getModulus()
  {return modulus;}
  
  
  
	/** 
	 * Returns whether or not this <code>ModularCounter</b> is equal to
	 *   <code>other</code>.
	 *
	 * @param  other specifies the other <code>ModularCounter</b> in the
	 *           equality check
	 *
	 * @return whether or not this <code>ModularCounter</b> is equal to
	 *   <code>other</code>
	*/
  public boolean equals(Object other)
  {
    if ( !(other instanceof ModularCounter) )
       return false;
    if (this == other)
      return true;
      
    ModularCounter otherMC = (ModularCounter)other;
    
    return value == otherMC.value && modulus == otherMC.modulus;
  }
  
  
  
	/** 
	 * Returns whether or not this <code>ModularCounter</b>'s value is
	 *   equal to zero (independent of the modulus).
	 *
	 * @return whether or not this <code>ModularCounter</b>'s value is
	 *   equal to zero (independent of the modulus)
	*/
  public boolean isZero()
  {return value == 0;}
  
  
  
	/** 
	 * Compares its two arguments for order.
	 * Returns a negative integer, zero, or a positive integer as the
	 *   first argument is less than, equal to, or greater than the
	 *   second.
	 *
	 * @return whether this <code>ModularCounter</b>'s value is
	 *   less than, equal to, or greater than <code>other</code>'s
	 *   value (independent of the moduls)
	*/
  public int compareTo(ModularCounter mc)
  {return value - mc.value;}
  
  
  
	/** 
	 * Returns a <code>String<code> representation of the state of this 
	 *  <code>ModularCounter</code>: value (mod modulus).
	 * E.g.: 6 (mod 10)
	 *
	 * @return a <code>String<code> representation of the state of this 
	 *  <code>ModularCounter</code>: value (mod modulus)
	*/
  public String toString ()
  {return value + "(mod " + modulus + ")";}
  
  

	/** 
	 * Reset this <code>ModularCounter</code> to store the value 0.
	*/
  public void reset()
  {value = 0;}
  
  
  
	/** 
	 * Increment this <code>ModularCounter</code> by 1 (with rollover to
	 *   0 beyond modulus-1).
	*/
  public void inc()
  {
    if (value < modulus-1)
      value++;
    else
      value = 0;
  }
  
  
  
	/** 
	 * Increment this <code>ModularCounter</code> by <code>delta</code>
	 *  (with rollover to 0 beyond modulus-1).
	 *
	 * @param  delta specifies the amount to increment by
	 *
	 * @throws IllegalArgumentException if <code>delta</b> is &lt 0
	*/
  public void inc (int delta)
    throws IllegalArgumentException
  {
    if (delta < 0)
      throw new IllegalArgumentException("ModularCounter: inc - delta("+delta+") non-positive");
    for (int i=1; i <= delta; i++)
      inc();
  }
  
  
  
	/** 
	 * Decrement this <code>ModularCounter</code> by 1 (with rollover to
	 *   modulus-1 beyond 0).
	*/
  public void dec()
  {
    if (value == 0)
      value = modulus-1;
    else
      value--;
  }
  
 
  
	/** 
	 * Decrement this <code>ModularCounter</code> by <code>delta</code>
	 *  (with rollover to modulus-1 beyond 0).
	 *
	 * @param  delta specifies the amount to decrement by
	 *
	 * @throws IllegalArgumentException if <code>delta</b> is &lt 0
	*/
  public void dec (int delta)
    throws IllegalArgumentException
  {
    if (delta < 0)
      throw new IllegalArgumentException("ModularCounter: inc - delta("+delta+") non-positive");
    for (int i=1; i <= delta; i++)
      dec();
  }
  
  
  
	/** 
	 * IncrementDecrement this <code>ModularCounter</code> by <code>delta</code>
	 *  (with rollover to 0 beyond modulus-1/modulus-1 beyond 0).
	 *
	 * @param  delta specifies the amount to increment/decrement by
	*/
  public void update (int delta)
  {
    if (delta >= 0)
      for (int i=1; i <= delta; i++)
        inc();
    else
      for (int i=1; i <= -delta; i++)
        dec();
  }
  
  
  
	/** 
	 * Returns a new <code>ModularCounter</code> after prompting the user
	 *   for its value and modulus.
	 * If an illegal value is entered, the user is reprompted
	 * For example, if we write <code>ModularCounter.prompt("Enter Hours")</code>
	 *   an interaction on the console screen might look like
	 * <code><pre>  Enter Hours
	 *    Enter value  : 2
	 *    Enter modulus: 12</pre></code>
	 *
	 * @param message specifies the message with which to prompt the user
   *
	 * @return a new <code>ModularCounter</code> after prompting the user
	 *   for its value and modulus.
	*/
  public static ModularCounter prompt (String message)
  {
    System.out.println(message);
    for (;;)
      try{
        int value   = Prompt.forInt("  Enter value  ");
        int modulus = Prompt.forInt("  Enter modulus");
        return new ModularCounter(value,modulus);
      }catch (Exception e)
        {System.out.println("Illegal ModularCounter entered; please try again");}
  }
  
  
  
	/** 
	 * Stores the value of the <code>ModularCounter</code>.
	 * It must always lie in the range <code>[0,modulus-1]</code>.
	*/
  private int value;
  
  
	/** 
	 * Stores the moduls of the <code>ModularCounter</code>.
	 * It must always be positive.
	*/
  private int modulus;
}