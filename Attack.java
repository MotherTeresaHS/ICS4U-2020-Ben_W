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

  public static int makeAttack(int str, int dex, String attackName, int attackRoll, int addition, int proficiency) {

    ///////////////////////////////////////////////////////////////////////////
    // Simple Weapons

    // Club -------------------------------------------------------------------
    if (attackName.equals("Club")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }

    // Dagger -----------------------------------------------------------------
    } else if (attackName.equals("Dagger")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
    
    // Greatclub --------------------------------------------------------------
    } else if (attackName.equals("Greatclub")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Handaxe ----------------------------------------------------------------
    } else if (attackName.equals("Handaxe")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Javelin --------------------------------------------------------------
    } else if (attackName.equals("Javelin")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Light Hammer -----------------------------------------------------------
    } else if (attackName.equals("Light Hammer")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Mace -------------------------------------------------------------------
    } else if (attackName.equals("Mace")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Quarterstaff -----------------------------------------------------------
    } else if (attackName.equals("Quarterstaff")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Sickle -----------------------------------------------------------------
    } else if (attackName.equals("Sickle")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Spear -----------------------------------------------------------------
    } else if (attackName.equals("Spear")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    ///////////////////////////////////////////////////////////////////////////
    // Simple Ranged Weapons
    
    // Light Crossbow ---------------------------------------------------------
    } else if (attackName.equals("Light Crossbow")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Dart -------------------------------------------------------------------
    } else if (attackName.equals("Dart")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Shortbow ---------------------------------------------------------------
    } else if (attackName.equals("Shortbow")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Sling -------------------------------------------------------------------
    } else if (attackName.equals("Sling")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    ///////////////////////////////////////////////////////////////////////////
    // Martial Melee Weapons
    
    // Battleaxe --------------------------------------------------------------
    } else if (attackName.equals("Battleaxe")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Flail --------------------------------------------------------------
    } else if (attackName.equals("Flail")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Glaive --------------------------------------------------------------
    } else if (attackName.equals("Glaive")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 10 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Greataxe --------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 12 + 1) + (int) (Math.random() * 12 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 12 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Greatsword --------------------------------------------------------------
    } else if (attackName.equals("Greatsword")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Halberd --------------------------------------------------------------
    } else if (attackName.equals("Greataxe")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 10 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Lance --------------------------------------------------------------
    } else if (attackName.equals("Lance")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 12 + 1) + (int) (Math.random() * 12 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 12 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Maul --------------------------------------------------------------
    } else if (attackName.equals("Maul")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Morningstar --------------------------------------------------------------
    } else if (attackName.equals("Morningstar")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Pike --------------------------------------------------------------
    } else if (attackName.equals("Pike")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 10 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
      
    // Rapier --------------------------------------------------------------
    } else if (attackName.equals("Rapier")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
      
    // Scimitar --------------------------------------------------------------
    } else if (attackName.equals("Scimitar")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
      
    // Shortsword --------------------------------------------------------------
    } else if (attackName.equals("Shortsword")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
      
    // Trident --------------------------------------------------------------
    } else if (attackName.equals("Trident")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
      
    // War Pick --------------------------------------------------------------
    } else if (attackName.equals("War Pick")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
    
    // Warhammer --------------------------------------------------------------
    } else if (attackName.equals("Warhammer")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
      
    // Whip --------------------------------------------------------------
    } else if (attackName.equals("Whip")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (((str - str % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 4 + 1) + (((str - str % 2) - 10) / 2) + addition;
      }
      
    ///////////////////////////////////////////////////////////////////////////
    // Martial Ranged Weapons

    // Blowgun --------------------------------------------------------------
    } else if (attackName.equals("Blowgun")) {
      if ((int) (Math.random() * 50 + 1) == 50) {
        return (int) (Math.random() * 500 + 1) + (((dex - dex % 2) - 10) / 2) + addition;

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
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 6 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
    
    // Hand Crossbow --------------------------------------------------------------
    } else if (attackName.equals("Heavy Crossbow")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 10 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
      
    // Longbow --------------------------------------------------------------
    } else if (attackName.equals("Longbow")) {
      if (attackRoll == 20) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (((dex - dex % 2) - 10) / 2) + addition;
      } else {
        return (int) (Math.random() * 8 + 1) + (((dex - dex % 2) - 10) / 2) + addition;
      }
    }
    return (((str - str % 2) - 10) / 2);
  }
}
