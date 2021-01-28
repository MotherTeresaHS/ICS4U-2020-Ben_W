/*
* The Main program is a program which runs my game.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-6 
*/

//=============================================================================

//-------------------------------------------------------------------------
// Imports:

import java.util.Scanner;  // Import the Scanner class

//=============================================================================

public class Main {

  //===========================================================================
  
  public static String lastWeapon = "";
  public static int lastWeaponNumber = -1;
  
  //===========================================================================

  /**
   * Makes the program sleep.
   */
  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }
  
  //===========================================================================

  /**
   * Makes the weapon attack.
   */
  public static String weaponAttack(String type, Fighter someFighter,
                                    Encounter someEncounter, Dice someDice,
                                    Attack someAttack, String encounter) {
    // Variable for the creature's attack roll status.
    String attackRollStatus = "";
    // Variable for how much damage a creature has done.
    int damage = 0;
    // For attack rolls.
    int attackRoll = 0;
    // Number of enemies killed in one turn.
    int enemiesKilled = 0;
    int attackNumber = 0;

    if (type.equals("Bonus Action")
        && !someAttack.attackProperty1(lastWeapon).equals("Light")) {
      return "You are unable to make an attack as a bonus action.";
    }
    // Finding the weapon -----------------------------------------------------
    // Clearing the screen.
    clearScreen();
    // Printing out info on the current character.
    System.out.println(someFighter.getCharacterInfo());
    // Showing the player their weapon choices.
    System.out.println(someFighter.showWeapons());
    // Asking them which weapon they would like to use.
    System.out.println("Which weapon would you like to attack with?");
    // Creating a scanner to scan the player's various choices.
    Scanner scanChoice = new Scanner(System.in);
    // Getting which weapon they would like to use and finding it.
    String weaponNumber = scanChoice.nextLine();
    String foundWeapon = someFighter.findWeapon(weaponNumber);
    // Going back to the main menu.
    if (weaponNumber.equals("B") || weaponNumber.equals("b")) {
      return "Going back...";
    // If it couldn't find weapon.
    } else if (foundWeapon.equals("Null")) {
      System.out.println("\u001B[31mX Couldn't find weapon X\u001B[0m");
    // Making sure they arent braking any rules on a bonus action.
    } else if (type.equals("Bonus Action")
               && (!someAttack.attackProperty1(foundWeapon).equals("Light")
               || lastWeaponNumber == Integer.parseInt(weaponNumber))) {
      return "You are unable to make an attack as a bonus action.";
    // If it did find the weapon.
    } else {
      // Setting the last weapon number to the current one.
      lastWeaponNumber = Integer.parseInt(weaponNumber);
      // Make an attack for each attack the character can make.
      for (attackNumber = 0; attackNumber < someFighter.numberOfAttacks;
           attackNumber++) {
        // Attack rolls -------------------------------------------------------
        // Telling the player that they made an attack.
        System.out.println("");
        System.out.println(someFighter.name + " made an attack with "
                           + "their " + foundWeapon + "!");
        wait(500);
        // Making the attack roll ---------------------------------------
        attackRoll = someFighter.makeAttackRoll(someAttack
                                                .attackProperty1(foundWeapon),
                                                someAttack
                                                .attackProperty2(foundWeapon));
        // Telling the player what the character rolled.
        System.out.println(someFighter.name + " rolled a " + attackRoll
                           + " to hit.");
        wait(500);
        // If it hits:
        if (attackRoll > someEncounter.enemyAc) {
          // Damage rolls ---------------------------------------------------
          // Getting how much damage the player did.
          damage = someAttack.makeAttack(someFighter.strength,
                                         someFighter.dexterity, foundWeapon,
                                         someFighter.criticalHit,
                                         someFighter.fightingStyle, 
                                         someFighter.proficiencyBonus);
          // Fighting style (Dueling) -----------------------------------
          // Checking if the player picked a two handed or ranged.
          if (someAttack.attackProperty1(foundWeapon).equals("Two-Handed")
              || someAttack.attackProperty2(foundWeapon).equals("Ranged")) {
            System.out.print("");
            // Making sure that the player has the fighting style
          } else if (someFighter.fightingStyle.equals("Dueling")) {
            damage += 2;
          }
          // Checking for crits.
          if (someFighter.criticalHit) {
            // Telling them that they scored a crit.
            System.out.println("\n---------------\n\033[0;93m CRITICAL"
                                + " HIT!\u001B[0m\n---------------");
            wait(500);
          }
          // Applying the damage ----------------------------------------
          System.out.println("\u001B[32m" + someFighter.name + " dealt "
                             + damage + " damage!\u001B[0m");
          wait(500);
          // Subtracking the damage fom their health.
          someEncounter.enemyHealth -= damage;
          // Keeping track of how many enemies were killed.
          enemiesKilled = 0;
          // Kills ------------------------------------------------------
          // Counting how many enemies were killed.
          while (true) {
            // Checking if the health is below the threshhold.
            if (someEncounter.enemyHealth < someEncounter.healthOfOne
                * (someEncounter.numberOfEnemies - 1)
                && someEncounter.numberOfEnemies - 1 != 0) {
              // Adding 1 to enemies killed.
              enemiesKilled++;
              // Subtrackign 1 from number of enemies.
              someEncounter.numberOfEnemies--;
              // If its done.
            } else {
              // End the count..
              break;
            }
          }
          // If the player killed any enemies
          if (enemiesKilled > 0) {
            System.out.println("");
            System.out.println(someFighter.name + " killed " + enemiesKilled
                               + " " + encounter);
            wait(500);
          }
        // Misses -------------------------------------------------------
        // Else if they miss:
        } else {
          System.out.println("\u001B[31mX " + someFighter.name
                             + " missed " + encounter + " X\u001B[0m");
        }
      }
      return "";
    }
    return "";
  }

  //===========================================================================

  /**
   * Runs the combat.
   */
  public static String runEncounter(String encounter, Weather someWeather,
                                    Dice someDice, Fighter someFighter,
                                    Attack someAttack,
                                    Encounter someEncounter) {
    //-------------------------------------------------------------------------
    // Scanners
    // Creating a scanner to scan the player's various choices.
    Scanner scanChoice = new Scanner(System.in);
    // Creating a scanner to scan for enter to be pressed.
    Scanner scanForEnter = new Scanner(System.in);
    // Clearing screen.
    clearScreen();
    // Variables --------------------------------------------------------------
    // Variable checking for enter.
    String enter;
    // Variable for the player's decision.
    String playerChoice;
    // Variable for the creature's ability roll status.
    String abilityRollStatus;
    // Variable for how much damage a creature has done.
    int damage = 0;
    // Which action is this?
    int actionNumber;
    // What the player chose to so.
    String playerDid;
    // Did they escaep?
    String escapeStatus;
    // Death Saves
    int deathSaveRoll;
    // Status of the attack roll.
    String attackRollStatus;
    String returnValue;
    
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
    // Encounters.

    // Returning none if there is no encounters today.
    if (encounter.equals("None")) {
      return "None";
    }
    // Printing out the encounter starting text.
    System.out.println(someEncounter.setEncounterStats(encounter));
    // If the encounter is Friendly:
    if (someEncounter.getEncounterType(encounter).equals("Friendly")) {
      wait(3000);
      // Returning to dailyProcedure().
      return "None";
    // If the encounter is Special:
    } else if (someEncounter.getEncounterType(encounter).equals("Special")) {
      wait(3000);
      // Returning to dailyProcedure().
      return "None";
    // If the encounter is Food:
    } else if (someEncounter.getEncounterType(encounter).equals("Food")) {
      wait(3000);
      // Clearing the screen.
      clearScreen();
      // Telling the user that they decided to share their food with the group.
      System.out.println("- After some time, they agree to share their food "
                         + "with you. -");
      // Maxing out the player's food and water.
      someFighter.lbsOfFood += 10;
      someFighter.gallonsOfWater += 10;
      wait(3000);
      // Clearing the screen.
      clearScreen();
      // Returning to dailyProcedure().
      return "None";
    // Otherwise:
    } else {
      wait(3000);
    }
    // Waiting to make sure that the players have seen the text.
    wait(3000);
    // Saving how many enemies there are at the start of the encounter.
    final int startingEnemies = someEncounter.numberOfEnemies;
    //-------------------------------------------------------------------------
    // Combat.
    // While loop to run the combat while there are still enemies.
    while (someEncounter.enemyHealth > 0) {
      //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      // Fighter's Turn
      actionNumber = 0;
      someFighter.numberOfActions = 1;
      while (actionNumber < someFighter.numberOfActions
             && someFighter.isUnconcious == false
             && someFighter.isDead == false) {
        // Clearing the screen.
        clearScreen();
        // Printing out info on the current character.
        System.out.println(someFighter.getCharacterInfo());
        // Asking the user what they want their character to do.
        System.out.println("What will " + someFighter.name + " do?");
        // Printing out what their choices are.
        System.out.println(someFighter.choices());
        // Getting their choice.
        playerChoice = scanChoice.nextLine();
        // What they actually did.
        playerDid = someFighter.makeChoice(playerChoice);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 1: Running away.
        if (playerDid.equals("Run")) {
          if (someFighter.canRun) {
            // Clearing the screen.
            clearScreen();
            // Resetting playerchoice.
            playerChoice = "-1";
            // Printing out info on the current character.
            System.out.println(someFighter.getCharacterInfo());
            // Asking the player how they wish to run.
            System.out.println("How do you want to do this?");
            // Printing out their options.
            System.out.println(someFighter.run(someEncounter.enemyType,
                                               someEncounter.runDc,
                                               someEncounter.intimidateDc,
                                               someEncounter.persuadeDc,
                                               someEncounter.tameDc, encounter,
                                               "-1"));
            // Getting their choice.
            playerChoice = scanChoice.nextLine();
            // Determining if they escaped or not.
            escapeStatus = someFighter.run(someEncounter.enemyType,
                                           someEncounter.runDc,
                                           someEncounter.intimidateDc,
                                           someEncounter.persuadeDc,
                                           someEncounter.tameDc, encounter,
                                           playerChoice);
            // Successfully escaped.
            if (escapeStatus.equals("Succsess")) {
              System.out.println("\u001B[32m- Got away safely -\u001B[0m");
              wait(1000);
              return "None";
            // Going back to the main menu.
            } else if (escapeStatus.equals("Back")) {
              System.out.println("");
            // Invalid.
            } else if (escapeStatus.equals("Invalid Input")) {
              System.out.println(escapeStatus);
              wait(1000);
            // Otherwise:
            } else {
              System.out.println(escapeStatus);
              wait(1000);
              // Increasing the action number and ending their turn.
              actionNumber++;
            }
          } else {
            // Printing out that the player is too tired to run.
            System.out.println(someFighter.name + " is too tired to run.");
          }
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 2: Attack.
        } else if (playerDid.equals("Attack")) {
          returnValue = weaponAttack("Action", someFighter, someEncounter,
                                     someDice, someAttack, encounter);
          if (returnValue.equals("")) {
            // Increasing the action number and ending their turn.
            actionNumber++;
          } else {
            System.out.println(returnValue);
            wait(1000);
          }
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 3: Dodge.
        } else if (playerDid.equals("Dodge")) {
          // Telling them that their character dodged.
          System.out.println("\u001B[32mØ " + someFighter.name + " has braced"
                             + " themselves for oncoming attacks Ø\u001B[0m");
          // Increasing the action number and ending their turn.
          actionNumber++;
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 4: Action Surge.
        } else if (playerDid.equals("Action Surge")) {
          // Telling them that their character action Surged.
          System.out.println(someFighter.actionSurge());
          wait(1000);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option N: Doing nothing.
        } else if (playerDid.equals("Nothing")) {
          // Printing out that the player didnt do anything.
          System.out.println(someFighter.name + " did nothing.");
          // Increasing the action number and ending their turn.
          actionNumber++;
        }
        wait(1000);
      }
      //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      // Fighter's Bonus Action
      while (someFighter.isUnconcious == false && someFighter.isDead == false) {
        // Clearing the screen.
        clearScreen();
        // Printing out info on the current character.
        System.out.println(someFighter.getCharacterInfo());
        // Asking the user what they want their character to do.
        System.out.println("What will " + someFighter.name
                           + " do as a bonus action?");
        // Showing them their choices.
        System.out.println(someFighter.bonusActions());
        // getting their choice.
        playerChoice = scanChoice.nextLine();
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 1: Second Wind
        if (playerChoice.equals("1")) {
          // Telling the user how many hit points they regained.
          System.out.println(someFighter.secondWind(someDice.rollD10()));
          // Ending their turn.
          break;
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Option 2: Attack.
        } else if (playerChoice.equals("2")) {
          returnValue = weaponAttack("Bonus Action", someFighter, someEncounter,
                                     someDice, someAttack, encounter);
          if (returnValue.equals("")) {
            // Increasing the action number and ending their turn.
            break;
          } else {
            System.out.println(returnValue);
            wait(1000);
          }
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Nothing
        } else if (playerChoice.equals("N") || playerChoice.equals("n")) {
          System.out.println(someFighter.name + " did nothing for their bonus " 
                             + "action.");
          break;
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
        // Invalid
        } else {
          System.out.println("\u001B[31mX Not an option X\u001B[0m");
          // Waiting to make sure that they see the text.
          wait(1000);
        }
      }
      //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      // Death Saves
      if (someFighter.isUnconcious && someFighter.isDead == false
          && someFighter.currentHitPoints <= 0) {
        clearScreen();
        System.out.println(someFighter.getCharacterInfo());
        // Making the death save.
        System.out.println(someFighter.makeDeathSave());
      }
      if (someFighter.isDead) {
        return ("Dead");
      }
      someFighter.currentArmorClass = someFighter.armorClass;
      //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      // Shield
      // Checking if the character has the shield bonus already.
      if (someFighter.hasShieldEquiped && someFighter.hasShield) {
        someFighter.currentArmorClass += 2;
        if (someFighter.fightingStyle.equals("Defense")) {
          someFighter.currentArmorClass += 1;
        }
      }
      //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
      // Fighting Style (Protection)
      if (someFighter.fightingStyle.equals("Protection")) {
        someFighter.isProtectionReady = true;
      }
      wait(4000);
      if (someEncounter.enemyHealth <= 0) {
        break;
      }
      //-----------------------------------------------------------------------
      // Enemy's Turn.
      // Clearing the screen.
      clearScreen();
      // Stating that it's the enemies turn.
      System.out.println("\u001B[31m- " + encounter + "'s turn -\u001B[0m");
      // Waiting to make sure that they see the text.
      // For every enemy in the encounter.
      for (int enemyNumber = 0; enemyNumber < someEncounter.numberOfEnemies;
           enemyNumber++) {
        if (someFighter.isUnconcious) {
          break;
        }
        // For every attack the enemy has.
        for (int attackNumber = 0; attackNumber < someEncounter.numberOfAttacks;
             attackNumber++) {
          wait(1000);
          // Attacking ----------------------------------------------------------
          System.out.println(encounter + " " + (enemyNumber + 1) + " attacks "
                             + someFighter.name);
          // Setting the attack roll status to neutral.
          attackRollStatus = "";
          // If the player decided to dodge:
          if ((someFighter.fightingStyle.equals("Protection") && someFighter
               .isProtectionReady) || someFighter.dodge) {
            // Setting the attack roll status to disadvantage.
            attackRollStatus = "Disadvantage";
            someFighter.isProtectionReady = false;
          }
          // Attack roll --------------------------------------------------------
          int enemyAttackRoll = someEncounter.enemyAttack(encounter,
                                                          "Attack Roll",
                                                          attackRollStatus,
                                                          (attackNumber + 1));
          // Checking to see if it hit.
          if (enemyAttackRoll > someFighter.currentArmorClass
              && someFighter.isUnconcious == false) {
            // Damage roll ----------------------------------------------------
            // If they hit:
            // Getting the damage dealt.
            damage = someEncounter.enemyAttack(encounter, "Damage Roll",
                                               attackRollStatus,
                                               (attackNumber + 1));
            // Applying the damage.
            someFighter.currentHitPoints -= damage;
            // Telling the player how much damage they took.
            System.out.println("\u001B[31m☠ " + encounter + " " 
                               + (enemyNumber + 1) + " has hit "
                               + someFighter.name + " for " + damage
                               + " damage ☠\u001B[0m");
            // Checking if the fighter is dead.
            if (someFighter.currentHitPoints <= 0) {
              wait(1000);
              // Telling the user that their character has died.
              if (someFighter.name.equals("Bobby")) {
                System.out.println("\u001B[31m☠ " + someFighter.name 
                                   + " did what he had to do ☠\u001B[0m");
              } else {
                System.out.println("\u001B[31m☠ " + someFighter.name 
                                   + " has fallen ☠\u001B[0m");
              }
              someFighter.isUnconcious = true;
            }
          // Otherise if the enemy missed:
          } else {
            System.out.println("\u001B[32m" + encounter + " "
                               + (enemyNumber + 1) + " missed "
                               + someFighter.name + "!\u001B[0m");
          }
        }
      }
      if (someFighter.currentHitPoints >= 1 && someFighter.isUnconcious) {
        break;
      }
    }
    if (someFighter.currentHitPoints >= 1 && someFighter.isUnconcious) {
      clearScreen();
      System.out.println("The " + encounter + " decided to leave you alone");
      wait(5000);
      return "0";
    }
    
    // End of battle ----------------------------------------------------------
    clearScreen();
    System.out.println("\u001B[32m The party defeated the " + encounter
                       + "s!\u001B[0m");
    System.out.println("\u001B[32m Everyone gained " + (someEncounter.expForOne
                                                        * startingEnemies / 1)
                                                        + "exp!\u001B[0m");
    someFighter.experiencePoints += someEncounter.expForOne * startingEnemies
                                    / 1;
    wait(5000);
    return "0";
  }

  //===========================================================================

  /**
   * Daily procedure.
   */
  public static String dailyProcedure(ChultMap someMap, Weather someWeather,
                                      Dice someDice, Fighter someFighter,
                                      Attack someAttack,
                                      Encounter someEncounter) {

    //-------------------------------------------------------------------------
    // Scanners

    // Creating a scanner to scan for direction.
    Scanner scanDirection = new Scanner(System.in);

    //-------------------------------------------------------------------------
    // Info on the day.

    // Clearing the screen.
    clearScreen();

    // Getting the day's weather.
    someWeather.setWeather(someDice.rollD20(""));
    // Showing the day's number.
    System.out.println("- Day: " + someWeather.dayNumber + " -");
    // Showing the day's weather.
    System.out.println("Today's Forcast: " + someWeather.getWeather());
    // Creating a scanner to scan for enter.
    Scanner scanForEnter = new Scanner(System.in);
    // Making sure that the player has seen the day's conditions.
    System.out.println("");
    System.out.println("[Press ENTER to continue]");
    String enter = scanForEnter.nextLine();
    
    // Clearing the screen.
    clearScreen();
    
    //-------------------------------------------------------------------------
    // Showing the world map.

    // Showing the player the world map.
    System.out.println("-  SHOWING MAP   -");
    System.out.println(someMap.showMap());
    
    //-------------------------------------------------------------------------
    // Getting direction.

    int playersLastPosition = someMap.playerPosition;
    String direction = "";

    // Making sure the player moves.
    while (true) {

      // Getting the direction to move from the player.
      System.out.println("What direction do you want to move in?");
      System.out.println("[▲ = 1] [▼ = 2] [◄ = 3] [► = 4] [Stay = 5]");
      direction = scanDirection.nextLine();
      
      // Clear screen.
      clearScreen();
      
      // Making sure that the input is valid.
      if (direction.equals("1") || direction.equals("2")
          || direction.equals("3") || direction.equals("4")
          || direction.equals("5")) {
        
        String navigationStatus = "";

        // having the player make a check to see if they're lost and moving
        // them.
        // If the weather is a Tropical Storm or the player has exhaustion:
        if (someWeather.getWeather().equals("Tropical storm")
            || someFighter.getExhaustion() != 0) {
          // Giving them disadvantage on the roll.
          navigationStatus = "Disadvantage";
      
        // If the weather is Light Rain or Heavy Rain:
        } else if (someWeather.getWeather().equals("Occasional light rain")
                   || someWeather.getWeather().equals("Heavy rain")) {
          // Making the roll normal.
          navigationStatus = "";
        }
        
        // Making a survival check.
        int check = someFighter.makeCheck("Wis", "Survival", "",
                                          someDice.rollD20(navigationStatus));
                                        
        // Showing the result of the check depending on where it was made..
        System.out.println("Survival Check: " + check);
        // Checking if the player is on a beach.
        if (someMap.map[someMap.playerPosition] [0] == 0) {
          // Failed the check.
          if (check < 10) {
            System.out.println("\u001B[31mX the party failed Survival check "
                             + "X\u001B[0m");
          // Passed the check.
          } else {
            System.out.println("\u001B[32m🗸 the party passed Survival check "
                             + "🗸\u001B[0m");
          }
        // Checking if the player is on anywhere else.
        } else {
          // Failed the check.
          if (check < 15) {
            System.out.println("\u001B[31mX the party failed Survival check "
                             + "X\u001B[0m");
          // Passed the check.
          } else {
            System.out.println("\u001B[32m🗸 the party passed Survival check "
                             + "🗸\u001B[0m");
          }
        }

        // Updating the map.
        System.out.println(someMap.movePlayer(someMap.isPlayerLost(direction,
                                                                   check)));
        break;

      } else {
        System.out.println("Invalid direction");
      }
      clearScreen();
    }

    //-------------------------------------------------------------------------
    // Showing the updated world map.

    // Showing the updated map to the player.
    System.out.println("-  SHOWING MAP   -");
    System.out.println(someMap.showMap());
    
    // Making sure that the player has seen the map.
    System.out.println("Here is the new map");
    System.out.println("[Press ENTER to continue]");
    enter = scanForEnter.nextLine();
    clearScreen();
    
    //-------------------------------------------------------------------------
    // Encounters
    
    //Checking for encounters.
    String encounter;
    if (someMap.playerPosition % 74 == 0) {
      encounter = someMap.rollEncounter(20, 29);
    } else {
      encounter = someMap.rollEncounter(someDice.rollD20(""),
                                        someDice.rollD100());
    }
    String end = runEncounter(encounter, someWeather, someDice, someFighter,
                              someAttack, someEncounter);
    if (end.equals("Dead")) {
      clearScreen();
      return ("- GAME OVER -");
    }
    if (someMap.playerPosition % 74 == 0) {
      clearScreen();
      return ("Won");
    }
    clearScreen();

    //-------------------------------------------------------------------------
    // The day's end results
    System.out.println("- The Day's Results");

    if (encounter.equals("None")) {
      System.out.println("\u001B[32m- No Encounters Today -\u001B[0m");
    }
    System.out.println("Current Location: " + someMap.getBiome());
    // Giving them some food if they're in a town.
    if (someMap.getBiome().equals("Settlement")) {
      if (someFighter.lbsOfFood <= 10) {
        System.out.println(someFighter.name + " got some food.");
        someFighter.lbsOfFood = 11;
      }
      if (someFighter.gallonsOfWater <= 21) {
        someFighter.gallonsOfWater = 22;
        System.out.println(someFighter.name + " got some water.");
      }
    }
    // Setting this var to 0 so it can see if this character too exhaustion.
    someFighter.tookExhaustion = 0;

    // Side effects of a tropical Storm
    // If they stayed still
    if (someWeather.getWeather().equals("Occasional light rain")
        || someWeather.getWeather().equals("Heavy rain")
        || direction.equals("5")) {
      System.out.print("");

    } else {
      // Fighter gains a level of exhaustion.
      System.out.println(someFighter.gainExhaustion());

      // Fighter makes a CON save to see if they take a second level of
      // exhaustion.
      if (someFighter.makeCheck("Con", "", "Saving Throw",
                                someDice.rollD20("")) < 10) {
        // Fighter gains a level of exhaustion.
        System.out.println(someFighter.gainExhaustion());
      // Passing the Con save.
      } else {
        System.out.print("");
      }
    }

    // Foraging for food.
    int forageRoll;
    String forageStatus = "";
    // Setting the check to disadvantage if they have exhaustion.
    if (someFighter.getExhaustion() != 0) {
      forageStatus = "Disadvantage";
    }

    // Foraging for food.
    System.out.println(someFighter.forage(someDice.rollD20(forageStatus),
                                          someDice.rollD6(), "Food"));
    // Foraging for water.
    System.out.println(someFighter.forage(someDice.rollD20(forageStatus),
                                          someDice.rollD6(), "Water"));
    // Eating.
    System.out.println(someFighter.eat());
    // Drinking.
    System.out.println(someFighter.drink());
    // Remaining food.
    System.out.println("Remaining Food: " + someFighter.lbsOfFood + " lbs");
    // Remaining water.
    System.out.println("Remaining Water: " + someFighter.gallonsOfWater
                       + " gallons");

    // Checking if the fighter has died to to exhaustion.
    if (someFighter.getExhaustion() >= 6) {
      if (someFighter.name.equals("Bobby")) {
        System.out.println("\u001B[31m☠ " + someFighter.name 
                           + " did what he had to do ☠\u001B[0m");
        System.out.println("[Press ENTER to continue]");
        enter = scanForEnter.nextLine();
        return "- GAME OVER -";
      }
      System.out.println("\u001B[31m☠ " + someFighter.name + " has passed "
                         + "away due to exhaustion " + "☠\u001B[0m");
      System.out.println("[Press ENTER to continue]");
      enter = scanForEnter.nextLine();
      clearScreen();
      return ("- GAME OVER -");
    }

    // Waiting for enter.
    System.out.println("[Press ENTER to continue]");
    enter = scanForEnter.nextLine();
    clearScreen();

    //-------------------------------------------------------------------------
    // Long rest
    System.out.println("- The Party Took a Long Rest -");
    // Loosing exhaustion
    System.out.println(someFighter.loseExhaustion());
    someFighter.isUnconcious = false;
    // Next day.
    someWeather.dayNumber += 1;
    // Regaining hp.
    someFighter.currentHitPoints = someFighter.maxHitPoints;
    someFighter.isSecondWindReady = true;
    someFighter.isActionSurgeReady = true;
    System.out.println(someFighter.levelUp());
    someFighter.deathSaveFailure = 0;
    someFighter.deathSaveSuccess = 0;

    // Waiting for enter.
    System.out.println("[Press ENTER to continue]");
    enter = scanForEnter.nextLine();
    clearScreen();
    return ("");
    //-------------------------------------------------------------------------
  }
  //===========================================================================
  
  /**
   * Clears the screen.
   */
  public static void clearScreen() {
    //-------------------------------------------------------------------------
    // Clears the screen.
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    //-------------------------------------------------------------------------
  }
  
  //===========================================================================
  
  /**
   * Check if a string is numeric.
   */
  public static boolean isNumeric(String str) {
    //-------------------------------------------------------------------------
    // Checking if a string is a number.
    try {
      Integer.parseInt(str);
      return true;

    } catch (NumberFormatException e) {
      return false;
    }
    //-------------------------------------------------------------------------
  }
  
  //===========================================================================
  public enum MartialWeaponNames {
    //-------------------------------------------------------------------------
    // List of Martial Weapons.
    Weapon1("Battleaxe"),
    Weapon2("Flail"),
    Weapon3("Glaive"),
    Weapon4("Greataxe"),
    Weapon5("Greatsword"),
    Weapon6("Halberd"),
    Weapon7("Lance"),
    Weapon8("Longsword"),
    Weapon9("Maul"),
    Weapon10("Morningstar"),
    Weapon11("Pike"),
    Weapon12("Rapier"),
    Weapon13("Scimitar"),
    Weapon14("Shortsword"),
    Weapon15("Trident"),
    Weapon16("War Pick"),
    Weapon17("Warhammer"),
    Weapon18("Whip"),
    Weapon19("Blowgun"),
    Weapon20("Hand Crossbow"),
    Weapon21("Heavy Crossbow"),
    Weapon22("Longbow");
    
    // Returning the value.
    private final String weapon;

    MartialWeaponNames(String weapon) {
      this.weapon = weapon;
    }

    public String getWeapon() {
      return this.weapon;
    }
    
    //-------------------------------------------------------------------------
  }
  
  //===========================================================================
  public enum MartialWeaponWeights {
    //-------------------------------------------------------------------------
    // List of Martial Weapon weight.
    Weapon1("4"),
    Weapon2("2"),
    Weapon3("6"),
    Weapon4("7"),
    Weapon5("6"),
    Weapon6("6"),
    Weapon7("6"),
    Weapon8("3"),
    Weapon9("10"),
    Weapon10("4"),
    Weapon11("18"),
    Weapon12("2"),
    Weapon13("3"),
    Weapon14("2"),
    Weapon15("4"),
    Weapon16("2"),
    Weapon17("2"),
    Weapon18("3"),
    Weapon19("1"),
    Weapon20("3"),
    Weapon21("18"),
    Weapon22("2");
    
    // Returning the value.
    private final String weight;

    MartialWeaponWeights(String weight) {
      this.weight = weight;
    }

    public String getWeight() {
      return this.weight;
    }
    
    //-------------------------------------------------------------------------
  }
  
  //===========================================================================
  
  /**
   * Creates the fighter.
   */
  public static void createFighter(Fighter someFighter, String hasPlayed) {
    //-------------------------------------------------------------------------
    // Creating the fighter character with the user.
    
    Scanner scanForEnter = new Scanner(System.in);

    // Title
    System.out.println("                  - The Fighter -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    // If player hasnt played before giving them a short description.
    if (hasPlayed.equals("1")) {
      System.out.print("");
    } else {
      System.out.println("Figher's have an unparalleled mastery with weapons");
      System.out.println("and armor, and a thorough knowledge of the skills of");
      System.out.println("combat. And they are well aquainted with death, both");
      System.out.println("meting it out and staring it defiantly in the face.");
      System.out.println("");
    }

    // Creating a scanner to scan info on the first character.
    Scanner scanFighter = new Scanner(System.in);

    // Giving the fighter a name.
    System.out.println("Insert your fighter's name:");
    someFighter.name = scanFighter.nextLine();
    System.out.println("");
    
    // Clearing the screen.
    clearScreen();

    //-------------------------------------------------------------------------
    // Equipment
    
    // Title
    System.out.println("                    - Equipment -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    
    // Giving the player a short definition of what equipment is.
    if (hasPlayed.equals("1")) {
      System.out.print("");
    } else {
      System.out.println("Each character you make will carry around equipment");
      System.out.println("to use during their adventures. This equipment");
      System.out.println("includes weapons, armor, and mundane items, each");
      System.out.println("their own properties and uses.");
      System.out.println("");
    }

    // First option. ----------------------------------------------------------
    System.out.println("Which of the folowing do you want " + someFighter.name
                       + " to have?");
    System.out.println("[1 = chain mail] or [2 = leather armor and a longbow]");
    System.out.println("");
    
    //Making sure that the player chooses a valid options.
    while (true) {
      String chosenOptionString = scanFighter.nextLine();

      // Making sure that the user inputs a valid option.
      if (isNumeric(chosenOptionString)) {
        // Converting it to an int.
        int chosenOption = Integer.parseInt(chosenOptionString);
        // Making sure it is in the range.
        if (chosenOption == 1) {
          // Giving them the chainmail.
          someFighter.addEquipment("Chainmail", "55", "Armor");
          // Updating their AC.
          someFighter.armorClass = 16;
          break;

        } else if (chosenOption == 2) {
          // Giving them the leather armor.
          someFighter.addEquipment("Leather Armor", "10", "Armor");
          // Updating their AC.
          someFighter.armorClass = 13 + (((someFighter.dexterity
                                           - someFighter.dexterity % 2) - 10)
                                           / 2);
          // Giving them the longbow.
          someFighter.addEquipment("Longbow", "2", "Weapon");
          break;

        } else {
          System.out.println("\u001B[31mThat is not an option.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mThat is not an option.\u001B[0m");
      }
    }
    
    clearScreen();

    // Second option. ----------------------------------------------------------
    // Title
    System.out.println("                    - Equipment -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    System.out.println("Which of the folowing do you want " + someFighter.name
                       + " to have?");
    System.out.println("[1 = a martial weapon and a shield] or "
                       + "[2 = 2 martial weapons]");

    System.out.println("");
    
    int numberOfWeapons;

    //Making sure that the player chooses a valid options.
    while (true) {
      String chosenOptionString = scanFighter.nextLine();

      // Making sure that the user inputs a valid option.
      if (isNumeric(chosenOptionString)) {
        // Converting it to an int.
        int chosenOption = Integer.parseInt(chosenOptionString);
        // Making sure it is in the range.
        if (chosenOption == 1) {
          // letting them choose 1 weapon.
          numberOfWeapons = 1;
          // Giving them the shield.
          someFighter.addEquipment("Shield", "6", "Armor");
          someFighter.hasShield = true;
          break;

        } else if (chosenOption == 2) {
          // letting them choose 1 weapon.
          numberOfWeapons = 2;
          break;

        } else {
          System.out.println("\u001B[31mThat is not an option.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mThat is not an option.\u001B[0m");
      }
    }
    clearScreen();
    
    // Picking weapons --------------------------------------------------------
    
    // Title
    System.out.println("                    - Equipment -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    System.out.println("Here are your posible choices for martial weapons:");
    System.out.println("[1 = Battleaxe] [2 = Flail] [3 = Glaive] [4 = Greatax"
                       + "e]");
    System.out.println("[5 = Greatsword] [6 = Halberd] [7 = Lance] [8 = Longsw" 
                       + "ord]");
    System.out.println("[9 = Maul] [10 = Morningstar] [11 = Pike] [12 = Rapier" 
                       + "]");
    System.out.println("[13 = Scimitar] [14 = Shortsword] [15 = Trident]");
    System.out.println("[16 = War Pick] [17 = Warhammer] [18 = Whip] [19 = Blo"
                       + "wgun]");
    System.out.println("[20 = Hand Crossbow] [21 = Heavy Crossbow] [22 = Longb"
                       + "ow]");

    int weaponsChosen = 0;

    while (true) {
      String chosenOptionString = scanFighter.nextLine();

      // Making sure that the user inputs a valid option.
      if (isNumeric(chosenOptionString)) {
        // Converting it to an int.
        int chosenOption = Integer.parseInt(chosenOptionString);
        // Making sure it is in the range.
        if (chosenOption >= 1 && chosenOption <= 22) {
          
          // Getting the weapon name from the enum.
          MartialWeaponNames n = MartialWeaponNames.valueOf("Weapon"
                                                            + chosenOption);
          // Getting the weapon's weight from the enum.
          MartialWeaponWeights w = MartialWeaponWeights.valueOf("Weapon"
                                                                + chosenOption);
          // Adding the skill to the fighter's proficiencies.
          someFighter.addEquipment(n.getWeapon(), w.getWeight(), "Weapon");
          // Making the number of picked skills increase.
          weaponsChosen++;
          
          if (weaponsChosen == numberOfWeapons) {
            break;
          }

        } else {
          System.out.println("\u001B[31mThat is not an option.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mThat is not an option.\u001B[0m");
      }
    }
    
    clearScreen();

    // Title
    System.out.println("                    - Equipment -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    // Third option. ----------------------------------------------------------
    System.out.println("Which of the folowing do you want " + someFighter.name
                       + " to have?");
    System.out.println("[1 = light crossbow] or [2 = handaxes]");
    System.out.println("");
    
    //Making sure that the player chooses a valid options.
    while (true) {
      String chosenOptionString = scanFighter.nextLine();

      // Making sure that the user inputs a valid option.
      if (isNumeric(chosenOptionString)) {
        // Converting it to an int.
        int chosenOption = Integer.parseInt(chosenOptionString);
        // Making sure it is in the range.
        if (chosenOption == 1) {
          // Giving them the crossbow.
          someFighter.addEquipment("Light Crossbow", "5", "Weapon");
          break;

        } else if (chosenOption == 2) {
          // Giving them the handaxe.
          someFighter.addEquipment("Handaxe", "2", "Weapon");
          // Giving them the handaxe.
          someFighter.addEquipment("Handaxe", "2", "Weapon");
          break;

        } else {
          System.out.println("\u001B[31mThat is not an option.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mThat is not an option.\u001B[0m");
      }
    }
    
    // Clearing the screen.
    clearScreen();
    
    // Title
    System.out.println("                - Fighting Style -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    // If player hasnt played before giving them a short description.
    if (hasPlayed.equals("1")) {
      System.out.print("");
    } else {
      System.out.println("Every fighter has a fighting style. These styles ");
      System.out.println("give you bonuses depending on which weapons you use");
      System.out.println("in combat.");
    }

    // Giving picking a fighting style ----------------------------------------
    System.out.println("Which of the following styles would you like?");
    System.out.println("[1 = Archery] [2 = Defense] [3 = Dueling]");
    System.out.println("[4 = Great Weapon Fighting] [5 = Protection]");
    System.out.println("[6 = Two Weapon Fighting]");
    while (true) {
      String chosenOption = scanFighter.nextLine();
      System.out.println("");
      if (chosenOption.equals("1")) {
        someFighter.fightingStyle = "Archery";
        break;
      } else if (chosenOption.equals("2")) {
        someFighter.fightingStyle = "Defense";
        break;
      } else if (chosenOption.equals("3")) {
        someFighter.fightingStyle = "Dueling";
        break;
      } else if (chosenOption.equals("4")) {
        someFighter.fightingStyle = "Great Weapon Fighting";
        break;
      } else if (chosenOption.equals("5")) {
        someFighter.fightingStyle = "Protection";
        break;
      } else if (chosenOption.equals("6")) {
        someFighter.fightingStyle = "Two Weapon Fighting";
        break;
      } else {
        System.out.println("\u001B[31mThat is not an option.\u001B[0m");
      }
    }

    // Clearing the screen.
    clearScreen();
    System.out.println(someFighter.levelUp());
    if (someFighter.level > 1) {
      wait(1000);
    }
    // Clearing the screen.
    clearScreen();
  }
  
  //===========================================================================
  /**
   * This function handles the input and output of the program.
   */
  public static void main(String[] args) {

    //-------------------------------------------------------------------------
    // Scanners
    
    // Creating a scanner to scan for enter.
    Scanner scanForEnter = new Scanner(System.in);

    //-------------------------------------------------------------------------
    // Objects

    // Creating fighter object.
    Fighter someFighter = new Fighter();

    // Creating map object and setting the player's starting position.
    ChultMap someMap = new ChultMap();
    
    // Creating weather object.
    Weather someWeather = new Weather();
    
    // Creating dice object.
    Dice someDice = new Dice();
    
    // Creating object to get stats for various attacks.
    Attack someAttack = new Attack();

    // Creating object to get encounter stats.
    Encounter someEncounter = new Encounter();
    
    System.out.println("                   JUNGLE OF ANNIHILATION");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="
                       + "-=-=-");
    System.out.println("                     .ed**** ***$$$$be.");
    System.out.println("                   -*           ^****$$$e.");
    System.out.println("                 .*                    $$$c");
    System.out.println("                /                      *4$$b");
    System.out.println("               d  3                      $$$$");
    System.out.println("               $  *                   .$$$$$$");
    System.out.println("              .$  ^c           $$$$$e$$$$$$$$.");
    System.out.println("              d$L  4.         4$$$$$$$$$$$$$$b");
    System.out.println("              $$$$b ^ceeeee.  4$$ECL.F*$$$$$$$");
    System.out.println("  e$**=.      $$$$P d$$$$F $ $$$$$$$$$- $$$$$$");
    System.out.println(" z$$b. ^c     3$$$F *$$$$b   $*$$$$$$$  $$$$**      .="
                       + "**$c");
    System.out.println("4$$$$L        $$P*  *$$b   .$ $$$$$...e$$        .=  e"
                       + "$$$.");
    System.out.println("^*$$$$$c  %..   *c    ..    $$ 3$$$$$$$$$$eF     zP  d"
                       + "$$$$$");
    System.out.println("  ***$$$ec   *   %ce**    $$$  $$$$$$$$$$*    .r* =$$"
                       + "$$P**");
    System.out.println("        **$b.  *c  *$e.    *** d$$$$$*L$$    .d*  e$$*"
                       + "***");
    System.out.println("          ^*$$c ^$c $$$      4J$$$$$% $$$ .e**.eeP*");
    System.out.println("             *$$$$$$* $=e....$*$$**$cz$$* *..d$**");
    System.out.println("               **$$$  *=%4.$ L L$ P3$$$F $$$P*");
    System.out.println("                  *$   *%*ebJLzb$e$$$$$b $P*");
    System.out.println("                    %..      4$$$$$$$$$$ *");
    System.out.println("                     $$$e   z$$$$$$$$$$%");
    System.out.println("                      **$c  *$$$$$$$P*");
    System.out.println("                       .****$$$$$$$$bc");
    System.out.println("                    .-*    .$***$$$****e.");
    System.out.println("                 .-*    .e$*     **$c  ^*b.");
    System.out.println("          .=*****    .e$**          **bc  **$e..");
    System.out.println("        .$*        .z**               ^*$e.   ******e"
                       + ".");
    System.out.println("        $$ee$c   .d*                     **$.        3"
                       + ".");
    System.out.println("        ^*$E*)$..$*                         *   .ee==d"
                       + "%");
    System.out.println("           $.d$$$*                           *  J$$$e"
                       + "*");
    System.out.println("            *****                              *$$$*");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="
                       + "-=-=-");
    System.out.println("                        [PRESS ENTER]"); 
    String enter = scanForEnter.nextLine();
    clearScreen();
  
    //-------------------------------------------------------------------------
    // Intro
    
    // Creating a scanner to scan if this is their first time playing.
    Scanner scanExperience = new Scanner(System.in);
    
    // Checking to see if the player needs an explanation.
    System.out.println("Have you played Jungle Of Annihilation before?");
    System.out.println("[1 = YES] [2 = NO]");
    String hasPlayed = scanExperience.nextLine();
    
    // Clearing the screen.
    clearScreen();
    
    if (hasPlayed.equals("1")) {
      System.out.print("");
    } else {
      // Story Overview.
      System.out.println("                - Game Overview -");
      System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
      System.out.println("Welcome to Jungle Of Annihilation! This is a game");
      System.out.println("based off of the D&D campaign of a similar name, ");
      System.out.println("and as such, it was use many of the same mechanics.");
      System.out.println("This adventure will see you traveling over the");
      System.out.println("vast jungles of the land of Chult in an attempt to");
      System.out.println("escape.");
      System.out.println("");
      System.out.println("To start, we will create your party members.");
      System.out.println("");

      // Looking for enter to continue.
      System.out.println("[Press ENTER to continue]");
      enter = scanForEnter.nextLine();
    }

    // Clearing the screen.
    clearScreen();
  
    //-------------------------------------------------------------------------
    // Setup.

    // Creating fighter.
    createFighter(someFighter, hasPlayed);

    // Generating map.
    someMap.generateMap();
    
    clearScreen();

    //-------------------------------------------------------------------------
    // Entering into the main game loop.
    while (true) {
      String end = dailyProcedure(someMap, someWeather, someDice, someFighter,
                                  someAttack, someEncounter);
      if (end.equals("- GAME OVER -")) {
        System.out.println("- GAME OVER -");
        break;
      } else if (end.equals("Won")) {
        System.out.println("You Successfully Escaped The Jungle!");
        break;
      }
    }
    //-------------------------------------------------------------------------
  }
}
