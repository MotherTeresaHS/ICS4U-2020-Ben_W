/*
* The Character program implements an application that
* creates a character.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-15
*/

import java.util.ArrayList;  // Import the ArrayList class

///////////////////////////////////////////////////////////////////////////////

abstract class Character {
  // Character Name.
  public String name;
  // Experience points.
  public int experiencePoints;
  // Hit points.
  public int tempHitPoints;
  public int maxHitPoints;
  public int currentHitPoints;
  // Armor Class.
  public int armorClass;
  public int currentArmorClass;
  // Initiative.
  public int initiative;
  // Ability Scores.
  public int strength;
  public int dexterity;
  public int constitution;
  public int intelligence;
  public int wisdom;
  public int charisma;
  // Death Saves.
  public int deathSaveSuccess;
  public int deathSaveFailure;
  // Proficiency Bonus.
  public int proficiencyBonus = 2;
  private int exhaustion = 0;

  public int lbsOfFood = 4;
  public int maxFood = 6;
  public int gallonsOfWater = 2;
  public int maxWater = 4;
  public int daysWithoutFood = 0;
  public int tookExhaustion;

  public boolean hasShield = false;
  public boolean hasShieldEquiped = true;
  
  public boolean dodge = false;
  public boolean canRun = true;

  public String fightingStyle = "";
  
  public int numberOfActions = 1;

  // Inventory
  private ArrayList<String> equipment = new ArrayList<String>();
  
  // Skills
  public ArrayList<String> proficiencies = new ArrayList<String>();

///////////////////////////////////////////////////////////////////////////////
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

///////////////////////////////////////////////////////////////////////////////
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
  
///////////////////////////////////////////////////////////////////////////////
  /**
   * Returns the weaponst.
   */
  public String showWeapons() {
    String weaponsAsString = "Here are " + name + "'s current weapons:\n";
    int listNumber = 1;
    for (int position = 0; position < equipment.size(); position++) {
      if ((position + 1) % 3 == 0) {
        if (equipment.get(position).equals("Weapon")) {
          weaponsAsString += "[" + listNumber + ". " + equipment.get(position - 2) + "] \n";
          listNumber++;
        }
      }
    }
    if (weaponsAsString.equals("")) {
      return name + " is not carrying any weapons at the moment";
    } else {
      return weaponsAsString;
    }
  }
  
///////////////////////////////////////////////////////////////////////////////
  /**
   * Returns the weaponst.
   */
  public String findWeapon(int weaponNumber) {
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
    return "Couldn't find weapon";
  }

///////////////////////////////////////////////////////////////////////////////
  /**
   * Gain Exhaustion.
   */
  public String gainExhaustion() {
    tookExhaustion = -1;
    exhaustion += 1;
    return "\u001B[31m☠ " + name + " now has " + exhaustion 
           + " levels of exhaustion ☠\u001B[0m";
  }

///////////////////////////////////////////////////////////////////////////////
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

///////////////////////////////////////////////////////////////////////////////
  public int getExhaustion() {
    return exhaustion;
  }
  
///////////////////////////////////////////////////////////////////////////////
  public String eat() {
    lbsOfFood -= 1;
    if (lbsOfFood > maxFood) {
      lbsOfFood = maxFood;
    }

    if (lbsOfFood < 0) {
      lbsOfFood = 0;
      if (daysWithoutFood > 3 + ((constitution - constitution % 2) / 2)) {
         return "\u001B[31m☠ " + name + " did not consume enough food ☠\u001B[0m\n" + gainExhaustion();
      } else {
        return "\u001B[31m☠ " + name + " did not consume enough food ☠\u001B[0m";
      }
    }
    return "\u001B[32m🗸 " + name + " ate 1 lbs of food 🗸\u001B[0m";
  }

///////////////////////////////////////////////////////////////////////////////
  public String drink() {
    gallonsOfWater -= 2;
    if (gallonsOfWater > maxWater) {
      gallonsOfWater = maxWater;
    }
    if (gallonsOfWater < 0) {
      gallonsOfWater = 0;
      return "\u001B[31m☠ " + name + " did not consume enough water ☠\u001B[0m\n" + gainExhaustion() ;
    } else {
      return "\u001B[32m🗸 " + name + " drank 2 gallonsOfWater 🗸\u001B[0m";
    }
  }
  
  ///////////////////////////////////////////////////////////////////////////////
  public void rollInt(int roll) {
    initiative = roll + dexterity;
  }

///////////////////////////////////////////////////////////////////////////////

  public String forageFood(int forageRoll, int foundRoll) {
    // Foraging for food.
    // Succsess.
    if (makeCheck( "Wis", "Survival", "", forageRoll) > 15) {

      // Adding the food to their character.
      lbsOfFood += foundRoll;

      // Showing how much they found.
      return "\u001B[32m🗸 " + name + " found " + foundRoll
             + " lbs of food 🗸\u001B[0m";
      
    // Failure.
    } else {
      return "\u001B[31mX " + name + " didn't find any food X" 
             + "\u001B[0m";
    }
  }

///////////////////////////////////////////////////////////////////////////////

  public String forageWater(int forageRoll, int foundRoll) {
    // Foraging for food.
    // Succsess.
    if (makeCheck( "Wis", "Survival", "", forageRoll) > 15) {

      // Adding the food to their character.
      gallonsOfWater += foundRoll;

      // Showing how much they found.
      return "\u001B[32m🗸 " + name + " found " + foundRoll
             + " gallonsOfWater 🗸\u001B[0m";
      
    // Failure.
    } else {
      return "\u001B[31mX " + name + " didn't find any water X" 
             + "\u001B[0m";
    }
  }

///////////////////////////////////////////////////////////////////////////////
  public int makeCheck(String ability, String skill, String type, int roll) {
    int position;
    // What to do for a saving throw.
    if (type.equals("Saving Throw")) {
      for (position = 0; position < proficiencies.size(); position++) {
        if (proficiencies.get(position).equals(ability)) {
          roll += proficiencyBonus;
        }
      }
    // Otherwise it's just a normal ability check.
    } else {
      for (position = 0; position < proficiencies.size(); position++) {
        if (proficiencies.get(position).equals(skill)) {
          roll += proficiencyBonus;
        }
      }
    }

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
  
  /////////////////////////////////////////////////////////////////////////////
  public String getCharacterInfo() {
    if (hasShield && hasShieldEquiped) {
      return name + ":\n♥ HP: " + currentHitPoints + "\nØ AC: " + armorClass
             + "\nØ Shield: +2\n"
             +"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    }
    return name + ":\n♥ HP: " + currentHitPoints + "\nØ AC: " + armorClass
           + "\nØ Shield: Not equiped\n"
          +"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
  }
  
  /////////////////////////////////////////////////////////////////////////////
  public int makeAttackRoll(int attackRoll, String property1, String property2) {

    if (property1.equals("Two-handed")) {
      hasShieldEquiped = false;
    } else if (property1.equals("Finesse")) {
      return attackRoll + (((dexterity - dexterity % 2) - 10) / 2)
             + proficiencyBonus;
    }
    if (property2.equals("Ranged")) {
      if (fightingStyle.equals("Archery")) {
        return attackRoll + (((dexterity - dexterity % 2) - 10) / 2)
               + proficiencyBonus + 2;
      } else {
        return attackRoll + (((dexterity - dexterity % 2) - 10) / 2)
               + proficiencyBonus;
      }
    }
    return attackRoll + (((strength - strength % 2) - 10) / 2)
           + proficiencyBonus;
  }
}
