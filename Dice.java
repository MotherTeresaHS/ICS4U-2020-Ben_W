/*
* The dice program implements an application that
* rolls dice.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-14
*/

import java.util.Random; // Import the random number class

///////////////////////////////////////////////////////////////////////////////

public class Dice {

///////////////////////////////////////////////////////////////////////////////

  public static int rollD100() {

    return (int) (Math.random() * 100 + 1);
  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD20(String rollWith) {
    // 2 Rolls.
    int roll1;
    int roll2;

    if (rollWith.equals("Advantage")) {
      roll1 = (int) (Math.random() * 20 + 1);
      roll2 = (int) (Math.random() * 20 + 1);
      if (roll1 > roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else if (rollWith.equals("Disadvantage")) {
      roll1 = (int) (Math.random() * 20 + 1);
      roll2 = (int) (Math.random() * 20 + 1);
      if (roll1 < roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else {
      return (int) (Math.random() * 20 + 1);
    }
  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD12(String rollWith) {
    // 2 Rolls.
    int roll1;
    int roll2;

    if (rollWith.equals("Advantage")) {
      roll1 = (int) (Math.random() * 12 + 1);
      roll2 = (int) (Math.random() * 12 + 1);
      if (roll1 > roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else if (rollWith.equals("Disadvantage")) {
      roll1 = (int) (Math.random() * 12 + 1);
      roll2 = (int) (Math.random() * 12 + 1);
      if (roll1 < roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else {
      return (int) (Math.random() * 12 + 1);
    }
  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD10() {

    return (int) (Math.random() * 10 + 1);

  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD8(String rollWith) {
    // 2 Rolls.
    int roll1;
    int roll2;

    if (rollWith.equals("Advantage")) {
      roll1 = (int) (Math.random() * 8 + 1);
      roll2 = (int) (Math.random() * 8 + 1);
      if (roll1 > roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else if (rollWith.equals("Disadvantage")) {
      roll1 = (int) (Math.random() * 8 + 1);
      roll2 = (int) (Math.random() * 8 + 1);
      if (roll1 < roll2) {
        return roll1;
      } else {
        return roll2;
      }
    } else {
      return (int) (Math.random() * 8 + 1);
    }
  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD6() {
    return (int) (Math.random() * 6 + 1);
  }

///////////////////////////////////////////////////////////////////////////////

  public static int rollD4() {
    return (int) (Math.random() * 4 + 1);
  }

///////////////////////////////////////////////////////////////////////////////
}
