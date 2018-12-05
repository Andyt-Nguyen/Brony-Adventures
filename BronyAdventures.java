import java.io.*;
import java.util.*;

class BronyAdventures {
    public static Player player = new Player();
    public final static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Room> roomList = new ArrayList<Room>();
    public static void main(String args []) throws IOException {
        
        String userInput = "";
        readyPlayer();
        /**
         * To access the player object just use variable "player"
         * player.getHighScore(); player.getFood(); etc...
         */

         //Creation of Rooms.. Format goes RoomID, Room Name, Room Description, A help message for the "Search" command,
         //the north door value, the south door value, the east door value, the west door value, then a 0 or a 1,
         //based on those classes fields and whether that object is in the room or not.

        Bedroom guestBedroom = new Bedroom(1, "Guest Bedroom", "This is the guest bedroom at the back of the house.",
        "\nYou see a door to the north, a dresser and a bed in this room.", 2, 0, 0, 0, 1 , 1, 0, 0);
        Hallway hallway1 = new Hallway(2, "Southern Hallway", "This is the hallway right outside the guest bedroom.",
        "\nYou see doors to the  east and to the west, a lamp, and a window.", 0, 0, 4, 3, 1, 1, 0, 0, 0);
        Bathroom masterBathroom = new Bathroom(3, "Master Bathroom", "This is the bathroom closest to the master bedroom.",
        "\nYou see a door to the east and to the north, you also notice a working shower and toiet.", 5, 0, 2, 0, 1, 1, 0, 0, 0, 1);
        Kitchen kitchen1 = new Kitchen(4, "South-East Kitchen", "This is the kitchen at the back right corner of the house.",
        "\nYou see doors to the west and to the north, you also notice a knife and a running fridge.", 6, 0, 0, 2, 1, 1, 0, 0);
        Hallway hallway2 = new Hallway(5, "Western Hallway", "This hallway is to the west of a bedroom.", 
        "\nYou see doors to the south, east, and west. You also see a statue, another window, and a closet.", 8, 3, 7, 0, 0, 1, 1, 0, 1);
        Hallway hallway3 = new Hallway(6, "Eastern Hallway", "This hallway is to the east of a bedroom.", 
        "\nYou see doors to the south, west, and north. You also see a window, an attic panel, and a statue.", 9, 4, 0, 7, 0, 1, 0, 1, 1);
        Bedroom masterBedroom = new Bedroom(7, "Master Bedroom", "This is the master bedroom that hovers over the courtyard.",
        "\nYou see doors to the east and west, a mirror, a bed, and a dresser in here.", 0, 0, 6, 5, 1, 1, 1, 1);
        Kitchen kitchen2 = new Kitchen(8,"North-West Kitchen", "This kitchen is in the north-west side of the house.",
        "\nYou see doors to the east and the south, you see a fridge, a useable sink, and a chair.", 0, 5, 10, 0, 0, 1, 1, 1);
        Bathroom bathroom2 = new Bathroom(9, "Bathroom", "This bathroom is on the north-east side of the house.",
        "\nYou see doors to the south and to the west, you see a toilet, no shower, a plunger, and some cabinets with a mirror above.",
        0, 6, 0, 10, 1, 0, 1, 1, 1, 1);
        Hallway hallway4 = new Hallway(10, "Northern Hallway", "This hallway is the most northern hallway.",
        "\nyou see doors to the north, east, and west, and a statue.", 11, 0, 9, 8, 0, 0, 0, 0, 1);

        //Adds keywords to intialize an action in guestBedroom.
        guestBedroom.addKeyword("Walk to Dresser");
        guestBedroom.addKeyword("Walk to Bed");
        guestBedroom.addKeyword("Move North");

        //Adds keywords to intialize an action in hallway1.
        hallway1.addKeyword("Walk to Lamp");
        hallway1.addKeyword("Walk to Window");
        hallway1.addKeyword("Move West");
        hallway1.addKeyword("Move East");

        //Adds keywords to intialize an action in masterBathroom.
        masterBathroom.addKeyword("Walk to Toilet");
        masterBathroom.addKeyword("Walk to Shower");
        masterBathroom.addKeyword("Walk to Sink");
        masterBathroom.addKeyword("Move East");
        masterBathroom.addKeyword("Move North");

        //Adds keywords to intialize an action in kitchen1.
        kitchen1.addKeyword("Walk to Fridge");
        kitchen1.addKeyword("Walk to Knife");
        kitchen1.addKeyword("Move West");
        kitchen1.addKeyword("Move North");

        //Adds keywords to intialize an action in hallway2.
        hallway2.addKeyword("Walk to Window");
        hallway2.addKeyword("Walk to Statue");
        hallway2.addKeyword("Walk to Closet");
        hallway2.addKeyword("Move North");
        hallway2.addKeyword("Move East");
        hallway2.addKeyword("Move South");

        //Adds keywords to intialize an action in hallway3.
        hallway3.addKeyword("Walk to Window");
        hallway3.addKeyword("Walk to Attic Panel");
        hallway3.addKeyword("Walk to Statue");
        hallway3.addKeyword("Move North");
        hallway3.addKeyword("Move West");
        hallway3.addKeyword("Move South");

        //Adds keywords to initialize list of actions for the master bedroom.
        masterBedroom.addKeyword("Walk to Dresser");
        masterBedroom.addKeyword("Walk to Bed");
        masterBedroom.addKeyword("Walk to Mirror");
        masterBedroom.addKeyword("Move East");
        masterBedroom.addKeyword("Move West");

        //Adds keywords to initialize list of actions for the north-west kitchen.
        kitchen2.addKeyword("Walk to Fridge");
        kitchen2.addKeyword("Walk to Sink");
        kitchen2.addKeyword("Walk to Chair");
        kitchen2.addKeyword("Move East");
        kitchen2.addKeyword("Move South");

        //Adds keywords to initialize list of actions for the 2nd bathroom.
        bathroom2.addKeyword("Walk to Toilet");
        bathroom2.addKeyword("Walk to Plunger");
        bathroom2.addKeyword("Walk to Sink");
        bathroom2.addKeyword("Walk to Mirror");
        bathroom2.addKeyword("Move South");
        bathroom2.addKeyword("Move West");

        //Adds keywords to initialize list of actions for northern hallway.
        hallway4.addKeyword("Walk to Statue");
        hallway4.addKeyword("Move North");
        hallway4.addKeyword("Move East");
        hallway4.addKeyword("Move West");

        //Determines amount of times room has been vistied(used to determine message sent to user).
        int hallway1Visited = 0;
        int hallway2Visited = 0;
        int hallway3Visited = 0;
        int hallway4Visited = 0;
        int guestBedroomVisited = 0;
        int masterBedroomVisited = 0;
        int kitchen1Visited = 0;
        int kitchen2Visited = 0;
        int masterBathroomVisited = 0;
        int bathroomVisited = 0;



        //roomList.add(guestBedroom);
        //player.setLocation(1);
       // player.setHp(100);

       //Basic while loop to get input from the user if they are alive.

        while(player.getHp() > 0){
            int currentRoom = player.getLocation();
            userInput = IR5.getString("\nEnter a single word associated with what you would like to do (ex to exit).").trim().toLowerCase();
            if(userInput.startsWith("hel")){
                Menus.displayHelp();//Displays the help menu.
            }else if(userInput.startsWith("sea")){ //Displays the room help window.
                if(currentRoom == 1){
                    System.out.println(guestBedroom.getRoomUniques());
                    guestBedroom.displayRoomHelp();
                }else if(currentRoom == 2){
                    System.out.println(hallway1.getRoomUniques());
                    hallway1.displayRoomHelp();
                }else if(currentRoom == 3){
                    System.out.println(masterBathroom.getRoomUniques());
                    masterBathroom.displayRoomHelp();
                }else if(currentRoom == 4){
                    System.out.println(kitchen1.getRoomUniques());
                    kitchen1.displayRoomHelp();
                }else if(currentRoom == 5){
                    System.out.println(hallway2.getRoomUniques());
                    hallway2.displayRoomHelp();
                }else if(currentRoom == 6){
                    System.out.println(hallway3.getRoomUniques());
                    hallway3.displayRoomHelp();
                }else if(currentRoom == 7){
                    System.out.println(masterBedroom.getRoomUniques());
                    masterBedroom.displayRoomHelp();
                }else if(currentRoom == 8){
                    System.out.println(kitchen2.getRoomUniques());
                    kitchen2.displayRoomHelp();
                }else if(currentRoom == 9){
                    System.out.println(bathroom2.getRoomUniques());
                    bathroom2.displayRoomHelp();
                }else if(currentRoom == 10){
                    System.out.println(hallway4.getRoomUniques());
                    hallway4.displayRoomHelp();
                }
            }else if (userInput.startsWith("scr")){ //Random command to scream.
                System.out.println("You scream in fear!");

            }else if (userInput.startsWith("health")){ //Shows health for user.
                System.out.println("Hp: " + player.getHp() + "/100");
            }else if(userInput.startsWith("ex")){ //Saves and closes the game.
                ProjectFileIO_v2.writeFile();
                System.exit(0);
            }else if(userInput.startsWith("walk t") || userInput.startsWith("move ")){ //Based on whichever room the user is in, will complete an action.
                if(currentRoom == 1){
                    guestBedroom.findKeyword(player, userInput);
                }else if(currentRoom == 2){
                    hallway1.findKeyword(player, userInput);
                }else if(currentRoom == 3){
                    masterBathroom.findKeyword(player, userInput);
                }else if(currentRoom == 4){
                    kitchen1.findKeyword(player, userInput);
                }else if(currentRoom == 5){
                    hallway2.findKeyword(player, userInput);
                }else if(currentRoom == 6){
                    hallway3.findKeyword(player, userInput);
                }else if(currentRoom == 7){
                    masterBedroom.findKeyword(player, userInput);
                }else if(currentRoom == 8){
                    kitchen2.findKeyword(player, userInput);
                }else if(currentRoom == 9){
                    bathroom2.findKeyword(player, userInput);
                }else if(currentRoom == 10){
                    hallway4.findKeyword(player, userInput);
                }
            }else{
                System.out.println("\nSorry the command " + userInput + " is not recognized.");
            }

            //Sends a message to the user if they have entered a new room.
            int newLocation = player.getLocation();
            if(newLocation == 2 && newLocation != currentRoom){
                if (hallway1Visited == 1){
                System.err.println("\nYou entered the hallway on the south side of the building.");
                }else if(hallway1Visited == 0){
                    System.err.println("\nYou enter a hallway on the south side of the building, you see a window leading");
                    System.err.println("to the center courtyard, a lamp in the corner of the room, and doors to the east,");
                    System.err.println("and the west.");
                    System.err.println("\nYou here the door behind you lock..");
                    hallway1Visited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 3 && newLocation != currentRoom){
                if(masterBathroomVisited == 1){
                    System.err.println("\nYou entered the master bathroom.");
                }else if(masterBathroomVisited == 0){
                    System.err.println("\nYou enter the master bathroom, you see a useable shower and toilet,");
                    System.err.println("a sink that looks like it was being re-done, and that's about it");
                    System.err.println("in here..");
                    masterBathroomVisited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 4 && newLocation != currentRoom){
                if(kitchen1Visited == 1){
                    System.err.println("\nYou entered the kitchen on the south-east side of the building.");
                }else if(kitchen1Visited == 0){
                    System.err.println("\nYou enter the kitchen on the south-east side of the building.");
                    System.err.println("You see a working fridge and a knife on the counter..");
                    System.err.println("Nothing else seems of interest in here..");
                    kitchen1Visited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 5 && newLocation != currentRoom){
                if(hallway2Visited == 1){
                    System.err.println("\nYou entered the hallway on the west side of the building.");
                }else if(hallway2Visited == 0){
                    System.err.println("\nYou entered the hallway on the west side of the building..");
                    System.err.println("You see another window, an unidentifiable statue in the corner of the room,");
                    System.err.println("and there is a closet as well.");
                    hallway2Visited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 6 && newLocation != currentRoom){
                if(hallway3Visited == 1){
                System.err.println("\nYou entered the hallway on the east side of the building.");
                }else if(hallway3Visited == 0){
                    System.err.println("\nYou entered the hallway on the west side of the building.");
                    System.err.println("There is another window, a statue in the corner of the room,");
                    System.err.println("and what seems to be a panel that leads to the attic.");
                    hallway3Visited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 7 && newLocation != currentRoom){
                if(masterBedroomVisited == 1){
                    System.err.println("\nYou re-entered the master bedroom.");
                }else if(masterBedroomVisited == 0){
                    System.err.println("\nYou enter the master bedroom, which seems to be in the center of the building.");
                    System.err.println("You see doors to the east and west, a mirror, a bed, and a dresser.");
                    masterBedroomVisited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 8 && newLocation != currentRoom){
                if(kitchen2Visited == 1){
                    System.err.println("\nYou re-entered the kitchen on the north-west side of the building.");
                }else if(kitchen2Visited == 0){
                    System.err.println("\nYou enter a second kitchen on the north west side of the building..");
                    System.err.println("You see doors to the east and the south, a fridge, a sink that might work,");
                    System.err.println("And a chair just out on its own..");
                    kitchen2Visited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 9 && newLocation != currentRoom){
                if(bathroomVisited == 1){
                    System.err.println("\nYou re-entered the bathroom on the north-east side of the building.");
                }else if(bathroomVisited == 0){
                    System.err.println("\nYou enter a smaller bathroom on the north-east side of the building..");
                    System.err.println("You see doors to the south and the west, a toilet with a plunger, a sink,");
                    System.err.println("And a mirror above it.");
                    bathroomVisited++;
                    player.addToPoints(5);
                }
            }else if(newLocation == 10 && newLocation != currentRoom){
                if(hallway4Visited == 1){
                    System.err.println("\nYou re-renter the hallway on the north side of the building.");
                }else if(hallway4Visited == 0){
                    System.err.println("\nYou enter the hallway on the north side of the building..");
                    System.err.println("It has 3 doors, one to the north, one to the east, and one to the west.");
                    System.err.println("Other than that all you see is a statue.");
                    hallway4Visited++;
                    player.addToPoints(5);
                }
            }
          }

    
    }



    /******************* Start: Create or get user information *********************/
    
    public static void readyPlayer() throws IOException {
        ProjectFileIO_v2.readFile();
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

    public static boolean createUser() throws IOException {
        String username = getString("Create username");
        String password = getString("Create password");
        Player newPlayer = new Player(username,password, 0, 100, 0, 1);
        boolean isNewPlayer = ProjectFileIO_v2.addNewPlayer(newPlayer);
        if(isNewPlayer) {
            player = newPlayer;
            ProjectFileIO_v2.writeFile();
            //System.out.println("Welcome " + username + " to Brony Adventures!");
            return true;
        } else {
            System.out.println("There is already a user with that username");
            return false;
        }
    }

    public static boolean signinUser() throws IOException {
        String username = getString("Enter username");
        String password = getString("Enter password");
        Player isPlayerExist = ProjectFileIO_v2.getPlayer(username,password);
        if(isPlayerExist != null) {
            System.out.println("Welcome back " + username + "!");
            player = isPlayerExist;
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
        }
        catch (Exception e) {
           System.err.println("Error reading input from user. Ending program.");
           System.exit(-1);
        } 
        
        while (answer.replace(" ", "").equals("")) {
           System.err.println("Error: Missing input.");
           try {
              System.out.println(msg);
              answer = scanner.nextLine(); 
           }
           catch (Exception e) {
              System.err.println("Error reading input from user. Ending program.");
              System.exit(-1);
           } 
        }
        return answer;            
     }


}
