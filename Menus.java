public class Menus {
    public static void displayHelp() {
        System.out.println("------------------------------------");
        System.out.println("How to pick up items: Pick up [item]");
        System.out.println("View inventory: Inventory");
        System.out.println("View your surroundings: Look around");
        System.out.println("View health: Diagnostics");
        System.out.println("Look under objects: Look under [object]");
        System.out.println("Fight: Fight [enemy]");
        System.out.println("------------------------------------");
    }

    public static void displayGameOver(Player player){
        System.out.println("----------------------------------------------");
        System.out.println("It seems you have died.");
        System.err.println("You collected a total of " + player.getHighScore() + " points this game.");
        System.err.println("Your stats have been placed on the highscores.");
        System.out.println("----------------------------------------------");
    }
}