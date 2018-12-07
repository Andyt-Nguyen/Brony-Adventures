import java.io.Serializable;
import java.io.*;
import java.util.*;

public class Menus implements Serializable {

    public static void displayStartGame(Player player){
        System.out.println("************************************************************");
        System.out.println("* Welcome " + player.getUsername() + " to Brony Adventures.                            *");
        System.out.println("* You wake up from a deep sleep in an abandoned house.     *");
        System.out.println("* The goal is to find the key...                           *");
        System.out.println("* Get to the hallway on the north side of the building..   *");
        System.out.println("* And escape through the front door.                       *");
        System.out.println("* It seems like you are in a room with a bed, a dresser,   *");
        System.out.println("* And a door to the north..                                *");
        System.out.println("* You can use the command \"help\" to get started..          *");
        System.out.println("************************************************************");
    }

    public static void displayGameWon(Player player){
        System.out.println("****************************************************************");
        System.out.printf("*  Congratulations, %8s" , player.getUsername() + ". You have escaped!                     *" );
        System.out.println();
        System.out.println("*  Your score of " + player.getHighScore() + " points will be recorded to the highscores.*");
        System.out.println("*  I hope you enjoyed playing our game!                        *");
        System.out.println("****************************************************************");
    }
    
    public static void displayHelp() {
        System.out.println("********************************************************");
        System.out.println("* Command             |    Action                      *");
        System.out.println("********************************************************");
        System.out.println("* Walk to -object-    |    Moves to object             *");
        System.out.println("* type a direction    |    Changes rooms in direction. *");
        System.out.println("* Center              |    Return to center of room.   *");
        System.out.println("* Search              |    Examines Room               *");
        System.out.println("* Scream              |    Player Screams              *");
        System.out.println("* Eat Food            |    Eats food if available.     *");
        System.out.println("* Check Food          |    Checks amt of food          *");
        System.out.println("* Health              |    Displays HP                 *");
        System.out.println("* Points              |    Displays points             *");
        System.out.println("* Highscores          |    Displays highscores         *");
        System.out.println("* Exit                |    Saves and Quits Game        *");
        System.out.println("********************************************************");
    }

    public static void displayGameOver(Player player){
        System.out.println("----------------------------------------------");
        System.out.println("It seems you have died.");
        System.err.println("You collected a total of " + player.getHighScore() + " points this game.");
        System.err.println("Your stats have been placed on the highscores.");
        System.out.println("----------------------------------------------");
    }

    @SuppressWarnings("unchecked")
    public static void sortHighscores(){
        ArrayList<PlayBronyGame> gameList = FileIo.getGameList();
        Collections.sort(gameList, new Comparator<PlayBronyGame>() {
            public int compare(PlayBronyGame p1, PlayBronyGame p2) {
                return Integer.valueOf(p2.getPlayerHighscore())
                              .compareTo(p1.getPlayerHighscore());
            }
        });
        if(gameList.size() <= 1){
            System.out.println("\nThere are no highscores to display!");
        }else{
            System.out.println("-------------* Brony Adventures Highscores *-------------");
            System.out.println("      Username             |               Points");
            System.out.println("---------------------------------------------------------");
            for(int i = 0 ; i < gameList.size(); i++) {

                displayNameAndScore((i + 1)
                                    ,gameList.get(i).getPlayerName(),
                                    gameList.get(i).getPlayerHighscore());
            }
            System.out.println("---------------------------------------------------------");
        }

    }

    public static void displayNameAndScore(int inc, String username, int highscore){
        
            System.out.printf("     " + inc + ". %-12s" , username);
            System.out.print("                         " + highscore);
            System.out.println();
    }
}