/*
* The dice program implements an application that
* attacks in the game.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-19
*/

import java.util.Random; // Import the random number class

///////////////////////////////////////////////////////////////////////////////

public class Attack {

///////////////////////////////////////////////////////////////////////////////
// Weapon Attacks.

  public static int makeAttack(int str, int dex, String attackName, int attackRoll, String fightingStyle, int proficiency) {

    ///////////////////////////////////////////////////////////////////////////
    // Simple Weapons

    // Club -------------------------------------------------------------------
    if (attackName.equals("Club")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }

    // Dagger -----------------------------------------------------------------
    } else if (attackName.equals("Dagger")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((dex - dex % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((dex - dex % 2) - 10) / 2);
      }
    
    // Greatclub --------------------------------------------------------------
    } else if (attackName.equals("Greatclub")) {
      int roll1 = (int) (Math.random() * 8 + 1);
      int roll2 = (int) (Math.random() * 8 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Handaxe ----------------------------------------------------------------
    } else if (attackName.equals("Handaxe")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Javelin --------------------------------------------------------------
    } else if (attackName.equals("Javelin")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Light Hammer -----------------------------------------------------------
    } else if (attackName.equals("Light Hammer")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Mace -------------------------------------------------------------------
    } else if (attackName.equals("Mace")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Quarterstaff -----------------------------------------------------------
    } else if (attackName.equals("Quarterstaff")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 6 + 1);
        roll2 = (int) (Math.random() * 6 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Sickle -----------------------------------------------------------------
    } else if (attackName.equals("Sickle")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Spear -----------------------------------------------------------------
    } else if (attackName.equals("Spear")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 6 + 1);
        roll2 = (int) (Math.random() * 6 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    ///////////////////////////////////////////////////////////////////////////
    // Simple Ranged Weapons
    
    // Light Crossbow ---------------------------------------------------------
    } else if (attackName.equals("Light Crossbow")) {
      int roll1 = (int) (Math.random() * 8 + 1);
      int roll2 = (int) (Math.random() * 8 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Dart -------------------------------------------------------------------
    } else if (attackName.equals("Dart")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Shortbow ---------------------------------------------------------------
    } else if (attackName.equals("Shortbow")) {
      int roll1 = (int) (Math.random() * 6 + 1);
      int roll2 = (int) (Math.random() * 6 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 6 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 6 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Sling -------------------------------------------------------------------
    } else if (attackName.equals("Sling")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    ///////////////////////////////////////////////////////////////////////////
    // Martial Melee Weapons
    
    // Battleaxe --------------------------------------------------------------
    } else if (attackName.equals("Battleaxe")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 10 + 1);
        roll2 = (int) (Math.random() * 10 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Flail --------------------------------------------------------------
    } else if (attackName.equals("Flail")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Glaive --------------------------------------------------------------
    } else if (attackName.equals("Glaive")) {
      int roll1 = (int) (Math.random() * 10 + 1);
      int roll2 = (int) (Math.random() * 10 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Greataxe --------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      int roll1 = (int) (Math.random() * 12 + 1);
      int roll2 = (int) (Math.random() * 12 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 12 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 12 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Greatsword --------------------------------------------------------------
    } else if (attackName.equals("Greatsword")) {
      int roll1 = (int) (Math.random() * 6 + 1);
      int roll2 = (int) (Math.random() * 6 + 1);
      int roll3 = (int) (Math.random() * 6 + 1);
      int roll4 = (int) (Math.random() * 6 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 6 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 6 + 1);
        }
        if (roll3 == 1 || roll3 == 2) {
          roll3 = (int) (Math.random() * 6 + 1);
        }
        if (roll4 == 1 || roll4 == 2) {
          roll4 = (int) (Math.random() * 6 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + roll3 + roll4 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      }
    
    // Halberd --------------------------------------------------------------
    } else if (attackName.equals("Halberd")) {
      int roll1 = (int) (Math.random() * 10 + 1);
      int roll2 = (int) (Math.random() * 10 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Lance --------------------------------------------------------------
    } else if (attackName.equals("Lance")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2);
      }
      
    // Longsword --------------------------------------------------------------
    } else if (attackName.equals("Longsword")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 10 + 1);
        roll2 = (int) (Math.random() * 10 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    
    // Maul --------------------------------------------------------------
    } else if (attackName.equals("Maul")) {
      int roll1 = (int) (Math.random() * 6 + 1);
      int roll2 = (int) (Math.random() * 6 + 1);
      int roll3 = (int) (Math.random() * 6 + 1);
      int roll4 = (int) (Math.random() * 6 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 6 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 6 + 1);
        }
        if (roll3 == 1 || roll3 == 2) {
          roll3 = (int) (Math.random() * 6 + 1);
        }
        if (roll4 == 1 || roll4 == 2) {
          roll4 = (int) (Math.random() * 6 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + roll3 + roll4 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      }
    
    // Morningstar --------------------------------------------------------------
    } else if (attackName.equals("Morningstar")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Pike --------------------------------------------------------------
    } else if (attackName.equals("Pike")) {
      int roll1 = (int) (Math.random() * 10 + 1);
      int roll2 = (int) (Math.random() * 10 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
      
    // Rapier --------------------------------------------------------------
    } else if (attackName.equals("Rapier")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((dex - dex % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 8 + 1) + (((dex - dex % 2) - 10) / 2);
      }
      
    // Scimitar --------------------------------------------------------------
    } else if (attackName.equals("Scimitar")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((dex - dex % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2);
      }
      
    // Shortsword --------------------------------------------------------------
    } else if (attackName.equals("Shortsword")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((dex - dex % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2);
      }
      
    // Trident --------------------------------------------------------------
    } else if (attackName.equals("Trident")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 6 + 1);
        roll2 = (int) (Math.random() * 6 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
      
    // War Pick --------------------------------------------------------------
    } else if (attackName.equals("War Pick")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2);
      }
    
    // Warhammer --------------------------------------------------------------
    } else if (attackName.equals("Warhammer")) {
      int roll1 = 0;
      int roll2 = 0;
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        roll1 = (int) (Math.random() * 10 + 1);
        roll2 = (int) (Math.random() * 10 + 1);
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      } else {
        roll1 = (int) (Math.random() * 8 + 1);
        roll2 = (int) (Math.random() * 8 + 1);
      }

      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
      
    // Whip --------------------------------------------------------------
    } else if (attackName.equals("Whip")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2);
      }
      
    ///////////////////////////////////////////////////////////////////////////
    // Martial Ranged Weapons

    // Blowgun --------------------------------------------------------------
    } else if (attackName.equals("Blowgun")) {
      if ((int) (Math.random() * 50 + 1) == 50) {
        return (int) (Math.random() * 500 + 1) + (((dex - dex % 2) - 10) / 2);

      } else {
        if (attackRoll == 20) {
          return 2;
        }
        return 1;
      }
      
    // Hand Crossbow --------------------------------------------------------------
    } else if (attackName.equals("Hand Crossbow")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((dex - dex % 2) - 10) / 2);
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2);
      }
    
    // Hand Crossbow --------------------------------------------------------------
    } else if (attackName.equals("Heavy Crossbow")) {
      int roll1 = (int) (Math.random() * 10 + 1);
      int roll2 = (int) (Math.random() * 10 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 10 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 10 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
      
    // Longbow --------------------------------------------------------------
    } else if (attackName.equals("Longbow")) {
      int roll1 = (int) (Math.random() * 8 + 1);
      int roll2 = (int) (Math.random() * 8 + 1);
      // Great Weapon Fighting Style ------------------------------------------
      if (fightingStyle.equals("Great Weapon Fighting")) {
        if (roll1 == 1 || roll1 == 2) {
          roll1 = (int) (Math.random() * 8 + 1);
        }
        if (roll2 == 1 || roll2 == 2) {
          roll2 = (int) (Math.random() * 8 + 1);
        }
      }
      if (attackRoll == 20) {
        return roll1 + roll2 + (((str - str % 2) - 10) / 2);
      } else {
        return roll1 + (((str - str % 2) - 10) / 2);
      }
    }
    return (((str - str % 2) - 10) / 2);
  }

///////////////////////////////////////////////////////////////////////////////
// Weapon properties.

  public static String attackProperty1(String attackName) {

    ///////////////////////////////////////////////////////////////////////////
    // Simple Weapons

    // Club -------------------------------------------------------------------
    if (attackName.equals("Club")) {
      return "";

    // Dagger -----------------------------------------------------------------
    } else if (attackName.equals("Dagger")) {
      return "Finesse";
    
    // Greatclub --------------------------------------------------------------
    } else if (attackName.equals("Greatclub")) {
      return "Two-handed";
    
    // Handaxe ----------------------------------------------------------------
    } else if (attackName.equals("Handaxe")) {
      return "";
    
    // Javelin ----------------------------------------------------------------
    } else if (attackName.equals("Javelin")) {
      return "";
    
    // Light Hammer -----------------------------------------------------------
    } else if (attackName.equals("Light Hammer")) {
      return "";
    
    // Mace -------------------------------------------------------------------
    } else if (attackName.equals("Mace")) {
      return "";
    
    // Quarterstaff -----------------------------------------------------------
    } else if (attackName.equals("Quarterstaff")) {
      return "";
    
    // Sickle -----------------------------------------------------------------
    } else if (attackName.equals("Sickle")) {
      return "";
    
    // Spear ------------------------------------------------------------------
    } else if (attackName.equals("Spear")) {
      return "";
    
    ///////////////////////////////////////////////////////////////////////////
    // Simple Ranged Weapons
    
    // Light Crossbow ---------------------------------------------------------
    } else if (attackName.equals("Light Crossbow")) {
      return "Two-handed";
    
    // Dart -------------------------------------------------------------------
    } else if (attackName.equals("Dart")) {
      return "Finesse";
    
    // Shortbow ---------------------------------------------------------------
    } else if (attackName.equals("Shortbow")) {
      return "Two-handed";
    
    // Sling ------------------------------------------------------------------
    } else if (attackName.equals("Sling")) {
      return "";
    
    ///////////////////////////////////////////////////////////////////////////
    // Martial Melee Weapons
    
    // Battleaxe --------------------------------------------------------------
    } else if (attackName.equals("Battleaxe")) {
      return "";
    
    // Flail ------------------------------------------------------------------
    } else if (attackName.equals("Flail")) {
      return "";
    
    // Glaive -----------------------------------------------------------------
    } else if (attackName.equals("Glaive")) {
      return "Two-handed";
    
    // Greataxe ---------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      return "Two-handed";
    
    // Greatsword -------------------------------------------------------------
    } else if (attackName.equals("Greatsword")) {
      return "Two-handed";
    
    // Halberd ----------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      return "Two-handed";
    
    // Lance ------------------------------------------------------------------
    } else if (attackName.equals("Lance")) {
      return "";
      
    // Lance ------------------------------------------------------------------
    } else if (attackName.equals("Longsword")) {
      return "";
    
    // Maul -------------------------------------------------------------------
    } else if (attackName.equals("Maul")) {
      return "Two-handed";
    
    // Morningstar ------------------------------------------------------------
    } else if (attackName.equals("Morningstar")) {
      return "";
    
    // Pike -------------------------------------------------------------------
    } else if (attackName.equals("Pike")) {
      return "Two-handed";
      
    // Rapier -----------------------------------------------------------------
    } else if (attackName.equals("Rapier")) {
      return "Finesse";
      
    // Scimitar ---------------------------------------------------------------
    } else if (attackName.equals("Scimitar")) {
      return "Finesse";
      
    // Shortsword -------------------------------------------------------------
    } else if (attackName.equals("Shortsword")) {
      return "Finesse";
      
    // Trident ----------------------------------------------------------------
    } else if (attackName.equals("Trident")) {
      return "";
      
    // War Pick ---------------------------------------------------------------
    } else if (attackName.equals("War Pick")) {
      return "";
    
    // Warhammer --------------------------------------------------------------
    } else if (attackName.equals("Warhammer")) {
      return "";
      
    // Whip -------------------------------------------------------------------
    } else if (attackName.equals("Whip")) {
      return "Finesse";
      
    ///////////////////////////////////////////////////////////////////////////
    // Martial Ranged Weapons

    // Blowgun ----------------------------------------------------------------
    } else if (attackName.equals("Blowgun")) {
      return "";
      
    // Hand Crossbow ----------------------------------------------------------
    } else if (attackName.equals("Hand Crossbow")) {
      return "";
    
    // Heavy Crossbow ----------------------------------------------------------
    } else if (attackName.equals("Heavy Crossbow")) {
      return "Two-handed";
      
    // Longbow ----------------------------------------------------------------
    } else if (attackName.equals("Longbow")) {
      return "Two-handed";

    // ------------------------------------------------------------------------
    } else {
      return "";
    }
  }
  
  /////////////////////////////////////////////////////////////////////////////
  public static String attackProperty2(String attackName) {

    ///////////////////////////////////////////////////////////////////////////
    // Simple Weapons

    // Club -------------------------------------------------------------------
    if (attackName.equals("Club")) {
      return "Light";

    // Dagger -----------------------------------------------------------------
    } else if (attackName.equals("Dagger")) {
      return "Light";
    
    // Greatclub --------------------------------------------------------------
    } else if (attackName.equals("Greatclub")) {
      return "";
    
    // Handaxe ----------------------------------------------------------------
    } else if (attackName.equals("Handaxe")) {
      return "Light";
    
    // Javelin ----------------------------------------------------------------
    } else if (attackName.equals("Javelin")) {
      return "";
    
    // Light Hammer -----------------------------------------------------------
    } else if (attackName.equals("Light Hammer")) {
      return "Light";
    
    // Mace -------------------------------------------------------------------
    } else if (attackName.equals("Mace")) {
      return "";
    
    // Quarterstaff -----------------------------------------------------------
    } else if (attackName.equals("Quarterstaff")) {
      return "";
    
    // Sickle -----------------------------------------------------------------
    } else if (attackName.equals("Sickle")) {
      return "Light";
    
    // Spear ------------------------------------------------------------------
    } else if (attackName.equals("Spear")) {
      return "";
    
    ///////////////////////////////////////////////////////////////////////////
    // Simple Ranged Weapons
    
    // Light Crossbow ---------------------------------------------------------
    } else if (attackName.equals("Light Crossbow")) {
      return "Ranged";
    
    // Dart -------------------------------------------------------------------
    } else if (attackName.equals("Dart")) {
      return "Ranged";
    
    // Shortbow ---------------------------------------------------------------
    } else if (attackName.equals("Shortbow")) {
      return "Ranged";
    
    // Sling ------------------------------------------------------------------
    } else if (attackName.equals("Sling")) {
      return "Ranged";
    
    ///////////////////////////////////////////////////////////////////////////
    // Martial Melee Weapons
    
    // Battleaxe --------------------------------------------------------------
    } else if (attackName.equals("Battleaxe")) {
      return "";
    
    // Flail ------------------------------------------------------------------
    } else if (attackName.equals("Flail")) {
      return "";
    
    // Glaive -----------------------------------------------------------------
    } else if (attackName.equals("Glaive")) {
      return "";
    
    // Greataxe ---------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      return "";
    
    // Greatsword -------------------------------------------------------------
    } else if (attackName.equals("Greatsword")) {
      return "";
    
    // Halberd ----------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      return "";
    
    // Lance ------------------------------------------------------------------
    } else if (attackName.equals("Lance")) {
      return "";
      
    // Lance ------------------------------------------------------------------
    } else if (attackName.equals("Longsword")) {
      return "";
    
    // Maul -------------------------------------------------------------------
    } else if (attackName.equals("Maul")) {
      return "";
    
    // Morningstar ------------------------------------------------------------
    } else if (attackName.equals("Morningstar")) {
      return "";
    
    // Pike -------------------------------------------------------------------
    } else if (attackName.equals("Pike")) {
      return "";
      
    // Rapier -----------------------------------------------------------------
    } else if (attackName.equals("Rapier")) {
      return "";
      
    // Scimitar ---------------------------------------------------------------
    } else if (attackName.equals("Scimitar")) {
      return "Light";
      
    // Shortsword -------------------------------------------------------------
    } else if (attackName.equals("Shortsword")) {
      return "Light";
      
    // Trident ----------------------------------------------------------------
    } else if (attackName.equals("Trident")) {
      return "";
      
    // War Pick ---------------------------------------------------------------
    } else if (attackName.equals("War Pick")) {
      return "";
    
    // Warhammer --------------------------------------------------------------
    } else if (attackName.equals("Warhammer")) {
      return "";
      
    // Whip -------------------------------------------------------------------
    } else if (attackName.equals("Whip")) {
      return "";
      
    ///////////////////////////////////////////////////////////////////////////
    // Martial Ranged Weapons

    // Blowgun ----------------------------------------------------------------
    } else if (attackName.equals("Blowgun")) {
      return "Ranged";
      
    // Hand Crossbow ----------------------------------------------------------
    } else if (attackName.equals("Hand Crossbow")) {
      return "Ranged";
    
    // Heavy Crossbow ----------------------------------------------------------
    } else if (attackName.equals("Heavy Crossbow")) {
      return "Ranged";
      
    // Longbow ----------------------------------------------------------------
    } else if (attackName.equals("Longbow")) {
      return "Ranged";

    // ------------------------------------------------------------------------
    } else {
      return "";
    }
  }
}
