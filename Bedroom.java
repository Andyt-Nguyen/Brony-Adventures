import java.io.Serializable;
import java.util.*;

public class Bedroom extends Room implements Serializable {

    private int dresser;
    private int diary;
    private int bed;
    private int gun;
    private int key;
    private int mirror;
    private boolean isOpen;
    private boolean isMade;
    private List<String> bedroomKeywords = new ArrayList<String>();
    private static String dresserActions[] = {"examine", "open","close","kick","sit on","smell"};
    private static String bedActions[] = {"examine", "lay on", "look under", "make sheets", "smell"};
    private static String mirrorActions[] = {"examine", "kick", "punch", "look in"};

    Bedroom(){
        super();
        this.dresser = 0;
        this.gun = 0;
        this.bed = 0;
        this.diary = 0;
        this.key = 0;
        this.mirror = 0;
    }

    Bedroom(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor,
    int dresser, int bed, int key, int mirror){
        super(roomID, roomName, roomDescription, roomUniques, northDoor, southDoor, eastDoor, westDoor);
        this.dresser = dresser;
        this.bed = bed;
        this.key = key;
        this.mirror = mirror;
        this.isOpen = false;
        this.isMade = false;
        if(this.dresser > 0){
            this.diary = 1;
        }else{
            this.diary = 0;
        }
        if(this.bed > 0){
            this.gun = 1;
        }else{
            this.gun = 0;
        }
    }

    public void setDresser(int dresser){
        this.dresser = dresser;
    }

    public void setBed(int bed){
        this.bed = bed;
    }

    public void setKey(int key){
        this.key = key;
    }

    public void setMirror(int mirror){
        this.mirror = mirror;
    }

    public int getDresser(){
        return this.dresser;
    }

    public int getBed(){
        return this.bed;
    }

    public int getKey(){
        return this.key;
    }

    public int getMirror(){
        return this.mirror;
    }

    public void addKeyword(String keyword){
        this.bedroomKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.bedroomKeywords.size();i++){
            if(this.bedroomKeywords.get(i).toLowerCase().startsWith(keyword)){
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
        String userAction;
        boolean center = false;
        if(keyword.startsWith("walk to d") && this.dresser == 1 && this.getRoomID() == 1){
            userAction = IR5.getString("\nYou walk up to the dresser.Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayDresserActions();
                }
                for(int i = 0; i < dresserActions.length; i++){
                    if(userAction.equals(dresserActions[i])){
                        if(dresserActions[i].equals("open") && !this.isOpen){
                            this.isOpen = true;
                            userChoice = IR5.getYorN("\nYou open the dresser and find a diary, would you like to read it.?");
                            if(userChoice){
                                readDiary();
                                found = true;
                            }else{
                                System.out.println("\nYou choose to not read the diary.");
                                found = true;
                            }
                        }else if(dresserActions[i].equals("open") && this.isOpen){
                            System.out.println("\nThis dresser is already open.");
                            found = true;
                        }else if(dresserActions[i].equals("close")){
                            if(this.isOpen){
                                System.out.println("\nYou close the dresser.");
                                this.isOpen = false;
                                found = true;
                            }else{
                                System.out.println("\nThis dresser is already closed.");
                                found = true;
                            }
                        }else if(dresserActions[i].equals("kick")){
                            System.out.println("\nYou decide to kick the wooden dresser..");
                            System.out.println("That didn't feel too good, you take some damage.");
                            player.decreaseHp(5);
                            found = true;
                        }else if(dresserActions[i].equals("sit on")){
                            System.out.println("\nYou sit on the dresser.");
                            System.out.println("This seems like a good way of doing nothing.");
                            found = true;
                        }else if(dresserActions[i].equals("smell")){
                            System.out.println("\nThis dresser smells like wood!");
                            found = true;
                        }else if(dresserActions[i].equals("examine")){
                            System.out.println("\nIt's a wooden dresser, there isn't much to it..");
                            found = true;
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
        if(keyword.startsWith("walk to d") && this.dresser == 1 && this.getRoomID() == 7){
            System.err.println("You take the key off the dresser!");
            player.setKey(1);
            player.addToPoints(2);
        }
        if(keyword.startsWith("walk to b") && this.bed == 1 && this.getRoomID() == 1){
            userAction = IR5.getString("\nYou walk up to the bed.Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayBedActions();
                    found = true;
                }
                for(int i = 0; i < bedActions.length; i++){
                    if(userAction.equals(bedActions[i])){
                        if(bedActions[i].equals("lay on")){
                            if(!this.isMade){
                                System.out.println("\nYou lay on the bed.");
                                System.out.println("It's not very comfy, whoever owns this house doesn't seem to take care of their guests!");
                                System.out.println("You stand back up.");
                                found = true;
                            }else if(this.isMade){
                                System.out.println("\nWhy would you want to lay on this nicely made bed?");
                                found = true;
                            }
                        }else if(bedActions[i].equals("look under")){
                            System.out.println("\nYou stoop down to your knees and look under the bed..");
                            System.out.println("You find nothing of interest.");
                            found = true;
                        }else if(bedActions[i].equals("make sheets")){
                            if(!this.isMade){
                                System.out.println("\nYou neatly make the sheets.");
                                this.isMade = true;
                                found = true;
                            }else{
                                System.out.println("\nThis bed is already made.");
                                found = true;
                            }
                        }else if(bedActions[i].equals("smell")){
                            System.out.println("\nYou smell the bed..");
                            System.out.println("It smells like whoever slept in this had some bad gas..");
                            System.out.println("Oh wait, it was you!");
                            found = true;
                        }else if(bedActions[i].equals("examine")){
                            if(this.isMade){
                                System.out.println("\nIt's a nicely made twin size bed!");
                                found = true;
                            }else if(!this.isMade){
                                System.out.println("\nIt's a twin size bed that is unmade..");
                                System.out.println("It looks like whoever slept here doesn't know how to make their bed!");
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
        if(keyword.startsWith("move")){
            moveRoom(player, keyword);
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.bedroomKeywords.size() + 1; i++){
            System.out.println("- " + this.bedroomKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

    public void readDiary(){
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("- It's night number 3 in this odd house..                            -");
        System.out.println("- I keep hearing noises outside my room in the middle of the night.. -");
        System.out.println("- It kind of sounds like a stampede running through the hallway..    -");
        System.out.println("- I am way too scared to check it out..                              -");
        System.out.println("- Hopefully it's just my imagination..                               -");
        System.out.println("----------------------------------------------------------------------");
    }

    public void displayDresserActions(){
        System.out.println("\nHere are some commands you can use on the dresser!");
        System.out.println("**********************************************************");
        for(int i = 0; i < dresserActions.length; i++){
            System.out.println("- " + dresserActions[i]);
        }
        System.out.println("- Center");
        System.out.println("**********************************************************");
    }

    public void displayBedActions(){
        System.out.println("\nHere are some commands you can use on the bed!");
        System.out.println("**********************************************************");
        for(int i = 0; i < bedActions.length; i++){
            System.out.println("- " + bedActions[i]);
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

    

}