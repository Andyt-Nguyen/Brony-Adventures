import java.util.*;

public class Kitchen extends Room{

    private int knife;
    private int fridge;
    private int food;
    private int sink;
    private int chair;
    
    private ArrayList<String> kitchenKeywords = new ArrayList<String>();
    Kitchen(){
        super();
        this.knife = 0;
        this.fridge = 0;
        this.food = 0;
        this.sink = 0;
        this.chair = 0;
    }
    
    
    Kitchen(int roomID, String roomName, String roomDescription, String roomUniques, int door1, int door2, int door3, int door4,
    int knife, int fridge, int sink, int chair){
        super(roomID, roomName, roomDescription, roomUniques, door1, door2, door3, door4);
        this.knife = knife;
        this.fridge = fridge;
        this.sink = sink;
        this.chair = chair;
        if(fridge > 0){
            this.food = 1;
        }else{
            this.food = 0;
        }
    }

    public void setKnife(int knife){
        this.knife = knife;
    }

    public void setFridge(int fridge){
        this.fridge = fridge;
    }

    public void setSink(int sink){
        this.sink = sink;
    }

    public void setChair(int chair){
        this.chair = chair;
    }

    public int getKnife(){
        return this.knife;
    }

    public int getFridge(){
        return this.fridge;
    }
    
    public int getSink(){
        return this.sink;
    }

    public int getChair(){
        return this.chair;
    }

    public void addKeyword(String keyword){
        this.kitchenKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.kitchenKeywords.size();i++){
            if(this.kitchenKeywords.get(i).equalsIgnoreCase(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("\nThe game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("knife") && this.knife == 1 && this.getRoomID() == 4){
            System.out.println("\nYou pick up the knife and accidentally dropped it on your toe and lose 10 hp, but don't worry it's still useable.");
            setKnife(0);
            player.setKnife(1);
            player.decreaseHp(10);
            player.addToPoints(2);
            this.kitchenKeywords.remove("Knife");
        }else if(keyword.equalsIgnoreCase("knife") && this.knife == 0 && this.getRoomID() == 4){
            System.err.println("\nSorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("fridge") && this.fridge == 1 && this.getRoomID() == 4){
            System.out.println("\nYou decide to look in the fridge, you find a half eaten apple and decide to eat it anyways.");
            System.out.println("You gained some health back..");
            setFridge(0);
            if(player.getHp() < 100){
                player.increaseHp(15);
            }
            this.kitchenKeywords.remove("Fridge");
            player.addToPoints(2);
        }else if(keyword.equalsIgnoreCase("fridge") && this.fridge == 0 && this.getRoomID() == 4){
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
        for(int i = 1; i < this.kitchenKeywords.size() + 1; i++){
            System.out.println("- " + this.kitchenKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

}