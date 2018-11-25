public class Player {
    private int hp;
    private int food;
    private String username;
    private String password;
    private int location;
    private int knife;
    private int highScore;
    
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
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int geKnife(){
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
        return this.highScore;
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

    public void addToPoints(int points) {
        this.highScore += points;
    }

    public void increaseHp(int hp){
        this.hp += hp;
    }

    public void decreaseHp(int hp){
        this.hp -= hp;
    }

}