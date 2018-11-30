import java.util.*;

public class Bathroom extends Room{

    private int toilet;
    private int shower;
    private int mirror;
    private int cabinets;
    private int plunger;
    private ArrayList<String> bathroomKeywords = new ArrayList<String>();


    Bathroom(){
        super();
        this.toilet = 0;
        this.shower = 0;
        this.mirror = 0;
        this.cabinets = 0;
        this.plunger = 0;
    }

    Bathroom(int roomID, String roomName, String roomDescription, String roomUniques, int door1, int door2, int door3, int door4,
    int toilet, int shower, int mirror, int cabinets, int plunger){
        super(roomID, roomName, roomDescription, roomUniques, door1, door2, door3, door4);
        this.toilet = toilet;
        this.shower = shower;
        this.mirror = mirror;
        this.cabinets = cabinets;
        this.plunger = plunger;
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
            if(this.bathroomKeywords.get(i).equalsIgnoreCase(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("\nThe game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("toilet") && this.toilet == 1 && this.getRoomID() == 3){
            System.out.println("\nYou decide to drop a nuke in the toilet, it becomes unuseable but you feel slightly refreshed.");
            System.out.println("Your health has recovered slightly.");
            setToilet(0);
            player.increaseHp(20);
            this.bathroomKeywords.remove("Toilet");
            player.addToPoints(2);
        }else if(keyword.equalsIgnoreCase("toilet") && this.toilet == 0 && this.getRoomID() == 3){
            System.err.println("\nSorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("shower") && this.shower == 1 && this.getRoomID() == 3){
            System.out.println("\nYou take a nice hot shower, as you finish a demonic pony comes and rams you into the wall" +
            " causing you to lose half of your health.");
            setShower(0);
            player.decreaseHp(player.getHp()/2);
            this.bathroomKeywords.remove("Shower");
            player.addToPoints(2);
        }else if(keyword.equalsIgnoreCase("shower") && this.shower == 0 && this.getRoomID() == 3){
            System.err.println("\nSorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("north") && this.getDoor1() != 0){
            player.setLocation(this.getDoor1());
        }else if(keyword.equalsIgnoreCase("north") && this.getDoor1() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.equalsIgnoreCase("south") && this.getDoor2() != 0){
            player.setLocation(this.getDoor2());
        }else if(keyword.equalsIgnoreCase("south") && this.getDoor2() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.equalsIgnoreCase("east") && this.getDoor3() != 0){
            player.setLocation(this.getDoor3());
        }else if(keyword.equalsIgnoreCase("east") && this.getDoor3() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.equalsIgnoreCase("west") && this.getDoor4() != 0){
            player.setLocation(this.getDoor4());
        }else if(keyword.equalsIgnoreCase("west") && this.getDoor4() == 0){
            System.err.println("\nYou cannot go this direction.");
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
}