import java.util.*;

public class Bedroom extends Room{

    private int dresser;
    private int diary;
    private int bed;
    private int gun;
    private int key;
    private int mirror;
    private List<String> bedroomKeywords = new ArrayList<String>();

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
            if(this.bedroomKeywords.get(i).equalsIgnoreCase(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("\nThe game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("dresser") && this.dresser == 1 && this.getRoomID() == 1){
            System.out.println("\nYou open up the dresser and find a diary, you decide to read it");
            setDresser(0);
            readDiary();
            this.bedroomKeywords.remove("Dresser");
        }else if(keyword.equalsIgnoreCase("dresser") && this.dresser == 0 && this.getRoomID() == 1){
            System.err.println("\nSorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("bed") && this.bed == 1 && this.getRoomID() == 1){
            System.out.println("\nYou search under the bed and find a gun. Turns out you don't know how to use it..");
            System.out.println("You accidentally fire the gun into a mirror and a piece of broken glasses grazes your arm.");
            System.out.println("You lose 5 hp.");
            System.out.println("Luckily something worse didn't happen!");
            setBed(0);
            player.decreaseHp(5);
            this.bedroomKeywords.remove("Bed");
        }else if(keyword.equalsIgnoreCase("bed") && this.bed == 0 && this.getRoomID() == 1){
            System.err.println("\nSorry the command " + keyword + " is not useable here.");
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

}