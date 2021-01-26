/*
* The Fighter program is an extension from the Character program.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-15
*/

///////////////////////////////////////////////////////////////////////////////

public class Fighter extends Character {
  
  public boolean isSecondWindReady = true;
  public boolean isProtectionReady = true;
  
  public Fighter() {
    super();
    tempHitPoints = 0;
    maxHitPoints = 12; //12
    currentHitPoints = 12; //12
    armorClass = 13; //Chain Shirt.
    initiative = 0;
    // Ability Scores.
    strength = 16; //16
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
  }
  
  public static String choices() {
    return "[1 = Run] [2 = Attack] [3 = Dodge] [4 = Nothing]";
  }
  
  public static String bonusActions() {
    return "[1 = Second Wind] [2 = Attack] [3 = Nothing]";
  }
  
  public String secondWind(int roll) {
    if (isSecondWindReady) {
      isSecondWindReady = false;
      currentHitPoints += roll + 1;
      if (currentHitPoints > maxHitPoints) {
        currentHitPoints = maxHitPoints;
        return "\u001B[32m" + name + " got their second wind! " + name
               + " returned to max health!\u001B[0m";
      }
      return "\u001B[32m" + name + " got their second wind! " + name
             + " regained " + (roll + 1) + " hit points!\u001B[0m";
    }
    return "\u001B[31m" + name + " has already used second wind, finish a long"
           + " rest to use it again!\u001B[0m";
  }
}
