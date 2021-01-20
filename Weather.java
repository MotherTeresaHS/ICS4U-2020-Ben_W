/*
* The weather program implements an application that
* determines the weather.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-14
*/

///////////////////////////////////////////////////////////////////////////////

public class Weather {
  
  private static String currentWeather;
  
  public int dayNumber = 1;
  
  public static int setWeather(int roll) {
    if (roll <= 16) {
      currentWeather = "Occasional light rain";
    } else if (roll >= 17 && roll <= 19) {
      currentWeather = "Heavy rain";
    } else if (roll == 20) {
      currentWeather = "Tropical storm";
    }
    return roll;
  }

  public static String getWeather() {
    return currentWeather;
  }
}
