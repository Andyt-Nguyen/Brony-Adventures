import java.io.*;
import java.util.*;

class BronyAdventures {
    public static Player player = new Player();
    public final static Scanner scanner = new Scanner(System.in);
    public ArrayList<Room> roomList = new ArrayList<Room>();
    public static void main(String args []) throws IOException {
        
        String userInput = "";
        readyPlayer();
        /**
         * To access the player object just use variable "player"
         * player.getHighScore(); player.getFood(); etc...
         */

        Bedroom guestBedroom = new Bedroom(1, "Guest Bedroom", "This is the guest bedroom at the back of the house.",
          1 , 1);
        guestBedroom.addKeyword("Dresser");
        guestBedroom.addKeyword("Bed");
        player.setHp(100);
        while(!userInput.equalsIgnoreCase("ex")){
            userInput = IR5.getString("\nEnter a single word associated with what you would like to do (ex to exit).");
            if(userInput.equalsIgnoreCase("help")){
                guestBedroom.displayRoomHelp();
            }else if(userInput.equalsIgnoreCase("ex")){
                System.exit(0);
            }else{
                guestBedroom.findKeyword(player, userInput);
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
