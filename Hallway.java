public class Hallway extends Room{

    private int lamp;
    private int window;

    Hallway(){
        super();
        this.lamp = 0;
        this.window = 0;
    }

    Hallway(int roomID, String roomName, String roomDescription, String defaultMsg1, String defaultMsg2, int lamp, int window){
        super(roomID, roomName, roomDescription, defaultMsg1, defaultMsg2);
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

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("lamp") && this.lamp == 1){
            System.out.println("It's a pony shaped lamp, you decide you're already deathly freightened of ponies and stay away from it.");
            setLamp(0);
        }else if(keyword.equalsIgnoreCase("lamp") && this.lamp == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("window") && this.window == 1){
            System.out.println("You see a window and decide to make a jump for it, as you're jumping out you get " +
            " tripped up on your foot, you fall two stories onto your head and die.");
            Menus.displayGameOver(player);
            setWindow(0);
            player.setHp(0);
        }else if(keyword.equalsIgnoreCase("window") && this.window== 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
    }
}