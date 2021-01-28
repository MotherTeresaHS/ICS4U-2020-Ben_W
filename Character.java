/*
* The Character program implements an application that
* creates a character.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-15
*/

import java.util.ArrayList;  // Import the ArrayList class
import java.util.Random; // Import the random number class
//=============================================================================
abstract class Character {
  // Character Name.
  public String name;
  // Experience points and levels.
  public int experiencePoints = 0;
  public int level = 1;
  // Hit points.
  public int maxHitPoints;
  public int currentHitPoints;
  // Armor Class.
  public int armorClass;
  public int currentArmorClass;
  // Ability Scores.
  public int strength;
  public int dexterity;
  public int constitution;
  public int intelligence;
  public int wisdom;
  public int charisma;
  // Death Saves.
  public boolean isUnconcious = false;
  public boolean isDead = false;
  public int deathSaveSuccess;
  public int deathSaveFailure;
  // Proficiency Bonus.
  public int proficiencyBonus = 2;
  // Exhaustion.
  private int exhaustion = 0;
  // Food and water.
  public int lbsOfFood = 11;
  public int gallonsOfWater = 22;
  private int daysWithoutFood = 0;
  public int tookExhaustion;
  // Shield.
  public boolean hasShield = false;
  public boolean hasShieldEquiped = true;
  // Dodge.
  public boolean dodge = false;
  public boolean canRun = true;
  // Fighting style.
  public String fightingStyle = "";
  // Numbers of actions.
  public int numberOfActions = 1;
  // If they hit a critical.
  public boolean criticalHit = false;
  // How many attacks they have.
  public int numberOfAttacks = 1;
  // Inventory
  private ArrayList<String> equipment = new ArrayList<String>();
  // Skills
  public ArrayList<String> proficiencies = new ArrayList<String>();
//=============================================================================
  /**
   * Replicates rolling a D20.
   */
  private int rollD20(String rollWith) {
    // 2 Rolls.
    int roll1 = (int) (Math.random() * 20 + 1);
    int roll2 = (int) (Math.random() * 20 + 1);
    // Rolling with advantage.
    if (rollWith.equals("Advantage")) {
      if (roll1 > roll2) {
        return roll1;
      } else {
        return roll2;
      }
    // Rolling with disadvantage.
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
   * Adds equipment to the characters inventory.
   */
  public void addEquipment(String item, String weight, String type) {
    int currentWeight = 0;
    int weightAsInt;
    //Getting the current weight of the characters gear.
    for (int position = 0; position > equipment.size(); position++) {
      if ((position + 1) % 2 == 0 && (position + 1) % 3 != 0) {
        weightAsInt = Integer.parseInt(equipment.get(position));
        currentWeight += weightAsInt;
      }
    }
    weightAsInt = Integer.parseInt(weight);
    if (currentWeight + weightAsInt <= strength * 15) {
      equipment.add(item);
      equipment.add(weight);
      equipment.add(type);
    }
  }
//=============================================================================
  /**
   * Returns the equipment.
   */
  public String showEquipment() {
    String equipmentAsString = "";
    for (int position = 0; position < equipment.size(); position++) {
      if (position % 3 == 0) {
        equipmentAsString += ((position / 3) + 1) + ". | "
                             + equipment.get(position);
      }
      else if ((position - 1) % 3 == 0) {
        equipmentAsString += " = " + equipment.get(position) + " lbs\n";
      }
    }
    if (equipmentAsString.equals("")) {
      return "This Character Is Not Carrying Anything At The Moment";
    } else {
      return equipmentAsString;
    }
  }
//=============================================================================
  /**
   * Returns the weapons.
   */
  public String showWeapons() {
    String weaponsAsString = "Here are " + name + "'s current weapons:\n";
    int listNumber = 1;
    for (int position = 0; position < equipment.size(); position++) {
      if ((position + 1) % 3 == 0) {
        if (equipment.get(position).equals("Weapon")) {
          weaponsAsString += "[" + listNumber + ". "
                            + equipment.get(position - 2) + "] \n";
          listNumber++;
        }
      }
    }
    if (weaponsAsString.equals("")) {
      return name + " is not carrying any weapons at the moment";
    } else {
      return weaponsAsString + "\n[B = Back]";
    }
  }
//=============================================================================
  /**
   * Returns the weapons.
   */
  public String findWeapon(String weaponString) {
    try {
      int weaponNumber = Integer.parseInt(weaponString);
      int listNumber = 1;
      for (int position = 0; position < equipment.size(); position++) {
        if ((position + 1) % 3 == 0) {
          if (equipment.get(position).equals("Weapon")) {
            if (listNumber == weaponNumber) {
              return equipment.get(position - 2);
            }
            listNumber++;
          }
        }
      }
      return "Null";
    } catch (Exception e) {
      return "Null";
    }
  }
//=============================================================================
  /**
   * Gain Exhaustion.
   */
  public String gainExhaustion() {
    tookExhaustion = -1;
    exhaustion += 1;
    return "\u001B[31m☠ " + name + " now has " + exhaustion 
           + " levels of exhaustion ☠\u001B[0m";
  }
//=============================================================================
  /**
   * Lose Exhaustion.
   */
  public String loseExhaustion() {
    if (tookExhaustion != 0) {
      return "\u001B[31m☠ " + name + " can't loose exhaustion taken "
           + "today ☠\u001B[0m";
    } else {
      if (exhaustion > 0) {
        exhaustion -= 1;
        return "\u001B[32m- " + name + " now has " + exhaustion
               + " levels of exhaustion -\u001B[0m";
      } else {
        return "";
      }
    }
  }
//=============================================================================
  /**
   * Getting how many levels of exhaustion they have.
   */
  public int getExhaustion() {
    return exhaustion;
  }
//=============================================================================
  /**
   * Eat.
   */
  public String eat() {
    lbsOfFood -= 1;
    if (lbsOfFood < 0) {
      lbsOfFood = 0;
      if (daysWithoutFood > 3 + ((constitution - constitution % 2) / 2)) {
         return "\u001B[31m☠ " + name + " did not consume enough food ☠"
                + "\u001B[0m\n" + gainExhaustion();
      } else {
        daysWithoutFood++;
        return "\u001B[31m☠ " + name + " did not consume enough food ☠"
               + "\u001B[0m";
      }
    }
    return "\u001B[32m🗸 " + name + " ate 1 lbs of food 🗸\u001B[0m";
  }
//=============================================================================
  /**
   * Drink.
   */
  public String drink() {
    gallonsOfWater -= 2;
    if (gallonsOfWater < 0) {
      gallonsOfWater = 0;
      return "\u001B[31m☠ " + name + " did not consume enough water ☠"
             + "\u001B[0m\n" + gainExhaustion() ;
    } else {
      return "\u001B[32m🗸 " + name + " drank 2 gallonsOfWater 🗸\u001B[0m";
    }
  }
//=============================================================================
  /**
   * Foraging for food and water.
   */
  public String forage(int forageRoll, int foundRoll, String forageType) {
    // Succsess.
    if (makeCheck( "Wis", "Survival", "", forageRoll) > 15) {
      if (forageType.equals("Water")) {
        gallonsOfWater += foundRoll;
        // Showing how much they found.
        return "\u001B[32m🗸 " + name + " found " + foundRoll
               + " gallons of water 🗸\u001B[0m";
      } else {
        lbsOfFood += foundRoll;
        return "\u001B[32m🗸 " + name + " found " + foundRoll
             + " lbs of food 🗸\u001B[0m";
      }
    } else {
      if (forageType.equals("Water")) {
        // Showing how much they found.
        return "\u001B[31mX " + name + " didn't find any water X" 
               + "\u001B[0m";
      } else {
        return "\u001B[31mX " + name + " didn't find any food X" 
             + "\u001B[0m";
      }
    }
  }
//=============================================================================
  /**
   * Making a skill check..
   */
  public int makeCheck(String ability, String skill, String type, int roll) {
    int position;
    // What to do for a saving throw.
    for (position = 0; position < proficiencies.size(); position++) {
      if (proficiencies.get(position).equals(ability)
          || proficiencies.get(position).equals(skill)) {
        roll += proficiencyBonus;
      }
    }
    if (!type.equals("Saving Throw") && skill.equals("")) {
      roll -= proficiencyBonus;
    }
    // Adding skill mods ------------------------------------------------------
    if (ability.equals("Str")) {
      roll += (((strength - strength % 2) - 10) / 2);
    } else if (ability.equals("Con")) {
      roll += (((constitution - constitution % 2) - 10) / 2);
    } else if (ability.equals("Dex")) {
      roll += (((dexterity - dexterity % 2) - 10) / 2);
    } else if (ability.equals("Int")) {
      roll += (((intelligence - intelligence % 2) - 10) / 2);
    } else if (ability.equals("Wis")) {
      roll += (((wisdom - wisdom % 2) - 10) / 2);
    } else {
      roll += (((charisma - charisma % 2) - 10) / 2);
    }
    return roll;
  }
//=============================================================================
  /**
   * Getting character info.
   */
  public String getCharacterInfo() {
    // Reseting if they have their shield equiped.
    hasShieldEquiped = true;
    // Initial character info.
    String characterInfo = name + ":\n♥ HP: " + currentHitPoints + "\nØ AC: "
                           + armorClass + "\nØ Shield:";
    // Adding to it if they have a shield.
    if (hasShield && hasShieldEquiped) {
      characterInfo += "+2";
    } else {
      characterInfo += "Not equiped";
    }
    // Death Saves ------------------------------------------------------------
    characterInfo += "\n☆ Lv: " + level + "\n☆ exp: " + experiencePoints 
                    + "\nDeath Saves\n Successes: ";
    for (int count1 = 0; count1 < 3; count1++) {
      if (count1 < deathSaveSuccess) {
        characterInfo += " 🗸";
      } else {
        characterInfo += " O";
      }
    }
    characterInfo += "\n  Failures: ";
    for (int count2 = 0; count2 < 3; count2++) {
      if (count2 < deathSaveFailure) {
        characterInfo += " X";
      } else {
        characterInfo += " O";
      }
    }
    return characterInfo + "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
  }
//=============================================================================
  /**
   * Making an attack roll.
   */
  public int makeAttackRoll(String property1, String property2) {
    // The Setup --------------------------------------------------------------
    // Reseting roll status.
    String rollStatus = "";
    // Setting it to disadvantage if they have exhaustion.
    if (exhaustion > 1) {
      rollStatus = "disadvantage";
    }
    // Reseting critical hit.
    criticalHit = false;
    // Making the attack roll.
    int attackRoll = rollD20(rollStatus);
    // Checking for crits.
    if (attackRoll == 20) {
      criticalHit = true;
    }
    // The Attack Roll --------------------------------------------------------
    // Getting rid of the shield if they are using a two handed weapon.
    if (property1.equals("Two-handed")) {
      hasShieldEquiped = false;
    }
    // Getting the value.
    // Ranged attacks use the dex mod.
    if (property1.equals("Finesse") || property2.equals("Ranged")) {
      if (fightingStyle.equals("Archery") && property2.equals("Ranged")) {
        attackRoll += 2;
      }
      return attackRoll + (((dexterity - dexterity % 2) - 10) / 2)
             + proficiencyBonus;
    }
    // Normal weapons use your str mod.
    return attackRoll + (((strength - strength % 2) - 10) / 2)
           + proficiencyBonus;
  }
//=============================================================================
  /**
   * This function is used to tell if the player has successfuly run away from
   * the encounter.
   */
  public String run(String enemyType, int runDc, int intimidateDc,
                    int persuadeDc, int tameDc, String encounter,
                    String playerChoice) {
    String rollStatus = "";
    // Checking if the player has any exhaustion first.
    if (exhaustion > 0) {
      rollStatus = "disadvantage";
    }

    // Run options if the enemies are humanoids.
    if (enemyType.equals("Humanoid") && playerChoice.equals("-1")) {
      return "[1 = Run away] [2 = Inimidate] [3 = Persuade] [B = Back]";
    // Run options if the enemies are beasts.
    } else if (enemyType.equals("Beast") && playerChoice.equals("-1")) {
      return "[1 = Run away] [2 = Inimidate] [3 = Tame] [B = Back]";
    // First option: Run ------------------------------------------------------
    } else if (playerChoice.equals("1")) {
      // Making a check to see if they got away.
      if (makeCheck("Dex", "Acrobatics", "Check", rollD20(rollStatus)) > runDc)
      {
        return "Succsess";
      // Failed to get away
      } else {
        return "\u001B[31m- The party failed to escape from the " + encounter
               + "s -\u001B[0m";
      }
    // Second option: Intimidate ----------------------------------------------
    } else if (playerChoice.equals("2")) {
      // Making a check to see if they got away.
      if (makeCheck("Cha", "Intimidation", "Check", rollD20(rollStatus))
          > intimidateDc) {
        return "Succsess";
      // Failed to get away
      } else {
        return "\u001B[31m- The " + name + " failed to scare off the "
               + encounter + "s -\u001B[0m";
      }
    // Third option (Humanoid): Persuade --------------------------------------
    } else if (playerChoice.equals("3") && enemyType.equals("Humanoid")) {
      // Making a check to see if they got away.
      if (makeCheck("Cha", "Persuasion", "Check", rollD20(rollStatus))
          > persuadeDc) {
        return "Succsess";
      // Failed to get away
      } else {
        return "\u001B[31m- The party failed to persuade the " + encounter
               + "s to leave you alone -\u001B[0m";
      }
    // Third option (Beast): Tame ---------------------------------------------
    } else if (playerChoice.equals("3") && enemyType.equals("Beast")) {
      // Making a check to see if they got away.
      if (makeCheck("Cha", "Animal Handling", "Check", rollD20(rollStatus))
          > tameDc) {
        return "Succsess";
      // Failed to get away
      } else {
        return "\u001B[31m- The party failed to tame the " + encounter
               + "s -\u001B[0m";
      }
    // Going back -------------------------------------------------------------
    } else if (playerChoice.equals("B") || playerChoice.equals("b")) {
      return "Back";
    // If the player's choice isn't an option.
    } else {
      return "Invalid Input";
    }
  }
//=============================================================================
  /**
   * This function makes death saves for the character.
   */
   public String makeDeathSave() {
    if (deathSaveFailure >= 3) {
      isDead = true;
      return "\u001B[31m☠ " + name + " has passed away... ☠\u001B[0m";
    } else if (deathSaveSuccess >= 3) {
      currentHitPoints = 1;
      return "\u001B[32m " + name + " has become stable!\u001B[0m";
    }
    if (rollD20("") >= 10) {
      deathSaveSuccess++;
      return "\u001B[32m " + name + " is holding on!\u001B[0m";
    } else {
      deathSaveFailure++;
      return "\u001B[31m☠ " + name + " is in critical condition! ☠\u001B[0m";
    }
  }
} 
