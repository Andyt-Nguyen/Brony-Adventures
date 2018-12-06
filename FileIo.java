import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FileIo {
    private static ArrayList<PlayBronyGame> gameList = new ArrayList<PlayBronyGame>();
    private static String FILE_NAME = "BronyDatabase.txt";

    @SuppressWarnings("unchecked")
    public static void readFile() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            gameList =(ArrayList <PlayBronyGame>) in.readObject();

        } catch (FileNotFoundException e){
            System.out.println("File note found");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            System.out.println("Creating test user");
            addNewPlayer(new PlayBronyGame());
            writeFile();
        } catch(IOException e) {
            System.out.println("Error " + e);
        } catch(ClassNotFoundException e) {
            System.out.println("Error " + e);
        }
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

    public static void writeFile() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException{
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\nSaving game...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Processing...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Processing....");
        TimeUnit.SECONDS.sleep(2);
         // write file
         ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
         out.writeObject(gameList); // save data
         System.out.println("\nGame successfully saved.\n");
    }

    public static PlayBronyGame getGame(String username, String password) throws InterruptedException {
            for(int i = 0; i < gameList.size(); i++) {
                if(gameList.get(i).getPlayerName().toLowerCase().equals(username.toLowerCase()) &&
                   gameList.get(i).getPlayerPassword().equals(password)) {
                    System.out.println("Getting user: " + gameList.get(i).getPlayerName());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Processing...");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Loading game.");
                    TimeUnit.SECONDS.sleep(2);
                    return gameList.get(i);
                }
            }
            return null;
    }
    
}