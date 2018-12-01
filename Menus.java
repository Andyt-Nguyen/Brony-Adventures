public class Menus {

    public static void displayStartGame(Player player){
        System.out.println("************************************************************");
        System.out.println("* Welcome " + player.getUsername() + " to Brony Adventures.*");
        System.out.println("* You wake up from a deep sleep in an abandoned house.     *");
        System.out.println("* The goal is to find the key...                           *");
        System.out.println("* Get to the hallway on the north side of the building..   *");
        System.out.println("* And escape through the front door.                       *");
        System.out.println("* You can use the command \"help\" to get started..        *");
        System.out.println("************************************************************");
    }
    
    public static void displayHelp() {
        System.out.println("**************************************************");
        System.out.println("* Command             |    Action                *");
        System.out.println("**************************************************");
        System.out.println("  Search              |    Examines Room         *");
        System.out.println("  Scream              |    Player Screams        *");
        System.out.println("  Health              |    Displays HP           *");
        System.out.println("  Exit                |    Saves and Quits Game  *");
        System.out.println("**************************************************");
    }

    public static void displayGameOver(Player player){
        System.out.println("----------------------------------------------");
        System.out.println("It seems you have died.");
        System.err.println("You collected a total of " + player.getHighScore() + " points this game.");
        System.err.println("Your stats have been placed on the highscores.");
        System.out.println("----------------------------------------------");
    }
}