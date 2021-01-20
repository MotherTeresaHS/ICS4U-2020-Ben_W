/*
* The Fighter program is an extension from the Character program.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-15
*/

///////////////////////////////////////////////////////////////////////////////

public class Fighter extends Character {
  
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

  public static String run() {
    return "[1 = Run away] [2 = Inimidate] [3 = Persuade] [4 = Return]";
  }
  
  public int makeAttackRoll(int attackRoll) {
    return attackRoll + (((strength - strength % 2) - 10) / 2) + super.proficiencyBonus;
  }
}
