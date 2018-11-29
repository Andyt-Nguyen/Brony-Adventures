public class Menus {
    
    public static void displayHelp() {
        System.out.println("*********************************************");
        System.out.println("* Command           |    Action               *");
        System.out.println("*********************************************");
        System.out.println("  move north          |    Move up               *");
        System.out.println("  move south          |    Move down             *");
        System.out.println("  move east           |    Move right            *");
        System.out.println("  move west           |    Move left             *");
        System.out.println("  scream              |    Player screams        *");
        System.out.println("  pick up [item]      |    Player picks up wepon *");
        System.out.println("  view inventory      |    look in inventory     *");
        System.out.println("  view surroundings   |    look around room      *");
        System.out.println("  look under [object] |    looks under object    *");
        System.out.println("  diagnostics         |    view hp               *");
        System.out.println("  fight [enemy]       |    fight enemy           *");
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