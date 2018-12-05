import java.io.Serializable;
import java.util.*;

public class Kitchen extends Room implements Serializable {

    private int knife;
    private int fridge;
    private int food;
    private int sink;
    private int chair;
    private boolean doorOpen;
    private boolean sinkOn;
    private boolean chairFlipped;
    private ArrayList<String> kitchenKeywords = new ArrayList<String>();
    private static String[] knifeActions = {"examine" , "pick up"};
    private static String[] fridgeActions = {"examine" , "open door", "close door", "take food", "kick"};
    private static String[] sinkActions = {"examine" , "turn on", "turn off", "wash dishes", "wash hands"};
    private static String[] chairActions = {"examine" , "sit on", "stand on", "flip", "set up"};


    Kitchen(){
        super();
        this.knife = 0;
        this.fridge = 0;
        this.food = 0;
        this.sink = 0;
        this.chair = 0;
    }
    
    
    Kitchen(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor,
    int knife, int fridge, int sink, int chair){
        super(roomID, roomName, roomDescription, roomUniques, northDoor, southDoor, eastDoor, westDoor);
        this.knife = knife;
        this.fridge = fridge;
        this.sink = sink;
        this.chair = chair;
        this.doorOpen = false;
        this.sinkOn = false;
        this.chairFlipped = false;
        if(fridge > 0){
            this.food = 5;
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

    public void decreaseFood(int food){
        this.food -= food;
    }

    public void addKeyword(String keyword){
        this.kitchenKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.kitchenKeywords.size();i++){
            if(this.kitchenKeywords.get(i).toLowerCase().startsWith(keyword)){
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
        boolean center = false;
        String userAction;
        if(keyword.startsWith("walk to k") && this.knife == 1){
            userAction = IR5.getString("\nYou walk up to the knife. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayKnifeActions();
                    found = true;
                }
                for(int i = 0; i < knifeActions.length; i++){
                    if(userAction.equals(knifeActions[i])){
                        if(knifeActions[i].equals("examine")){
                            System.out.println("\nIt's a super sharp knife, it could probably be used as a weapon.");
                            found = true;
                        }else if(knifeActions[i].equals("pick up")){
                            System.out.println("\nYou pick up the sharp knife and decide you want to carry it with you.");
                            this.knife = 0;
                            center = true;
                            player.setKnife(1);
                            found = true;
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                if(this.knife == 1){
                    userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                }
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to fr") && this.fridge == 1){
            userAction = IR5.getString("\nYou walk up to the knife. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayFridgeActions();
                    found = true;
                }
                //"examine" , "open door", "close door", "take food", "kick"};
                for(int i = 0; i < fridgeActions.length; i++){
                    if(userAction.equals(fridgeActions[i])){
                        if(fridgeActions[i].equals("examine")){
                            System.out.println("\nIt's a very basic, white fridge.");
                            found = true;
                        }else if(fridgeActions[i].equals("open door")){
                            if(doorOpen){
                                System.out.println("\nThe fridge door is already open!");
                                found = true;
                            }else if(!doorOpen){
                                System.out.println("\nYou open the fridge door..");
                                System.out.println("It looks like there is a bit of edible food in here!");
                                found = true;
                            }   
                        }else if(fridgeActions[i].equals("close door")){
                            if(doorOpen){
                                System.out.println("\nYou shut the fridge door.");
                                found = true;
                            }else if(!doorOpen){
                                System.out.println("\nThe fridge door is already shut..");
                                found = true;
                            }
                        }else if(fridgeActions[i].equals("take food")){
                            if(doorOpen && this.food > 0){
                                System.out.println("\nYou take the food, which happens to be tater tots..");
                                System.out.println("And you stash them in your pockets for later");
                                player.addFood(5);
                                found = true;
                            }else if(!doorOpen){
                                System.out.println("\nThe fridge door has to be open for you to take the food..");
                                found = true;
                            }else if(doorOpen && this.food == 0){
                                System.out.println("\nYou already took the food that was in here!");
                                found = true;
                            }
                        }else if(fridgeActions[i].equals("kick")){
                            if(this.food == 0){
                                System.out.println("\nYou violently kick the fridge wishing there was more food to take..");
                                System.out.println("You hurt your foot and lost some health in the process..");
                                player.decreaseHp(10);
                                found = true;
                            }else{
                                System.out.println("\nYou give the fridge a nice lil' kick!");
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
        for(int i = 1; i < this.kitchenKeywords.size() + 1; i++){
            System.out.println("- " + this.kitchenKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

    public void displayKnifeActions(){
        System.out.println("\nHere are some commands you can use on the knife!");
        System.out.println("**********************************************************");
        for(int i = 0; i < knifeActions.length; i++){
            System.out.println("- " + knifeActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayFridgeActions(){
        System.out.println("\nHere are some commands you can use on the fridge!");
        System.out.println("**********************************************************");
        for(int i = 0; i < fridgeActions.length; i++){
            System.out.println("- " + fridgeActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displaySinkActions(){
        System.out.println("\nHere are some commands you can use on the sink!");
        System.out.println("**********************************************************");
        for(int i = 0; i < sinkActions.length; i++){
            System.out.println("- " + sinkActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayChairActions(){
        System.out.println("\nHere are some commands you can use on the chair!");
        System.out.println("**********************************************************");
        for(int i = 0; i < chairActions.length; i++){
            System.out.println("- " + chairActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

}