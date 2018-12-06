
import java.io.*;

public class PlayBronyGame implements Serializable {
    Player player;
    Bedroom guestBedroom;
    Hallway hallway1;
    Bathroom masterBathroom;
    Kitchen kitchen1;
    Hallway hallway2;
    Hallway hallway3;
    Bedroom masterBedroom;
    Kitchen kitchen2;
    Bathroom bathroom2;
    Hallway hallway4;

    public PlayBronyGame() {
        this.player = new Player();
        this.guestBedroom = new Bedroom();
        this.hallway1 = new Hallway();
        this.masterBathroom = new Bathroom();
        this.kitchen1 = new Kitchen();
        this.hallway2 = new Hallway();
        this.hallway3 = new Hallway();
        this.masterBedroom = new Bedroom();
        this.kitchen2 = new Kitchen();
        this.bathroom2 = new Bathroom();
        this.hallway4 = new Hallway();
    }

    public PlayBronyGame(Player player, Bedroom guestBedroom, Hallway hallway1, Bathroom masterBathroom,
            Kitchen kitchen1, Hallway hallway2, Hallway hallway3, Bedroom masterBedroom, Kitchen kitchen2,
            Bathroom bathroom2, Hallway hallway4) {
        this.player = player;
        this.guestBedroom = guestBedroom;
        this.hallway1 = hallway1;
        this.masterBathroom = masterBathroom;
        this.kitchen1 = kitchen1;
        this.hallway2 = hallway2;
        this.hallway3 = hallway3;
        this.masterBedroom = masterBedroom;
        this.kitchen2 = kitchen2;
        this.bathroom2 = bathroom2;
        this.hallway4 = hallway4;
    }

    public void playGame() throws FileNotFoundException, IOException, ClassNotFoundException, InterruptedException {
        String userInput = "";


        // Basic while loop to get input from the user if they are alive.

        while (player.getHp() > 0) {
            int currentRoom = player.getLocation();
            userInput = IR5.getString("\nEnter a single word associated with what you would like to do (ex to exit).")
                    .trim().toLowerCase();
            if (userInput.startsWith("hel")) {
                Menus.displayHelp();// Displays the help menu.
            } else if (userInput.startsWith("sea")) { // Displays the room help window.
                if (currentRoom == 1) {
                    System.out.println(guestBedroom.getRoomUniques());
                    guestBedroom.displayRoomHelp();
                } else if (currentRoom == 2) {
                    System.out.println(hallway1.getRoomUniques());
                    hallway1.displayRoomHelp();
                } else if (currentRoom == 3) {
                    System.out.println(masterBathroom.getRoomUniques());
                    masterBathroom.displayRoomHelp();
                } else if (currentRoom == 4) {
                    System.out.println(kitchen1.getRoomUniques());
                    kitchen1.displayRoomHelp();
                } else if (currentRoom == 5) {
                    System.out.println(hallway2.getRoomUniques());
                    hallway2.displayRoomHelp();
                } else if (currentRoom == 6) {
                    System.out.println(hallway3.getRoomUniques());
                    hallway3.displayRoomHelp();
                } else if (currentRoom == 7) {
                    System.out.println(masterBedroom.getRoomUniques());
                    masterBedroom.displayRoomHelp();
                } else if (currentRoom == 8) {
                    System.out.println(kitchen2.getRoomUniques());
                    kitchen2.displayRoomHelp();
                } else if (currentRoom == 9) {
                    System.out.println(bathroom2.getRoomUniques());
                    bathroom2.displayRoomHelp();
                } else if (currentRoom == 10) {
                    System.out.println(hallway4.getRoomUniques());
                    hallway4.displayRoomHelp();
                }
            } else if (userInput.startsWith("scr")) { // Random command to scream.
                System.out.println("You scream in fear!");

            } else if (userInput.startsWith("health")) { // Shows health for user.
                System.out.println("Hp: " + player.getHp() + "/100");
            } else if (userInput.startsWith("ex")) { // Saves and closes the game.
                FileIo.writeFile();
                System.exit(0);
            } else if (userInput.startsWith("walk t") || userInput.startsWith("move ")) { // Based on whichever room the
                                                                                          // user is in, will complete
                                                                                          // an action.
                if (currentRoom == 1) {
                    guestBedroom.findKeyword(player, userInput);
                } else if (currentRoom == 2) {
                    hallway1.findKeyword(player, userInput);
                } else if (currentRoom == 3) {
                    masterBathroom.findKeyword(player, userInput);
                } else if (currentRoom == 4) {
                    kitchen1.findKeyword(player, userInput);
                } else if (currentRoom == 5) {
                    hallway2.findKeyword(player, userInput);
                } else if (currentRoom == 6) {
                    hallway3.findKeyword(player, userInput);
                } else if (currentRoom == 7) {
                    masterBedroom.findKeyword(player, userInput);
                } else if (currentRoom == 8) {
                    kitchen2.findKeyword(player, userInput);
                } else if (currentRoom == 9) {
                    bathroom2.findKeyword(player, userInput);
                } else if (currentRoom == 10) {
                    hallway4.findKeyword(player, userInput);
                }
            } else if (userInput.startsWith("eat fo")) {
                player.eatFood(player);
            }else if(userInput.startsWith("check f")){
                System.out.println("\nYou have " + player.getFood() + " pieces of food left.");
            }else if(userInput.startsWith("po")){
                System.out.println("\nYou currently have " + player.getHighScore() + " points..");
            }else if(userInput.startsWith("high")){
                Menus.sortHighscores();
            } else {
                System.out.println("\nSorry the command " + userInput + " is not recognized.");
            }

            // Sends a message to the user if they have entered a new room.
            int newLocation = player.getLocation();
            if (newLocation == 2 && newLocation != currentRoom) {
                if (hallway1.getHallwayVisited()) {
                    System.err.println("\nYou entered the hallway on the south side of the building.");
                } else if (!hallway1.getHallwayVisited()) {
                    System.err.println("\nYou enter a hallway on the south side of the building, you see a window leading");
                    System.err.println("to the center courtyard, a lamp in the corner of the room, and doors to the east,");
                    System.err.println("and the west.");
                    System.err.println("\nYou here the door behind you lock..");
                    hallway1.setHallwayVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 3 && newLocation != currentRoom) {
                if (masterBathroom.getBathroomVisited()) {
                    System.err.println("\nYou entered the master bathroom.");
                } else if (!masterBathroom.getBathroomVisited()) {
                    System.err.println("\nYou enter the master bathroom, you see luxurious shower, toilet,");
                    System.err.println("and sink. That's about it in here..");
                    masterBathroom.setBathroomVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 4 && newLocation != currentRoom) {
                if (kitchen1.getKitchenVisited()) {
                    System.err.println("\nYou entered the kitchen on the south-east side of the building.");
                } else if (!kitchen1.getKitchenVisited()) {
                    System.err.println("\nYou enter the kitchen on the south-east side of the building.");
                    System.err.println("You see a working fridge and a knife on the counter..");
                    System.err.println("Nothing else seems of interest in here..");
                    kitchen1.setKitchenVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 5 && newLocation != currentRoom) {
                if (hallway2.getHallwayVisited()) {
                    System.err.println("\nYou entered the hallway on the west side of the building.");
                } else if (!hallway2.getHallwayVisited()) {
                    System.err.println("\nYou entered the hallway on the west side of the building..");
                    System.err.println("You see another window, an unidentifiable statue in the corner of the room,");
                    System.err.println("and there is a closet as well.");
                    hallway2.setHallwayVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 6 && newLocation != currentRoom) {
                if (hallway3.getHallwayVisited()) {
                    System.err.println("\nYou entered the hallway on the east side of the building.");
                } else if (!hallway3.getHallwayVisited()) {
                    System.err.println("\nYou entered the hallway on the west side of the building.");
                    System.err.println("There is another window, a statue in the corner of the room,");
                    System.err.println("and what seems to be a panel that leads to the attic.");
                    hallway3.setHallwayVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 7 && newLocation != currentRoom) {
                if (masterBedroom.getBedroomVisited()) {
                    System.err.println("\nYou re-entered the master bedroom.");
                } else if (!masterBedroom.getBedroomVisited()) {
                    System.err.println("\nYou enter the master bedroom, which seems to be in the center of the building.");
                    System.err.println("You see doors to the east and west, a mirror, a bed, and a dresser.");
                    masterBedroom.setBedroomVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 8 && newLocation != currentRoom) {
                if (kitchen2.getKitchenVisited()) {
                    System.err.println("\nYou re-entered the kitchen on the north-west side of the building.");
                } else if (!kitchen2.getKitchenVisited()) {
                    System.err.println("\nYou enter a second kitchen on the north west side of the building..");
                    System.err.println("You see doors to the east and the south, a fridge, a sink that might work,");
                    System.err.println("And a chair just out on its own..");
                    kitchen2.setKitchenVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 9 && newLocation != currentRoom) {
                if (bathroom2.getBathroomVisited()) {
                    System.err.println("\nYou re-entered the bathroom on the north-east side of the building.");
                } else if (bathroom2.getBathroomVisited()) {
                    System.err.println("\nYou enter a smaller bathroom on the north-east side of the building..");
                    System.err.println("You see doors to the south and the west, a toilet with a plunger, a sink,");
                    System.err.println("And a mirror above it.");
                    bathroom2.setBathroomVisited(true);
                    player.addToPoints(5);
                }
            } else if (newLocation == 10 && newLocation != currentRoom) {
                if (hallway4.getHallwayVisited()) {
                    System.err.println("\nYou re-renter the hallway on the north side of the building.");
                } else if (hallway4.getHallwayVisited()) {
                    System.err.println("\nYou enter the hallway on the north side of the building..");
                    System.err.println("It has 3 doors, one to the north, one to the east, and one to the west.");
                    System.err.println("Other than that all you see is a statue.");
                    hallway4.setHallwayVisited(true);
                    player.addToPoints(5);
                }
            }
        }
    }

    public String getPlayerName() {
        return player.getUsername();
    }

    public String getPlayerPassword() {
        return player.getPassword();
    }

    public int getPlayerHighscore(){
        return player.getHighScore();
    }
}
