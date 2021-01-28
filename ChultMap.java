/*
* The ChultMap program implements an application that
* generates a map and can show the user where on the map they are.
*
* @author  Ben Whitten
* @version 1.1
* @since   2021-1-3
*/
//=============================================================================

public class ChultMap {

//=============================================================================
  // Define color constants
  private static final String ANSI_RESET  = "\u001B[0m";
  private static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
  private static final String RED_BRIGHT = "\033[0;91m";    // RED
  private static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
  private static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
  private static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
  private static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
  private static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
  private static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
  private static final String TEXT_RESET = "\u001B[0m";
  private static final String TEXT_BLACK = "\u001B[30m";
  private static final String TEXT_RED = "\u001B[31m";
  private static final String TEXT_GREEN = "\u001B[32m";
  private static final String TEXT_YELLOW = "\u001B[33m";
  private static final String TEXT_BLUE = "\u001B[34m";
  private static final String TEXT_PURPLE = "\u001B[35m";
  private static final String TEXT_CYAN = "\u001B[36m";
  private static final String TEXT_WHITE = "\u001B[37m";
  private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
//=============================================================================
  private static final int numberOfPositions = 6192;
  // Each different tiles.
  private static final String beachTile = GREEN_BRIGHT + "██" + ANSI_RESET;
  private static final String noUndeadTile = ANSI_GREEN_BACKGROUND + "  "
                                             + ANSI_RESET;
  private static final String lesserUndeadTile = YELLOW_BRIGHT + "██"
                                                 + ANSI_RESET;
  private static final String greaterUndeadTile = TEXT_PURPLE + "██"
                                                  + ANSI_RESET;
  private static final String mountainTile = BLACK_BRIGHT + "██" + ANSI_RESET;
  private static final String riverTile = BLUE_BRIGHT + "██" + ANSI_RESET;
  private static final String swampTile = PURPLE_BRIGHT + "██" + ANSI_RESET;
  private static final String wastelandTile = TEXT_YELLOW + "██" + ANSI_RESET;
  private static final String townTile = TEXT_WHITE + ANSI_BLACK_BACKGROUND
                                         + "[]" + ANSI_RESET;
  private static final String playerTile = RED_BRIGHT + "██" + ANSI_RESET;
  private static final String islandTile = GREEN_BRIGHT + ANSI_BLUE_BACKGROUND
                                           + " ■" + ANSI_RESET;
  private static final String unknownTile = ANSI_WHITE_BACKGROUND + TEXT_BLACK
                                            + "[]" + ANSI_RESET;
  private static final String oceanTile = TEXT_BLUE + "██" + ANSI_RESET;
  // Player's position.
  public static int playerPosition = 1481;
  // Map list.
  public static int [] [] map = new int [numberOfPositions] [2];
//=============================================================================
  /**
   * This function generates the map.
   */
  public static void generateMap() {
    // Creates values for each position on map.
    /**
     * 0 = Type Of Space.
     * (Beach = 0, No Undead = 1, Lesser Undead = 2, Greater Undead = 3,
     *  Mountains = 4, Rivers = 5, Swamp = 6, Wasteland = 7, town = 8
     * island = 9, ocean = 10.)
     * 1 = Has The Player Explored This Space?
     * (Explored = 0, Hasn't Explored = 1)
     * 2 = Is it a location?
     */
    for (int listPosition = 0; listPosition < numberOfPositions;
         listPosition++) {
      // Setting if the location has been explored yet. It may be long but it
      // works.
      if ((listPosition == 1691) //Row 1.
          || (listPosition >= 1762 && listPosition <= 1764)   //Row 2.
          || (listPosition >= 1831 && listPosition <= 1833)   //Row 3.
          || (listPosition == 1835) || (listPosition == 1836)
          || (listPosition >= 1901 && listPosition <= 1908)   //Row 4.
          || (listPosition >= 1971 && listPosition <= 1978)   //Row 5.
          || (listPosition >= 1971 && listPosition <= 1978)   //Row 5.
          || (listPosition >= 2041 && listPosition <= 2048)   //Row 6.
          || (listPosition >= 2112 && listPosition <= 2118)   //Row 7.
          || (listPosition == 2131) || (listPosition == 2132)
          || (listPosition >= 2183 && listPosition <= 2190)   //Row 8.
          || (listPosition == 2181)
          || (listPosition >= 2197 && listPosition <= 2199)
          || (listPosition == 2203) || (listPosition == 2204)
          || (listPosition >= 2254 && listPosition <= 2260)   //Row 9.
          || (listPosition >= 2267 && listPosition <= 2271)
          || (listPosition >= 2274 && listPosition <= 2276)
          || (listPosition == 2323)                           //Row 10.
          || (listPosition >= 2325 && listPosition <= 2332)
          || (listPosition >= 2337 && listPosition <= 2343)
          || (listPosition >= 2346 && listPosition <= 2348)
          || (listPosition >= 2395 && listPosition <= 2415)   //Row 11.
          || (listPosition >= 2418 && listPosition <= 2420)
          || (listPosition == 2424) || (listPosition == 2425)
          || (listPosition >= 2467 && listPosition <= 2492)   //Row 12.
          || (listPosition >= 2496 && listPosition <= 2498)
          || (listPosition >= 2540 && listPosition <= 2564)   //Row 13.
          || (listPosition >= 2568 && listPosition <= 2570)
          || (listPosition >= 2613 && listPosition <= 2634)   //Row 14.
          || (listPosition >= 2639 && listPosition <= 2641)
          || (listPosition >= 2683 && listPosition <= 2706)   //Row 15.
          || (listPosition >= 2710 && listPosition <= 2713)
          || (listPosition >= 2756 && listPosition <= 2758)   //Row 16.
          || (listPosition >= 2761 && listPosition <= 2778)
          || (listPosition >= 2782 && listPosition <= 2786)
          || (listPosition >= 2834 && listPosition <= 2851)   //Row 17.
          || (listPosition >= 2854 && listPosition <= 2857)
          || (listPosition >= 2906 && listPosition <= 2929)   //Row 18.
          || (listPosition >= 2977 && listPosition <= 3001)   //Row 19.
          || (listPosition >= 3048 && listPosition <= 3073)   //Row 20.
          || (listPosition >= 3119 && listPosition <= 3145)   //Row 21.
          || (listPosition >= 3189 && listPosition <= 3217)   //Row 22.
          || (listPosition >= 3259 && listPosition <= 3290)   //Row 23.
          || (listPosition >= 3295 && listPosition <= 3297)
          || (listPosition >= 3330 && listPosition <= 3363)   //Row 24.
          || (listPosition >= 3365 && listPosition <= 3371)
          || (listPosition >= 3402 && listPosition <= 3436)   //Row 25.
          || (listPosition >= 3438 && listPosition <= 3444)
          || (listPosition >= 3474 && listPosition <= 3507)   //Row 26.
          || (listPosition >= 3510 && listPosition <= 3517)
          || (listPosition >= 3548 && listPosition <= 3550)   //Row 27.
          || (listPosition >= 3553 && listPosition <= 3579)
          || (listPosition >= 3582 && listPosition <= 3590)
          || (listPosition >= 3625 && listPosition <= 3650)   //Row 28.
          || (listPosition == 3654)
          || (listPosition >= 3657 && listPosition <= 3659)
          || (listPosition == 3662) || (listPosition == 3663)
          || (listPosition >= 3695 && listPosition <= 3708)   //Row 29.
          || (listPosition >= 3711 && listPosition <= 3721)
          || (listPosition == 3730)
          || (listPosition == 3768)                           //Row 30.
          || (listPosition >= 3770 && listPosition <= 3775)
          || (listPosition == 3778) || (listPosition == 3783)
          || (listPosition == 3784)
          || (listPosition >= 3788 && listPosition <= 3793)
          || (listPosition == 3795)
          || (listPosition >= 3844 && listPosition <= 3847)   //Row 31.
          || (listPosition >= 3859 && listPosition <= 3869)
          || (listPosition == 3874) || (listPosition == 3875)
          || (listPosition == 3883)
          || (listPosition >= 3915 && listPosition <= 3919)   //Row 32.
          || (listPosition >= 3934 && listPosition <= 3943)
          || (listPosition >= 3946 && listPosition <= 3949)
          || (listPosition >= 3952 && listPosition <= 3956)
          || (listPosition >= 3987 && listPosition <= 3991)   //Row 33.
          || (listPosition >= 4007 && listPosition <= 4015)
          || (listPosition >= 4018 && listPosition <= 4028)
          || (listPosition == 4055)                           //Row 34.
          || (listPosition >= 4057 && listPosition <= 4065)
          || (listPosition >= 4073 && listPosition <= 4100)
          || (listPosition >= 4127 && listPosition <= 4141)   //Row 35.
          || (listPosition >= 4144 && listPosition <= 4172)
          || (listPosition >= 4200 && listPosition <= 4213)   //Row 36.
          || (listPosition >= 4216 && listPosition <= 4240)
          || (listPosition >= 4242 && listPosition <= 4244)
          || (listPosition >= 4272 && listPosition <= 4276)   //Row 37.
          || (listPosition == 4282)
          || (listPosition >= 4284 && listPosition <= 4288)
          || (listPosition >= 4290 && listPosition <= 4294)
          || (listPosition >= 4296 && listPosition <= 4308)
          || (listPosition == 4310) || (listPosition == 4315)
          || (listPosition == 4316)
          || (listPosition == 4344) || (listPosition == 4358) //Row 38.
          || (listPosition == 4362) || (listPosition == 4363)
          || (listPosition >= 4369 && listPosition <= 4379)
          || (listPosition == 4385) || (listPosition == 4386)
          || (listPosition == 4434) || (listPosition == 4442) //Row 39.
          || (listPosition == 4444)
          || (listPosition >= 4448 && listPosition <= 4452)
          || (listPosition == 4457) || (listPosition == 4458)
          || (listPosition >= 4520 && listPosition <= 4524)   //Row 40.
          || (listPosition == 4530)
          || (listPosition >= 4592 && listPosition <= 4597)   //Row 41.
          || (listPosition >= 4664 && listPosition <= 4668)   //Row 42.
          || (listPosition >= 4737 && listPosition <= 4740)   //Row 43.
          || (listPosition == 4810))                           //Row 44.
      {
        map [listPosition] [1] = 1;
      } else {
        map [listPosition] [1] = 0;
      }
      // Setting if the position is a beach.
      if ((listPosition == 3)                               //Row 1.
          || (listPosition >= 8 && listPosition <= 11)
          || (listPosition >= 13 && listPosition <= 15)
          || (listPosition == 75)                           //Row 2.
          || (listPosition >= 80 && listPosition <= 84)
          || (listPosition == 86) || (listPosition == 87)
          || (listPosition == 146) || (listPosition == 147) //Row 3.
          || (listPosition == 150) || (listPosition == 151)
          || (listPosition >= 154 && listPosition <= 156)
          || (listPosition == 158) || (listPosition == 159)
          || (listPosition >= 218 && listPosition <= 221)   //Row 4.
          || (listPosition >= 226 && listPosition <= 228)
          || (listPosition == 231)
          || (listPosition == 291) || (listPosition == 292) //Row 5.
          || (listPosition == 303)
          || (listPosition == 363) || (listPosition == 370) //Row 6.
          || (listPosition == 441) || (listPosition == 443) //Row 7.
          || (listPosition == 447)
          || (listPosition == 507) || (listPosition == 508) //Row 8.
          || (listPosition == 510) || (listPosition == 520)
          || (listPosition >= 578 && listPosition <= 584)   //Row 9.
          || (listPosition >= 588 && listPosition <= 590)
          || (listPosition == 592) || (listPosition == 593)
          || (listPosition >= 651 && listPosition <= 655)   //Row 10.
          || (listPosition == 657) || (listPosition == 658)
          || (listPosition == 660) || (listPosition == 662)
          || (listPosition == 689)
          || (listPosition == 727) || (listPosition == 731)   //Row 11.
          || (listPosition == 733)
          || (listPosition == 1376) || (listPosition == 1378)
          || (listPosition == 1448) || (listPosition == 1449)
          || (listPosition == 1519)
          || (listPosition == 761) //Row 11
          || (listPosition == 833) //Row 12
          || (listPosition == 895) || (listPosition == 896) //Row 13
          || (listPosition == 905) || (listPosition == 906)
          || (listPosition == 967) || (listPosition == 978) //Row 14
          || (listPosition == 987) || (listPosition == 988)
          || (listPosition == 1050) || (listPosition == 1058) //Row 15
          || (listPosition == 1059) || (listPosition == 1061)
          || (listPosition == 1123) || (listPosition == 1124) //Row 16
          || (listPosition == 1129) || (listPosition == 1134)
          || (listPosition == 1194) || (listPosition == 1196) //Row 17
          || (listPosition == 1206)
          || (listPosition == 1257) || (listPosition == 1260) //Row 18
          || (listPosition == 1263) || (listPosition == 1264)
          || (listPosition == 1266) || (listPosition == 1268)
          || (listPosition == 1277) || (listPosition == 1278)
          || (listPosition == 1328) //Row 19
          || (listPosition >= 1336 && listPosition <= 1339)
          || (listPosition == 1348)
          || (listPosition == 1399) || (listPosition == 1406) //Row 20
          || (listPosition == 1410) || (listPosition == 1420)
          || (listPosition == 1471) || (listPosition == 1478) //Row 21
          || (listPosition == 1491)
          || (listPosition == 1542) || (listPosition == 1549) //Row 22
          || (listPosition == 1550)
          || (listPosition >= 1552 && listPosition <= 1554)
          || (listPosition == 1563)
          || (listPosition == 1612) || (listPosition == 1613) //Row 23
          || (listPosition >= 1623 && listPosition <= 1625)
          || (listPosition == 1635)
          || (listPosition == 1682) || (listPosition == 1683) //Row 24
          || (listPosition == 1779) //Row 25
          || (listPosition == 1822) //Row 26
          || (listPosition == 1893) || (listPosition == 1893) //Row 27
          || (listPosition == 1920)
          || (listPosition == 2137) || (listPosition == 2138) //Row 30
          || (listPosition == 2174) || (listPosition == 2175) //Row 31
          || (listPosition == 2211) || (listPosition == 2212)
          || (listPosition == 2245) || (listPosition == 2285) //Row 32
          || (listPosition == 2358) //Row 33
          || (listPosition == 2430) //Row 34
          || (listPosition == 2501) //Row 35
          || (listPosition == 2515)
          || (listPosition >= 2584 && listPosition <= 2586) //Row 36
          || (listPosition == 2532)
          || (listPosition == 2600) || (listPosition == 2645) //Row 37
          || (listPosition == 2656) || (listPosition == 2658)
          || (listPosition == 2672) || (listPosition == 2717) //Row 38
          || (listPosition == 2744) || (listPosition == 2745) //Row 39
          || (listPosition == 2789)
          || (listPosition == 2817) || (listPosition == 2826) //Row 40
          || (listPosition == 2861)
          || (listPosition == 2889) || (listPosition == 2896) //Row 41
          || (listPosition == 2897) || (listPosition == 2932)
          || (listPosition == 2961) || (listPosition == 2962) //Row 42
          || (listPosition == 3034) || (listPosition == 3036) //Row 43
          || (listPosition == 3040) || (listPosition == 3075)
          || (listPosition >= 3079 && listPosition <= 3082)
          || (listPosition == 3107) || (listPosition == 3112) //Row 44
          || (listPosition == 3148) || (listPosition == 3150)
          || (listPosition == 3151)
          || (listPosition == 3221) //Row 45
          || (listPosition >= 3451 && listPosition <= 3452) //Row 48
          || (listPosition == 3527) //Row 49
          || (listPosition == 3752) //Row 53
          || (listPosition == 3824) //Row 54
          || (listPosition == 3896) //Row 55
          || (listPosition == 3968) //Row 56
          || (listPosition == 4040) //Row 57
          || (listPosition == 4113) //Row 58
          || (listPosition == 4410) //Row 62
          || (listPosition == 4482) || (listPosition == 4486) //Row 63
          || (listPosition == 4488)
          || (listPosition == 4556) || (listPosition == 4557) //Row 64
          || (listPosition == 4559) || (listPosition == 4560)
          || (listPosition == 4570) || (listPosition == 4572)
          || (listPosition >= 4580 && listPosition <= 4584)
          || (listPosition == 4586)
          || (listPosition == 4627) //Row 65
          || (listPosition >= 4633 && listPosition <= 4635)
          || (listPosition == 4643) || (listPosition == 4645)
          || (listPosition == 4651)
          || (listPosition >= 4657 && listPosition <= 4660)
          || (listPosition == 4717) || (listPosition == 4725) //Row 66
          || (listPosition == 4726)
          || (listPosition == 4802) || (listPosition == 4804) //Row 67
          || (listPosition >= 4873 && listPosition <= 4876) //Row 68
          || (listPosition == 4895)
          || (listPosition == 4933) || (listPosition == 4947) //Row 69
          || (listPosition == 4949)
          || (listPosition >= 4964 && listPosition <= 4967)
          || (listPosition >= 4996 && listPosition <= 4998) //Row 70
          || (listPosition == 5005) || (listPosition == 5008)
          || (listPosition >= 5036 && listPosition <= 5039)
          || (listPosition == 5069) || (listPosition == 5076) //Row 71
          || (listPosition == 5077)
          || (listPosition >= 5081 && listPosition <= 5084)
          || (listPosition == 5086) || (listPosition == 5092)
          || (listPosition == 5106) || (listPosition == 5108)
          || (listPosition == 5109)
          || (listPosition == 5149) || (listPosition == 5150) //Row 72
          || (listPosition >= 5154 && listPosition <= 5159)
          || (listPosition == 5164) || (listPosition == 5176)
          || (listPosition >= 5178 && listPosition <= 5181)
          || (listPosition == 5183)
          || (listPosition == 5223) || (listPosition == 5224) //Row 73
          || (listPosition >= 5226 && listPosition <= 5229)
          || (listPosition == 5231) || (listPosition == 5237)
          || (listPosition == 5238) || (listPosition == 5247)
          || (listPosition == 5251) || (listPosition == 5254)
          || (listPosition == 5255)
          || (listPosition == 5299) || (listPosition == 5300) //Row 74
          || (listPosition >= 5310 && listPosition <= 5312)
          || (listPosition == 5319) || (listPosition == 5321)
          || (listPosition == 5322)
          || (listPosition >= 5324 && listPosition <= 5327)
          || (listPosition == 5371) || (listPosition == 5383) //Row 75
          || (listPosition == 5390)
          || (listPosition >= 5393 && listPosition <= 5399)
          || (listPosition == 5456) || (listPosition == 5457) //Row 76
          || (listPosition == 5460)
          || (listPosition >= 5465 && listPosition <= 5467)
          || (listPosition == 5469)
          || (listPosition == 5528) || (listPosition == 5532) //Row 77
          || (listPosition == 5538) || (listPosition == 5539)
          || (listPosition == 5600) || (listPosition == 5603) //Row 78
          || (listPosition == 5610) || (listPosition == 5611)
          || (listPosition == 5672) || (listPosition == 5674) //Row 79
          || (listPosition == 5675) || (listPosition == 5681)
          || (listPosition == 5682) || (listPosition == 5686)
          || (listPosition == 5742) || (listPosition == 5743) //Row 80
          || (listPosition == 5754) || (listPosition == 5757)
          || (listPosition == 5758) || (listPosition == 5745)
          || (listPosition == 5828) || (listPosition == 5829) //Row 81
          || (listPosition >= 5899 && listPosition <= 5901) //Row 82
          )
          {
        map [listPosition] [0] = 0;
      // Setting if the position is no undead.
      } else if ((listPosition == 690) //Row 10.
                 || (listPosition >= 762 && listPosition <= 764) //Row 11.
                 || (listPosition == 834) || (listPosition == 835) //Row 12.
                 || (listPosition == 907) || (listPosition == 908) //Row 13
                 || (listPosition == 979) || (listPosition == 980) //Row 14
                 || (listPosition >= 1051 && listPosition <= 1054) //Row 15.
                 || (listPosition == 1056)
                 || (listPosition == 1126) || (listPosition == 1127) //Row 16
                 || (listPosition >= 1197 && listPosition <= 1199) //Row 17.
                 || (listPosition == 1204)
                 || (listPosition >= 1269 && listPosition <= 1272) //Row 18.
                 || (listPosition == 1275) || (listPosition == 1276)
                 || (listPosition >= 1340 && listPosition <= 1344) //Row 19
                 || (listPosition == 1346) || (listPosition == 1347)
                 || (listPosition == 1411) || (listPosition == 1412) //Row 20
                 || (listPosition == 1418) || (listPosition == 1419)
                 || (listPosition == 1483) //Row 21
                 || (listPosition == 1543) || (listPosition == 1544) //Row 22
                 || (listPosition == 1548)
                 || (listPosition == 1614) || (listPosition == 1615) //Row 23
                 || (listPosition >= 1618 && listPosition <= 1621)
                 || (listPosition == 1626)
                 || (listPosition == 1684) || (listPosition == 1685) //Row 24
                 || (listPosition >= 1690 && listPosition <= 1693)
                 || (listPosition >= 1695 && listPosition <= 1697)
                 || (listPosition == 1707)
                 || (listPosition >= 1752 && listPosition <= 1755) //Row 25
                 || (listPosition == 1760)
                 || (listPosition >= 1763 && listPosition <= 1766)
                 || (listPosition == 1823) //Row 26
                 || (listPosition >= 1830 && listPosition <= 1837)
                 || (listPosition == 1850) || (listPosition == 1851)
                 || (listPosition == 1892) //Row 27
                 || (listPosition >= 1899 && listPosition <= 1909)
                 || (listPosition == 1921)
                 || (listPosition == 1962) || (listPosition == 1963) //Row 28
                 || (listPosition >= 1970 && listPosition <= 1979)
                 || (listPosition == 1992)
                 || (listPosition == 2032) || (listPosition == 2033) //Row 29
                 || (listPosition >= 2040 && listPosition <= 2049)
                 || (listPosition == 2104) //Row 30
                 || (listPosition >= 2110 && listPosition <= 2119)
                 || (listPosition >= 2183 && listPosition <= 2191) //Row 31
                 || (listPosition == 2201)
                 || (listPosition >= 2208 && listPosition <= 2210)
                 || (listPosition >= 2255 && listPosition <= 2262) //Row 32
                 || (listPosition == 2264)
                 || (listPosition >= 2279 && listPosition <= 2284)
                 || (listPosition == 2317) || (listPosition == 2318) //Row 33
                 || (listPosition >= 2323 && listPosition <= 2325) 
                 || (listPosition >= 2327 && listPosition <= 2329)
                 || (listPosition >= 2331 && listPosition <= 2333)
                 || (listPosition == 2338) || (listPosition == 2350)
                 || (listPosition >= 2352 && listPosition <= 2357)
                 || (listPosition >= 2389 && listPosition <= 2391) //Row 34
                 || (listPosition >= 2395 && listPosition <= 2397)
                 || (listPosition == 2399) || (listPosition == 2421)
                 || (listPosition >= 2424 && listPosition <= 2429)
                 || (listPosition >= 2462 && listPosition <= 2463) //Row 35
                 || (listPosition >= 2467 && listPosition <= 2470)
                 || (listPosition >= 2473 && listPosition <= 2474)
                 || (listPosition >= 2482 && listPosition <= 2484)
                 || (listPosition >= 2494 && listPosition <= 2500)
                 || (listPosition >= 2533 && listPosition <= 2534) //Row 36
                 || (listPosition >= 2541 && listPosition <= 2543)
                 || (listPosition == 2545) || (listPosition == 2547)
                 || (listPosition >= 2557 && listPosition <= 2558)
                 || (listPosition == 2564)
                 || (listPosition >= 2567 && listPosition <= 2573)
                 || (listPosition == 2610) //Row 37
                 || (listPosition >= 2612 && listPosition <= 2615)
                 || (listPosition == 2617)
                 || (listPosition >= 2626 && listPosition <= 2629)
                 || (listPosition == 2631)
                 || (listPosition >= 2638 && listPosition <= 2644)
                 || (listPosition >= 2681 && listPosition <= 2687) //Row 38
                 || (listPosition >= 2689 && listPosition <= 2691)
                 || (listPosition == 2699) || (listPosition == 2700)
                 || (listPosition == 2702) || (listPosition == 2703)
                 || (listPosition == 2709)
                 || (listPosition >= 2711 && listPosition <= 2716)
                 || (listPosition >= 2754 && listPosition <= 2758) //Row 39
                 || (listPosition >= 2760 && listPosition <= 2763)
                 || (listPosition == 2781) || (listPosition == 2782)
                 || (listPosition >= 2785 && listPosition <= 2788)
                 || (listPosition >= 2822 && listPosition <= 2824) //Row 40
                 || (listPosition == 2827) || (listPosition == 2829)
                 || (listPosition == 2830)
                 || (listPosition >= 2832 && listPosition <= 2835)
                 || (listPosition == 2853)
                 || (listPosition >= 2857 && listPosition <= 2860)
                 || (listPosition == 2893) || (listPosition == 2895) //Row 41
                 || (listPosition == 2900) || (listPosition == 2904)
                 || (listPosition == 2905) || (listPosition == 2924)
                 || (listPosition == 2925)
                 || (listPosition >= 2929 && listPosition <= 2931)
                 || (listPosition == 2965) || (listPosition == 2971) //Row 42
                 || (listPosition == 2975) || (listPosition == 2978)
                 || (listPosition == 2980)
                 || (listPosition >= 2995 && listPosition <= 2998)
                 || (listPosition >= 3001 && listPosition <= 3003)
                 || (listPosition == 3047) || (listPosition == 3048) //Row 43
                 || (listPosition == 3052)
                 || (listPosition >= 3066 && listPosition <= 3070)
                 || (listPosition == 3074) || (listPosition == 3084)
                 || (listPosition >= 3118 && listPosition <= 3122) //Row 43
                 || (listPosition == 3124) //Row 44
                 || (listPosition >= 3139 && listPosition <= 3142)
                 || (listPosition == 3144)
                 || (listPosition == 3146) || (listPosition == 3147)
                 || (listPosition >= 3152 && listPosition <= 3155)
                 || (listPosition == 3157)
                 || (listPosition >= 3188 && listPosition <= 3193) //Row 45
                 || (listPosition >= 3195 && listPosition <= 3197)
                 || (listPosition >= 3215 && listPosition <= 3220)
                 || (listPosition >= 3222 && listPosition <= 3226)
                 || (listPosition >= 3258 && listPosition <= 3263) //Row 46
                 || (listPosition == 3269)
                 || (listPosition >= 3288 && listPosition <= 3300)
                 || (listPosition >= 3330 && listPosition <= 3333) //Row 47
                 || (listPosition >= 3361 && listPosition <= 3372)
                 || (listPosition == 3375) || (listPosition == 3376)
                 || (listPosition == 3378) || (listPosition == 3379)
                 || (listPosition == 3382)
                 || (listPosition >= 3402 && listPosition <= 3404) //Row 48
                 || (listPosition == 3420) || (listPosition == 3421)
                 || (listPosition == 3433)
                 || (listPosition >= 3436 && listPosition <= 3445)
                 || (listPosition >= 3449 && listPosition <= 3450)
                 || (listPosition >= 3453 && listPosition <= 3455)
                 || (listPosition == 3475) || (listPosition == 3476) //Row 49
                 || (listPosition == 3492)
                 || (listPosition >= 3510 && listPosition <= 3518)
                 || (listPosition >= 3522 && listPosition <= 3524)
                 || (listPosition == 3540) || (listPosition == 3548) //Row 50
                 || (listPosition >= 3565 && listPosition <= 3567)
                 || (listPosition == 3578)
                 || (listPosition == 3582) || (listPosition == 3583)
                 || (listPosition >= 3585 && listPosition <= 3587)
                 || (listPosition >= 3589 && listPosition <= 3592)
                 || (listPosition == 3595)
                 || (listPosition >= 3598 && listPosition <= 3600)
                 || (listPosition >= 3610 && listPosition <= 3612) //Row 51
                 || (listPosition == 3621) || (listPosition == 3624)
                 || (listPosition == 3632) || (listPosition == 3636)
                 || (listPosition == 3637) || (listPosition == 3639)
                 || (listPosition == 3644) || (listPosition == 3646)
                 || (listPosition == 3648) || (listPosition == 3649)
                 || (listPosition >= 3657 && listPosition <= 3660)
                 || (listPosition == 3663) || (listPosition == 3667)
                 || (listPosition == 3671)
                 || (listPosition >= 3682 && listPosition <= 3683) //Row 52
                 || (listPosition == 3690)
                 || (listPosition >= 3694 && listPosition <= 3697)
                 || (listPosition >= 3705 && listPosition <= 3707)
                 || (listPosition >= 3715 && listPosition <= 3721)
                 || (listPosition == 3731)
                 || (listPosition >= 3753 && listPosition <= 3755) //Row 53
                 || (listPosition >= 3762 && listPosition <= 3763)
                 || (listPosition >= 3767 && listPosition <= 3770)
                 || (listPosition == 3772)
                 || (listPosition >= 3788 && listPosition <= 3796)
                 || (listPosition == 3801)
                 || (listPosition >= 3808 && listPosition <= 3810)
                 || (listPosition == 3812) || (listPosition == 3813)
                 || (listPosition == 3825) || (listPosition == 3830) //Row 54
                 || (listPosition == 3832) || (listPosition == 3833)
                 || (listPosition == 3835) || (listPosition == 3836)
                 || (listPosition >= 3842 && listPosition <= 3847)
                 || (listPosition == 3859)
                 || (listPosition >= 3861 && listPosition <= 3870)
                 || (listPosition == 3874)
                 || (listPosition == 3897) || (listPosition == 3898) //Row 55
                 || (listPosition >= 3901 && listPosition <= 3904)
                 || (listPosition == 3910) || (listPosition == 3912)
                 || (listPosition >= 3914 && listPosition <= 3918)
                 || (listPosition >= 3935 && listPosition <= 3943)
                 || (listPosition >= 3946 && listPosition <= 3950)
                 || (listPosition == 3958)
                 || (listPosition == 3969) || (listPosition == 3970) //Row 56
                 || (listPosition >= 3973 && listPosition <= 3977)
                 || (listPosition >= 3982 && listPosition <= 3991)
                 || (listPosition == 4004)
                 || (listPosition >= 4006 && listPosition <= 4024)
                 || (listPosition == 4026)
                 || (listPosition >= 4028 && listPosition <= 4032)
                 || (listPosition == 4041) || (listPosition == 4042) //Row 57
                 || (listPosition == 4046) || (listPosition == 4047)
                 || (listPosition == 4055)
                 || (listPosition >= 4057 && listPosition <= 4063)
                 || (listPosition >= 4077 && listPosition <= 4103)
                 || (listPosition >= 4114 && listPosition <= 4116) //Row 58
                 || (listPosition == 4118)
                 || (listPosition >= 4129 && listPosition <= 4133)
                 || (listPosition == 4135)
                 || (listPosition >= 4149 && listPosition <= 4175)
                 || (listPosition >= 4187 && listPosition <= 4189) //Row 59
                 || (listPosition >= 4203 && listPosition <= 4204)
                 || (listPosition >= 4220 && listPosition <= 4239)
                 || (listPosition == 4241)
                 || (listPosition >= 4243 && listPosition <= 4248)
                 || (listPosition == 4261) || (listPosition == 4275) //Row 60
                 || (listPosition == 4293) || (listPosition == 4294)
                 || (listPosition >= 4297 && listPosition <= 4306)
                 || (listPosition >= 4314 && listPosition <= 4315)
                 || (listPosition == 4319)
                 || (listPosition >= 4369 && listPosition <= 4373) //Row 61
                 || (listPosition >= 4375 && listPosition <= 4380)
                 || (listPosition >= 4384 && listPosition <= 4386)
                 || (listPosition >= 4390 && listPosition <= 4391)
                 || (listPosition >= 4442 && listPosition <= 4443)
                 || (listPosition == 4445)
                 || (listPosition >= 4448 && listPosition <= 4452)
                 || (listPosition >= 4456 && listPosition <= 4458)
                 || (listPosition == 4461)
                 || (listPosition == 4506) //Row 63
                 || (listPosition >= 4508 && listPosition <= 4512)
                 || (listPosition == 4514)
                 || (listPosition >= 4520 && listPosition <= 4524)
                 || (listPosition == 4573) || (listPosition == 4574) //Row 64
                 || (listPosition == 4578) || (listPosition == 4579)
                 || (listPosition == 4585) || (listPosition == 4587)
                 || (listPosition == 4588)
                 || (listPosition >= 4591 && listPosition <= 4597)
                 || (listPosition == 4607)
                 || (listPosition == 4646) || (listPosition == 4647) //Row 65
                 || (listPosition == 4661)
                 || (listPosition >= 4664 && listPosition <= 4669)
                 || (listPosition == 4674) || (listPosition == 4676)
                 || (listPosition == 4719) || (listPosition == 4733) //Row 66
                 || (listPosition >= 4737 && listPosition <= 4739)
                 || (listPosition == 4742)
                 || (listPosition >= 4746 && listPosition <= 4750)
                 || (listPosition == 4791) //Row 67
                 || (listPosition >= 4814 && listPosition <= 4815)
                 || (listPosition >= 4818 && listPosition <= 4823)
                 || (listPosition >= 4863 && listPosition <= 4865) //Row 68
                 || (listPosition >= 4885 && listPosition <= 4894)
                 || (listPosition == 4950) || (listPosition == 4951) //Row 69
                 || (listPosition >= 4956 && listPosition <= 4963)
                 || (listPosition == 5022)
                 || (listPosition >= 5026 && listPosition <= 5035)
                 || (listPosition == 5093) //Row 71
                 || (listPosition >= 5097 && listPosition <= 5105)
                 || (listPosition == 5107)
                 || (listPosition == 5165) || (listPosition == 5166) //Row 72
                 || (listPosition >= 5169 && listPosition <= 5175)
                 || (listPosition == 5177)
                 || (listPosition >= 5239 && listPosition <= 5241) //Row 73
                 || (listPosition >= 5243 && listPosition <= 5245)
                 || (listPosition == 5315) //Row 74
                 )
                 {
        map [listPosition] [0] = 1;
      // Setting if the position is lesser undead.
      } else if ((listPosition >= 1413 && listPosition <= 1416) //Row 20
                 || (listPosition >= 1484 && listPosition <= 1490) //Row 21
                 || (listPosition == 1556) || (listPosition == 1557) //Row 22
                 || (listPosition == 1561) || (listPosition == 1562)
                 || (listPosition == 1628) || (listPosition == 1633) //Row 23
                 || (listPosition == 1634)
                 || (listPosition == 1698) || (listPosition == 1700) //Row 24
                 || (listPosition == 1705) || (listPosition == 1706)
                 || (listPosition >= 1768 && listPosition <= 1770) //Row 25
                 || (listPosition == 1772) || (listPosition == 1773)
                 || (listPosition >= 1776 && listPosition <= 1778)
                 || (listPosition >= 1840 && listPosition <= 1842) //Row 26
                 || (listPosition >= 1844 && listPosition <= 1849)
                 || (listPosition >= 1911 && listPosition <= 1913) //Row 27
                 || (listPosition >= 1916 && listPosition <= 1919)
                 || (listPosition >= 1982 && listPosition <= 1985) //Row 28
                 || (listPosition >= 1987 && listPosition <= 1991)
                 || (listPosition >= 2052 && listPosition <= 2057) //Row 29
                 || (listPosition >= 2059 && listPosition <= 2063)
                 || (listPosition >= 2122 && listPosition <= 2127) //Row 30
                 || (listPosition >= 2130 && listPosition <= 2134)
                 || (listPosition >= 2193 && listPosition <= 2197) //Row 31
                 || (listPosition == 2199)
                 || (listPosition >= 2202 && listPosition <= 2205)
                 || (listPosition >= 2265 && listPosition <= 2268) //Row 32
                 || (listPosition >= 2274 && listPosition <= 2277)
                 || (listPosition == 2339) || (listPosition == 2340) //Row 33
                 || (listPosition >= 2346 && listPosition <= 2349)
                 || (listPosition == 2411) || (listPosition == 2412) //Row 34
                 || (listPosition >= 2416 && listPosition <= 2420)
                 || (listPosition == 2485) || (listPosition == 2486) //Row 35
                 || (listPosition >= 2488 && listPosition <= 2492)
                 || (listPosition >= 2559 && listPosition <= 2563) //Row 36
                 || (listPosition >= 2632 && listPosition <= 2635) //Row 37
                 || (listPosition >= 2704 && listPosition <= 2707) //Row 38
                 || (listPosition == 2770) || (listPosition == 2771) //Row 39
                 || (listPosition == 2773) || (listPosition == 2775)
                 || (listPosition == 2779)
                 || (listPosition == 2840) || (listPosition == 2841) //Row 40
                 || (listPosition == 2850) || (listPosition == 2851)
                 || (listPosition == 2912) || (listPosition == 2913) //Row 41
                 || (listPosition == 2920) || (listPosition == 2921)
                 || (listPosition >= 2984 && listPosition <= 2986) //Row 42
                 || (listPosition == 2992) || (listPosition == 2993)
                 || (listPosition == 3059) || (listPosition == 3060) //Row 43
                 || (listPosition == 3062) || (listPosition == 3064)
                 || (listPosition == 3126) || (listPosition == 3128) //Row 44
                 || (listPosition >= 3132 && listPosition <= 3136)
                 || (listPosition == 3194) //Row 45
                 || (listPosition >= 3198 && listPosition <= 3208)
                 || (listPosition >= 3264 && listPosition <= 3268) //Row 46
                 || (listPosition == 3270) || (listPosition == 3271)
                 || (listPosition >= 3273 && listPosition <= 3279)
                 || (listPosition >= 3334 && listPosition <= 3337) //Row 47
                 || (listPosition >= 3339 && listPosition <= 3343)
                 || (listPosition >= 3347 && listPosition <= 3350)
                 || (listPosition >= 3405 && listPosition <= 3407) //Row 48
                 || (listPosition == 3419)
                 || (listPosition >= 3477 && listPosition <= 3480) //Row 49
                 || (listPosition == 3491)
                 || (listPosition == 3549) || (listPosition == 3551) //Row 50
                 || (listPosition == 3552) || (listPosition == 3560)
                 || (listPosition >= 3562 && listPosition <= 3564)
                 || (listPosition == 3623) || (listPosition == 3625) //Row 51
                 || (listPosition == 3626) || (listPosition == 3631)
                 || (listPosition >= 3633 && listPosition <= 3635)
                 || (listPosition >= 3698 && listPosition <= 3700) //Row 52
                 || (listPosition == 3702) || (listPosition == 3703)
                 || (listPosition == 3771) //Row 53
                 || (listPosition >= 3773 && listPosition <= 3775)
                 ){
        map [listPosition] [0] = 2;
      // Setting if the position is greater undead.
      } else if ((listPosition >= 1558 && listPosition <= 1560) //Row 22
                 || (listPosition >= 1629 && listPosition <= 1632) //Row 23
                 || (listPosition >= 1701 && listPosition <= 1704) //Row 24
                 || (listPosition == 1774) || (listPosition == 1775) //Row 25
                 || (listPosition == 2198) //Row 31
                 || (listPosition >= 2269 && listPosition <= 2271) //Row 32
                 || (listPosition >= 2341 && listPosition <= 2343) //Row 33
                 || (listPosition >= 2413 && listPosition <= 2415) //Row 34
                 || (listPosition == 2487) //Row 35
                 || (listPosition == 2772) || (listPosition == 2774) //Row 39
                 || (listPosition >= 2776 && listPosition <= 2778)
                 || (listPosition >= 2842 && listPosition <= 2849) //Row 40
                 || (listPosition >= 2914 && listPosition <= 2919) //Row 41
                 || (listPosition >= 2987 && listPosition <= 2991) //Row 42
                 || (listPosition == 3061) || (listPosition == 3063) //Row 43
                 || (listPosition == 3272) //Row 46
                 || (listPosition == 3338) //Row 47
                 || (listPosition >= 3344 && listPosition <= 3346)
                 || (listPosition >= 3408 && listPosition <= 3418) //Row 48
                 || (listPosition >= 3481 && listPosition <= 3490) //Row 49
                 || (listPosition >= 3553 && listPosition <= 3559) //Row 50
                 || (listPosition == 3561)
                 || (listPosition >= 3627 && listPosition <= 3630) //Row 51
                 || (listPosition == 3701) //Row 52
                 ){
        map [listPosition] [0] = 3;
      // Setting if the position is a mountain.
      } else if ((listPosition == 374) //Row 1.
                 || (listPosition == 442) //Row 2.
                 || (listPosition >= 444 && listPosition <= 446) //Row 3.
                 || (listPosition >= 512 && listPosition <= 519) //Row 4.
                 || (listPosition >= 585 && listPosition <= 587) //Row 5.
                 || (listPosition == 591)
                 || (listPosition == 659) || (listPosition == 663)   //Row 6.
                 || (listPosition == 664)
                 || (listPosition >= 734 && listPosition <= 737) //Row 7.
                 || (listPosition >= 806 && listPosition <= 809) //Row 8.
                 || (listPosition >= 878 && listPosition <= 880) //Row 9.
                 || (listPosition >= 950 && listPosition <= 951) //Row 10.
                 || (listPosition >= 968 && listPosition <= 970) //Row 14.
                 || (listPosition >= 1040 && listPosition <= 1043) //Row 15.
                 || (listPosition == 1060)
                 || (listPosition >= 1113 && listPosition <= 1115) //Row 16.
                 || (listPosition == 1128)
                 || (listPosition >= 1130 && listPosition <= 1133)
                 || (listPosition >= 1185 && listPosition <= 1187) //Row 17.
                 || (listPosition >= 1200 && listPosition <= 1203)
                 || (listPosition == 1205)
                 || (listPosition == 1258) || (listPosition == 1259) //Row 18
                 || (listPosition == 1273) || (listPosition == 1274)
                 || (listPosition >= 1329 && listPosition <= 1332) //Row 19
                 || (listPosition == 1345)
                 || (listPosition >= 1400 && listPosition <= 1405) //Row 20
                 || (listPosition == 1417)
                 || (listPosition >= 1472 && listPosition <= 1477) //Row 21
                 || (listPosition >= 1545 && listPosition <= 1547) //Row 22
                 || (listPosition == 1616) || (listPosition == 1617) //Row 23
                 || (listPosition >= 1686 && listPosition <= 1689) //Row 24
                 || (listPosition >= 1756 && listPosition <= 1759) //Row 25
                 || (listPosition == 1761) || (listPosition == 1762)
                 || (listPosition >= 1824 && listPosition <= 1829) //Row 26
                 || (listPosition >= 1894 && listPosition <= 1898) //Row 27
                 || (listPosition >= 1964 && listPosition <= 1969) //Row 28
                 || (listPosition >= 2034 && listPosition <= 2039) //Row 29
                 || (listPosition >= 2105 && listPosition <= 2109) //Row 30
                 || (listPosition >= 2176 && listPosition <= 2180) //Row 31
                 || (listPosition >= 2246 && listPosition <= 2253) //Row 32
                 || (listPosition >= 2319 && listPosition <= 2322) //Row 33
                 || (listPosition >= 2392 && listPosition <= 2394) //Row 34
                 || (listPosition >= 2464 && listPosition <= 2466) //Row 35
                 || (listPosition >= 2535 && listPosition <= 2540) //Row 36
                 || (listPosition >= 2601 && listPosition <= 2609) //Row 37
                 || (listPosition == 2611)
                 || (listPosition >= 2673 && listPosition <= 2680) //Row 38
                 || (listPosition >= 2746 && listPosition <= 2753) //Row 39
                 || (listPosition >= 2817 && listPosition <= 2821) //Row 40
                 || (listPosition == 2825)
                 || (listPosition >= 2890 && listPosition <= 2892) //Row 41
                 || (listPosition == 2963) || (listPosition == 2964) //Row 42
                 || (listPosition == 2970)
                 || (listPosition >= 2972 && listPosition <= 2974)
                 || (listPosition == 3035) //Row 43
                 || (listPosition >= 3041 && listPosition <= 3046)
                 || (listPosition >= 3113 && listPosition <= 3117) //Row 43
                 || (listPosition == 3156) || (listPosition == 3158) //Row 44
                 || (listPosition == 3160)
                 || (listPosition >= 3184 && listPosition <= 3187) //Row 45
                 || (listPosition >= 3227 && listPosition <= 3232)
                 || (listPosition == 3256) || (listPosition == 3257) //Row 46
                 || (listPosition >= 3301 && listPosition <= 3303)
                 || (listPosition >= 3326 && listPosition <= 3329) //Row 47
                 || (listPosition == 3373) || (listPosition == 3374)
                 || (listPosition >= 3398 && listPosition <= 3401) //Row 48
                 || (listPosition >= 3446 && listPosition <= 3448)
                 || (listPosition >= 3470 && listPosition <= 3474) //Row 49
                 || (listPosition >= 3508 && listPosition <= 3509)
                 || (listPosition >= 3519 && listPosition <= 3521)
                 || (listPosition >= 3525 && listPosition <= 3526)
                 || (listPosition >= 3541 && listPosition <= 3547) //Row 50
                 || (listPosition == 3580) || (listPosition == 3581)
                 || (listPosition == 3584) || (listPosition == 3588)
                 || (listPosition == 3593) || (listPosition == 3594)
                 || (listPosition == 3596) || (listPosition == 3597)
                 || (listPosition >= 3613 && listPosition <= 3615) //Row 51
                 || (listPosition == 3638)
                 || (listPosition >= 3650 && listPosition <= 3656)
                 || (listPosition == 3661) || (listPosition == 3662)
                 || (listPosition >= 3664 && listPosition <= 3666)
                 || (listPosition >= 3668 && listPosition <= 3670)
                 || (listPosition >= 3684 && listPosition <= 3687) //Row 52
                 || (listPosition == 3704)
                 || (listPosition >= 3708 && listPosition <= 3710)
                 || (listPosition == 3714)
                 || (listPosition >= 3722 && listPosition <= 3730)
                 || (listPosition >= 3732 && listPosition <= 3743)
                 || (listPosition >= 3756 && listPosition <= 3761) //Row 53
                 || (listPosition >= 3776 && listPosition <= 3782)
                 || (listPosition >= 3784 && listPosition <= 3787)
                 || (listPosition >= 3797 && listPosition <= 3800)
                 || (listPosition >= 3802 && listPosition <= 3807)
                 || (listPosition == 3811)
                 || (listPosition == 3814) || (listPosition == 3815)
                 || (listPosition >= 3826 && listPosition <= 3829) //Row 54
                 || (listPosition == 3831) || (listPosition == 3834)
                 || (listPosition >= 3848 && listPosition <= 3858)
                 || (listPosition == 3860)
                 || (listPosition >= 3871 && listPosition <= 3873)
                 || (listPosition >= 3875 && listPosition <= 3879)
                 || (listPosition >= 3884 && listPosition <= 3887)
                 || (listPosition == 3899) || (listPosition == 3900) //Row 55
                 || (listPosition >= 3905 && listPosition <= 3909)
                 || (listPosition >= 3921 && listPosition <= 3934)
                 || (listPosition >= 3944 && listPosition <= 3945)
                 || (listPosition == 3951) || (listPosition == 3957)
                 || (listPosition == 3959)
                 || (listPosition == 3971) || (listPosition == 3972) //Row 56
                 || (listPosition >= 3978 && listPosition <= 3981)
                 || (listPosition >= 3994 && listPosition <= 4001)
                 || (listPosition == 4003) || (listPosition == 4005)
                 || (listPosition >= 4043 && listPosition <= 4045) //Row 57
                 || (listPosition >= 4051 && listPosition <= 4054)
                 || (listPosition == 4056) || (listPosition == 4067)
                 || (listPosition >= 4069 && listPosition <= 4071)
                 || (listPosition == 4117) //Row 58
                 || (listPosition >= 4123 && listPosition <= 4128)
                 || (listPosition >= 4142 && listPosition <= 4143)
                 || (listPosition >= 4196 && listPosition <= 4199) //Row 59
                 || (listPosition == 4215) || (listPosition == 4240)
                 || (listPosition == 4242)
                 || (listPosition >= 4269 && listPosition <= 4271) //Row 60
                 || (listPosition == 4274) || (listPosition == 4276)
                 || (listPosition == 4277)
                 || (listPosition >= 4282 && listPosition <= 4284)
                 || (listPosition == 4288) || (listPosition == 4289)
                 || (listPosition == 4292) || (listPosition == 4295) 
                 || (listPosition == 4296)
                 || (listPosition >= 4307 && listPosition <= 4313)
                 || (listPosition >= 4316 && listPosition <= 4318)
                 || (listPosition >= 4340 && listPosition <= 4350) //Row 61
                 || (listPosition >= 4354 && listPosition <= 4361)
                 || (listPosition >= 4364 && listPosition <= 4368)
                 || (listPosition == 4374)
                 || (listPosition >= 4381 && listPosition <= 4383)
                 || (listPosition >= 4387 && listPosition <= 4389)
                 || (listPosition >= 4411 && listPosition <= 4422) //Row 62
                 || (listPosition >= 4428 && listPosition <= 4441)
                 || (listPosition == 4444) || (listPosition == 4446)
                 || (listPosition == 4447)
                 || (listPosition >= 4453 && listPosition <= 4455)
                 || (listPosition >= 4459 && listPosition <= 4460)
                 || (listPosition == 4462) || (listPosition == 4463)
                 || (listPosition >= 4483 && listPosition <= 4485) //Row 63
                 || (listPosition == 4487)
                 || (listPosition >= 4489 && listPosition <= 4491)
                 || (listPosition == 4493)
                 || (listPosition >= 4499 && listPosition <= 4505)
                 || (listPosition == 4507) || (listPosition == 4513)
                 || (listPosition >= 4515 && listPosition <= 4519)
                 || (listPosition >= 4525 && listPosition <= 4535)
                 || (listPosition == 4555) //Row 64
                 || (listPosition >= 4561 && listPosition <= 4563)
                 || (listPosition == 4571)
                 || (listPosition >= 4575 && listPosition <= 4577)
                 || (listPosition == 4589) || (listPosition == 4590)
                 || (listPosition >= 4598 && listPosition <= 4606)
                 || (listPosition >= 4638 && listPosition <= 4640) //Row 65
                 || (listPosition >= 4648 && listPosition <= 4650)
                 || (listPosition == 4662) || (listPosition == 4663)
                 || (listPosition >= 4670 && listPosition <= 4673)
                 || (listPosition == 4675)
                 || (listPosition >= 4677 && listPosition <= 4679)
                 || (listPosition >= 4708 && listPosition <= 4711) //Row 66
                 || (listPosition >= 4720 && listPosition <= 4724)
                 || (listPosition >= 4734 && listPosition <= 4736)
                 || (listPosition == 4740) || (listPosition == 4741)
                 || (listPosition >= 4743 && listPosition <= 4745)
                 || (listPosition == 4751)
                 || (listPosition >= 4781 && listPosition <= 4784) //Row 67
                 || (listPosition >= 4792 && listPosition <= 4797)
                 || (listPosition >= 4806 && listPosition <= 4813)
                 || (listPosition >= 4816 && listPosition <= 4817)
                 || (listPosition >= 4853 && listPosition <= 4855) //Row 68
                 || (listPosition == 4867)
                 || (listPosition >= 4877 && listPosition <= 4884)
                 || (listPosition == 4925) || (listPosition == 4926) //Row 69
                 || (listPosition >= 4934 && listPosition <= 4937)
                 || (listPosition >= 4952 && listPosition <= 4955)
                 || (listPosition == 5006) || (listPosition == 5007) //Row 70
                 || (listPosition == 5009)
                 || (listPosition >= 5023 && listPosition <= 5025)
                 || (listPosition >= 5078 && listPosition <= 5080) //Row 71
                 || (listPosition >= 5094 && listPosition <= 5096)
                 || (listPosition >= 5151 && listPosition <= 5153) //Row 72
                 || (listPosition == 5167) || (listPosition == 5168)
                 || (listPosition == 5225) || (listPosition == 5242) //Row 73
                 || (listPosition == 5246)
                 || (listPosition == 5313) || (listPosition == 5314) //Row 74
                 || (listPosition >= 5316 && listPosition <= 5318)
                 || (listPosition >= 5385 && listPosition <= 5390)
                 || (listPosition == 5458) || (listPosition == 5459)
                 || (listPosition == 5468)
                 || (listPosition >= 5529 && listPosition <= 5531) //Row 77
                 || (listPosition >= 5540 && listPosition <= 5542)
                 || (listPosition >= 5601 && listPosition <= 5602) //Row 78
                 || (listPosition >= 5612 && listPosition <= 5616)
                 || (listPosition == 5673) //Row 79
                 || (listPosition >= 5683 && listPosition <= 5685)
                 || (listPosition == 5687)
                 || (listPosition == 5755) || (listPosition == 5756) //Row 80
                 || (listPosition == 5815) || (listPosition == 5816) //Row 81
                 || (listPosition == 5827)
                 || (listPosition >= 5887 && listPosition <= 5889) //Row 82
                 || (listPosition >= 5959 && listPosition <= 5961) //Row 83
                 ){
        map [listPosition] [0] = 4;
      // Setting if the position is a river.
      } else if ((listPosition == 1555) //Row 22
                 || (listPosition == 1622) || (listPosition == 1627) //Row 23
                 || (listPosition == 1694) || (listPosition == 1699) //Row 24
                 || (listPosition == 1767) || (listPosition == 1771) //Row 25
                 || (listPosition == 1838) || (listPosition == 1839) //Row 26
                 || (listPosition == 1843)
                 || (listPosition == 1910) || (listPosition == 1914) //Row 27
                 || (listPosition == 1915)
                 || (listPosition == 1980) || (listPosition == 1981) //Row 28
                 || (listPosition == 1986)
                 || (listPosition == 2051) || (listPosition == 2058) //Row 29
                 || (listPosition == 2120) || (listPosition == 2121) //Row 30
                 || (listPosition == 2128) || (listPosition == 2129)
                 || (listPosition == 2136)
                 || (listPosition == 2181) || (listPosition == 2182) //Row 31
                 || (listPosition == 2192) || (listPosition == 2200)
                 || (listPosition == 2206) || (listPosition == 2207)
                 || (listPosition == 2254) || (listPosition == 2272) //Row 32
                 || (listPosition == 2273)
                 || (listPosition == 2326) || (listPosition == 2335) //Row 33
                 || (listPosition == 2344) || (listPosition == 2345)
                 || (listPosition == 2351)
                 || (listPosition == 2398) || (listPosition == 2422) //Row 34
                 || (listPosition == 2423)
                 || (listPosition == 2471) || (listPosition == 2472) //Row 35
                 || (listPosition == 2493)
                 || (listPosition == 2544) || (listPosition == 2565) //Row 36
                 || (listPosition == 2566)
                 || (listPosition == 2616) || (listPosition == 2636) //Row 37
                 || (listPosition == 2637)
                 || (listPosition == 2688) || (listPosition == 2708) //Row 38
                 || (listPosition == 2759) || (listPosition == 2780) //Row 39
                 || (listPosition == 2828) || (listPosition == 2852) //Row 40
                 || (listPosition == 2898) || (listPosition == 2899) //Row 41
                 || (listPosition >= 2901 && listPosition <= 2903)
                 || (listPosition == 2922) || (listPosition == 2923)
                 || (listPosition == 2969) || (listPosition == 2994) //Row 42
                 || (listPosition == 3065) //Row 43
                 || (listPosition == 3137) //Row 44
                 || (listPosition == 3209) //Row 45
                 || (listPosition == 3281) || (listPosition == 3284) //Row 46
                 || (listPosition >= 3353 && listPosition <= 3357) //Row 47
                 || (listPosition >= 3427 && listPosition <= 3430) //Row 48
                 || (listPosition == 3499) //Row 49
                 || (listPosition >= 3501 && listPosition <= 3503)
                 || (listPosition == 5110) || (listPosition == 5111) //Row 71
                 || (listPosition == 5182) //Row 72
                 || (listPosition >= 5248 && listPosition <= 5250) //Row 72
                 || (listPosition >= 5252 && listPosition <= 5253)
                 || (listPosition == 5320) || (listPosition == 5323)
                 || (listPosition >= 5391 && listPosition <= 5392)
                 ){
        map [listPosition] [0] = 5;
      // Setting if the position is a swamp.
      } else if ((listPosition == 2334) || (listPosition == 2336) //Row 33
                 || (listPosition == 2337)
                 || (listPosition >= 2405 && listPosition <= 2410) //Row 34
                 || (listPosition >= 2476 && listPosition <= 2481) //Row 35
                 || (listPosition >= 2548 && listPosition <= 2554) //Row 36
                 || (listPosition >= 2620 && listPosition <= 2625) //Row 37
                 || (listPosition >= 2692 && listPosition <= 2698) //Row 38
                 || (listPosition >= 2764 && listPosition <= 2769) //Row 39
                 || (listPosition == 2784)
                 || (listPosition >= 2836 && listPosition <= 2839) //Row 40
                 || (listPosition >= 2854 && listPosition <= 2856)
                 || (listPosition >= 2909 && listPosition <= 2911) //Row 41
                 || (listPosition >= 2926 && listPosition <= 2928)
                 || (listPosition >= 2981 && listPosition <= 2983) //Row 42
                 || (listPosition == 2999) || (listPosition == 3000)
                 || (listPosition >= 3055 && listPosition <= 3058) //Row 43
                 || (listPosition >= 3071 && listPosition <= 3073)
                 || (listPosition == 3127) //Row 44
                 || (listPosition >= 3129 && listPosition <= 3131)
                 || (listPosition == 3138) || (listPosition == 3143)
                 || (listPosition == 3145)
                 || (listPosition >= 3210 && listPosition <= 3214) //Row 45
                 || (listPosition == 3280) || (listPosition == 3282) //Row 46
                 || (listPosition == 3283) || (listPosition == 3285)
                 || (listPosition == 3352) //Row 47
                 ){
        map [listPosition] [0] = 6;
      // Setting if the position is a wasteland.
      } else if ((listPosition >= 4 && listPosition <= 7)
                 || (listPosition >= 76 && listPosition <= 79)
                 || (listPosition == 148) || (listPosition == 149)
                 || (listPosition == 2330) //Row 33
                 || (listPosition >= 2400 && listPosition <= 2404) //Row 34
                 || (listPosition == 2475) //Row 35
                 || (listPosition == 2546) || (listPosition == 2555) //Row 36
                 || (listPosition == 2556)
                 || (listPosition == 2618) || (listPosition == 2619) //Row 37
                 || (listPosition == 2630)
                 || (listPosition == 2701) || (listPosition == 2710) //Row 38
                 || (listPosition == 2783)
                 || (listPosition >= 2906 && listPosition <= 2908) //Row 41
                 || (listPosition == 2976) || (listPosition == 2977) //Row 42
                 || (listPosition == 2979)
                 || (listPosition >= 3049 && listPosition <= 3051) //Row 43
                 || (listPosition == 3053) || (listPosition == 3054)
                 || (listPosition == 3123) || (listPosition == 3125) //Row 44
                 || (listPosition == 3182) || (listPosition == 3183) //Row 45
                 || (listPosition == 3254) || (listPosition == 3255) //Row 46
                 || (listPosition == 3286) || (listPosition == 3287)
                 || (listPosition == 3324) || (listPosition == 3325) //Row 47
                 || (listPosition == 3351)
                 || (listPosition >= 3358 && listPosition <= 3360)
                 || (listPosition == 3396) || (listPosition == 3397) //Row 48
                 || (listPosition >= 3422 && listPosition <= 3426)
                 || (listPosition == 3431) || (listPosition == 3432)
                 || (listPosition == 3434) || (listPosition == 3435)
                 || (listPosition >= 3422 && listPosition <= 3426)
                 || (listPosition >= 3467 && listPosition <= 3469) //Row 49
                 || (listPosition >= 3493 && listPosition <= 3498)
                 || (listPosition == 3500)
                 || (listPosition >= 3504 && listPosition <= 3507)
                 || (listPosition == 3539) //Row 50
                 || (listPosition >= 3568 && listPosition <= 3577)
                 || (listPosition == 3579)
                 || (listPosition >= 3616 && listPosition <= 3620) //Row 51
                 || (listPosition == 3622)
                 || (listPosition >= 3640 && listPosition <= 3643)
                 || (listPosition == 3645) || (listPosition == 3647)
                 || (listPosition >= 3688 && listPosition <= 3689) //Row 52
                 || (listPosition >= 3691 && listPosition <= 3693)
                 || (listPosition >= 3711 && listPosition <= 3713)
                 || (listPosition >= 3764 && listPosition <= 3766) //Row 53
                 || (listPosition == 3783)
                 || (listPosition >= 3837 && listPosition <= 3841) //Row 54
                 || (listPosition >= 3880 && listPosition <= 3883)
                 || (listPosition == 3911) || (listPosition == 3913) //Row 55
                 || (listPosition == 3919) || (listPosition == 3920)
                 || (listPosition >= 3952 && listPosition <= 3956)
                 || (listPosition >= 3992 && listPosition <= 3993)
                 || (listPosition == 4002) || (listPosition == 4025)
                 || (listPosition == 4027)
                 || (listPosition == 4049) || (listPosition == 4050) //Row 57
                 || (listPosition >= 4064 && listPosition <= 4066)
                 || (listPosition == 4068)
                 || (listPosition >= 4072 && listPosition <= 4076)
                 || (listPosition == 4134)
                 || (listPosition >= 4136 && listPosition <= 4141)
                 || (listPosition >= 4144 && listPosition <= 4148)
                 || (listPosition == 4195) //Row 89
                 || (listPosition >= 4200 && listPosition <= 4202)
                 || (listPosition >= 4205 && listPosition <= 4214)
                 || (listPosition >= 4216 && listPosition <= 4219)
                 || (listPosition == 4267) || (listPosition == 4268) //Row 60
                 || (listPosition == 4272) || (listPosition == 4273)
                 || (listPosition >= 4278 && listPosition <= 4281)
                 || (listPosition >= 4285 && listPosition <= 4287)
                 || (listPosition == 4290) || (listPosition == 4291)
                 || (listPosition == 4339) //Row 61
                 || (listPosition >= 4351 && listPosition <= 4353)
                 || (listPosition >= 4362 && listPosition <= 4363)
                 || (listPosition >= 4423 && listPosition <= 4427) //Row 62
                 || (listPosition == 4492) || (listPosition == 4495) //Row 63
                 || (listPosition == 4497) || (listPosition == 4498)
                 || (listPosition == 4564) || (listPosition == 4565) //Row 64
                 || (listPosition == 5470) || (listPosition == 5471)
                 || (listPosition == 5543)
                 ){
        map [listPosition] [0] = 7;
      // Setting if the position is a town.
      } else if ((listPosition == 1125)
                 || (listPosition == 1481)
                 || (listPosition == 1993)
                 || (listPosition == 2050)
                 || (listPosition == 2135)
                 || (listPosition == 2263)
                 || (listPosition == 2278)
                 || (listPosition == 2831)
                 || (listPosition == 3149)
                 || (listPosition == 3550)
                 ){
        map [listPosition] [0] = 8;
      // Setting if the position is a island.
      } else if ((listPosition == 1379) || (listPosition == 1451)
                 || (listPosition == 1520)
                 || (listPosition == 3305) || (listPosition == 3306) //Row 46
                 || (listPosition == 3308) || (listPosition == 3309)
                 || (listPosition == 3383)
                 || (listPosition == 4923) //Row 69
                 || (listPosition == 5232)
                 || (listPosition >= 5302 && listPosition <= 5304)
                 || (listPosition == 5676) //Row 79
                 ){
        map [listPosition] [0] = 9;
      // Otherwise setting it to ocean.
      } else {
        map [listPosition] [0] = 10;
      }
    }
  }
//=============================================================================
  /**
   * This function displays the map.
   */
  public static String showMap() {
    String mapAsString = ("██================================================"
                           + "==============================================="
                           + "==============================================="
                           + "██\n");
    double temp;
    int mapStart = (playerPosition - (playerPosition % 72)) - (16 * 72);
    int mapEnd = (playerPosition - (playerPosition % 72)) + (16 * 72);
    if (mapStart < 0) {
      mapStart = 0;
      mapEnd = 2304;
    }
    if (mapEnd > numberOfPositions) {
      mapStart = 0;
    }
    for (int listPosition = mapStart; listPosition < mapEnd;
         listPosition++) {
      temp = listPosition % 72;
      if (temp == 0 && listPosition != mapStart) {
        mapAsString += ("||\n||");
      } else if (listPosition == mapStart) {
        mapAsString += ("||");
      } else if (listPosition == playerPosition) {
         mapAsString += playerTile;
      // Displaying unknown tiles.
      } else if (map [listPosition] [1] == 1 && map [listPosition] [0] != 5) {
        mapAsString += unknownTile;
      // Displaying beach tiles.
      } else if (map [listPosition] [0] == 0){
        mapAsString += beachTile;
      // Displaying no undead tiles.
      } else if (map [listPosition] [0] == 1){
        mapAsString += noUndeadTile;
      // Displaying lesser undead tiles.
      } else if (map [listPosition] [0] == 2){
        mapAsString += lesserUndeadTile;
      // Displaying greater undead tiles.
      } else if (map [listPosition] [0] == 3){
        mapAsString += greaterUndeadTile;
      // Displaying mountain tiles.
      } else if (map [listPosition] [0] == 4){
        mapAsString += mountainTile;
      // Displaying river tiles.
      } else if (map [listPosition] [0] == 5){
        mapAsString += riverTile;
      // Displaying swamp tiles.
      } else if (map [listPosition] [0] == 6){
        mapAsString += swampTile;
      // Displaying wasteland tiles.
      } else if (map [listPosition] [0] == 7){
        mapAsString += wastelandTile;
      // Displaying town tiles.
      } else if (map [listPosition] [0] == 8){
        mapAsString += townTile;
      // Displaying island tiles.
      } else if (map [listPosition] [0] == 9){
        mapAsString += islandTile;
      // Displaying ocean tiles.
      } else if (map [listPosition] [0] == 10){
        mapAsString += oceanTile;
      }
    }
    mapAsString += ("||\n██================================================"
                    + "================================================"
                    + "==============================================██");
    return mapAsString;
  }
//=============================================================================
  /**
   * This function returns the biome the player is in..
   */
  public static String getBiome() {
    // Returning beach.
    if (map [playerPosition] [0] == 0){
      return "Beach";
    // Returning no undead.
    } else if (map [playerPosition] [0] == 1){
      return "Jungle (No Undead)";
    // Returning lesser undead.
    } else if (map [playerPosition] [0] == 2){
      return "Jungle (Lesser Undead)";
    // Returning greater undead.
    } else if (map [playerPosition] [0] == 3){
      return "Jungle (Greater Undead)";
    // Returning mountain.
    } else if (map [playerPosition] [0] == 4){
      return "Mountains";
    // Returning river.
    } else if (map [playerPosition] [0] == 5){
      return "River";
    // Returning swamp.
    } else if (map [playerPosition] [0] == 6){
      return "Swamp";
    // Returning wasteland.
    } else if (map [playerPosition] [0] == 7){
      return "Wasteland";
    // Returning town.
    } else if (map [playerPosition] [0] == 8){
      return "Settlement";
    }
    return "???";
  }
  
//=============================================================================
  /**
   * This function determines if the player is lost.
   */
  public static String isPlayerLost(String direction, int check) {
    String originalDirection = direction;
    if (map[playerPosition] [0] == 0) {
      if (check < 10 && (direction.equals("1") || direction.equals("2")
                         || direction.equals("3") || direction.equals("4"))) {
        direction = "" + (int) (Math.random() * 4 + 1);
      }
    } else {
      if (check < 15 && (direction.equals("1") || direction.equals("2")
                         || direction.equals("3") || direction.equals("4"))) {
        direction = "" + (int) (Math.random() * 4 + 1);
      }
    }
    if (direction.equals("1")) { //Up
      if (map[playerPosition - 72] [0] != 10
          && map[playerPosition - 72] [0] != 9
          && map[playerPosition - 72] [0] != 5) {
        return direction;
      } else {
        return isPlayerLost("2", 20);
      }
    } else if (direction.equals("2")) { //Down
      if (map[playerPosition + 72] [0] != 10
          && map[playerPosition + 72] [0] != 9
          && map[playerPosition + 72] [0] != 5) {
        return direction;
      } else {
        return isPlayerLost("3", 20);
      }
    } else if (direction.equals("3")) { //Left
      if (map[playerPosition - 1] [0] != 10
          && map[playerPosition - 1] [0] != 9
          && map[playerPosition - 1] [0] != 5) {
        return direction;
      } else {
        return isPlayerLost("4", 20);
      }
    } else if (direction.equals("4")) { //Right
      if (map[playerPosition + 1] [0] != 10
          && map[playerPosition + 1] [0] != 9
          && map[playerPosition + 1] [0] != 5) {
         return direction;
      } else {
        return isPlayerLost("1", 20);
      }
    }
    return (originalDirection);
  }
//=============================================================================
  /**
   * This function moves the player's location.
   */
  public static String movePlayer(String direction) {
    if (map[playerPosition] [1] == 1) {
      map[playerPosition] [1] = 0;
    }
    if (direction.equals("1")) { //Up
      if (map[playerPosition - 72] [0] != 10
          && map[playerPosition - 72] [0] != 9
          && map[playerPosition - 72] [0] != 5) {
        playerPosition -= 72;
        return (TEXT_GREEN + "Successfully Moved" + ANSI_RESET);
      } else {
        return (TEXT_RED + "Cannot Move In That Direction" + ANSI_RESET);
      }
    } else if (direction.equals("2")) { //Down
      if (map[playerPosition + 72] [0] != 10
          && map[playerPosition + 72] [0] != 9
          && map[playerPosition + 72] [0] != 5) {
        playerPosition += 72;
        return (TEXT_GREEN + "Successfully Moved" + ANSI_RESET);
      } else {
        return (TEXT_RED + "Cannot Move In That Direction" + ANSI_RESET);
      }
    } else if (direction.equals("3")) { //Left
      if (map[playerPosition - 1] [0] != 10
          && map[playerPosition - 1] [0] != 9
          && map[playerPosition - 1] [0] != 5) {
        playerPosition -= 1;
        return (TEXT_GREEN + "Successfully Moved" + ANSI_RESET);
      } else {
        return (TEXT_RED + "Cannot Move In That Direction" + ANSI_RESET);
      }
    } else if (direction.equals("4")) { //Right
      if (map[playerPosition + 1] [0] != 10
          && map[playerPosition + 1] [0] != 9
          && map[playerPosition + 1] [0] != 5) {
        playerPosition += 1;
        return (TEXT_GREEN + "Successfully Moved" + ANSI_RESET);
      } else {
        return (TEXT_RED + "Cannot Move In That Direction" + ANSI_RESET);
      }
    } else if (direction.equals("5")) {
      return ("Successfully Stopped");
    } else {
      return (TEXT_RED + "Invalid Direction" + ANSI_RESET);
    }
  }
//=============================================================================
  /**
   * This determins if there's an encounter.
   */
  public static String rollEncounter(int encounterRoll, int whichEncounter) {
    if (encounterRoll >= 16) {
      // Beach encounters: ----------------------------------------------------
      if (map [playerPosition] [0] == 0) {
        if (whichEncounter >= 1 && whichEncounter <= 7) {
          return "Aarakocra";
        } else if (whichEncounter == 8) {
          return "Artus Cimber";
        } else if (whichEncounter == 9 || whichEncounter == 10) {
          return "Cache";
        } else if (whichEncounter == 11 || whichEncounter == 12) {
          return "Chwinga";
        } else if (whichEncounter == 13 || whichEncounter == 14) {
          return "Allosaurus";
        } else if (whichEncounter == 15 || whichEncounter == 16) {
          return "Dimetrodon";
        } else if (whichEncounter >= 17 && whichEncounter <= 21) {
          return "Plesiosaurus";
        } else if (whichEncounter >= 22 && whichEncounter <= 28) {
          return "Pteranodon";
        } else if (whichEncounter >= 29 && whichEncounter <= 31) {
          return "Quetzalcoatlus";
        } else if (whichEncounter >= 32 && whichEncounter <= 37) {
          return "Velociraptor";
        } else if (whichEncounter >= 38 && whichEncounter <= 40) {
          return "Red Dragon";
        } else if (whichEncounter >= 41 && whichEncounter <= 42) {
          return "Emerald Enclave";
        } else if (whichEncounter >= 43 && whichEncounter <= 46) {
          return "Explorer";
        } else if (whichEncounter >= 47 && whichEncounter <= 49) {
          return "Flaming Fist";
        } else if (whichEncounter >= 50 && whichEncounter <= 52) {
          return "Flying Monkey";
        } else if (whichEncounter >= 53 && whichEncounter <= 55) {
          return "Flying Snake";
        } else if (whichEncounter >= 56 && whichEncounter <= 57) {
          return "Frost Giant";
        } else if (whichEncounter >= 58 && whichEncounter <= 63) {
          return "Giant Lizard";
        } else if (whichEncounter >= 64 && whichEncounter <= 67) {
          return "Giant Snapping Turtle";
        } else if (whichEncounter >= 68 && whichEncounter <= 71) {
          return "Lizardfolk";
        } else if (whichEncounter >= 72 && whichEncounter <= 74) {
          return "Red Wizard";
        } else if (whichEncounter >= 75 && whichEncounter <= 84) {
          return "Sea Hag";
        } else if (whichEncounter >= 85 && whichEncounter <= 87) {
          return "Stirge";
        } else if (whichEncounter >= 88 && whichEncounter <= 89) {
          return "Swarm of Bats";
        } else if (whichEncounter >= 90 && whichEncounter <= 94) {
          return "Tabaxi Hunter";
        } else if (whichEncounter >= 95 && whichEncounter <= 100) {
          return "Tri-flower Frond";
        }
      // No undead enounters: -------------------------------------------------
      } else if (map [playerPosition] [0] == 1) {
        if (whichEncounter == 1) {
          return "Albino Dwarves";
        } else if (whichEncounter == 2) {
          return "Almiraj";
        } else if (whichEncounter == 3 || whichEncounter == 4) {
          return "Ape";
        } else if (whichEncounter == 5) {
          return "Artus Cimber";
        } else if (whichEncounter == 6 || whichEncounter == 7) {
          return "Assassin Vine";
        } else if (whichEncounter == 8) {
          return "Axe Beak";
        } else if (whichEncounter == 9) {
          return "Baboon";
        } else if (whichEncounter == 10 || whichEncounter == 11) {
          return "Cache";
        } else if (whichEncounter == 12 || whichEncounter == 13) {
          return "Cannibal";
        } else if (whichEncounter == 14 || whichEncounter == 15) {
          return "Chwinga";
        } else if (whichEncounter == 16) {
          return "Cyclops";
        } else if (whichEncounter == 17) {
          return "Allosaurus";
        } else if (whichEncounter == 18) {
          return "Ankylosaurus";
        } else if (whichEncounter == 19) {
          return "Brontosaurus";
        } else if (whichEncounter == 20 || whichEncounter == 21) {
          return "Deinonychus";
        } else if (whichEncounter == 22 || whichEncounter == 23) {
          return "Hadrosaurus";
        } else if (whichEncounter == 24) {
          return "Pteranodon";
        } else if (whichEncounter == 25 || whichEncounter == 26) {
          return "Stegosaurus";
        } else if (whichEncounter == 27 || whichEncounter == 28) {
          return "Triceratops";
        } else if (whichEncounter == 29 || whichEncounter == 30) {
          return "Tyrannosaurus";
        } else if (whichEncounter >= 31 && whichEncounter <= 35) {
          return "Velociraptor";
        } else if (whichEncounter == 36) {
          return "Faerie Dragon";
        } else if (whichEncounter == 37) {
          return "Elbis";
        } else if (whichEncounter >= 38 && whichEncounter <= 42) {
          return "Emerald Enclave";
        } else if (whichEncounter == 43 || whichEncounter == 44) {
          return "Dead Explorer";
        } else if (whichEncounter == 45) {
          return "Explorer";
        } else if (whichEncounter == 46) {
          return "Flail Snail";
        } else if (whichEncounter >= 47 && whichEncounter <= 50) {
          return "Flaming Fist";
        } else if (whichEncounter == 51) {
          return "Flying Monkey";
        } else if (whichEncounter == 52 || whichEncounter == 53) {
          return "Flying Snake";
        } else if (whichEncounter == 54 || whichEncounter == 55) {
          return "Frost Giant";
        } else if (whichEncounter == 56) {
          return "Giant Boar";
        } else if (whichEncounter == 57) {
          return "Giant Frog";
        } else if (whichEncounter == 58) {
          return "Giant Lizard";
        } else if (whichEncounter == 59) {
          return "Giant Scorpion";
        } else if (whichEncounter == 60) {
          return "Giant Wasp";
        } else if (whichEncounter == 61 || whichEncounter == 62) {
          return "Girallon";
        } else if (whichEncounter == 63 || whichEncounter == 64) {
          return "Goblin";
        } else if (whichEncounter == 65 || whichEncounter == 66) {
          return "Grung";
        } else if (whichEncounter == 67) {
          return "Jaculis";
        } else if (whichEncounter == 68) {
          return "Kamadan";
        } else if (whichEncounter == 69 || whichEncounter == 70) {
          return "Lizardfolk";
        } else if (whichEncounter == 71 || whichEncounter == 72) {
          return "Mad Monkey Mist";
        } else if (whichEncounter == 73) {
          return "Mantrap";
        } else if (whichEncounter == 74) {
          return "Night Hag";
        } else if (whichEncounter == 75) {
          return "Pterafolk";
        } else if (whichEncounter == 76) {
          return "Rare Plant";
        } else if (whichEncounter == 77) {
          return "Red Wizard";
        } else if (whichEncounter == 78 || whichEncounter == 79) {
          return "Constrictor Snake";
        } else if (whichEncounter == 80) {
          return "Giant Constrictor Snake";
        } else if (whichEncounter == 81) {
          return "Giant Poisonous Snake";
        } else if (whichEncounter == 82) {
          return "Spider";
        } else if (whichEncounter >= 83 && whichEncounter <= 85) {
          return "Statue of Ubtao";
        } else if (whichEncounter == 86) {
          return "Stirge";
        } else if (whichEncounter == 87) {
          return "Su-monster";
        } else if (whichEncounter == 88) {
          return "Swarm of Bats";
        } else if (whichEncounter == 89) {
          return "Swarm of Insects";
        } else if (whichEncounter == 90) {
          return "Tabaxi Hunter";
        } else if (whichEncounter == 91) {
          return "Tiger";
        } else if (whichEncounter == 92) {
          return "Tri-flower Frond";
        } else if (whichEncounter == 93) {
          return "Vegepygmie";
        } else if (whichEncounter == 94) {
          return "Wereboar";
        } else if (whichEncounter == 95) {
          return "Weretiger";
        } else if (whichEncounter == 96) {
          return "Winterscape";
        } else if (whichEncounter == 97) {
          return "Yellow Musk Creeper And Zombies";
        } else if (whichEncounter == 98) {
          return "Yuan-ti";
        } else if (whichEncounter == 99) {
          return "Zhentarim";
        } else if (whichEncounter == 100) {
          return "Zorbos";
        }
      // Lesser undead encounters: --------------------------------------------
      } else if (map [playerPosition] [0] == 2) {
        if (whichEncounter == 1) {
          return "Albino Dwarves";
        } else if (whichEncounter == 2) {
          return "Artus Cimber";
        } else if (whichEncounter >= 3 && whichEncounter <= 5) {
          return "Assassin Vine";
        } else if (whichEncounter == 6) {
          return "Axe Beak";
        } else if (whichEncounter == 7 || whichEncounter == 8) {
          return "Cannibal";
        } else if (whichEncounter == 9 || whichEncounter == 10) {
          return "Chwinga";
        } else if (whichEncounter == 11) {
          return "Allosaurus";
        } else if (whichEncounter == 12) {
          return "Ankylosaurus";
        } else if (whichEncounter == 13) {
          return "Deinonychus";
        } else if (whichEncounter == 14) {
          return "Hadrosaurus";
        } else if (whichEncounter == 15) {
          return "Pteranodon";
        } else if (whichEncounter == 16) {
          return "Stegosaurus";
        } else if (whichEncounter == 17) {
          return "Triceratops";
        } else if (whichEncounter == 18) {
          return "Tyrannosaurus";
        } else if (whichEncounter == 19 || whichEncounter == 20) {
          return "Emerald Enclave";
        } else if (whichEncounter == 21 || whichEncounter == 22) {
          return "Dead Explorer";
        } else if (whichEncounter == 23) {
          return "Explorer";
        } else if (whichEncounter >= 24 && whichEncounter <= 26) {
          return "Flaming Fist";
        } else if (whichEncounter == 27) {
          return "Flying Snake";
        } else if (whichEncounter == 28) {
          return "Giant Lizard";
        } else if (whichEncounter == 29) {
          return "Giant Wasp";
        } else if (whichEncounter == 30 || whichEncounter == 31) {
          return "Girallon";
        } else if (whichEncounter == 32 || whichEncounter == 33) {
          return "Goblin";
        } else if (whichEncounter == 34 || whichEncounter == 35) {
          return "Grung";
        } else if (whichEncounter >= 36 && whichEncounter <= 39) {
          return "Mad Monkey Mist";
        } else if (whichEncounter == 40 || whichEncounter == 41) {
          return "Mantrap";
        } else if (whichEncounter == 42) {
          return "Night Hag";
        } else if (whichEncounter == 43 || whichEncounter == 44) {
          return "Pterafolk";
        } else if (whichEncounter == 45) {
          return "Rare Plant";
        } else if (whichEncounter == 46) {
          return "Red Wizard";
        } else if (whichEncounter == 47 ||whichEncounter == 48) {
          return "Constrictor Snake";
        } else if (whichEncounter == 49) {
          return "Giant Constrictor Snake";
        } else if (whichEncounter == 50) {
          return "Giant Poisonous Snake";
        } else if (whichEncounter == 51 ||whichEncounter == 52) {
          return "Spider";
        } else if (whichEncounter >= 53 && whichEncounter <= 55) {
          return "Statue of Ubtao";
        } else if (whichEncounter == 56 || whichEncounter == 57) {
          return "Stirge";
        } else if (whichEncounter == 58 || whichEncounter == 59) {
          return "Su-monster";
        } else if (whichEncounter >= 60 && whichEncounter <= 62) {
          return "Swarm of Bats";
        } else if (whichEncounter >= 63 && whichEncounter <= 65) {
          return "Swarm of Insects";
        } else if (whichEncounter == 66) {
          return "Tri-flower Frond";
        } else if (whichEncounter == 67) {
          return "Troll";
        } else if (whichEncounter >= 68 && whichEncounter <= 72) {
          return "Ghoul";
        } else if (whichEncounter >= 73 && whichEncounter <= 77) {
          return "Skeleton";
        } else if (whichEncounter >= 78 && whichEncounter <= 79) {
          return "Specter";
        } else if (whichEncounter >= 80 && whichEncounter <= 89) {
          return "Zombie";
        } else if (whichEncounter == 90 || whichEncounter == 91) {
          return "Vegepygmie";
        } else if (whichEncounter == 92) {
          return "Wereboar";
        } else if (whichEncounter == 93) {
          return "Weretiger";
        } else if (whichEncounter == 94) {
          return "Winterscape";
        } else if (whichEncounter == 95 || whichEncounter == 96) {
          return "Yellow Musk Creeper And Zombies";
        } else if (whichEncounter == 97 || whichEncounter == 98) {
          return "Yuan-ti";
        } else if (whichEncounter == 99) {
          return "Zhentarim";
        } else if (whichEncounter == 100) {
          return "Zorbos";
        }
      // Greater undead encounters: -------------------------------------------
      } else if (map [playerPosition] [0] == 3) {
        if (whichEncounter == 1) {
          return "Artus Cimber";
        } else if (whichEncounter == 2) {
          return "Assassin Vine";
        } else if (whichEncounter >= 3 && whichEncounter <= 5) {
          return "Cache";
        } else if (whichEncounter == 6) {
          return "Allosaurus";
        } else if (whichEncounter == 7) {
          return "Ankylosaurus";
        } else if (whichEncounter == 8) {
          return "Hadrosaurus";
        } else if (whichEncounter == 9) {
          return "Pteranodon";
        } else if (whichEncounter == 10) {
          return "Stegosaurus";
        } else if (whichEncounter == 11 || whichEncounter == 12) {
          return "Tyrannosaurus";
        } else if (whichEncounter == 13 || whichEncounter == 14) {
          return "Velociraptor";
        } else if (whichEncounter == 15 || whichEncounter == 16) {
          return "Emerald Enclave";
        } else if (whichEncounter >= 17 && whichEncounter <= 20) {
          return "Dead Explorer";
        } else if (whichEncounter == 21) {
          return "Explorer";
        } else if (whichEncounter >= 22 && whichEncounter <= 23) {
          return "Flaming Fist";
        } else if (whichEncounter == 24) {
          return "Giant Wasp";
        } else if (whichEncounter == 25) {
          return "Mantrap";
        } else if (whichEncounter == 26) {
          return "Pterafolk";
        } else if (whichEncounter == 27) {
          return "Rare Plant";
        } else if (whichEncounter == 28) {
          return "Red Wizard";
        } else if (whichEncounter >= 29 && whichEncounter <= 31) {
          return "Constrictor Snake";
        } else if (whichEncounter == 32) {
          return "Giant Constrictor Snake";
        } else if (whichEncounter == 33) {
          return "Giant Poisonous Snake";
        } else if (whichEncounter >= 34 && whichEncounter <= 36) {
          return "Spider";
        } else if (whichEncounter >= 37 && whichEncounter <= 40) {
          return "Statue of Ubtao";
        } else if (whichEncounter >= 41 && whichEncounter <= 44) {
          return "Stirge";
        } else if (whichEncounter == 45) {
          return "Su-monster";
        } else if (whichEncounter == 46) {
          return "Swarm of Bats";
        } else if (whichEncounter >= 47 && whichEncounter <= 49) {
          return "Swarm of Insects";
        } else if (whichEncounter == 50) {
          return "Tri-flower Frond";
        } else if (whichEncounter == 51) {
          return "Troll";
        } else if (whichEncounter >= 52 && whichEncounter <= 63) {
          return "Ghoul";
        } else if (whichEncounter >= 64 && whichEncounter <= 67) {
          return "Skeleton";
        } else if (whichEncounter >= 68 && whichEncounter <= 70) {
          return "Specter";
        } else if (whichEncounter >= 71 && whichEncounter <= 73) {
          return "Wight";
        } else if (whichEncounter >= 74 && whichEncounter <= 85) {
          return "Zombie";
        } else if (whichEncounter == 86 || whichEncounter == 87) {
          return "Vegepygmie";
        } else if (whichEncounter == 88 || whichEncounter == 89) {
          return "Wereboar";
        } else if (whichEncounter == 90 || whichEncounter == 91) {
          return "Weretiger";
        } else if (whichEncounter == 92) {
          return "Winterscape";
        } else if (whichEncounter >= 93 && whichEncounter <= 96) {
          return "Yellow Musk Creeper And Zombies";
        } else if (whichEncounter == 97 || whichEncounter == 98) {
          return "Yuan-ti";
        } else if (whichEncounter == 99 || whichEncounter == 100) {
          return "Zorbos";
        }
      // Mountain encounters: -------------------------------------------------
      } else if (map [playerPosition] [0] == 4) {
        if (whichEncounter >= 1 && whichEncounter <= 11) {
          return "Aarakocra";
        } else if (whichEncounter >= 12 && whichEncounter <= 17) {
          return "Albino Dwarves";
        } else if (whichEncounter >= 18 && whichEncounter <= 20) {
          return "Ape";
        } else if (whichEncounter == 21 || whichEncounter == 22) {
          return "Baboon";
        } else if (whichEncounter >= 23 && whichEncounter <= 25) {
          return "Cache";
        } else if (whichEncounter == 26 || whichEncounter == 27) {
          return "Chwinga";
        } else if (whichEncounter == 28 || whichEncounter == 29) {
          return "Cyclops";
        } else if (whichEncounter >= 30 && whichEncounter <= 38) {
          return "Pteranodon";
        } else if (whichEncounter >= 39 && whichEncounter <= 42) {
          return "Quetzalcoatlus";
        } else if (whichEncounter >= 43 && whichEncounter <= 45) {
          return "Quetzalcoatlus";
        } else if (whichEncounter == 46 || whichEncounter == 47) {
          return "Emerald Enclave";
        } else if (whichEncounter >= 48 && whichEncounter <= 50) {
          return "Dead Explorer";
        } else if (whichEncounter >= 51 && whichEncounter <= 53) {
          return "Explorer";
        } else if (whichEncounter >= 54 && whichEncounter <= 59) {
          return "Flying Monkey";
        } else if (whichEncounter == 60 || whichEncounter == 61) {
          return "Flying Snake";
        } else if (whichEncounter == 62) {
          return "Giant Boar";
        } else if (whichEncounter == 63) {
          return "Giant Lizard";
        } else if (whichEncounter == 64 || whichEncounter == 65) {
          return "Giant Wasp";
        } else if (whichEncounter >= 66 && whichEncounter <= 70) {
          return "Girallons";
        } else if (whichEncounter >= 71 && whichEncounter <= 73) {
          return "Night Hag";
        } else if (whichEncounter >= 74 && whichEncounter <= 80) {
          return "Pterafolk";
        } else if (whichEncounter == 81) {
          return "Red Wizard";
        } else if (whichEncounter >= 82 && whichEncounter <= 84) {
          return "Giant Poisonous Snake";
        } else if (whichEncounter >= 85 && whichEncounter <= 87) {
          return "Statue of Ubtao";
        } else if (whichEncounter >= 88 && whichEncounter <= 90) {
          return "Swarm of Bats";
        } else if (whichEncounter == 91 || whichEncounter == 92) {
          return "Tabaxi Hunter";
        } else if (whichEncounter >= 93 && whichEncounter <= 97) {
          return "Troll";
        } else if (whichEncounter >= 98 && whichEncounter <= 100) {
          return "Wereboar";
        }
      // River encounters: ----------------------------------------------------
      } else if (map [playerPosition] [0] == 5) {
        if (whichEncounter >= 1 && whichEncounter <= 3) {
          return "Aarakocra";
        } else if (whichEncounter >= 4 && whichEncounter <= 7) {
          return "Aldani";
        } else if (whichEncounter == 8 || whichEncounter == 9) {
          return "Artus Cimber";
        } else if (whichEncounter == 10) {
          return "Assassin Vine";
        } else if (whichEncounter == 11 || whichEncounter == 12) {
          return "Cache";
        } else if (whichEncounter >= 13 && whichEncounter <= 15) {
          return "Cannibal";
        } else if (whichEncounter >= 16 && whichEncounter <= 18) {
          return "Chwinga";
        } else if (whichEncounter >= 19 && whichEncounter <= 23) {
          return "Crocodile";
        } else if (whichEncounter == 24) {
          return "Brontosaurus";
        } else if (whichEncounter == 25 || whichEncounter == 26) {
          return "Dimetrodon";
        } else if (whichEncounter == 27 || whichEncounter == 28) {
          return "Hadrosaurus";
        } else if (whichEncounter >= 29 && whichEncounter <= 31) {
          return "Plesiosaurus";
        } else if (whichEncounter >= 32 && whichEncounter <= 34) {
          return "Pteranodon";
        } else if (whichEncounter == 35 || whichEncounter == 36) {
          return "Quetzalcoatlus";
        } else if (whichEncounter == 37) {
          return "Faerie Dragon";
        } else if (whichEncounter >= 38 && whichEncounter <= 40) {
          return "Elbis";
        } else if (whichEncounter >= 41 && whichEncounter <= 43) {
          return "Emerald Enclave";
        } else if (whichEncounter == 44 || whichEncounter == 45) {
          return "Dead Explorer";
        } else if (whichEncounter >= 46 && whichEncounter <= 49) {
          return "Explorer";
        } else if (whichEncounter == 50 || whichEncounter == 51) {
          return "Flaming Fist";
        } else if (whichEncounter == 52 || whichEncounter == 53) {
          return "Flying Monkey";
        } else if (whichEncounter == 54 || whichEncounter == 55) {
          return "Flying Snake";
        } else if (whichEncounter >= 56 && whichEncounter <= 58) {
          return "Giant Crocodile";
        } else if (whichEncounter == 59 || whichEncounter == 60) {
          return "Giant Frog";
        } else if (whichEncounter == 61 || whichEncounter == 62) {
          return "Giant Snapping Turtle";
        } else if (whichEncounter == 63) {
          return "Giant Wasp";
        } else if (whichEncounter >= 64 && whichEncounter <= 66) {
          return "Grung";
        } else if (whichEncounter == 67) {
          return "Jaculis";
        } else if (whichEncounter == 68) {
          return "Lizardfolk";
        } else if (whichEncounter == 69 || whichEncounter == 70) {
          return "Mad Monkey Mist";
        } else if (whichEncounter == 71 || whichEncounter == 72) {
          return "Pterafolk";
        } else if (whichEncounter == 73) {
          return "Rare Plant";
        } else if (whichEncounter == 74) {
          return "Red Wizard";
        } else if (whichEncounter == 75 || whichEncounter == 76) {
          return "Sea Hag";
        } else if (whichEncounter >= 77 && whichEncounter <= 79) {
          return "Constrictor Snake";
        } else if (whichEncounter == 80) {
          return "Giant Constrictor Snake";
        } else if (whichEncounter == 81) {
          return "Statue of Ubtao";
        } else if (whichEncounter == 82 || whichEncounter == 83) {
          return "Stirges";
        } else if (whichEncounter == 84 || whichEncounter == 85) {
          return "Swarm of Insects";
        } else if (whichEncounter >= 86 && whichEncounter <= 91) {
          return "Swarm of Quippers";
        } else if (whichEncounter == 92 || whichEncounter == 93) {
          return "Tabaxi Hunter";
        } else if (whichEncounter == 94) {
          return "Ghoul";
        } else if (whichEncounter == 95) {
          return "Skeleton";
        } else if (whichEncounter == 96) {
          return "Zombie";
        } else if (whichEncounter == 97 || whichEncounter == 98) {
          return "Yuan-ti";
        } else if (whichEncounter == 99 || whichEncounter == 100) {
          return "Zhentarim";
        }
      // Swamp encounters: ----------------------------------------------------
      } else if (map [playerPosition] [0] == 6) {
        if (whichEncounter >= 1 && whichEncounter <= 10) {
          return "Aldani";
        } else if (whichEncounter == 11) {
          return "Artus Cimber";
        } else if (whichEncounter >= 12 && whichEncounter <= 14) {
          return "Assassin Vines";
        } else if (whichEncounter == 15 || whichEncounter == 16) {
          return "Chwinga";
        } else if (whichEncounter >= 17 && whichEncounter <= 21) {
          return "Crocodile";
        } else if (whichEncounter == 22) {
          return "Allosaurus";
        } else if (whichEncounter == 23) {
          return "Ankylosaurus";
        } else if (whichEncounter == 24 || whichEncounter == 25) {
          return "Brontosaurus";
        } else if (whichEncounter >= 26 && whichEncounter <= 30) {
          return "Dimetrodon";
        } else if (whichEncounter >= 31 && whichEncounter <= 33) {
          return "Hadrosaurus";
        } else if (whichEncounter == 34 || whichEncounter == 35) {
          return "Pteranodon";
        } else if (whichEncounter >= 36 && whichEncounter <= 39) {
          return "Elbis";
        } else if (whichEncounter == 40 || whichEncounter == 41) {
          return "Dead Explorer";
        } else if (whichEncounter >= 42 && whichEncounter <= 45) {
          return "Explorer";
        } else if (whichEncounter == 46 || whichEncounter == 47) {
          return "Flail Snail";
        } else if (whichEncounter >= 48 && whichEncounter <= 50) {
          return "Flying Snake";
        } else if (whichEncounter >= 51 && whichEncounter <= 53) {
          return "Giant Crocodile";
        } else if (whichEncounter >= 54 && whichEncounter <= 55) {
          return "Giant Frog";
        } else if (whichEncounter == 57 || whichEncounter == 58) {
          return "Giant Lizard";
        } else if (whichEncounter == 59 || whichEncounter == 60) {
          return "Giant Snapping Turtle";
        } else if (whichEncounter == 61 || whichEncounter == 62) {
          return "Giant Wasp";
        } else if (whichEncounter == 63 || whichEncounter == 64) {
          return "Grung";
        } else if (whichEncounter == 65 || whichEncounter == 66) {
          return "Lizardfolk";
        } else if (whichEncounter >= 67 && whichEncounter <= 69) {
          return "Mad Monkey Mist";
        } else if (whichEncounter == 70) {
          return "Mephit";
        } else if (whichEncounter == 71) {
          return "Night Hag";
        } else if (whichEncounter == 72) {
          return "Rare Plant";
        } else if (whichEncounter >= 73 && whichEncounter <= 76) {
          return "Shambling Mound";
        } else if (whichEncounter >= 77 && whichEncounter <= 80) {
          return "Constrictor Snake";
        } else if (whichEncounter == 81 || whichEncounter == 82) {
          return "Giant Constrictor Snake";
        } else if (whichEncounter >= 83 && whichEncounter <= 85) {
          return "Statue of Ubtao";
        } else if (whichEncounter == 86 || whichEncounter == 87) {
          return "Stirge";
        } else if (whichEncounter == 88 || whichEncounter == 89) {
          return "Swarm of Bats";
        } else if (whichEncounter >= 90 && whichEncounter <= 94) {
          return "Swarm of Insects";
        } else if (whichEncounter == 95) {
          return "Ghoul";
        } else if (whichEncounter == 96 || whichEncounter == 97) {
          return "Skeleton";
        } else if (whichEncounter == 98) {
          return "Zombie";
        } else if (whichEncounter == 99 || whichEncounter == 100) {
          return "Zhentarim";
        }
      // Wasteland encounters: ------------------------------------------------
      } else if (map [playerPosition] [0] == 7) {
        if (whichEncounter == 1) {
          return "Artus Cimber";
        } else if (whichEncounter >= 2 && whichEncounter <= 5) {
          return "Cache";
        } else if (whichEncounter >= 6 && whichEncounter <= 9) {
          return "Red Dragon";
        } else if (whichEncounter >= 10 && whichEncounter <= 18) {
          return "Dead Explorer";
        } else if (whichEncounter == 19) {
          return "Explorer";
        } else if (whichEncounter >= 20 && whichEncounter <= 37) {
          return "Firenewt";
        } else if (whichEncounter >= 38 && whichEncounter <= 45) {
          return "Giant Scorpion";
        } else if (whichEncounter >= 46 && whichEncounter <= 54) {
          return "Magmin";
        } else if (whichEncounter >= 55 && whichEncounter <= 71) {
          return "Mephit";
        } else if (whichEncounter >= 72 && whichEncounter <= 78) {
          return "Night Hag";
        } else if (whichEncounter == 79) {
          return "Statue of Ubtao";
        } else if (whichEncounter >= 80 && whichEncounter <= 83) {
          return "Troll";
        } else if (whichEncounter == 84 || whichEncounter == 85) {
          return "Ghoul";
        } else if (whichEncounter >= 86 && whichEncounter <= 95) {
          return "Skeleton";
        } else if (whichEncounter == 96 || whichEncounter == 97) {
          return "Wight";
        } else if (whichEncounter == 98) {
          return "Zombie";
        } else if (whichEncounter == 99 || whichEncounter == 100) {
          return "Zhentarim";
        }
      }
    }
    return "None";
  }
}
