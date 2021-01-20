/*
* The Main program is a program which runs my game.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-6 
*/

import java.util.Scanner;  // Import the Scanner class

///////////////////////////////////////////////////////////////////////////////

public class Main {
  
  public static int fighterAction(Fighter someFighter) {
    Scanner scanChoice = new Scanner(System.in);
    System.out.println("Hp: " + someFighter.currentHitPoints);
    System.out.println("What would you like " + someFighter.name + " to do?");
    System.out.println(someFighter.choices());
    while (true) {
      try {
        int choice = scanChoice.nextInt();
        return choice;
          
      } catch (Exception e) {
        System.out.println("Invalid Choice");
      }
    }
  }
  
  /////////////////////////////////////////////////////////////////////////////
  
  public static int enemyAttack(String encounter, String attackName,
                                  Dice someDice, String rollStatus) {
    //-------------------------------------------------------------------------
    // Goblins.
    if (encounter.equals("Goblin")) {
      if (attackName.equals("Attack Roll")) {
        return someDice.rollD20(rollStatus) + 4;
      } else if (attackName.equals("Damage Roll")) {
        return someDice.rollD6() + 2;
      }
    }
    return 0;
    //-------------------------------------------------------------------------
  }

  public static String runEncounter(String encounter, Weather someWeather,
                                    Dice someDice, Fighter someFighter,
                                    Attack someAttack) {
    //-------------------------------------------------------------------------
    // Scanners
    Scanner scanChoice = new Scanner(System.in);
    Scanner scanForEnter = new Scanner(System.in);
    
    //-------------------------------------------------------------------------
    // Initializing the combat.

    // Clearing screen.
    clearScreen();

    // Initializing variables.
    String enter;
    String playerChoice;
    int numberOfEnemies = 0;
    int enemyHealth = 0;
    int healthOfOne = 0;
    int runDc = 0;
    int enemyAc = 0;
    String attack1Name;
    String attack2Name;
    int damage = 0;
    String rollStatus = "";
    
    //-------------------------------------------------------------------------
    // Goblins.
    // If the encounter is goblins.
    if (encounter.equals("Goblin")) { 
      numberOfEnemies = someDice.rollD6() + someDice.rollD6() + 4;
      System.out.println("- The party encountered " + (numberOfEnemies - 1)
                         + " goblins lead by a goblin boss -");
      healthOfOne = 7;
      enemyHealth = healthOfOne * numberOfEnemies;
      runDc = 20;
      enemyAc = 15;
    
    //-------------------------------------------------------------------------
    // Goblins.
    }
    
    // Looking for enter to continue.
    System.out.println("[Press ENTER to continue]");
    enter = scanForEnter.nextLine();

    //-------------------------------------------------------------------------
    // Running the combat.
    while (enemyHealth > 0) {
      //-----------------------------------------------------------------------
      // Fighter's Turn.
      while (true) {
        // Clearing the screen.
        clearScreen();
        // Printing out info on the character.
        System.out.println(someFighter.name + ":");
        System.out.println("♥ HP: " + someFighter.currentHitPoints);
        System.out.println("Ø AC: " + someFighter.armorClass);
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("What will " + someFighter.name + " do?");
        System.out.println(someFighter.choices());
        playerChoice = scanChoice.nextLine();
        
        // Running ------------------------------------------------------------
        if (playerChoice.equals("1")) {
          // Clearing the screen.
          clearScreen();
          // Printing out info on the character.
          System.out.println(someFighter.name + ":");
          System.out.println("♥ HP: " + someFighter.currentHitPoints);
          System.out.println("Ø AC: " + someFighter.armorClass);
          System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
          System.out.println("How do you want to do this?");
          System.out.println(someFighter.run());
          playerChoice = scanChoice.nextLine();
          
          if (someFighter.getExhaustion() > 0) {
            rollStatus = "Disadvantage";
          } else {
            rollStatus = "";
          }

          // Run.
          if (playerChoice.equals("1")) {
            if (someFighter.makeCheck("Dex", "Acrobatics", "",
                                      someDice.rollD20(rollStatus)) > runDc) {
              System.out.println("\u001B[32m- The party successfully escaped "
                                 + " from the " + encounter + "s -\u001B[0m");
              return "Got away";
              
              
            } else {
              System.out.println("\u001B[31m- The party failed to escape "
                                 + " from the " + encounter + "s -\u001B[0m");
              break;
            }

          // Intimidate.
          } else if (playerChoice.equals("2")) {
            if (someFighter.makeCheck("Cha", "Intimidation", "",
                                      someDice.rollD20(rollStatus)) > runDc) {
              System.out.println("\u001B[32m- " + someFighter.name
                                 + " successfully scared off the "
                                 + encounter + "s -\u001B[0m");
              return "Got away";
              
              
            } else {
              System.out.println("\u001B[31m- " + someFighter.name + " failed "
                                 + "to scare off the " + encounter
                                 + "s -\u001B[0m");
              break;
            }

          // Persuade.
          } else if (playerChoice.equals("3")) {
            if (someFighter.makeCheck("Cha", "Persuasion", "",
                                      someDice.rollD20(rollStatus)) > runDc) {
              System.out.println("\u001B[32m- " + someFighter.name
                                 + " successfully persuaded the " + encounter
                                 + "s to leave -\u001B[0m");
              return "Got away";
              
              
            } else {
              System.out.println("\u001B[31m- " + someFighter.name + " failed "
                                 + "to persuade the " + encounter
                                 + "s to leave -\u001B[0m");
              break;
            }
 
          // Return.
          } else if (playerChoice.equals("4")) {
            System.out.println("");
          } else {
            System.out.println("\u001B[31mX Not an option X\u001B[0m");
            // Looking for enter to continue.
            System.out.println("[Press ENTER to continue]");
            enter = scanForEnter.nextLine();
          }
          
        // Attacking ----------------------------------------------------------
        } else if (playerChoice.equals("2")) {
          // Clearing the screen.
          clearScreen();
          // Printing out info on the character.
          System.out.println(someFighter.name + ":");
          System.out.println("♥ HP: " + someFighter.currentHitPoints);
          System.out.println("Ø AC: " + someFighter.armorClass);
          System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
          System.out.println("Here are " + someFighter.name + "'s current weapons:");
          System.out.println(someFighter.showWeapons());
          
          if (someFighter.showWeapons().equals(someFighter.name + " is not carrying any weapons at the moment")) {
            System.out.println("[Press ENTER to continue]");
            enter = scanForEnter.nextLine();
          } else {
            System.out.println("Which weapon would you like to use?");
            String weaponNumber = scanChoice.nextLine();
            if (isNumeric(weaponNumber)) {
              String foundWeapon = someFighter.findWeapon(Integer.parseInt(weaponNumber));
              
              if (foundWeapon.equals("Couldn't find weapon")) {
                System.out.println("\u001B[31mX Not an option X\u001B[0m");

              } else {
                int attackRoll = someDice.rollD20("");
                int finalValue = someFighter.makeAttackRoll(attackRoll);
                System.out.println(finalValue);

                if (finalValue > enemyAc) {
                  if (attackRoll == 20) {
                    System.out.println("\u001B[32m CRITICAL HIT! \u001B[0m");
                  }
                  damage = someAttack.makeAttack(someFighter.strength, someFighter.dexterity, foundWeapon, attackRoll, 0, someFighter.proficiencyBonus);
                  System.out.println(damage);
                  enemyHealth -= damage;

                  if (enemyHealth < healthOfOne * (numberOfEnemies - 1)) {
                    System.out.println("\u001B[32m" + someFighter.name + " has killed " + encounter + "\u001B[0m");
                    numberOfEnemies--;
                  }

                  break;
                } else {
                  System.out.println("\u001B[31mX " + someFighter.name + " missed " + encounter + " X\u001B[0m");
                  break;
                }
              }
              System.out.println("[Press ENTER to continue]");
              enter = scanForEnter.nextLine();
            } else {
              System.out.println("\u001B[31mX Not an option X\u001B[0m");
              // Looking for enter to continue.
              System.out.println("[Press ENTER to continue]");
              enter = scanForEnter.nextLine();
            }
          }
          
        // Dodge --------------------------------------------------------------
        } else if (playerChoice.equals("3")) {
          System.out.println("\u001B[32mØ " + someFighter.name + " has braced"
                             + " themselves for oncoming attacks Ø\u001B[0m");
          someFighter.dodge = true;
          break;
          
        // Nothing ------------------------------------------------------------
        } else if (playerChoice.equals("4")) {
          break;
          
        // Not an option ------------------------------------------------------
        } else {
          System.out.println("\u001B[31mX Not an option X\u001B[0m");
          // Looking for enter to continue.
          System.out.println("[Press ENTER to continue]");
          enter = scanForEnter.nextLine();
        }
      }
      System.out.println("[Press ENTER to continue]");
      enter = scanForEnter.nextLine();
      
      //-----------------------------------------------------------------------
      // Enemy's Turn.
      clearScreen();
      System.out.println("\u001B[31m- " + encounter + "'s turn -\u001B[0m");
      for (int enemyNumber = 0; enemyNumber < numberOfEnemies; enemyNumber++) {
        System.out.println(encounter + " " + (enemyNumber + 1) + " attacks " + someFighter.name);
        rollStatus = "";
        if (someFighter.dodge) {
          rollStatus = "Disadvantage";
        }
        int enemyAttackRoll = enemyAttack(encounter, "Attack Roll", someDice, rollStatus);
        if (someFighter.hasShieldEquiped && someFighter.hasShield) {
          if (enemyAttackRoll > someFighter.armorClass + 2) {
            damage = enemyAttack(encounter, "Damage Roll", someDice, rollStatus);
            someFighter.currentHitPoints -= damage;
            System.out.println("\u001B[31m☠ " + encounter + " " + (enemyNumber + 1) + " has hit " + someFighter.name + " for " + damage + " damage ☠\u001B[0m");
            if (someFighter.currentHitPoints <= 0) {
              System.out.println("\u001B[31m☠ " + someFighter.name + " has died ☠\u001B[0m");
              System.out.println("[Press ENTER to continue]");
              enter = scanForEnter.nextLine();
              return "dead";
            }
          } else {
            System.out.println(encounter + " " + (enemyNumber + 1) + " missed " + someFighter.name);
          }
        } else {
          if (enemyAttackRoll > someFighter.armorClass) {
            damage = enemyAttack(encounter, "Damage Roll", someDice, rollStatus);
            someFighter.currentHitPoints -= damage;
            System.out.println("\u001B[31m☠ " + encounter + " " + (enemyNumber + 1) + " has hit " + someFighter.name + " for " + damage + " damage ☠\u001B[0m");
            if (someFighter.currentHitPoints <= 0) {
              System.out.println("\u001B[31m☠ " + someFighter.name + " has died ☠\u001B[0m");
              System.out.println("[Press ENTER to continue]");
              enter = scanForEnter.nextLine();
              return "dead";
            }
            
          } else {
            System.out.println(encounter + " " + (enemyNumber + 1) + " missed " + someFighter.name);
          }
        }
      }
      System.out.println("[Press ENTER to continue]");
      enter = scanForEnter.nextLine();
      clearScreen();
    }
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
          System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
      }
    }
    
    clearScreen();

    // Second option. ----------------------------------------------------------
    // Title
    System.out.println("                    - Equipment -");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

    System.out.println("Which of the folowing do you want " + someFighter.name
                       + " to have?");
    System.out.println("[1 = a martial weapon and a shield] or [2 = 2 martial weapons]");

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
          System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
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
          MartialWeaponNames n = MartialWeaponNames.valueOf("Weapon" + chosenOption);
          // Getting the weapon's weight from the enum.
          MartialWeaponWeights w = MartialWeaponWeights.valueOf("Weapon" + chosenOption);
          // Adding the skill to the fighter's proficiencies.
          someFighter.addEquipment(n.getWeapon(), w.getWeight(), "Weapon");
          // Making the number of picked skills increase.
          weaponsChosen++;
          
        if (weaponsChosen == numberOfWeapons) {
          break;
        }

        } else {
          System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
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
          System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
        }

      // Implementing the choice.
      } else {
        System.out.println("\u001B[31mYou cannot pick that number.\u001B[0m");
      }
    }
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
    
    // Creating weapon object.
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
      String end = dailyProcedure(someMap, someWeather, someDice, someFighter, someAttack);
      if (end.equals("- GAME OVER -")) {
        System.out.println("- GAME OVER -");
        break;
      }
    }
    //-------------------------------------------------------------------------
  }
}
