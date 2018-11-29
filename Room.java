import java.io.*;
import java.util.*;

public class Room{

    private int roomID;
    private String roomName;
    private String roomDescription;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomName = "";
        roomDescription = "";
    }

    Room(int roomID, String roomName, String roomDescription){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
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

    public int getRoomID(){
        return this.roomID;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public String getDescription(){
        return this.roomDescription;
    }

}