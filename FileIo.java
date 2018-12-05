import java.io.*;
import java.util.ArrayList;

public class FileIo {
    private static ArrayList<PlayBronyGame> gameList = new ArrayList<PlayBronyGame>();
    private static String FILE_NAME = "BronyDatabase.txt";
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        readFile();
    }

    public static void readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Game.txt"));
            gameList =(ArrayList <PlayBronyGame>) in.readObject();

        } catch (FileNotFoundException e){
            System.out.println("File note found");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Game.txt"));
            System.out.println("Creating test user");
            addNewPlayer(new PlayBronyGame());
            writeFile();
        } catch(IOException e) {
            System.out.println("Error " + e);
        } catch(ClassNotFoundException e) {
            System.out.println("Error " + e);
        }

        // if(gameList.size() == 0) {
        //     System.out.println("No player in the list");
        // } else {
        //     for(int i = 0; i < gameList.size(); i++) {
        //         System.out.println(gameList.get(i).getPlayerName());
        //     }
        // }
    }



    public static boolean addNewPlayer(PlayBronyGame game) {
        for(int i = 0; i < gameList.size(); i++) {
            if(gameList.get(i).getPlayerName().equals(game.getPlayerName())) {
                return false;
            }
        }
        gameList.add(game);
        return true;
    }




    public static void writeFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("Writing file: " + FILE_NAME);
         // write file
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Game.txt"));
         out.writeObject(gameList); // save data
    }

    public static PlayBronyGame getGame(String username, String password) {
            for(int i = 0; i < gameList.size(); i++) {
                if(gameList.get(i).getPlayerName().equals(username) &&
                   gameList.get(i).getPlayerPassword().equals(password)) {
                    return gameList.get(i);
                }
            }
            return null;
    }

    // public static void  saveGame() {
    //     for(int i = 0; i < gameList.size(); i++) {
    //         if(gameList.get(i).getPlayerName().equals(game.getPlayerName())) {
    //             gameList.remove(i);
    //             gameList.add(i, game);
    //             System.out.println("Saving game");
    //         }
    //     }
    // }



    
}