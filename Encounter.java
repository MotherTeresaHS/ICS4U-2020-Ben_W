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

public class Encounter {

  // Variables --------------------------------------------------------------

  // Amount of exp gained for killing one:
  public int expForOne = 0;
  // Number of attacks the enemy has (default = 1):
  public int numberOfAttacks = 1;
  // Depicting what type of creature the enemy is (for run DCs):
  public String enemyType = "";
  // How many enemies are there in this encounter?
  public int numberOfEnemies = 0;
  // How much health do they all have together?
  public int enemyHealth = 0;
  // How much health does one enemy have?
  public int healthOfOne = 0;
  // How difficult is it to hit them?
  public int enemyAc = 0;
  // How difficult is it to avoid the encounter?
  public int runDc = 0;  // Trying to run away.
  public int intimidateDc = 0; // Tyring to scare them off.
  public int persuadeDc = 0; // Trying to persuade them to leave.
  public int tameDc = 0; // Trying to take them.
  // Roll to see what zombie encounter they get.
  private int zombieRoll = 0;

//=============================================================================

  private int rollD20(String rollWith) {
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

  public String getEncounterType(String encounter) {
    // Getting what type of encounter it is:
    if (encounter.equals("Almiraj") || encounter.equals("Chwinga")
        || encounter.equals("Pteranodon") || encounter.equals("Quetzalcoatlus")
        || encounter.equals("Faerie Dragon") || encounter.equals("Red Dragon")
        || encounter.equals("Night Hag")
        || encounter.equals("Winterscape") || encounter.equals("Zorbos")) {
      // Returning that the encounter's friendly.
      return "Friendly";
    } else if (encounter.equals("Aarakocra")
               || encounter.equals("Emerald Enclave")
               || encounter.equals("Explorer")
               || encounter.equals("Flaming Fist")
               || encounter.equals("Tabaxi Hunter")
               || encounter.equals("Wereboar")
               || encounter.equals("Weretiger")) {
      // Returning that the encounter gives food.
      return "Food";
    } else if (encounter.equals("Artus Cimber") || encounter.equals("Cache")
               || encounter.equals("Dead Explorer")
               || encounter.equals("Mad Monkey Mist")
               || encounter.equals("Rare Plant")
               || encounter.equals("Statue of Ubtao")) {
      // Returning that the encounter gives food.
      return "Special";
    } else {
      // Returning that the encounter's Hostile.
      return "Hostile";
    }
  }

//=============================================================================

  public String setEncounterStats(String encounter) {
    // Initializing the encounter statistics:
    
    //=========================================================================
    // Friendly Encounters:
    
    // Aarakocra --------------------------------------------------------------
    if (encounter.equals("Aarakocra")) {
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      return "- The Party notices " + numberOfEnemies + " half "
             + "bird half man creatures flying over the jungle "
             + "heading west -";
             
    // Almiraj ----------------------------------------------------------------
    } else if (encounter.equals("Almiraj")) {
      numberOfEnemies = (int) (Math.random() * 6 + 1);
      return "- The Party sees " + numberOfEnemies + " almiraj "
             + " in the distance. - ";
      
    // Artus Cimber -----------------------------------------------------------
    } else if (encounter.equals("Artus Cimber")) {
      return "None";
      
    // Cache ------------------------------------------------------------------
    } else if (encounter.equals("Cache")) {
      return "- The Party found a cache of items! -";

    // Chwinga ----------------------------------------------------------------
    } else if (encounter.equals("Chwinga")) {
      return "- The party spots a Chwinga stealing something from their packs."
             + " They quickly dissapear into the jungle upon being seen. -";
      
    // Pteranodon -------------------------------------------------------------
    } else if (encounter.equals("Pteranodon")) {
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      return "- The party spots a flock of " + numberOfEnemies
             + " pteranodons flying through the sky. -";
      
    // Quetzalcoatlus ---------------------------------------------------------
    } else if (encounter.equals("Quetzalcoatlus")) {
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      return "- The party spots a flock of " + numberOfEnemies
             + " quetzalcoatluses flying through the sky. -";

    // Faerie Dragon ----------------------------------------------------------
    } else if (encounter.equals("Faerie Dragon")) {
      return "- The party spots a small faerie dragon playing in trees. -";
      
    // Red Dragon -------------------------------------------------------------
    } else if (encounter.equals("Red Dragon")) {
      return "- The party spots a red dragon flying west over Chult. -";

    // Emerald Enclave --------------------------------------------------------
    } else if (encounter.equals("Emerald Enclave")) {
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      return "- The party is greeted by " + numberOfEnemies
             + " members of the Emerald Enclave. -";
      
    // Dead Explorer ----------------------------------------------------------
    } else if (encounter.equals("Dead Explorer")) {
      return "- The party finds the body of a dead explorer. - ";
      
    // Explorer ---------------------------------------------------------------
    } else if (encounter.equals("Explorer")) {
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      return "- The party is greeted by a party of " + numberOfEnemies
             + " explorers. -";
      
    // Flaming Fist -----------------------------------------------------------
    } else if (encounter.equals("Flaming Fist")) {
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      return "- The party is greeted by a patrol of " + numberOfEnemies
             + " members of the Flaming Fist. -";

    // Mad Monkey Mist --------------------------------------------------------
    } else if (encounter.equals("Mad Monkey Mist")) {
      return "- A bank of blue mist drifts toward the party, covering the " 
             + "imediate area. -";

    // Night Hag --------------------------------------------------------------
    } else if (encounter.equals("Night Hag")) {
      return "- The party feels like they've lost some hair and blood. -";

    // Rare Plant -------------------------------------------------------------
    } else if (encounter.equals("Rare Plant")) {
      return "- The party finds a rare plant! -";

    // Statue of Ubtao --------------------------------------------------------
    } else if (encounter.equals("Statue of Ubtao")) {
      return "- The party finds a statue! -";

    // Tabaxi Hunter ----------------------------------------------------------
    } else if (encounter.equals("Tabaxi Hunter")) {
      return "- The party is greeted by a tabaxi hunter. -";

    // Wereboar ---------------------------------------------------------------
    } else if (encounter.equals("Wereboar")) {
      return "- The party is greeted by a friendly wereboar. -";
      
    // Weretiger --------------------------------------------------------------
    } else if (encounter.equals("Weretiger")) {
      return "- The party is greeted by a friendly weretiger. -";
      
    // Winterscape ------------------------------------------------------------
    } else if (encounter.equals("Winterscape")) {
      return "- The party finds an area 120 foot radius spere of " 
             + "frozen land in the jungle. -";

    // Zorbos -----------------------------------------------------------------
    } else if (encounter.equals("Zorbos")) {
      numberOfEnemies = (int) (Math.random() * 4 + 1)
                        + (int) (Math.random() * 4 + 1);
      return "- The party finds spots " + numberOfEnemies
             + " zorbos latched to various trees around you. -";

    //=========================================================================
    // Combat Encounters:

    // Albino Dwarves ---------------------------------------------------------
    } else if (encounter.equals("Albino Dwarves")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 3;
      healthOfOne = 30;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      persuadeDc = 10;
      enemyAc = 13;
      enemyType = "Humanoid";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------

      return "- The Party is suddenly ambushed by " + numberOfEnemies
             + " Albino Dwaves! -";

    // Aldani -----------------------------------------------------------------
    } else if (encounter.equals("Aldani")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 49;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      persuadeDc = 10;
      enemyAc = 14;
      enemyType = "Humanoid";
      expForOne = 200;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The Party notices that they are being shadowed by "
              + numberOfEnemies + " Aldani (Lobsterfolk). -\n- Eventually, "
              + "they come forward and attack! -";

    // Apes -------------------------------------------------------------------
    } else if (encounter.equals("Ape")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1)
                        + (int) (Math.random() * 4 + 1);
      healthOfOne = 19;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party are attacked by " + numberOfEnemies + " apes "
             + "enjoying some exelent fruit -";

    // Assassin Vine ----------------------------------------------------------
    } else if (encounter.equals("Assassin Vine")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 85;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 15;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 700;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------

      return "- The party unwittingly enter the hunting grounds of "
             + numberOfEnemies + " assassing vines! -";

    // Axe Beak ---------------------------------------------------------------
    } else if (encounter.equals("Axe Beak")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1) + 3;
      healthOfOne = 19;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 11;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is suddenly attack by a flock of " + numberOfEnemies
             + " axe beaks stampeding through the jungle! -";

    // Baboons -------------------------------------------------------------------
    } else if (encounter.equals("Baboon")) { 
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 3;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 5;
      intimidateDc = 10;
      tameDc = 5;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 10;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------

      return "- A pack of " + numberOfEnemies
             + " baboons take umbrage in the party's intrusion and attack -";

    // Cannibal ---------------------------------------------------------------
    } else if (encounter.equals("Cannibal")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      persuadeDc = 15;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 25;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party finds a group of " + numberOfEnemies
              + " tribal warriors feasting on the rotting remains "
              + "of a dismembered zombie. -\n- As they turn to face the "
              + "party, they prepare to attack! -";

    // Crocodile --------------------------------------------------------------
    } else if (encounter.equals("Crocodile")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      healthOfOne = 19;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies + " crocodiles! -";

    // Cyclops ----------------------------------------------------------------
    } else if (encounter.equals("Cyclops")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 138;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 5;
      intimidateDc = 15;
      persuadeDc = 10;
      enemyAc = 14;
      enemyType = "Humanoid";
      expForOne = 2300;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a Cyclops on it's way back home! -";

    // Allosaurus -------------------------------------------------------------
    } else if (encounter.equals("Allosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 3 + 1);
      healthOfOne = 51;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      tameDc = 20;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party's scent attracts " + numberOfEnemies
             + " allosauruses who attack on sight! -";
      
    // Ankylosaurus -----------------------------------------------------------
    } else if (encounter.equals("Ankylosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 68;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 0;
      enemyAc = 15;
      enemyType = "Beast";
      expForOne = 700;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party encounters a tame ankylosaurus gorging on plants. -";
      
    // Brontosaurus -----------------------------------------------------------
    } else if (encounter.equals("Brontosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 121;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 30;
      tameDc = 0;
      enemyAc = 15;
      enemyType = "Beast";
      expForOne = 1800;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- A tame brontosaurus lumbers toward the part. -";
      
    // Deinonychus ------------------------------------------------------------
    } else if (encounter.equals("Deinonychus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 2;
      healthOfOne = 26;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      intimidateDc = 20;
      tameDc = 20;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 3;
      // Stats ----------------------------------------------
      
      return "- A wild boar races past the party's path, followed "
             + "closely by a hunting pack of " + numberOfEnemies
             + " deinonychuses, which decide that you are more "
             + "intersting prey! -";

    // Dimetrodon -------------------------------------------------------------
    } else if (encounter.equals("Dimetrodon")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 26;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      intimidateDc = 20;
      tameDc = 20;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a pack of " + numberOfEnemies
             + " hungry dimetrodons! -";
      
    // Hadrosaurus ------------------------------------------------------------
    } else if (encounter.equals("Hadrosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 76;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 0;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party sees a herd of " + numberOfEnemies
             + " grazing nearby. -";
      
    // Pleiosaurus ------------------------------------------------------------
    } else if (encounter.equals("Pleiosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 68;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 5;
      intimidateDc = 30;
      tameDc = 20;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by 2 plesiosauruses fighting"
             + " over a giant octopus! -";
      
    // Stegosaurus ------------------------------------------------------------
    } else if (encounter.equals("Stegosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 76;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 0;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 1100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is approached by a stegosaurus in a genial move -";
      
    // Triceratops ------------------------------------------------------------
    } else if (encounter.equals("Triceratops")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 95;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 20;
      tameDc = 30;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 1800;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is approached by a grazing triceratops -";
      
    // Tyrannosaurus ----------------------------------------------------------
    } else if (encounter.equals("Tyrannosaurus")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 136;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      tameDc = 30;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 3900;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is suddenly stopped in their tracks by a triceratops"
             + " flying through the air and landing just infornt of them... -"
             + "\n- As the surrounding jungle grows quiet, the loud, "
             + "booming footsteps of a creature come into earshot... -"
             + "\n\u001B[31m- SUDDENLY! A tyrannosaurus rex emerges "
             + "from the trees and attacks the party! -\u001B[0m";
      
    // Velociraptor -----------------------------------------------------------
    } else if (encounter.equals("Velociraptor")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 10;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 10;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a pack of " + numberOfEnemies
             + " velociraptors! -";

    // Elbis ------------------------------------------------------------------
    } else if (encounter.equals("Elbis")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      healthOfOne = 26;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 0;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party spot multiple reed huts containing "
             + numberOfEnemies + " Elbis. -";

    // Firenewt ---------------------------------------------------------------
    } else if (encounter.equals("Firenewt")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      persuadeDc = 15;
      enemyAc = 16;
      enemyType = "Humanoid";
      expForOne = 100;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a patrol of " + numberOfEnemies
             + " firenewt warriors, each riding a giant spider! -";
      
    // Flail Snail ------------------------------------------------------------
    } else if (encounter.equals("Flail Snail")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 52;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      enemyAc = 16;
      enemyType = "";
      expForOne = 700;
      numberOfAttacks = 5;
      // Stats ----------------------------------------------
      
      return "- The party spots a slimy trail in the jungle. Upon reaching the"
             + "end of the trail, you are attacked by a flail snail! -";
      
    // Flying Monkey ----------------------------------------------------------
    } else if (encounter.equals("Flying Monkey")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 3;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 5;
      tameDc = 0;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 10;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is greated by " + numberOfEnemies
             + " playful flying monkeys. -";

    // Flying Snake -----------------------------------------------------------
    } else if (encounter.equals("Flying Snake")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 5;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 10;
      tameDc = 0;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 25;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party sees " + numberOfEnemies + " flying"
             + " snakes laying on some tree branches ahead. -";
      
    // Frost Giant ------------------------------------------------------------
    } else if (encounter.equals("Frost Giant")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 3;
      healthOfOne = 138;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 30;
      persuadeDc = 0;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 3900;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party sees a group of " + numberOfEnemies
             + " Frost Giants stomping through the wilderness. -";
      
    // Giant Boar -------------------------------------------------------------
    } else if (encounter.equals("Giant Boar")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      healthOfOne = 42;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party sees " + numberOfEnemies + " giant boars"
             + " foraging up ahead. -";
      
    // Giant Crocodile --------------------------------------------------------
    } else if (encounter.equals("Giant Crocodile")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 85;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 20;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 1800;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a giant crocodile! -";
      
    // Giant Frog -------------------------------------------------------------
    } else if (encounter.equals("Giant Frog")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 18;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      tameDc = 20;
      enemyAc = 11;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " hungry giant frogs! -";

    // Giant Lizard -----------------------------------------------------------
    } else if (encounter.equals("Giant Lizard")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1) + 1;
      healthOfOne = 19;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party spots " + numberOfEnemies + " giant "
             + "lizards sunning themselves on warm rocks. -";
      
    // Giant Scorpion ---------------------------------------------------------
    } else if (encounter.equals("Giant Scorpion")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 3 + 1);
      healthOfOne = 52;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 15;
      enemyType = "Beast";
      expForOne = 700;
      numberOfAttacks = 3;
      // Stats ----------------------------------------------
      
      return "- " + numberOfEnemies + " giant scorpions emerge "
             + "from their hiding places and attack the party. -";
      
    // Giant Snapping Turtle --------------------------------------------------
    } else if (encounter.equals("Giant Snapping Turtle")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 75;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 20;
      tameDc = 20;
      enemyAc = 17;
      enemyType = "Beast";
      expForOne = 700;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party spots a giant snapping turtle sunning"
             + " themself on the shore. -";

    // Giant Wasp -------------------------------------------------------------
    } else if (encounter.equals("Giant Wasp")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1);
      healthOfOne = 13;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- A droning sound announces the presence of "
             + numberOfEnemies + " giant wasps which attack the "
             + "party on sight! -";
      
    // Girallons --------------------------------------------------------------
    } else if (encounter.equals("Girallons")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 2;
      healthOfOne = 59;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 20;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 1100;
      numberOfAttacks = 5;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by 2 girallons sitting atop a rock! -";

    // Goblins ----------------------------------------------------------------
    } else if (encounter.equals("Goblin")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1) + 4;
      healthOfOne = 7;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      intimidateDc = 10;
      persuadeDc = 15;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is ambushed by " + (numberOfEnemies - 1)
              + " goblins lead by a goblin boss! -";
      
    // Grung ------------------------------------------------------------------
    } else if (encounter.equals("Grung")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 10;
      persuadeDc = 15;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is ambushed by a hunting party of "
             + numberOfEnemies + " grungs! -";
      
    // Jaculis ----------------------------------------------------------------
    } else if (encounter.equals("Jaculis")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1);
      healthOfOne = 16;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------

      return "- Without warning, " + numberOfEnemies + " jaculis" 
             + " lauch themselves at the party from the trees! -";

    // Kamadans ---------------------------------------------------------------
    } else if (encounter.equals("Kamadans")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 3 + 1);
      healthOfOne = 67;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 15;
      tameDc = 15;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 1100;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is ambushed by " + numberOfEnemies + " kamadans! -";
      
    // Lizardfolk -------------------------------------------------------------
    } else if (encounter.equals("Lizardfolk")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1)
                        + (int) (Math.random() * 4 + 1);
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 20;
      persuadeDc = 0;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 100;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is approached by a party of " + numberOfEnemies
             + " lizardfolk. -";

    // Magmin -----------------------------------------------------------------
    } else if (encounter.equals("Magmin")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 9;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 10;
      persuadeDc = 30;
      enemyAc = 14;
      enemyType = "Humanoid";
      expForOne = 100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is ambushed by " + numberOfEnemies + " magmins! -";
      
    // Mantrap ----------------------------------------------------------------
    } else if (encounter.equals("Mantrap")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      healthOfOne = 45;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 30;
      tameDc = 30;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party blunders into a patch of " + numberOfEnemies
             + " mantraps! -";
      
    // Pterafolk --------------------------------------------------------------
    } else if (encounter.equals("Pterafolk")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 2;
      healthOfOne = 26;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      intimidateDc = 20;
      persuadeDc = 20;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 200;
      numberOfAttacks = 3;
      // Stats ----------------------------------------------
      
      return "- A group of " + numberOfEnemies + " pterafolk loom "
             + "in the sky over the party. Eventually, they take "
             + "advantage of a distraction to attack the party! -";

    // Red Wizard -------------------------------------------------------------
    } else if (encounter.equals("Red Wizard")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1);
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 15;
      persuadeDc = 15;
      enemyAc = 16;
      enemyType = "Humanoid";
      expForOne = 25;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- A group of " + numberOfEnemies + " members of the "
             + "Red Wizards attack the party! -";
      
    // Sea Hag ----------------------------------------------------------------
    } else if (encounter.equals("Sea Hag")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 3;
      System.out.println();
      healthOfOne = 52;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 30;
      persuadeDc = 12;
      enemyAc = 14;
      enemyType = "Humanoid";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party finds 3 stranded adventurers looking for"
             + " help. However, after getting close to them, they "
             + "reveal themselves as sea hags and attack! -";
      
    // Shambling Mound --------------------------------------------------------
    } else if (encounter.equals("Shambling Mound")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 136;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 1800;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a shambling mound trudging through"
             + " muck! -";

    // Constrictor Snake ------------------------------------------------------
    } else if (encounter.equals("Constrictor Snake")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 13;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 10;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a constrictor snake! -";
      
    // Giant Constrictor Snake ------------------------------------------------
    } else if (encounter.equals("Giant Constrictor Snake")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      System.out.println();
      healthOfOne = 60;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 450;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a giant constrictor snake! -";
      
    // Giant Poisonous Snake --------------------------------------------------
    } else if (encounter.equals("Giant Poisonous Snake")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 15;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a giant poisonous snake! -";
      
    // Spider -----------------------------------------------------------------
    } else if (encounter.equals("Spider")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1);
      healthOfOne = 26;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 20;
      tameDc = 15;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " giant spiders! -";
      
    // Stirge -----------------------------------------------------------------
    } else if (encounter.equals("Stirge")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 2;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 10;
      tameDc = 10;
      enemyAc = 14;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies + " stirges! -";
      
    // Su-monster -------------------------------------------------------------
    } else if (encounter.equals("Su-monster")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1) + 1;
      healthOfOne = 27;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      tameDc = 25;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " su-monsters! -";
      
    // Swarm of Bats ----------------------------------------------------------
    } else if (encounter.equals("Swarm of Bats")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      tameDc = 25;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " swarms of bats! -";
      
    // Swarm of Insects -------------------------------------------------------
    } else if (encounter.equals("Swarm of Insects")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      tameDc = 25;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " swarms of insects! -";
      
    // Swarm of Quippers ------------------------------------------------------
    } else if (encounter.equals("Swarm of Quippers")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 28;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 20;
      tameDc = 25;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " swarms of quippers in the water! -";
      
    // Tiger ------------------------------------------------------------------
    } else if (encounter.equals("Tiger")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 12;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 10;
      tameDc = 20;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a tiger! -";
      
    // Tri-flower Frond -------------------------------------------------------
    } else if (encounter.equals("Tri-flower Frond")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 0;
      intimidateDc = 30;
      tameDc = 30;
      enemyAc = 10;
      enemyType = "Beast";
      expForOne = 100;
      numberOfAttacks = 3;
      // Stats ----------------------------------------------
      
      return "- The party wanders into a patch of " + numberOfEnemies
             + " tri-flower fronds! -";
      
    // Troll ------------------------------------------------------------------
    } else if (encounter.equals("Troll")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 84;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 25;
      persuadeDc = 20;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 1800;
      numberOfAttacks = 3;
      // Stats ----------------------------------------------
      
      return "- A hungry troll attacks the party! -";
      
    // Ghoul ------------------------------------------------------------------
    } else if (encounter.equals("Ghoul")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a group of " + numberOfEnemies
             + " ghouls! -";
    
    // Skeleton----------------------------------------------------------------
    } else if (encounter.equals("Skeleton")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 13;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 13;
      enemyType = "Humanoid";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a group of " + numberOfEnemies
             + " skeletons! -";
      
    // Specter ----------------------------------------------------------------
    } else if (encounter.equals("Specter")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 22;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 200;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a group a specter! -";
      
    // Wight ------------------------------------------------------------------
    } else if (encounter.equals("Wight")) {
      // Stats ----------------------------------------------
      numberOfEnemies = 1;
      healthOfOne = 45;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 15;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 14;
      enemyType = "Humanoid";
      expForOne = 700;
      numberOfAttacks = 2;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by a group a wight! -";
      
    // Zombie -----------------------------------------------------------------
    } else if (encounter.equals("Zombie")) {
      zombieRoll = (int) (Math.random() * 10 + 1);
      // Stats ----------------------------------------------
      if (zombieRoll >= 1 && zombieRoll <= 3) {
        numberOfEnemies = (int) (Math.random() * 6 + 1)
                          + (int) (Math.random() * 6 + 1)
                          + (int) (Math.random() * 6 + 1);
        healthOfOne = 22;
        enemyHealth = healthOfOne * numberOfEnemies;
        runDc = 10;
        intimidateDc = 30;
        persuadeDc = 30;
        enemyAc = 8;
        enemyType = "Humanoid";
        expForOne = 50;
        numberOfAttacks = 1;
        // Stats ----------------------------------------------
        
        return "- The party is attacked by " + numberOfEnemies + " zombies! -";

      } else if (zombieRoll == 4 || zombieRoll == 5) {
        // Stats ----------------------------------------------
        numberOfEnemies = 1;
        healthOfOne = 68;
        enemyHealth = healthOfOne * numberOfEnemies;
        runDc = 10;
        intimidateDc = 30;
        tameDc = 30;
        enemyAc = 14;
        enemyType = "Beast";
        expForOne = 700;
        numberOfAttacks = 1;
        // Stats ----------------------------------------------
        
        return "- The party is attacked by an ankylosaurus zombie! -";
        
      } else if (zombieRoll == 6 || zombieRoll == 7) {
        // Stats ----------------------------------------------
        numberOfEnemies = (int) (Math.random() * 4 + 1);
        healthOfOne = 59;
        enemyHealth = healthOfOne * numberOfEnemies;
        runDc = 15;
        intimidateDc = 30;
        tameDc = 30;
        enemyAc = 11;
        enemyType = "Beast";
        expForOne = 700;
        numberOfAttacks = 5;
        // Stats ----------------------------------------------
        
        return "- The party is attacked by " + numberOfEnemies
               + " girallon zombies! -";
        
      }  else if (zombieRoll == 8 || zombieRoll == 9) {
        // Stats ----------------------------------------------
        numberOfEnemies = (int) (Math.random() * 4 + 1);
        healthOfOne = 85;
        enemyHealth = healthOfOne * numberOfEnemies;
        runDc = 15;
        intimidateDc = 30;
        tameDc = 30;
        enemyAc = 8;
        enemyType = "Beast";
        expForOne = 450;
        numberOfAttacks = 1;
        // Stats ----------------------------------------------
        
        return "- The party is attacked by " + numberOfEnemies
               + " ogre zombies! -";
        
      }  else {
        // Stats ----------------------------------------------
        numberOfEnemies = 1;
        healthOfOne = 136;
        enemyHealth = healthOfOne * numberOfEnemies;
        runDc = 10;
        intimidateDc = 30;
        tameDc = 30;
        enemyAc = 11;
        enemyType = "Beast";
        expForOne = 3900;
        numberOfAttacks = 2;
        // Stats ----------------------------------------------
        
        return "- The party is suddenly attacked by tyranosaurus zombie! -";
      }
      
    // Vegepygmie -------------------------------------------------------------
    } else if (encounter.equals("Vegepygmie")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 9;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 10;
      persuadeDc = 30;
      enemyAc = 13;
      enemyType = "Beast";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " vegepygmies! -";

    // Yellow Musk Creeper And Zombies ----------------------------------------
    } else if (encounter.equals("Yellow Musk Creeper And Zombies")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1)
                        + (int) (Math.random() * 6 + 1);
      healthOfOne = 33;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      persuadeDc = 30;
      enemyAc = 9;
      enemyType = "Humanoid";
      expForOne = 50;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " zombies made by a yellow musk creature! -";
      
    // Yuan-ti ----------------------------------------------------------------
    } else if (encounter.equals("Yuan-ti")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 66;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 30;
      persuadeDc = 15;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 700;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies + " yuan-ti! -";
      
    // Zhentarim --------------------------------------------------------------
    } else if (encounter.equals("Zhentarim")) {
      // Stats ----------------------------------------------
      numberOfEnemies = (int) (Math.random() * 4 + 1);
      healthOfOne = 11;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      intimidateDc = 10;
      persuadeDc = 10;
      enemyAc = 12;
      enemyType = "Humanoid";
      expForOne = 25;
      numberOfAttacks = 1;
      // Stats ----------------------------------------------
      
      return "- The party is attacked by " + numberOfEnemies
             + " members of the Zhentarim! -";

    //-------------------------------------------------------------------------
    } else {
      // Returning none if there wasn't an encounter.
      return "None";
    }
  }

  //===========================================================================

  public int enemyAttack(String encounter, String attackName,
                         String rollStatus, int attackNumber) {
    //-------------------------------------------------------------------------
    // Enemy Attacks.

    // Albino Dwarves ---------------------------------------------------------
    if (encounter.equals("Albino Dwarves")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 1;
      }
      
    // Aldani -----------------------------------------------------------------
    } else if (encounter.equals("Aldani")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 1;
      }
      
    // Apes -------------------------------------------------------------------
    } else if (encounter.equals("Ape")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 3;
      }
      
    // Assassin Vine ----------------------------------------------------------
    } else if (encounter.equals("Assassin Vine")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1) + 4
               // Poison damage
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
      }
      
    // Axe Beak ---------------------------------------------------------------
    } else if (encounter.equals("Axe Beak")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 2;
      }
      
    // Baboons ----------------------------------------------------------------
    } else if (encounter.equals("Baboon")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 1;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) - 1;
      }
      
    // Cannibal ---------------------------------------------------------------
    } else if (encounter.equals("Cannibal")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 1;
      }
      
    // Crocodile --------------------------------------------------------------
    } else if (encounter.equals("Crocodile")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 10 + 1) + 2;
      }
      
    // Cyclops ----------------------------------------------------------------
    } else if (encounter.equals("Cyclops")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 9;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (int) (Math.random() * 8 + 1) + 6;
      }
      
    // Allosaurus -------------------------------------------------------------
    } else if (encounter.equals("Allosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
               + 6;
      }
      
    // Ankylosaurus -----------------------------------------------------------
    } else if (encounter.equals("Ankylosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 7;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 4;
      }

    // Brontosaurus -----------------------------------------------------------
    } else if (encounter.equals("Brontosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 8;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + 5;
      }
      
    // Deinonychus ------------------------------------------------------------
    } else if (encounter.equals("Deinonychus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 2;
      }
      
    // Dimetrodon -------------------------------------------------------------
    } else if (encounter.equals("Dimetrodon")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 2;
      }
      
    // Hadrosaurus ------------------------------------------------------------
    } else if (encounter.equals("Hadrosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 10 + 1) + 2;
      }
      
    // Pleiosaurus ------------------------------------------------------------
    } else if (encounter.equals("Pleiosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + 4;
      }
      
    // Stegosaurus ------------------------------------------------------------
    } else if (encounter.equals("Stegosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 7;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 5;
      }
      
    // Triceratops ------------------------------------------------------------
    } else if (encounter.equals("Triceratops")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 9;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + 6;
      }
      
    // Tyrannosaurus ----------------------------------------------------------
    } else if (encounter.equals("Tyrannosaurus")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 10;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 12 + 1) + (int) (Math.random() * 12 + 1)
                 + (int) (Math.random() * 12 + 1)
                 + (int) (Math.random() * 12 + 1) + 7;
        } else {
          return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
                 + (int) (Math.random() * 8 + 1) + 7;
        }
      }
      
    // Velociraptor -----------------------------------------------------------
    } else if (encounter.equals("Velociraptor")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 6 + 1) + 2;
        } else {
          return (int) (Math.random() * 4 + 1) + 2;
        }
      }

    // Elbis ------------------------------------------------------------------
    } else if (encounter.equals("Elbis")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + 3;
      }

    // Firenewt ---------------------------------------------------------------
    } else if (encounter.equals("Firenewt")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 1;
      }
      
    // Flail Snail ------------------------------------------------------------
    } else if (encounter.equals("Flail Snail")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 3;
      }
      
    // Flying Monkey ----------------------------------------------------------
    } else if (encounter.equals("Flying Monkey")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 1;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) - 3;
      }
      
    // Flying Snake -----------------------------------------------------------
    } else if (encounter.equals("Flying Snake")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (int) (Math.random() * 4 + 1) + 1;
      }
      
    // Frost Giant ------------------------------------------------------------
    } else if (encounter.equals("Frost Giant")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 9;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 12 + 1) + (int) (Math.random() * 12 + 1)
               + (int) (Math.random() * 12 + 1) + 6;
      }
      
    // Giant Boar -------------------------------------------------------------
    } else if (encounter.equals("Giant Boar")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 3;
      }
      
    // Giant Crocodile --------------------------------------------------------
    } else if (encounter.equals("Giant Crocodile")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
                 + (int) (Math.random() * 10 + 1) + 5;
          
        } else {
          return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
                 + 5;
        }
      }

    // Giant Frog -------------------------------------------------------------
    } else if (encounter.equals("Giant Frog")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 1;
      }
      
    // Giant Lizard -----------------------------------------------------------
    } else if (encounter.equals("Giant Frog")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 2;
      }
      
    // Giant Scorpion ---------------------------------------------------------
    } else if (encounter.equals("Giant Scorpion")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1 || attackNumber == 2) {
          return (int) (Math.random() * 8 + 1) + 2;
        } else {
          return (int) (Math.random() * 10 + 1) + (int) (Math.random() * 10 + 1)
                 + (int) (Math.random() * 10 + 1)
                 + (int) (Math.random() * 10 + 1) + 2;
        }
      }
      
    // Giant Snapping Turtle --------------------------------------------------
    } else if (encounter.equals("Giant Snapping Turtle")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 4;
      }
      
    // Giant Wasp -------------------------------------------------------------
    } else if (encounter.equals("Giant Wasp")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 2;
      }
      
    // Girallons --------------------------------------------------------------
    } else if (encounter.equals("Girallons")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 4;
      }

    // Goblins ----------------------------------------------------------------
    } else if (encounter.equals("Goblin")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 2;
      }
    
    // Grung ------------------------------------------------------------------
    } else if (encounter.equals("Grung")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 4 + 1)
               + (int) (Math.random() * 4 + 1) + 3;
      }
      
    // Jaculis ----------------------------------------------------------------
    } else if (encounter.equals("Jaculis")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 2;
      }
      
    // Kamadans ---------------------------------------------------------------
    } else if (encounter.equals("Kamadans")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 6 + 1) + 3;
        } else {
          return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + (int) (Math.random() * 6 + 1) + 3;
        }
      }
      
    // Lizardfolk -------------------------------------------------------------
    } else if (encounter.equals("Lizardfolk")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 2;
      }
      
    // Magmin -----------------------------------------------------------------
    } else if (encounter.equals("Magmin")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
      }
      
    // Mantrap ----------------------------------------------------------------
    } else if (encounter.equals("Mantrap")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
      }
      
    // Maphit -----------------------------------------------------------------
    } else if (encounter.equals("Maphit")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + 1;
      }

    // Pterafolk --------------------------------------------------------------
    } else if (encounter.equals("Pterafolk")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
                 + 2;
        } else {
          return (int) (Math.random() * 6 + 1) + 2;
        }
      }

    // Red Wizard -------------------------------------------------------------
    } else if (encounter.equals("Red Wizard")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 1;
      }
      
    // Sea Hag ----------------------------------------------------------------
    } else if (encounter.equals("Sea Hag")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 6;
      }
      
    // Shambling Mound --------------------------------------------------------
    } else if (encounter.equals("Shambling Mound")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 7;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + 4;
      }
      
    // Constrictor Snake ------------------------------------------------------
    } else if (encounter.equals("Constrictor Snake")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 2;
      }
      
    // Giant Constrictor Snake ------------------------------------------------
    } else if (encounter.equals("Giant Constrictor Snake")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + 4;
      }
      
    // Giant Poisonous Snake --------------------------------------------------
    } else if (encounter.equals("Giant Poisonous Snake")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 4;
      }
      
    // Spider -----------------------------------------------------------------
    } else if (encounter.equals("Spider")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 6;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
               + (int) (Math.random() * 8 + 1) + 3;
      }
      
    // Stirge -----------------------------------------------------------------
    } else if (encounter.equals("Stirge")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + 3;
      }
      
    // Su-monster -------------------------------------------------------------
    } else if (encounter.equals("Su-monster")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 4 + 1) + 2;
        } else {
          return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
                 + (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
                 + 3;
        }
      }
      
    // Swarm of Bats ----------------------------------------------------------
    } else if (encounter.equals("Swarm of Bats")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1);
      }
      
    // Swarm of Insects -------------------------------------------------------
    } else if (encounter.equals("Swarm of Insects")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (int) (Math.random() * 4 + 1);
      }
      
    // Swarm of Quippers ------------------------------------------------------
    } else if (encounter.equals("Swarm of Quippers")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1);
      }
      
    // Tiger ------------------------------------------------------------------
    } else if (encounter.equals("Tiger")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 10 + 1) + 3;
      }
      
    // Tri-flower Frond -------------------------------------------------------
    } else if (encounter.equals("Tri-flower Frond")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 2;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 4 + 1)
               + (int) (Math.random() * 4 + 1);
      }
      
    // Troll ------------------------------------------------------------------
    } else if (encounter.equals("Troll")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 7;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 6 + 1) + 4;
        } else {
          return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + 4;
        }
      }

    // Ghoul ------------------------------------------------------------------
    } else if (encounter.equals("Ghoul")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 2;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + 2;
      }

    // Skeleton----------------------------------------------------------------
    } else if (encounter.equals("Skeleton")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 2;
      }
      
    // Specter ----------------------------------------------------------------
    } else if (encounter.equals("Specter")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
               + (int) (Math.random() * 6 + 1);
      }
      
    // Wight ------------------------------------------------------------------
    } else if (encounter.equals("Wight")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 10 + 1) + 2;
      }
      
    // Zombie
    } else if (encounter.equals("Zombie")) {
      // Normal Zombie.
      if (zombieRoll >= 1 && zombieRoll <= 3) {
        if (attackName.equals("Attack Roll")) {
          return rollD20(rollStatus) + 3;
        } else if (attackName.equals("Damage Roll")) {
          return (int) (Math.random() * 6 + 1) + 1;
        }

      // Anylosaurus Zombie.
      } else if (zombieRoll == 4 || zombieRoll == 5) {
        if (attackName.equals("Attack Roll")) {
          return rollD20(rollStatus) + 6;
        } else if (attackName.equals("Damage Roll")) {
          return (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + (int) (Math.random() * 6 + 1) + (int) (Math.random() * 6 + 1)
                 + 4;
        }
        
      // Garillon Zombie.
      } else if (zombieRoll == 6 || zombieRoll == 7) {
        if (attackName.equals("Attack Roll")) {
          return rollD20(rollStatus) + 4;
        } else if (attackName.equals("Damage Roll")) {
          return (int) (Math.random() * 6 + 1) + 4;
        }
        
      // Ogre Zombie.
      }  else if (zombieRoll == 8 || zombieRoll == 9) {
        if (attackName.equals("Attack Roll")) {
          return rollD20(rollStatus) + 6;
        } else if (attackName.equals("Damage Roll")) {
          return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
                 + 4;
        }
        
      // Tyrannosaurus Zombie.
      } else {
        if (attackNumber == 1) {
          return (int) (Math.random() * 12 + 1) + (int) (Math.random() * 12 + 1)
                 + (int) (Math.random() * 12 + 1)
                 + (int) (Math.random() * 12 + 1) + 7;
        } else {
          return (int) (Math.random() * 8 + 1) + (int) (Math.random() * 8 + 1)
                 + (int) (Math.random() * 8 + 1) + 7;
        }
      }

    // Vegepygmie -------------------------------------------------------------
    } else if (encounter.equals("Vegepygmie")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 2;
      }

    // Yellow Musk Creeper And Zombies ----------------------------------------
    } else if (encounter.equals("Yellow Musk Creeper And Zombies")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 8 + 1) + 1;
      }

    // Yuan-ti ----------------------------------------------------------------
    } else if (encounter.equals("Yuan-ti")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        if (attackNumber == 1) {
          return (int) (Math.random() * 4 + 1) + (int) (Math.random() * 6 + 1)
                 + (int) (Math.random() * 6 + 1) + 3;
        } else {
          return (int) (Math.random() * 6 + 1) + 3;
        }
      }
      
    // Zhentarim --------------------------------------------------------------
    } else if (encounter.equals("Zhentarim")) {
      if (attackName.equals("Attack Roll")) {
        return rollD20(rollStatus) + 3;
      } else if (attackName.equals("Damage Roll")) {
        return (int) (Math.random() * 6 + 1) + 1;
      }
    }

    return 0;
  }
}
