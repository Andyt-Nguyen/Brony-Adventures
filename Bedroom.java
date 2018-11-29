import java.util.*;

public class Bedroom extends Room{

    private int dresser;
    private int diary;
    private int bed;
    private int gun;
    private List<String> bedroomKeywords = new ArrayList<String>();

    Bedroom(){
        super();
        this.dresser = 0;
        this.gun = 0;
        this.bed = 0;
        this.diary = 0;
    }

    Bedroom(int roomID, String roomName, String roomDescription, int dresser, int bed){
        super(roomID, roomName, roomDescription);
        this.dresser = dresser;
        this.bed = bed;
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

    public int getDresser(){
        return this.dresser;
    }

    public int getBed(){
        return this.bed;
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
            System.out.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("dresser") && this.dresser == 1){
            System.out.println("\nYou open up the dresser and find a diary, you decide to read it");
            setDresser(0);
            readDiary();
        }else if(keyword.equalsIgnoreCase("dresser") && this.dresser == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("bed") && this.bed == 1){
            System.out.println("You search under the bed and find a gun. Turns out you don't know how to use it..");
            System.out.println("You accidentally fire the gun into a mirror and a piece of broken glasses grazes your arm.");
            System.out.println("Luckily something worse didn't happen!");
            setBed(0);
            player.decreaseHp(5);
        }else if(keyword.equalsIgnoreCase("bed") && this.bed == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.bedroomKeywords.size() + 1; i++){
            System.out.println(i + ". " + this.bedroomKeywords.get(i - 1));
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