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
            if(this.hallwayKeywords.get(i).equalsIgnoreCase(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("lamp") && this.lamp == 1 && this.getRoomID() == 2){
            System.out.println("It's a pony shaped lamp, you decide you're already deathly freightened of ponies and stay away from it.");
            setLamp(0);
            this.hallwayKeywords.remove("Lamp");
            player.addToPoints(2);
        }else if(keyword.equalsIgnoreCase("lamp") && this.lamp == 0 && this.getRoomID() == 2){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("window") && this.window == 1 && this.getRoomID() == 2){
            System.out.println("You see a window and decide to make a jump for it, as you're jumping out you get " +
            " tripped up on your foot, you fall two stories onto your head and die.");
            player.addToPoints(2);
            Menus.displayGameOver(player);
            setWindow(0);
            player.setHp(0);
            this.hallwayKeywords.remove("Window");
        }else if(keyword.equalsIgnoreCase("window") && this.window == 0 && this.getRoomID() == 2){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("north") || keyword.equalsIgnoreCase("south") || keyword.equalsIgnoreCase("east") || 
        keyword.equalsIgnoreCase("west")){
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