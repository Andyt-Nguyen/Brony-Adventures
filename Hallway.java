import java.util.*;

public class Hallway extends Room{

    private int lamp;
    private int window;
    private int closet;
    private int atticPanel;
    private int statue;
    private List<String> hallwayKeywords = new ArrayList<String>();

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
        if(keyword.startsWith("walk to la") && this.lamp == 1 && this.getRoomID() == 2){
            System.out.println("\nIt's a pony shaped lamp, you decide you're already deathly freightened of ponies and stay away from it.");
            setLamp(0);
            player.addToPoints(2);
        }
        if(keyword.startsWith("walk to wi") && this.window == 1 && this.getRoomID() == 2){
            System.out.println("\nYou see a window..");
            userChoice = IR5.getYorN("\nWould you like to try and escape from this window?");
            if(userChoice){
                System.out.println("\nYou decide to make a jump for it, as you're jumping out you get " +
                " tripped up on your foot, you fall two stories onto your head and die.");
                player.addToPoints(2);
                Menus.displayGameOver(player);
                setWindow(0);
                player.setHp(0);
            }else{
                System.out.println("\nYou decide not to attempt to escape.");
            }
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
}