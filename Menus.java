public class Menus {
    
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