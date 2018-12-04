import java.util.*;

public class Bedroom extends Room{

    private int dresser;
    private int diary;
    private int bed;
    private int gun;
    private int key;
    private int mirror;
    private boolean isOpen;
    private boolean isMade;
    private List<String> bedroomKeywords = new ArrayList<String>();
    private static String dresserActions[] = {"open","close","kick","sit on","smell"};
    private static String bedActions[] = {"lay on", "look under", "make sheets", "smell"};
    private static String mirrorActions[] = {"kick", "punch", "look in"};

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
            }else if(userAction.equals("help")){
                displayDresserActions();
                userAction = IR5.getString("\nChoose Action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }
            while(!center){
                for(int i = 0; i < dresserActions.length; i++){
                    boolean found = false;
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
                        }
                    }else if(found = false){
                        System.out.println("\nYou cannot use this action here!");
                    }
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }
            System.out.println("You return to the center of the room.");
            //userChoice = IR5.getYorN("\nYou see a dresser would you like to open it?(Y/N)");
            //if(userChoice){
            //    System.out.println("\nYou open up the dresser and find a diary, you find a diary in it.");
            //    userChoice = IR5.getYorN("Would you like to read the diary?(Y/N)");
            //    if(userChoice){
            //        readDiary();
            //        player.addToPoints(2);
            //    }else{
            //        System.out.println("\nYou decide to not read the diary, you put it back in the dresser.");
            //    }
            //}else{
            //    System.out.println("\nYou take a step back away from the dresser.");
            //}
        }
        if(keyword.startsWith("walk to d") && this.dresser == 1 && this.getRoomID() == 7){
            System.err.println("You take the key off the dresser!");
            player.setKey(1);
            player.addToPoints(2);
        }
        if(keyword.startsWith("walk to b") && this.bed == 1 && this.getRoomID() == 1){
            userChoice = IR5.getYorN("\nWould you like to search under the bed you woke up on?(Y/N)");
            if(userChoice){
                System.out.println("\nYou search under the bed and find a gun. Turns out you don't know how to use it..");
                System.out.println("You accidentally fire the gun, it didn't hit you but you decide to put it back where it was.");
                System.out.println("Luckily something worse didn't happen!");
                player.addToPoints(2);
            }else{
                System.out.println("\nYou choose to not look under it.");
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
        System.out.println("**********************************************************");
    }

}