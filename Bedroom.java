public class Bedroom extends Room{

    private int dresser;
    private int diary;
    private int bed;
    private int gun;

    Bedroom(){
        super();
        this.dresser = 0;
        this.gun = 0;
        this.bed = 0;
        this.diary = 0;
    }

    Bedroom(int roomID, String roomName, String roomDescription, String defaultMsg1, String defaultMsg2,
            String defaultMsg3, String defaultMsg4, String defaultMsg5, int dresser, int bed){
        super(roomID, roomName, roomDescription, defaultMsg1, defaultMsg2, defaultMsg3, defaultMsg4, defaultMsg5);
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

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("dresser") && this.dresser == 1){
            System.out.println("You open up the dresser and find a diary, you decide to read it");
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

    public void readDiary(){

    }

}