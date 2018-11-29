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

        Bedroom guestBedroom = new Bedroom(1, "Guest Bedroom", "This is the guest bedroom at the back of the house.",
        "\nYou see a door to the north, a dresser and a bed in this room.", 2, 0,
          1 , 1);
        Hallway hallway1 = new Hallway(2, "Hallway", "This is the hallway right outside the guest bedroom.",
        "\nYou see a door to your east and to the west, a lamp, and a window.", 3, 4, 1, 1);
        Bathroom bathroom1 = new Bathroom(3, "Master Bathroom", "This is the bathroom closest to the master bedroom.",
        "\nYou see a door to the east and to the north, you also notice a working shower and toiet.", 2, 5, 1, 1);
        Kitchen kitchen1 = new Kitchen(4, "Kitchen 1", "This is the kitchen at the back right corner of the house.",
        "\nYou see a door to your west and to the north, you also notice a knife and a running fridge.", 2, 6, 1, 1);

        guestBedroom.addKeyword("Dresser");
        guestBedroom.addKeyword("Bed");
        guestBedroom.addKeyword("Up");

        hallway1.addKeyword("Lamp");
        hallway1.addKeyword("Window");
        hallway1.addKeyword("Left");
        hallway1.addKeyword("Right");

        bathroom1.addKeyword("Toilet");
        bathroom1.addKeyword("Shower");
        bathroom1.addKeyword("Right");
        bathroom1.addKeyword("Up");

        kitchen1.addKeyword("Fridge");
        kitchen1.addKeyword("Knife");
        kitchen1.addKeyword("Left");
        kitchen1.addKeyword("Up");



        roomList.add(guestBedroom);
        player.setLocation(1);
        player.setHp(100);

        while(!userInput.equalsIgnoreCase("ex")){
            int currentRoom = player.getLocation();
            userInput = IR5.getString("\nEnter a single word associated with what you would like to do (ex to exit).");
            if(userInput.equalsIgnoreCase("help")){
                Menus.displayHelp();
            }else if(userInput.equalsIgnoreCase("search")){
                if(currentRoom == 1){
                    System.out.println(guestBedroom.getRoomUniques());
                    guestBedroom.displayRoomHelp();
                }else if(currentRoom == 2){
                    System.out.println(hallway1.getRoomUniques());
                    hallway1.displayRoomHelp();
                }else if(currentRoom == 3){
                    System.out.println(bathroom1.getRoomUniques());
                    bathroom1.displayRoomHelp();
                }else if(currentRoom == 4){
                    System.out.println(kitchen1.getRoomUniques());
                    kitchen1.displayRoomHelp();
                }
            }else if (userInput.equalsIgnoreCase("scream")){
                System.out.println("You scream in fear!");

            }else if (userInput.equalsIgnoreCase("health")){
                System.out.println("Hp: " + player.getHp() + "/100");
            }else if(userInput.equalsIgnoreCase("ex")){
                System.exit(0);
            }else{
                if(currentRoom == 1){
                    guestBedroom.findKeyword(player, userInput);
                }else if(currentRoom == 2){
                    hallway1.findKeyword(player, userInput);
                }else if(currentRoom == 3){
                    bathroom1.findKeyword(player, userInput);
                }else if(currentRoom == 4){
                    kitchen1.findKeyword(player, userInput);
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
        Player newPlayer = new Player(username,password, 0, 0, 0, 0);
        boolean isNewPlayer = ProjectFileIO_v2.addNewPlayer(newPlayer);
        if(isNewPlayer) {
            player = newPlayer;
            ProjectFileIO_v2.writeFile();
            System.out.println("Welcome " + username + " to Brony Adventures!");
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
        System.out.println("* 1. Sign Up            *");
        System.out.println("* 2. Login              *");
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
