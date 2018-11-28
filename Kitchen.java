public class Kitchen extends Room{

    private int knife;
    private int fridge;
    private int food;

    Kitchen(){
        super();
        this.knife = 0;
        this.fridge = 0;
        this.food = 0;
    }
    
    
    Kitchen(int roomID, String roomName, String roomDescription, String defaultMsg1, String defaultMsg2, int knife, int fridge){
        super(roomID, roomName, roomDescription, defaultMsg1, defaultMsg2);
        this.knife = knife;
        this.fridge = fridge;
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

    public int getKnife(){
        return this.knife;
    }

    public int getFridge(){
        return this.fridge;
    }

    public void performAction(Player player, String keyword){
        if(keyword.equalsIgnoreCase("knife") && this.knife == 1){
            System.out.println("You pick up the knife and accidentally dropped it on your toe, but don't worry it's still useable.");
            setKnife(0);
            player.setKnife(1);
            player.decreaseHp(10);
        }else if(keyword.equalsIgnoreCase("knife") && this.knife == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
        if(keyword.equalsIgnoreCase("fridge") && this.fridge == 1){
            System.out.println("You decide to look in the fridge, you find a half eaten apple and decide to eat it anyways.");
            setFridge(0);
            player.increaseHp(15);
        }else if(keyword.equalsIgnoreCase("fridge") && this.fridge == 0){
            System.err.println("Sorry the command " + keyword + " is not useable here.");
        }
    }

}