/*
* The dice program implements an application that
* rolls dice.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-14
*/

import java.util.Random; // Import the random number class
//=============================================================================

public class Dice {

//=============================================================================
  /**
   * This functions generates a random number from 1-100.
   */
  public static int rollD100() {
    return (int) (Math.random() * 100 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-20.
   */
  public static int rollD20(String rollWith) {
    // 2 Rolls.
    int roll1 = (int) (Math.random() * 20 + 1);
    int roll2 = (int) (Math.random() * 20 + 1);
    if (rollWith.equals("Advantage")) {
      if (roll1 > roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else if (rollWith.equals("Disadvantage")) {
      if (roll1 < roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else {
      return roll1;
    }
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-12.
   */
  public static int rollD12() {
    return (int) (Math.random() * 12 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-10.
   */
  public static int rollD10() {
    return (int) (Math.random() * 10 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-8.
   */
  public static int rollD8() {
    return (int) (Math.random() * 8 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-6.
   */
  public static int rollD6() {
    return (int) (Math.random() * 6 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-4.
   */
  public static int rollD4() {
    return (int) (Math.random() * 4 + 1);
  }
//=============================================================================
  /**
   * This functions generates a random number from 1-3.
   */
  public static int rollD3() {
    return (int) (Math.random() * 3 + 1);
  }
//=============================================================================
}
