public class Person {
    private int hp;
    private int food;
    private String username;
    private String password;
    private int location;
    
    public Person() {
        this.username = "";
        this.password = "";
        this.hp = 100;
        this.food = "";
        this.location = 0;
    }

    public Person(String username, String password, int hp, int food, int location) {
        this.username = username;
        this.password = password;
        this.hp = hp;
        this.food = food;
        this.location = location;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassowrd(String password) {
        this.password = password;
    }



}