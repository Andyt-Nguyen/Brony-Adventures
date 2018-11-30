import java.io.*;
import java.util.*;

public class Room{

    private int roomID;
    private String roomName;
    private String roomDescription;
    private String roomUniques;
    private int door1;
    private int door2;
    private int door3;
    private int door4;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomName = "";
        roomDescription = "";
        roomUniques = "";
        door1 = 0;
        door2 = 0;
        door3 = 0;
        door4 = 0;
    }

    Room(int roomID, String roomName, String roomDescription, String roomUniques, int door1, int door2, int door3, int door4){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomUniques = roomUniques;
        this.door1 = door1;
        this.door2 = door2;
        this.door3 = door3;
        this.door4 = door4;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public void setDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    public void setRoomUniques(String roomUniques){
        this.roomUniques = roomUniques;
    }

    public void setDoor1(int door1){
        this.door1 = door1;
    }

    public void setDoor2(int door2){
        this.door2 = door2;
    }

    public void setDoor3(int door3){
        this.door3 = door3;
    }
    
    public void setDoor4(int door4){
        this.door4 = door4;
    }

    public int getRoomID(){
        return this.roomID;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public String getDescription(){
        return this.roomDescription;
    }

    public String getRoomUniques(){
        return this.roomUniques;
    }

    public int getDoor1(){
        return this.door1;
    }

    public int getDoor2(){
        return this.door2;
    }

    public int getDoor3(){
        return this.door3;
    }

    public int getDoor4(){
        return this.door4;
    }

}