import java.util.*;

public class Bathroom extends Room{

    private int toilet;
    private int shower;
    private ArrayList<String> bathroomKeywords = new ArrayList<String>();


    Bathroom(){
        super();
        this.toilet = 0;
        this.shower = 0;
    }

    Bathroom(int roomID, String roomName, String roomDescription, int toilet, int shower){
        super(roomID, roomName, roomDescription);
        this.toilet = toilet;
        this.shower = shower;
    }

    public void setToilet(int toilet){
        this.toilet = toilet;
    }

    public void setShower(int shower){
        this.shower = shower;
    }

    public int getToilet(){
        return this.toilet;
    }

    public int getShower(){
        return this.shower;
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
            System.out.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("toilet") && this.toilet == 1){
            System.out.println("You decide to drop a nuke in the toilet, it becomes unuseable but you feel slightly refreshed.");
            setToilet(0);
            player.increaseHp(20);
        }else if(keyword.equalsIgnoreCase("toilet") && this.toilet == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("shower") && this.shower == 1){
            System.out.println("You take a nice hot shower, as you finish a demonic pony comes and rams you into the wall" +
            " causing you to lose half of your health.");
            setShower(0);
            player.decreaseHp(50);
        }else if(keyword.equalsIgnoreCase("shower") && this.shower == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n------" + this.getRoomName() + "------");
        for(int i = 1; i < this.bathroomKeywords.size() + 1; i++){
            System.out.println(i + ". " + this.bathroomKeywords.get(i));
        }
    }
}