import java.io.Serializable;

public class Player implements Serializable {
    private int hp;
    private int food;
    private String username;
    private String password;
    private int location;
    private int knife;
    private int highScore;
    private int key;
    private boolean winGame;
    
    public Player() {
        this.username = "";
        this.password = "";
        this.hp = 100;
        this.food = 0;
        this.location = 0;
    }

    public Player(String username, String password, int highscore, int hp, int food, int location) {
        this.username = username;
        this.password = password;
        this.hp = hp;
        this.food = food;
        this.location = location;
        this.winGame = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getKnife(){
        return this.knife;
    }
    public int getHp() {
        return this.hp;
    }

    public int getFood() {
        return food;
    } 

    public int getLocation() {
        return location;
    }

    public int getHighScore(){
        return highScore;
    }

    public int getKey(){
        return this.key;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setWinGame(Boolean isTrue){
        this.winGame = isTrue;
    }

    public Boolean getWinGame(){
        return this.winGame;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKnife(int knife){
        this.knife = knife;
    }

    public void setPoints(int highScore) {
        this.highScore = highScore;
    }

    public void setKey(int key){
        this.key = key;
    }

    public void addToPoints(int points) {
        this.highScore += points;
    }

    public void increaseHp(int hp){
        this.hp += hp;
        if(this.hp > 100){
            this.hp = 100;
        }
    }

    public void decreaseHp(int hp){
        this.hp -= hp;
        if(this.hp < 0){
            this.hp = 0;
        }
    }

    public void addFood(int food){
        this.food += food;
    }

    public void decreaseFood(int food){
        this.food -= food;
    }

    public void eatFood(Player player){
        if(this.food < 1){
            System.err.println("\nSorry you don't have any food to eat.");
        }else if (this.food >= 1){
            System.out.println("\nYou eat a piece of food.");
            System.out.println("You slightly recovered some health");
            player.increaseHp(5);
            player.decreaseFood(1);
        }
    }

}