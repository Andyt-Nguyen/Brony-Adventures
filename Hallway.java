import java.io.Serializable;
import java.util.*;

public class Hallway extends Room implements Serializable {

    private int lamp;
    private int window;
    private int closet;
    private int atticPanel;
    private int statue;
    private boolean lampOn;
    private boolean windowOpen;
    private boolean panelOpen;
    private boolean closetOpen;
    private List<String> hallwayKeywords = new ArrayList<String>();
    private static String[] lampActions = {"examine" , "punch", "turn on", "turn off"};
    private static String[] windowActions = {"examine" , "open", "close", "jump out", "look out"};
    private static String[] closetActions = {"examine" , "open", "close", "listen", "knock", "look inside"};
    private static String[] panelActions = {"examine" , "open", "close", "look inside"};
    private static String[] statueActions = {"examine" , "punch", "kick", "imitate", "smell"};

    Hallway(){
        super();
        this.lamp = 0;
        this.window = 0;
        this.closet = 0;
        this.atticPanel = 0;
        this.statue = 0;
    }

    Hallway(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor,
    int lamp, int window, int closet, int atticPanel, int statue){
        super(roomID, roomName, roomDescription, roomUniques, northDoor, southDoor, eastDoor, westDoor);
        this.lamp = lamp;
        this.window = window;
        this.closet = closet;
        this.atticPanel = atticPanel;
        this.statue = statue;
        this.lampOn = false;
        this.windowOpen = false;
        this.panelOpen = false;
        this.closetOpen = false;
    }

    public void setLamp(int lamp){
        this.lamp = lamp;
    }

    public void setWindow(int window){
        this.window = window;
    }

    public void setCloset(int closet){
        this.closet = closet;
    }

    public void setAtticPanel(int atticPanel){
        this.atticPanel = atticPanel;
    }

    public void setStatue(int statue){
        this.statue = statue;
    }

    public int getCloset(){
        return this.closet;
    }

    public int getAtticPanel(){
        return this.atticPanel;
    }

    public int getStatue(){
        return this.statue;
    }

    public int getLamp(){
        return this.lamp;
    }
    
    public int getWindow(){
        return this.window;
    }

    public void addKeyword(String keyword){
        this.hallwayKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.hallwayKeywords.size();i++){
            if(this.hallwayKeywords.get(i).toLowerCase().startsWith(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        boolean userChoice;
        boolean center = false;
        String userAction;
        if(keyword.startsWith("walk to l") && this.lamp == 1){
            userAction = IR5.getString("\nYou walk up to the lamp. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayLampActions();
                    found = true;
                }
                for(int i = 0; i < lampActions.length; i++){
                    if(userAction.equals(lampActions[i])){
                        if(lampActions[i].equals("examine")){
                            System.out.println("\nIt's a pony shaped lamp!");
                            found = true;
                        }else if(lampActions[i].equals("punch")){
                            System.out.println("\nYou're scared to brake something that seems somewhat valuable.");
                            System.out.println("You wouldn't want any bad karma coming your way!");
                            found = true;
                        }else if(lampActions[i].equals("turn on")){
                            if(this.lampOn){
                                System.out.println("\nThis lamp is already turned on!");
                                found = true;
                            }else if(!this.lampOn){
                                System.out.println("\nYou turn on the lamp!");
                                found = true;
                                this.lampOn = true;
                            }
                        }else if(lampActions[i].equals("turn off")){
                            if(this.lampOn){
                                System.out.println("\nYou turn the lamp off!");
                                this.lampOn = false;
                                found = true;
                            }else if(!this.lampOn){
                                System.out.println("\nThis lamp is already off!");
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
        if(keyword.startsWith("walk to w") && this.window == 1 && this.getRoomID() == 2){
            userAction = IR5.getString("\nYou walk up to the window. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayWindowActions();
                    found = true;
                }
                for(int i = 0; i < windowActions.length; i++){
                    if(userAction.equals(windowActions[i])){
                        if(windowActions[i].equals("examine")){
                            System.out.println("\nIt's a window on the north side of the room. It looks like it could be opened or closed.");
                            found = true;
                        }else if(windowActions[i].equals("open")){
                            if(windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou open the window!");
                                windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(windowOpen){
                                System.out.println("\nYou shut the window.");
                                windowOpen = false;
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou close the window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(windowOpen){
                                System.out.println("\nYou decide to jump out the window..");
                                System.out.println("As you are jumping out, your foot gets tripped up on the window ceil.");
                                System.out.println("You fall two stories landing on your head causing instant death...");
                                Menus.displayGameOver(player);
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You see nothing in the distance.");
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou can't see much through this closed window.");
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
        if(keyword.startsWith("walk to wi") && this.window == 1 && this.getRoomID() == 5){
            System.out.println("\nYou see window..");
            userChoice = IR5.getYorN("\nWould you like to try to escape through the window?(Y/N)");
            if(userChoice){
                System.out.println("\nYou open up the window, you climb out and as you're hanging onto the ledge you notice");
                System.out.println("that this window is hanging right over a pen of those demonic ponies!");
                System.out.println("You decide to climb back up but this process has weakened you..");
                player.decreaseHp(5);
                player.addToPoints(2);
                setWindow(0);
            }else{
                System.out.println("\nYou decide to not try and escape from the window. You take a step back from it.");
            }
        }
        if(keyword.startsWith("walk to st") && this.statue == 1 && this.getRoomID() == 5){
            System.out.println("You notice a statue in the corner of the room..");
            userChoice = IR5.getYorN("It's kind of hard to see from here would you like to get closer?(Y/N)");
            if(userChoice){
                System.out.println("\nYou inch closer towards the statue..");
                System.out.println("As you get closer the statue becomes more and more familiar..");
                System.out.println("......");
                System.out.println("You realize that it's a statue of you!");
                System.out.println("Oddly, this doesn't freak you out and instead you gain some confidence.");
                System.out.println("Seeing something so beautiful makes you feel stronger.");
                player.increaseHp(10);
                player.addToPoints(2);
                setStatue(0);
            }else{
                System.out.println("You decide not to get closer to the statue, it remains in the dark unknown..");
            }
        }
        if(keyword.startsWith("move")){
            moveRoom(player, keyword);
        }
    }
    

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.hallwayKeywords.size() + 1; i++){
            System.out.println("- " + this.hallwayKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

    public void displayLampActions(){
        System.out.println("\nHere are some commands you can use on the lamp!");
        System.out.println("**********************************************************");
        for(int i = 0; i < lampActions.length; i++){
            System.out.println("- " + lampActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayWindowActions(){
        System.out.println("\nHere are some commands you can use on the window!");
        System.out.println("**********************************************************");
        for(int i = 0; i < windowActions.length; i++){
            System.out.println("- " + windowActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayPanelActions(){
        System.out.println("\nHere are some commands you can use on the attic panel!");
        System.out.println("**********************************************************");
        for(int i = 0; i < panelActions.length; i++){
            System.out.println("- " + panelActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayClosetActions(){
        System.out.println("\nHere are some commands you can use on the closet!");
        System.out.println("**********************************************************");
        for(int i = 0; i < closetActions.length; i++){
            System.out.println("- " + closetActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayStatueActions(){
        System.out.println("\nHere are some commands you can use on the statue!");
        System.out.println("**********************************************************");
        for(int i = 0; i < statueActions.length; i++){
            System.out.println("- " + statueActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }
}