import java.io.*;
import java.util.*;

public class Room{

    private int roomID;
    private String roomName;
    private String roomDescription;
    private int door1;
    private int door2;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomName = "";
        roomDescription = "";
    }

    Room(int roomID, String roomName, String roomDescription, int door1, int door2){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.door1 = door1;
        this.door2 = door2;
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

    public void setDoor1(int door1){
        this.door1 = door1;
    }

    public void setDoor2(int door2){
        this.door2 = door2;
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

    public int getDoor1(){
        return this.door1;
    }

    public int getDoor2(){
        return this.door2;
    }
}