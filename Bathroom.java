import java.util.*;
import java.io.Serializable;
public class Bathroom extends Room implements Serializable {

    private int toilet;
    private int shower;
    private int mirror;
    private int cabinets;
    private int plunger;
    private int sink;
    private Boolean seatUp;
    private Boolean curtainsClosed;
    private Boolean mirrorBroken;
    private Boolean cabinetsOpen;
    private Boolean isRunning;
    private ArrayList<String> bathroomKeywords = new ArrayList<String>();
    private static String[] toiletActions = {"examine" , "lift seat", "close seat", "flush", "take a piss", "take a dump"};
    private static String[] showerActions = {"examine" , "open curtain", "close curtain", "take shower"};
    private static String[] mirrorActions = {"examine" , "punch", "splash water", "admire self"};
    private static String[] cabinetsActions = {"examine" , "open", "close", "kick"};
    private static String[] plungerActions = {"examine" , "smell", "plung toilet"};
    private static String[] sinkActions = {"examine", "turn on", "turn off", "wash hands"};


    Bathroom(){
        super();
        this.toilet = 0;
        this.shower = 0;
        this.mirror = 0;
        this.cabinets = 0;
        this.plunger = 0;
    }

    Bathroom(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor,
    int toilet, int shower, int mirror, int cabinets, int plunger, int sink){
        super(roomID, roomName, roomDescription, roomUniques, northDoor, southDoor, eastDoor, westDoor);
        this.toilet = toilet;
        this.shower = shower;
        this.mirror = mirror;
        this.cabinets = cabinets;
        this.plunger = plunger;
        this.sink = sink;
        this.seatUp = false;
        this.curtainsClosed = true;
        this.mirrorBroken = false;
        this.cabinetsOpen = false;
        this.isRunning = false;
    }

    public void setToilet(int toilet){
        this.toilet = toilet;
    }

    public void setShower(int shower){
        this.shower = shower;
    }

    public void setMirror(int mirror){
        this.mirror = mirror;
    }

    public void setCabinets(int cabinets){
        this.cabinets = cabinets;
    }

    public void setPlunger(int plunger){
        this.plunger = plunger;
    }

    public int getToilet(){
        return this.toilet;
    }

    public int getShower(){
        return this.shower;
    }

    public int getMirror(){
        return this.mirror;
    }

    public int getCabinets(){
        return this.cabinets;
    }

    public int getPlunger(){
        return this.plunger;
    }

    public void addKeyword(String keyword){
        this.bathroomKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.bathroomKeywords.size();i++){
            if(this.bathroomKeywords.get(i).toLowerCase().startsWith(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("\nThe game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        boolean userChoice;
        boolean center = false;
        String userAction;
        //"examine" , "open curtain", "close curtain", "take shower"
        if(keyword.startsWith("walk to sh") && this.shower == 1){
            userAction = IR5.getString("\nYou walk up to the shower. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayShowerActions();
                    found = true;
                }
                for(int i = 0; i < showerActions.length; i++){
                    if(userAction.equals(showerActions[i])){
                        if(showerActions[i].equals("examine")){
                            System.out.println("\nIt's a very nice shower! What else would you expect in the master bathroom.");
                            found = true;
                        }else if(showerActions[i].equals("open curtain")){
                            if(curtainsClosed){
                                System.out.println("\nYou open up the shower curtains.");
                                curtainsClosed = false;
                                found = true;
                            }else if(!curtainsClosed){
                                System.out.println("\nThe curtains are already open!");
                                found = true;
                            }
                        }else if(showerActions[i].equals("close curtain")){
                            if(curtainsClosed){
                                System.out.println("\nThe curtains are already closed..");
                                found = true;
                            }else if(!curtainsClosed){
                                System.out.println("\nThe close the shower curtains!");
                                curtainsClosed = true;
                                found = true;
                            }
                        }else if(showerActions[i].equals("take shower")){
                            if(curtainsClosed){
                                System.out.println("\nYou need to open the curtains before you get in the shower!");
                                found = true;
                            }else if(!curtainsClosed){
                                System.out.println("\nYou have decided that it is a good time to take a shower.");
                                System.out.println("You take off your clothes and turn on the hot water, it feels really nice.");
                                System.out.println("After you feel squeaky clean, you hop out and put your clothes back on.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
                
            }
            System.out.println("\nYou return to the center of the room.");
        }
        if(keyword.startsWith("walk to sh") && this.shower == 1 && this.getRoomID() == 3){
            userChoice = IR5.getYorN("\nThere seems to be a working shower in here, would you like to take one?(Y/N)");
            if(userChoice){
                System.out.println("\nYou take a nice hot shower, as you finish a demonic pony comes and rams you into the wall" +
                " causing you to lose half of your health.");
                setShower(0);
                player.decreaseHp(player.getHp()/2);
                player.addToPoints(2);
            }else{
                System.out.println("\nYou decide you like being a dirty human being.");
            }
        }
        if(keyword.startsWith("move ")){
            moveRoom(player, keyword);
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.bathroomKeywords.size() + 1; i++){
            System.out.println("- " + this.bathroomKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

    public void displayToiletActions(){
        System.out.println("\nHere are some commands you can use on the toilet!");
        System.out.println("**********************************************************");
        for(int i = 0; i < toiletActions.length; i++){
            System.out.println("- " + toiletActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayShowerActions(){
        System.out.println("\nHere are some commands you can use on the shower!");
        System.out.println("**********************************************************");
        for(int i = 0; i < showerActions.length; i++){
            System.out.println("- " + showerActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayMirrorActions(){
        System.out.println("\nHere are some commands you can use on the mirror!");
        System.out.println("**********************************************************");
        for(int i = 0; i < mirrorActions.length; i++){
            System.out.println("- " + mirrorActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayCabinetsActions(){
        System.out.println("\nHere are some commands you can use on the cabinets!");
        System.out.println("**********************************************************");
        for(int i = 0; i < cabinetsActions.length; i++){
            System.out.println("- " + cabinetsActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayPlungerActions(){
        System.out.println("\nHere are some commands you can use on the plunger!");
        System.out.println("**********************************************************");
        for(int i = 0; i < plungerActions.length; i++){
            System.out.println("- " + plungerActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displaySinkActions(){
        System.out.println("\nHere are some commands you can use on the sink!");
        System.out.println("**********************************************************");
        for(int i = 0; i < sinkActions.length; i++){
            System.out.println("- " + sinkActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

}