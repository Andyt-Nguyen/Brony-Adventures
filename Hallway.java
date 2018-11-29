import java.util.*;

public class Hallway extends Room{

    private int lamp;
    private int window;
    private List<String> hallwayKeywords = new ArrayList<String>();

    Hallway(){
        super();
        this.lamp = 0;
        this.window = 0;
    }

    Hallway(int roomID, String roomName, String roomDescription, int door1, int door2, int lamp, int window){
        super(roomID, roomName, roomDescription, door1, door2);
        this.lamp = lamp;
        this.window = window;
    }

    public void setLamp(int lamp){
        this.lamp = lamp;
    }

    public void setWindow(int window){
        this.window = window;
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
        if(keyword.equalsIgnoreCase("lamp") && this.lamp == 1){
            System.out.println("It's a pony shaped lamp, you decide you're already deathly freightened of ponies and stay away from it.");
            setLamp(0);
            this.hallwayKeywords.remove("Lamp");
        }else if(keyword.equalsIgnoreCase("lamp") && this.lamp == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("window") && this.window == 1){
            System.out.println("You see a window and decide to make a jump for it, as you're jumping out you get " +
            " tripped up on your foot, you fall two stories onto your head and die.");
            Menus.displayGameOver(player);
            setWindow(0);
            player.setHp(0);
            this.hallwayKeywords.remove("Window");
            System.exit(0);
        }else if(keyword.equalsIgnoreCase("window") && this.window== 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("left") && this.getRoomID() == 2){
            System.out.println("\nYou enter the master bathroom.");
            player.setLocation(this.getDoor1());
        }
        if(keyword.equalsIgnoreCase("right") && this.getRoomID() == 2){
            System.out.println("\nYou enter a kitchen.");
            player.setLocation(this.getDoor2());
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.hallwayKeywords.size() + 1; i++){
            System.out.println(i + ". " + this.hallwayKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }
}