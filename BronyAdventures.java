import java.util.*;
import java.io.*;

class BronyAdventures {
    public static Player player = new Player();
    public final static Scanner scanner = new Scanner(System.in);
    public static PlayBronyGame game;

    public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {

        readyPlayer();
        game.playGame();
        /**
         * To access the player object just use variable "player" player.getHighScore();
         * player.getFood(); etc...
         */

        // Creation of Rooms.. Format goes RoomID, Room Name, Room Description, A help
        // message for the "Search" command,
        // the north door value, the south door value, the east door value, the west
        // door value, then a 0 or a 1,
        // based on those classes fields and whether that object is in the room or not.

    }

    /*******************
     * Start: Create or get user information
     *********************/

    
    
    public static void readyPlayer() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
        FileIo.readFile();
        boolean flag = false;
        while(!flag) {
            loggingUserMsg();
            int userOption = IR5.getInteger("Select option");

            if(userOption == 1) {
                boolean isNewUser = false;
                while(!isNewUser) {
                    isNewUser = createUser();
                    if(isNewUser) flag = true;
                    else {
                        String tryAgain = getString("Press any key to try again (type exit to go back)");
                        if(tryAgain.equals("exit")) break;
                    }
                    
                }
                Menus.displayStartGame(player);
            } else if(userOption == 2) {
                boolean doesUserExist = false;
                while(!doesUserExist) {
                    doesUserExist = signinUser();
                    if(doesUserExist) flag = true;
                    else {
                        String tryAgain = getString("Press any key to try again (type exit to go back)");
                        if(tryAgain.equals("exit")) break;
                    }
                }
            }
        }
    }



    public static boolean createUser() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException  {
        /**
         * Initial setup game
         */
        Bedroom guestBedroom = new Bedroom(1, "Guest Bedroom", "This is the guest bedroom at the back of the house.",
                "\nYou see a door to the north, a dresser and a bed in this room.", 2, 0, 0, 0, 1, 1, 0, 0);
        Hallway hallway1 = new Hallway(2, "Southern Hallway", "This is the hallway right outside the guest bedroom.",
                "\nYou see doors to the  east and to the west, a lamp, and a window.", 0, 0, 4, 3, 1, 1, 0, 0, 0);
        Bathroom masterBathroom = new Bathroom(3, "Master Bathroom",
                "This is the bathroom closest to the master bedroom.",
                "\nYou see a door to the east and to the north, you also notice a working shower and toiet.", 5, 0, 2,
                0, 1, 1, 0, 0, 0, 1);
        Kitchen kitchen1 = new Kitchen(4, "South-East Kitchen",
                "This is the kitchen at the back right corner of the house.",
                "\nYou see doors to the west and to the north, you also notice a knife and a running fridge.", 6, 0, 0,
                2, 1, 1, 0, 0);
        Hallway hallway2 = new Hallway(5, "Western Hallway", "This hallway is to the west of a bedroom.",
                "\nYou see doors to the south, east, and west. You also see a statue, another window, and a closet.", 8,
                3, 7, 0, 0, 1, 1, 0, 1);
        Hallway hallway3 = new Hallway(6, "Eastern Hallway", "This hallway is to the east of a bedroom.",
                "\nYou see doors to the south, west, and north. You also see a window, an attic panel, and a statue.",
                9, 4, 0, 7, 0, 1, 0, 1, 0);
        Bedroom masterBedroom = new Bedroom(7, "Master Bedroom",
                "This is the master bedroom that hovers over the courtyard.",
                "\nYou see doors to the east and west, a mirror, a bed, and a dresser in here.", 0, 0, 6, 5, 1, 1, 1,
                1);
        Kitchen kitchen2 = new Kitchen(8, "North-West Kitchen", "This kitchen is in the north-west side of the house.",
                "\nYou see doors to the east and the south, you see a fridge, a useable sink, and a chair.", 0, 5, 10,
                0, 0, 1, 1, 1);
        Bathroom bathroom2 = new Bathroom(9, "Bathroom", "This bathroom is on the north-east side of the house.",
                "\nYou see doors to the south and to the west, you see a toilet, no shower, a plunger, and some cabinets with a mirror above.",
                0, 6, 0, 10, 1, 0, 1, 1, 1, 1);
        Hallway hallway4 = new Hallway(10, "Northern Hallway", "This hallway is the most northern hallway.",
                "\nyou see doors to the north, east, and west, and a statue.", 11, 0, 9, 8, 0, 0, 0, 0, 1);

        // Adds keywords to intialize an action in guestBedroom.
        guestBedroom.addKeyword("Walk to Dresser");
        guestBedroom.addKeyword("Walk to Bed");
        guestBedroom.addKeyword("Move North");

        // Adds keywords to intialize an action in hallway1.
        hallway1.addKeyword("Walk to Lamp");
        hallway1.addKeyword("Walk to Window");
        hallway1.addKeyword("Move West");
        hallway1.addKeyword("Move East");

        // Adds keywords to intialize an action in masterBathroom.
        masterBathroom.addKeyword("Walk to Toilet");
        masterBathroom.addKeyword("Walk to Shower");
        masterBathroom.addKeyword("Walk to Sink");
        masterBathroom.addKeyword("Move East");
        masterBathroom.addKeyword("Move North");

        // Adds keywords to intialize an action in kitchen1.
        kitchen1.addKeyword("Walk to Fridge");
        kitchen1.addKeyword("Walk to Knife");
        kitchen1.addKeyword("Move West");
        kitchen1.addKeyword("Move North");

        // Adds keywords to intialize an action in hallway2.
        hallway2.addKeyword("Walk to Window");
        hallway2.addKeyword("Walk to Statue");
        hallway2.addKeyword("Walk to Closet");
        hallway2.addKeyword("Move North");
        hallway2.addKeyword("Move East");
        hallway2.addKeyword("Move South");

        // Adds keywords to intialize an action in hallway3.
        hallway3.addKeyword("Walk to Window");
        hallway3.addKeyword("Walk to Attic Panel");
        hallway3.addKeyword("Move North");
        hallway3.addKeyword("Move West");
        hallway3.addKeyword("Move South");

        // Adds keywords to initialize list of actions for the master bedroom.
        masterBedroom.addKeyword("Walk to Dresser");
        masterBedroom.addKeyword("Walk to Bed");
        masterBedroom.addKeyword("Walk to Mirror");
        masterBedroom.addKeyword("Move East");
        masterBedroom.addKeyword("Move West");

        // Adds keywords to initialize list of actions for the north-west kitchen.
        kitchen2.addKeyword("Walk to Fridge");
        kitchen2.addKeyword("Walk to Sink");
        kitchen2.addKeyword("Walk to Chair");
        kitchen2.addKeyword("Move East");
        kitchen2.addKeyword("Move South");

        // Adds keywords to initialize list of actions for the 2nd bathroom.
        bathroom2.addKeyword("Walk to Toilet");
        bathroom2.addKeyword("Walk to Plunger");
        bathroom2.addKeyword("Walk to Sink");
        bathroom2.addKeyword("Walk to Mirror");
        bathroom2.addKeyword("Move South");
        bathroom2.addKeyword("Move West");

        // Adds keywords to initialize list of actions for northern hallway.
        hallway4.addKeyword("Walk to Statue");
        hallway4.addKeyword("Move North");
        hallway4.addKeyword("Move East");
        hallway4.addKeyword("Move West");

        /**
         * Creating user
         */
        String username = getString("Create username");
        String password = getString("Create password");
        Player newPlayer = new Player(username, password, 0, 100, 0, 1);
        PlayBronyGame newGame = new PlayBronyGame(newPlayer, guestBedroom, hallway1, masterBathroom, kitchen1, hallway2,
                hallway3, masterBedroom, kitchen2, bathroom2, hallway4);

        boolean isNewPlayer = FileIo.addNewPlayer(newGame);
        if (isNewPlayer) {
            System.out.println("Creating new user");
            game = newGame;
            FileIo.writeFile();
            // System.out.println("Welcome " + username + " to Brony Adventures!");
            return true;
        } else {
            System.out.println("There is already a user with that username");
            return false;
        }
    }

    public static boolean signinUser() throws IOException, InterruptedException {
        String username = getString("Enter username");
        String password = getString("Enter password");
        PlayBronyGame isPlayerExist = FileIo.getGame(username, password);
        if (isPlayerExist != null) {
            System.out.println("Welcome back " + username + "!");
            game = isPlayerExist;
            return true;
        } else {
            System.out.println("User does not exist");
            return false;
        }
    }

    public static void loggingUserMsg() {
        System.out.println("*************************");
        System.out.println("* 1. New Game           *");
        System.out.println("* 2. Existing Game      *");
        System.out.println("*************************");
    }

    /******************* End: Create or get user information *********************/

    public static String getString(String msg) {
        String answer = "";
        System.out.print(msg + ": ");
        try {
            answer = scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Error reading input from user. Ending program.");
            System.exit(-1);
        }

        while (answer.replace(" ", "").equals("")) {
            System.err.println("Error: Missing input.");
            try {
                System.out.println(msg);
                answer = scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Error reading input from user. Ending program.");
                System.exit(-1);
            }
        }
        return answer;
    }

}
