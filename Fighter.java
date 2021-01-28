/*
* The Fighter program is an extension from the Character program.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-15
*/
//=============================================================================

public class Fighter extends Character {
  
//=============================================================================
  // If their various abilities are ready.
  public boolean isSecondWindReady = true;
  public boolean isProtectionReady = true;
  public boolean isActionSurgeReady = true;
//=============================================================================
  /**
   * Setting a bunch of various variables from Character.
   */
  public Fighter() {
    super();
    maxHitPoints = 12; //12
    currentHitPoints = 12; //12
    armorClass = 13; //Chain Shirt.
    // Ability Scores.
    strength = 16;
    dexterity = 11;
    constitution = 16;
    intelligence = 11;
    wisdom = 13;
    charisma = 10;
    // Proficiencies.
    proficiencies.add("Str");
    proficiencies.add("Con");
    proficiencies.add("Athletics");
    proficiencies.add("Intimidation");
    proficiencies.add("Acrobatics");
    proficiencies.add("Animal Handling");
    proficiencies.add("Survival");
    experiencePoints = 0;
  }
//=============================================================================
  /**
   * Printing out what the fighter can do.
   */
  public String choices() {
    String choices = "[1 = Run] [2 = Attack] [3 = Dodge]";
    if (super.level > 1) {
      choices += " [4 = Action Surge]";
    }
    return choices + " [N = Nothing]";
  }
//=============================================================================
  /**
   * Changing their choice from int so string.
   */
  public String makeChoice(String playerChoice) {
    if (playerChoice.equals("1")) {
      return "Run";
    } else if (playerChoice.equals("2")) {
      return "Attack";
    } else if (playerChoice.equals("3")) {
      super.dodge = true;
      return "Dodge";
    } else if (playerChoice.equals("4")) {
      return "Action Surge";
    } else if (playerChoice.equals("n") || playerChoice.equals("N")) {
      return "Nothing";
    } else {
      return "Invlaid";
    }
  }
//=============================================================================
  /**
   * Bonus action options.
   */
  public static String bonusActions() {
    return "[1 = Second Wind] [2 = Attack] [N = Nothing]";
  }
//=============================================================================
  /**
   * Second wind.
   */
  public String secondWind(int roll) {
    if (isSecondWindReady) {
      isSecondWindReady = false;
      currentHitPoints += roll + super.level;
      if (currentHitPoints > maxHitPoints) {
        currentHitPoints = maxHitPoints;
        return "\u001B[32m" + name + " got their second wind! " + name
               + " returned to max health!\u001B[0m";
      }
      return "\u001B[32m" + name + " got their second wind! " + name
             + " regained " + (roll + super.level) + " hit points!\u001B[0m";
    }
    return "\u001B[31m" + name + " has already used second wind, finish a long"
           + " rest to use it again!\u001B[0m";
  }
//=============================================================================
  /**
   * Action Surge.
   */
  public String actionSurge() {
    if (isActionSurgeReady) {
      super.numberOfActions = 2;
      isActionSurgeReady = false;
      return super.name + " is going even further beyond his limits!";
    } else {
      return "Null";
    }
  }
//=============================================================================
  /**
   * Leveling up.
   */
  public String levelUp() {
    int levelsGained = 0;
    // Level 2 ----------------------------------------------------------------
    if (super.experiencePoints >= 300 && super.level == 1) {
      super.level++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 3 ----------------------------------------------------------------
    if (super.experiencePoints >= 900 && super.level == 2) {
      super.level++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 4 ----------------------------------------------------------------
    if (super.experiencePoints >= 2700 && super.level == 3) {
      super.level++;
      super.constitution += 2;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 5 ----------------------------------------------------------------
    if (super.experiencePoints >= 6500 && super.level == 4) {
      super.level++;
      super.proficiencyBonus++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      super.numberOfAttacks++;
      levelsGained++;
    }
    // Level 6 ----------------------------------------------------------------
    if (super.experiencePoints >= 14000 && super.level == 5) {
      super.level++;
      super.strength += 2;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 7 ----------------------------------------------------------------
    if (super.experiencePoints >= 23000 && super.level == 6) {
      super.level++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 8 ----------------------------------------------------------------
    if (super.experiencePoints >= 34000 && super.level == 7) {
      super.level++;
      super.strength += 2;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 9 ----------------------------------------------------------------
    if (super.experiencePoints >= 48000 && super.level == 8) {
      super.level++;
      super.proficiencyBonus++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    // Level 10 ----------------------------------------------------------------
    if (super.experiencePoints >= 64000 && super.level == 9) {
      super.level++;
      super.maxHitPoints += (6 + (((super.constitution - super.constitution
                                    % 2) - 10) / 2));
      levelsGained++;
    }
    if (levelsGained > 0) {
      return super.name + " leveled up to level " + super.level + "!\nHit "
             + "points increased to: " + super.maxHitPoints;
    } else {
      return "";
    }
  }
}
