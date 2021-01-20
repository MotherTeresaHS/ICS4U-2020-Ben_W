/*
* The Main program is a program which runs my game.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-6 
*/

///////////////////////////////////////////////////////////////////////////////

//-------------------------------------------------------------------------
// Imports:

import java.util.Scanner;  // Import the Scanner class

///////////////////////////////////////////////////////////////////////////////

public class Main {
  
  /////////////////////////////////////////////////////////////////////////////
  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    }
    catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  
  public static int enemyAttack(String encounter, String attackName,
                                  Dice someDice, String rollStatus) {
    //-------------------------------------------------------------------------
    // Enemy Attacks.

    // Goblins ----------------------------------------------------------------
    if (encounter.equals("Goblin")) {
      if (attackName.equals("Attack Roll")) {
        return someDice.rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return someDice.rollD4() - 1;
      }
      
    // Apes ----------------------------------------------------------------
    } else if (encounter.equals("Ape")) {
      if (attackName.equals("Attack Roll")) {
        return someDice.rollD20(rollStatus) + 5;
      } else if (attackName.equals("Damage Roll")) {
        return someDice.rollD6() + 3 + someDice.rollD6() + 3;
      }
    
    // Baboons ----------------------------------------------------------------
    } else if (encounter.equals("Baboon")) {
      if (attackName.equals("Attack Roll")) {
        return someDice.rollD20(rollStatus) + 1;
      } else if (attackName.equals("Damage Roll")) {
        return someDice.rollD4() - 1;
      }
    }
    return 0;
    //-------------------------------------------------------------------------
  }
  
  /////////////////////////////////////////////////////////////////////////////

  public static String runEncounter(String encounter, Weather someWeather,
                                    Dice someDice, Fighter someFighter,
                                    Attack someAttack) {
    //-------------------------------------------------------------------------
    // Scanners

    // Creating a scanner to scan the player's various choices.
    Scanner scanChoice = new Scanner(System.in);
    
    // Creating a scanner to scan for enter to be pressed.
    Scanner scanForEnter = new Scanner(System.in);
    
    //-------------------------------------------------------------------------
    // Initializing the combat.

    // Clearing screen.
    clearScreen();

    // Variables --------------------------------------------------------------

    // Variable checking for enter.
    String enter;
    // Variable for the player's decision.
    String playerChoice;
    // Variable for the creature's ability roll status.
    String abilityRollStatus;
    // Variable for the creature's attack roll status.
    String attackRollStatus = "";
    // Variable for how much damage a creature has done.
    int damage = 0;
    // Which weapon they chose to use.
    String weaponNumber;
    // Which weapon they found.
    String foundWeapon = "";
    // For attack rolls.
    int attackRoll;
    // For the final value of the attack roll.
    int finalValue;
    // Number of enemies killed in one turn.
    int enemiesKilled;
    int actionNumber;
    int expForOne = 0;

    // Enemy Variables --------------------------------------------------------
    // Enemy's type.
    String enemyType = "";
    // How many enemies are there?
    int numberOfEnemies = 0;
    // How much health do they all have?
    int enemyHealth = 0;
    // How much health does one have?
    int healthOfOne = 0;
    // Howdifficult is it to escape from them?
    int runDc = 0;
    // How high is there armor class?
    int enemyAc = 0;

    //-------------------------------------------------------------------------
    // Enemy Encounters.

    // Goblins ----------------------------------------------------------------
    if (encounter.equals("Goblin")) { 
      numberOfEnemies = someDice.rollD6() + someDice.rollD6() + 4;
      System.out.println("- The party is ambushed by " + (numberOfEnemies - 1)
                         + " goblins lead by a goblin boss -");
      healthOfOne = 7;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      enemyAc = 15;
      enemyType = "Humanoid";
      expForOne = 50;
    
    // Apes -------------------------------------------------------------------
    } else if (encounter.equals("Ape")) { 
      numberOfEnemies = someDice.rollD4() + someDice.rollD4();
      System.out.println("- The party are attacked by " + (numberOfEnemies)
                         + " apes enjoying some exelent fruit -");
      healthOfOne = 19;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 10;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 100;
      
    // Baboons -------------------------------------------------------------------
    } else if (encounter.equals("Baboon")) { 
      numberOfEnemies = someDice.rollD6() + someDice.rollD6() + someDice.rollD6();
      System.out.println("- A pack of " + (numberOfEnemies)
                         + " baboons take umbrage in the party's intrusion and attack -");
      healthOfOne = 3;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 5;
      enemyAc = 12;
      enemyType = "Beast";
      expForOne = 10;
    
    //-------------------------------------------------------------------------
    } else {
      // Returning none if there wasn't an encounter.
      return "None";
    }

    int startingEnemies = numberOfEnemies;

    // Waiting to make sure that the players have seen the text.
    wait(3000);

    //-------------------------------------------------------------------------
    // Combat.
    
    // While loop to run the combat while there are still enemies.
    while (enemyHealth > 0) {

      actionNumber = 0;
      // Fighter's Turn -------------------------------------------------------
      while (actionNumber < someFighter.numberOfActions) {
        // Clearing the screen.
        clearScreen();
        someFighter.hasShieldEquiped = true;
        // Printing out info on the current character.
        System.out.println(someFighter.getCharacterInfo());

        // Asking the user what they want their character to do.
        System.out.println("What will " + someFighter.name + " do?");
        System.out.println(someFighter.choices());
        playerChoice = scanChoice.nextLine();

        // Exhaustion ---------------------------------------------------------
        // If the character has 1 or more levels of exhaustion.
        if (someFighter.getExhaustion() > 0) {
          abilityRollStatus = "Disadvantage";
        } else {
          abilityRollStatus = "";
        }
        
        // If the character has 3 or more levels of exhaustion.
        if (someFighter.getExhaustion() > 2) {
          attackRollStatus = "Disadvantage";
        } else {
          attackRollStatus = "";
        }

        // If the character has 5 or more levels of exhaustion.
        if (someFighter.getExhaustion() > 4) {
          someFighter.canRun = false;
        } else {
          someFighter.canRun = true;
        }

        // Option 1: Running --------------------------------------------------
        if (playerChoice.equals("1")) {
          // Clearing the screen.
          clearScreen();
          // Printing out info on the current character.
          System.out.println(someFighter.getCharacterInfo());

          // Asking the player how they wish to run.
          System.out.println("How do you want to do this?");
          // If the enemy is a humanoid.
          if (enemyType.equals("Humanoid")) {
            System.out.println("[1 = Run away] [2 = Inimidate] [3 = Persuade]"
                               + " [R = Return]");

          // If the enemy is a beast.
          } else if (enemyType.equals("Beast")) {
            System.out.println("[1 = Run away] [2 = Inimidate] [3 = Tame]"
                               + " [R = Return]");
          }
          // Getting the player's decision.
          playerChoice = scanChoice.nextLine();

          // Run away ---------------------------------------------------------
          if (playerChoice.equals("1")) {
            // Making the check to see if they've succeeded.
            if (someFighter.makeCheck("Dex", "Acrobatics", "", someDice.
                                      rollD20(abilityRollStatus)) > runDc) {
              // They did it.
              System.out.println("\u001B[32m- The party successfully escaped "
                                 + " from the " + encounter + "s -\u001B[0m");
              return "Got away";

            } else {
              // They didn't do it.
              System.out.println("\u001B[31m- The party failed to escape "
                                 + " from the " + encounter + "s -\u001B[0m");
              actionNumber++;
            }

          // Intimidate -------------------------------------------------------
          } else if (playerChoice.equals("2") && enemyType.equals("humanoid")) {
            // Making the check to see if they've succeeded.
            if (someFighter.makeCheck("Cha", "Intimidation", "", someDice.
                                      rollD20(abilityRollStatus)) > runDc) {
              // They did it.
              System.out.println("\u001B[32m- " + someFighter.name
                                 + " successfully scared off the "
                                 + encounter + "s -\u001B[0m");
              return "Got away";

            } else {
              // They didn't do it.
              System.out.println("\u001B[31m- " + someFighter.name + " failed "
                                 + "to scare off the " + encounter
                                 + "s -\u001B[0m");
              actionNumber++;
            }
            
          // Intimidate -------------------------------------------------------
          } else if (playerChoice.equals("2") && enemyType.equals("Beast")) {
            // Making the check to see if they've succeeded.
            if (someFighter.makeCheck("Wis", "Animal Handling", "", someDice.
                                      rollD20(abilityRollStatus)) > runDc) {
              // They did it.
              System.out.println("\u001B[32m- " + someFighter.name
                                 + " successfully tamed the "
                                 + encounter + "s -\u001B[0m");
              return "Got away";

            } else {
              // They didn't do it.
              System.out.println("\u001B[31m- " + someFighter.name + " failed "
                                 + "to tame the " + encounter
                                 + "s -\u001B[0m");
              actionNumber++;
            }

          // Persuade ---------------------------------------------------------
          } else if (playerChoice.equals("3") && enemyType.equals("Humanoid")) {
            // Making the check to see if they've succeeded.
            if (someFighter.makeCheck("Cha", "Persuasion", "", someDice.
                                      rollD20(abilityRollStatus)) > runDc) {
              // They did it.
              System.out.println("\u001B[32m- " + someFighter.name
                                 + " successfully persuaded the " + encounter
                                 + "s to leave -\u001B[0m");
              return "Got away";

            } else {
              // They didn't do it.
              System.out.println("\u001B[31m- " + someFighter.name + " failed "
                                 + "to persuade the " + encounter
                                 + "s to leave -\u001B[0m");
              actionNumber++;
            }
 
          // Return -----------------------------------------------------------
          } else if (playerChoice.equals("R") || playerChoice.equals("r")) {
            System.out.println("");

          // Invalid ----------------------------------------------------------
          } else {
            System.out.println("\u001B[31mX Not an option X\u001B[0m");
            // Waiting to make sure that they see the text.
            wait(1000);
          }

        // Option 2: Attacking ------------------------------------------------
        } else if (playerChoice.equals("2")) {
          // Clearing the screen.
          clearScreen();
          // Printing out info on the current character.
          System.out.println(someFighter.getCharacterInfo());
          System.out.println(someFighter.showWeapons());

          // If they dont have any weapons on them:
          if (someFighter.showWeapons().equals(someFighter.name + " is not "
                                               + "carrying any weapons at the "
                                               + "moment")) {
            // Waiting to make sure that they see the text.
            wait(1000);

          // If they do have any weapons on them:
          } else {
            // Asking them which weapon they would like to use.
            System.out.println("Which weapon would you like to attack with?");
            // Getting which weapon they would like to use.
            weaponNumber = scanChoice.nextLine();
            // Making sure that their choice is numeric.
            if (isNumeric(weaponNumber)) {
              // Finding the weapon in their equipment.
              foundWeapon = someFighter.findWeapon(Integer.parseInt(weaponNumber));

              // If it couldn't find the weapon the chose.
              if (foundWeapon.equals("Couldn't find weapon")) {
                System.out.println("\u001B[31mX Couldn't find that weapon "
                                   + "X\u001B[0m");
                // Waiting to make sure that they see the text.
                wait(1000);

              // If it did find the weapon.
              } else {
                // Attack rolls -----------------------------------------------
                // Telling the player that they made an attack.
                System.out.println("");
                System.out.println(someFighter.name + " made an attack with "
                                   + "their " + foundWeapon + "!");
                // Making the initial attack roll.
                attackRoll = someDice.rollD20(attackRollStatus);
                // Adding the player's modifiers
                finalValue = someFighter.
                             makeAttackRoll(attackRoll, someAttack.
                             attackProperty1(foundWeapon), someAttack.
                             attackProperty2(foundWeapon));
                System.out.println("");
                // Telling the player what the character rolled.
                System.out.println(someFighter.name + " rolled a " + finalValue
                                   + " to hit.");

                // If it hits:
                if (finalValue > enemyAc) {
                  if (attackRoll == 20) {
                    System.out.println("");
                    System.out.println("---------------");
                    System.out.println("\033[0;93m CRITICAL HIT! \u001B[0m");
                    System.out.println("---------------");
                  }
                  // Damage rolls ---------------------------------------------
                  // Getting how much damage the player did.
                  damage = someAttack.makeAttack(someFighter.strength,
                                                 someFighter.dexterity,
                                                 foundWeapon, attackRoll,
                                                 someFighter.fightingStyle, 
                                                 someFighter.proficiencyBonus);
                  // Fighting style (Dueling) ---------------------------------
                  if (someAttack.attackProperty1(foundWeapon).
                      equals("Two-Handed")
                      || someAttack.attackProperty2(foundWeapon).
                      equals("Ranged")
                      && someFighter.fightingStyle.equals("Dueling")) {
                    System.out.print("");
                  } else if (someFighter.fightingStyle.equals("Dueling")) {
                    damage += 2;
                  }
                  // Damage ---------------------------------------------------
                  System.out.println("");
                  System.out.println("\u001B[32m" + someFighter.name
                                     + " dealt " + damage
                                     + " damage!\u001B[0m");
                  enemyHealth -= damage;

                  // Keeping track of how many enemies were killed.
                  enemiesKilled = 0;

                  // Kills ----------------------------------------------------
                  // Counting how many enemies were killed.
                  while (true) {
                    if (enemyHealth < healthOfOne * (numberOfEnemies - 1)) {
                      enemiesKilled++;
                      numberOfEnemies--;
                    } else {
                      break;
                    }
                  }

                  // If the player killed any enemies
                  if (enemiesKilled > 0) {
                    System.out.println("");
                    System.out.println(someFighter.name + " killed "
                                       + enemiesKilled + " " + encounter);
                  }

                  actionNumber++;
                // Misses -----------------------------------------------------
                // Else if it misses:
                } else {
                  System.out.println("");
                  System.out.println("\u001B[31mX " + someFighter.name +
                                     " missed " + encounter + " X\u001B[0m");
                  actionNumber++;
                }
              }

            // If they chose an invalid option:
            } else {
              System.out.println("\u001B[31mX Not an option X\u001B[0m");
              // Waiting to make sure that they see the text.
              wait(1000);
            }
          }

        // Dodge --------------------------------------------------------------
        } else if (playerChoice.equals("3")) {
          System.out.println("\u001B[32mØ " + someFighter.name + " has braced"
                             + " themselves for oncoming attacks Ø\u001B[0m");
          someFighter.dodge = true;
          actionNumber++;
          
        // Nothing ------------------------------------------------------------
        } else if (playerChoice.equals("4")) {
          System.out.println(someFighter.name + " did nothing.");
          actionNumber++;

        // Not an option ------------------------------------------------------
        } else {
          System.out.println("\u001B[31mX Not an option X\u001B[0m");
          // Waiting to make sure that they see the text.
          wait(1000);
        }
      }
      // Waiting to make sure that they see the text.
      wait(5000);
      
      String lastWeapon = foundWeapon;
      
  /////////////////////////////////////////////////////////////////////////////
      
      // Fighter's Bonus Action -----------------------------------------------
      while (true) {
        // Clearing the screen.
        clearScreen();
        // Printing out info on the current character.
        System.out.println(someFighter.getCharacterInfo());

        // Asking the user what they want their character to do.
        System.out.println("What will " + someFighter.name
                           + " do as a bonus action?");
        System.out.println(someFighter.bonusActions());
        playerChoice = scanChoice.nextLine();
        
        // Second wind --------------------------------------------------------
        if (playerChoice.equals("1")) {
          System.out.println(someFighter.secondWind(someDice.rollD10()));
          break;
          
        // Bonus action attack ------------------------------------------------
        } else if (playerChoice.equals("2")
                   && someAttack.attackProperty2(lastWeapon).equals("Light")) {
          // Clearing the screen.
          clearScreen();
          // Printing out info on the current character.
          System.out.println(someFighter.getCharacterInfo());
          System.out.println(someFighter.showWeapons());

          // If they dont have any weapons on them:
          if (someFighter.showWeapons().equals(someFighter.name + " is not "
                                               + "carrying any weapons at the "
                                               + "moment")) {
            // Waiting to make sure that they see the text.
            wait(1000);

          // If they do have any weapons on them:
          } else {
            // Asking them which weapon they would like to use.
            System.out.println("Which weapon would you like to attack with?");
            // Getting which weapon they would like to use.
            weaponNumber = scanChoice.nextLine();
            // Making sure that their choice is numeric.
            if (isNumeric(weaponNumber)) {
              // Finding the weapon in their equipment.
              foundWeapon = someFighter.findWeapon(Integer.parseInt(weaponNumber));

              // If it couldn't find the weapon the chose.
              if (foundWeapon.equals("Couldn't find weapon")) {
                System.out.println("\u001B[31mX Couldn't find that weapon "
                                   + "X\u001B[0m");
                // Waiting to make sure that they see the text.
                wait(1000);

              // If it did find the weapon.
              } else {
                if (someAttack.attackProperty2(foundWeapon).equals("Light")) {
                  // Attack rolls -----------------------------------------------
                  // Telling the player that they made an attack.
                  System.out.println("");
                  System.out.println(someFighter.name + " made an attack with "
                                     + "their " + foundWeapon + "!");
                  // Making the initial attack roll.
                  attackRoll = someDice.rollD20(attackRollStatus);
                  // Adding the player's modifiers
                  finalValue = someFighter.
                               makeAttackRoll(attackRoll, someAttack.
                               attackProperty1(foundWeapon), someAttack.
                               attackProperty2(foundWeapon));
                  System.out.println("");
                  // Telling the player what the character rolled.
                  System.out.println(someFighter.name + " rolled a " + finalValue
                                     + " to hit.");

                  // If it hits:
                  if (finalValue > enemyAc) {
                    if (attackRoll == 20) {
                      System.out.println("");
                      System.out.println("---------------");
                      System.out.println("\033[0;93m CRITICAL HIT! \u001B[0m");
                      System.out.println("---------------");
                    }
                    // Damage rolls ---------------------------------------------
                    // Getting how much damage the player did.
                    damage = someAttack.makeAttack(someFighter.strength,
                                                   someFighter.dexterity,
                                                   foundWeapon, attackRoll,
                                                   someFighter.fightingStyle, 
                                                   someFighter.proficiencyBonus);
                    // Fighting style (Dueling) ---------------------------------
                    if (someFighter.fightingStyle.equals("Two-Weapon Fighting")) {
                      System.out.print("");
                    } else if (someAttack.attackProperty1(foundWeapon).
                               equals("Finesse")) {
                      damage -= (((someFighter.dexterity
                                   - someFighter.dexterity % 2) - 10) / 2);
                    } else {
                      damage -= (((someFighter.strength
                                   - someFighter.strength % 2) - 10) / 2);
                    }
                    // Damage ---------------------------------------------------
                    System.out.println("");
                    System.out.println("\u001B[32m" + someFighter.name
                                       + " dealt " + damage
                                       + " damage!\u001B[0m");
                    enemyHealth -= damage;
                    someFighter.hasShieldEquiped = false;

                    // Keeping track of how many enemies were killed.
                    enemiesKilled = 0;

                    // Kills --------------------------------------------------
                    // Counting how many enemies were killed.
                    while (true) {
                      if (enemyHealth < healthOfOne * (numberOfEnemies - 1)) {
                        enemiesKilled++;
                        numberOfEnemies--;
                      } else {
                        break;
                      }
                    }

                    // If the player killed any enemies
                    if (enemiesKilled > 0) {
                      System.out.println("");
                      System.out.println(someFighter.name + " killed "
                                         + enemiesKilled + " " + encounter);
                    }

                    break;
                  // Misses ---------------------------------------------------
                  // Else if it misses:
                  } else {
                    System.out.println("");
                    System.out.println("\u001B[31mX " + someFighter.name +
                                     " missed " + encounter + " X\u001B[0m");
                    break;
                  }
                } else {
                  System.out.println("\u001B[31mX " + someFighter.name
                                     + " cannot make an attack on their bonus "
                                     + "action with a wapon that isn't light "
                                     + "X\u001B[0m");
                  // Waiting to make sure that they see the text.
                  wait(1000);
                }
              }

            // If they chose an invalid option:
            } else {
              System.out.println("\u001B[31mX Not an option X\u001B[0m");
              // Waiting to make sure that they see the text.
              wait(1000);
            }
          }
 
        // Bonus action attack else -------------------------------------------
        } else if (playerChoice.equals("2")) {
          System.out.println("\u001B[31mX " + someFighter.name + " cannot do "
                             + "that, they didn't use a light weapon for their"
                             + " first attack X\u001B[0m");
          // Waiting to make sure that they see the text.
          wait(1000);

        // Nothing ------------------------------------------------------------
        } else if (playerChoice.equals("3")) {
          System.out.println(someFighter.name + " did nothing for their bonus " 
                             + "action.");
          break;
        // Invalid ------------------------------------------------------------
        } else {
          System.out.println("\u001B[31mX Not an option X\u001B[0m");
          // Waiting to make sure that they see the text.
          wait(1000);
        }
      }
      
      // Shield ---------------------------------------------------------------
      if (someFighter.hasShieldEquiped && someFighter.hasShield
          && someFighter.currentArmorClass == someFighter.armorClass) {
        someFighter.currentArmorClass += 2;
        System.out.println("\u001B[32mØ " + someFighter.name + " has put up"
                             + " their shield! Ø\u001B[0m");
      } else if (someFighter.currentArmorClass != someFighter.armorClass) {
        someFighter.currentArmorClass = someFighter.armorClass;
      }
      // Waiting to make sure that they see the text.
      
      // Fighting Style (Defense) ---------------------------------------------
      if (someFighter.fightingStyle.equals("Defense")) {
        if (someFighter.hasShieldEquiped && someFighter.hasShield) {
          if (someFighter.currentArmorClass == someFighter.armorClass + 2) {
            someFighter.currentArmorClass += 1;
          }
        } else if (someFighter.currentArmorClass == someFighter.armorClass) {
          someFighter.currentArmorClass += 1;
        }
      }

      // Fighting Style (Protection) ------------------------------------------
      if (someFighter.fightingStyle.equals("Protection")) {
        someFighter.isProtectionReady = true;
      }

      wait(4000);

      //-----------------------------------------------------------------------
      // Enemy's Turn.
      // Clearing the screen.
      clearScreen();
      // Stating that it's the enemies turn.
      System.out.println("\u001B[31m- " + encounter + "'s turn -\u001B[0m");
      // Waiting to make sure that they see the text.
      // For every enemy in the encounter.
      for (int enemyNumber = 0; enemyNumber < numberOfEnemies; enemyNumber++) {
        wait(1000);
        // Attacking ----------------------------------------------------------
        System.out.println(encounter + " " + (enemyNumber + 1) + " attacks "
                           + someFighter.name);
        // Setting the attack roll status to neutral.
        attackRollStatus = "";

        // If the player decided to dodge:
        if (someFighter.dodge) {
          // Setting the attack roll status to disadvantage.
          attackRollStatus = "Disadvantage";
        }
        
        // Fighting Style (Protection) ----------------------------------------
        if (someFighter.fightingStyle.equals("Protection")
            && someFighter.isProtectionReady) {
          attackRollStatus = "Disadvantage";
          someFighter.isProtectionReady = false;
        }

        // Attack roll --------------------------------------------------------
        int enemyAttackRoll = enemyAttack(encounter, "Attack Roll", someDice,
                                          attackRollStatus);
        // Checking to see if it hit.
        if (enemyAttackRoll > someFighter.currentArmorClass) {
          // Damage roll ----------------------------------------------------
          // If they hit:
          // Getting the damage dealt.
          damage = enemyAttack(encounter, "Damage Roll", someDice,
                                 attackRollStatus);
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
              System.out.println("\u001B[31m☠ " + someFighter.name + " did what he had to do ☠\u001B[0m");
              System.out.println("[Press ENTER to continue]");
              enter = scanForEnter.nextLine();
              return "dead";
            }
            System.out.println("\u001B[31m☠ " + someFighter.name + " has died ☠\u001B[0m");
            System.out.println("[Press ENTER to continue]");
            enter = scanForEnter.nextLine();
            return "dead";
          }
        // Otherise if the enemy missed:
        } else {
          System.out.println("\u001B[32m" + encounter + " "
                             + (enemyNumber + 1) + " missed "
                             + someFighter.name + "!\u001B[0m");
        }
      }
      wait(1000);
    }
    clearScreen();
    System.out.println("\u001B[32m The party defeated the " + encounter + "s!\u001B[0m");
    System.out.println("\u001B[32m Everyone gained " + (expForOne * startingEnemies / 1)+ "exp!\u001B[0m");
    wait(5000);
    return "0";
  }

  /////////////////////////////////////////////////////////////////////////////
  
  public static String dailyProcedure(ChultMap someMap, Weather someWeather,
                                      Dice someDice, Fighter someFighter,
                                      Attack someAttack) {

    //-------------------------------------------------------------------------
    // Scanners

    // Creating a scanner to scan for direction.
    Scanner scanDirection = new Scanner(System.in);
    
    // Creating a scanner to scan for enter.
    Scanner scanForEnter = new Scanner(System.in);

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
    String encounter = someMap.rollEncounter(someDice.rollD20(""),
                                             someDice.rollD100());
    String end = runEncounter(encounter, someWeather, someDice, someFighter, someAttack);
    if (end.equals("dead")) {
      clearScreen();
      return ("- GAME OVER -");
    }
    clearScreen();

    //-------------------------------------------------------------------------
    // The day's end results
    System.out.println("- The Day's Results");

    if (encounter.equals("None")) {
      System.out.println("\u001B[32m- No Encounters Today -\u001B[0m");
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
      if (someFighter.makeCheck( "Con", "", "Saving Throw",
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
    System.out.println(someFighter.forageFood(someDice.rollD20(forageStatus),
                                              someDice.rollD6()));
    // Foraging for water.
    System.out.println(someFighter.forageWater(someDice.rollD20(forageStatus),
                                               someDice.rollD6()));
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
        System.out.println("\u001B[31m☠ " + someFighter.name + " did what he had to do ☠\u001B[0m");
        System.out.println("[Press ENTER to continue]");
        enter = scanForEnter.nextLine();
        return "dead";
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
    // Next day.
    someWeather.dayNumber += 1;
    // Regaining hp.
    someFighter.currentHitPoints = someFighter.maxHitPoints;
    someFighter.isSecondWindReady = true;

    // Waiting for enter.
    System.out.println("[Press ENTER to continue]");
    enter = scanForEnter.nextLine();
    clearScreen();
    return ("");
    //-------------------------------------------------------------------------
  }
  
  /////////////////////////////////////////////////////////////////////////////
  
  public static void clearScreen() {
    //-------------------------------------------------------------------------
    // Clears the screen.
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    //-------------------------------------------------------------------------
  }
  
  /////////////////////////////////////////////////////////////////////////////
  
  public static boolean isNumeric(String str) {
    //-------------------------------------------------------------------------
    // Checking if a string is a number.
    try {
      Integer.parseInt(str);
      return true;

    } catch(NumberFormatException e) {
      return false;
    }
    //-------------------------------------------------------------------------
  }
  
  /////////////////////////////////////////////////////////////////////////////

  public enum FighterSkills {
    //-------------------------------------------------------------------------
    // List of fighter skills.
    Skill1("Acrobatics"),
    Skill2("Animal Handling"),
    Skill3("History"),
    Skill4("Insight"),
    Skill5("Perception"),
    Skill6("Survival");
    
    // Returning the value.
    private final String skill;

    FighterSkills(String skill) {
      this.skill = skill;
    }

    public String getSkill() {
      return this.skill;
    }
    
    //-------------------------------------------------------------------------
  }
  
  /////////////////////////////////////////////////////////////////////////////

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
  
  /////////////////////////////////////////////////////////////////////////////

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
  
  /////////////////////////////////////////////////////////////////////////////
  
  public static void createFighter(Fighter someFighter, String hasPlayed) {
    //-------------------------------------------------------------------------
    // Creating the fighter character with the user.

    // Creating a scanner to scan info on the first character.
    Scanner scanFighter = new Scanner(System.in);
    
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

    // Giving the fighter a name.
    System.out.println("Insert your fighter's name:");
    someFighter.name = scanFighter.nextLine();
    System.out.println("");
    
    // Clearing the screen.
    clearScreen();

    //-------------------------------------------------------------------------
    // Setting their skills.

    // Title
    System.out.println("                    - Skills -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    
    // Giving the player a short definition of what skills are if they havent
    // played before.
    if (hasPlayed.equals("1")) {
      System.out.print("");
    } else {
      System.out.println("Each character you make will have proficiency in a ");
      System.out.println("set of skills according to their class. You can still");
      System.out.println("use skills you don't have proficiency in, however,");
      System.out.println("you will be able to add your proficiency bonus to");
      System.out.println("those which you do, which is +2 to start.");
      System.out.println("");
    }

    // Allowing the player to choose which skills they want their fighter
    // to have.
    System.out.println("Which 2 of the following skills would you like "
                       + someFighter.name + " to have?");
    System.out.println("[1 = Acrobatics] [2 = Animal Handling] [3 = History]");
    System.out.println("    [4 = Insight] [5 = Perception] [6 = Survival]");
    System.out.println("");
    
    // Number of chosen skills and last picked skill.
    int numberOfSkills = 1;
    int pickedSkill = 0;
    
    //Making sure that the player chooses 2 valid options.
    while (true) {
      System.out.println("Choice #" + numberOfSkills);
      String chosenSkillString = scanFighter.nextLine();

      // Making sure that the user inputs a valid option.
      if (isNumeric(chosenSkillString)) {
        // Converting it to an int.
        int chosenSkill = Integer.parseInt(chosenSkillString);
        // Making sure it is in the range.
        if (chosenSkill >= 1 && chosenSkill <= 6) {
          // Getting the skill from the enum.
          FighterSkills s = FighterSkills.valueOf("Skill" + chosenSkill);
          // Adding the skill to the fighter's proficiencies.
          someFighter.proficiencies.add(s.getSkill());
          // Making the number of picked skills increase.
          numberOfSkills++;
          // Remembering the last picked skill so they can pick it twice.
          pickedSkill = chosenSkill;
        } else {
          System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
      }
      
      if (numberOfSkills > 2) {
        System.out.println("");
        //telling them the skills that they've chosen.
        System.out.println("You Picked: " + someFighter.proficiencies.
                                            get(someFighter.proficiencies.size()
                                                - 2) + " and " + someFighter.
                                            proficiencies.get(someFighter.
                                                              proficiencies.
                                                              size() - 1));
        // Looking for enter to continue.
        System.out.println("[Press ENTER to continue]");
        String enter = scanForEnter.nextLine();
        clearScreen();
        break;
      }
    }
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
  }
  
  /////////////////////////////////////////////////////////////////////////////

  /**
   * This function handles the input and output of the program.
   */
  public static void main(String[] args) {

    //-------------------------------------------------------------------------
    // Scanners

    // Creating a scanner to scan if this is their first time playing.
    Scanner scanExperience = new Scanner(System.in);
    
    // Creating a scanner to scan for enter.
    Scanner scanForEnter = new Scanner(System.in);
    
    String enter;

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
  
    //-------------------------------------------------------------------------
    // Intro
    
    // Checking to see if the player needs an explanation.
    System.out.println("Have you played Tomb Of Annihilation before?");
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
      System.out.println("Welcome to Tomb Of Annihilation! This is a game");
      System.out.println("based off of the D&D campaign of the same name, and");
      System.out.println("as such, it was use many of the same mechanics.");
      System.out.println("This adventure will see you traveling over the vast");
      System.out.println("jungles of the land of Chult in search of a");
      System.out.println("forbidden artifact. Therefore, when choosing your");
      System.out.println("skills, It is suggested to take ones such as");
      System.out.println("SURVIVAL, which will give you the upper hand while");
      System.out.println("exloring.");
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
                                  someAttack);
      if (end.equals("- GAME OVER -")) {
        System.out.println("- GAME OVER -");
        break;
      }
    }
    //-------------------------------------------------------------------------
  }
}
