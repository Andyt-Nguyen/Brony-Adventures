import java.io.*;
import java.util.*;

public class Room implements Serializable {

    private int roomID;
    private String roomName;
    private String roomDescription;
    private String roomUniques;
    private int northDoor;
    private int southDoor;
    private int eastDoor;
    private int westDoor;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomName = "";
        roomDescription = "";
        roomUniques = "";
        northDoor = 0;
        southDoor = 0;
        eastDoor = 0;
        westDoor = 0;
    }

    Room(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomUniques = roomUniques;
        this.northDoor = northDoor;
        this.southDoor = southDoor;
        this.eastDoor = eastDoor;
        this.westDoor = westDoor;
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

    public void setNorthDoor(int northDoor){
        this.northDoor = northDoor;
    }

    public void setSouthDoor(int southDoor){
        this.southDoor = southDoor;
    }

    public void setEastDoor(int eastDoor){
        this.eastDoor = eastDoor;
    }
    
    public void setWestDoor(int westDoor){
        this.westDoor = westDoor;
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

    public int getNorthDoor(){
        return this.northDoor;
    }

    public int getSouthDoor(){
        return this.southDoor;
    }

    public int getEastDoor(){
        return this.eastDoor;
    }

    public int getWestDoor(){
        return this.westDoor;
    }

    public void moveRoom(Player player, String keyword){

        if(keyword.startsWith("move n") && this.northDoor != 0 && this.roomID != 10){
                player.setLocation(this.northDoor);
        }else if(keyword.startsWith("move n") && this.northDoor == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.startsWith("move s") && this.southDoor != 0){
            player.setLocation(this.southDoor);
        }else if(keyword.startsWith("move s") && this.southDoor == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.startsWith("move e") && this.eastDoor != 0){
            player.setLocation(this.eastDoor);
        }else if(keyword.startsWith("move e") && this.eastDoor == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.startsWith("move w") && this.westDoor != 0){
            player.setLocation(this.westDoor);
        }else if(keyword.startsWith("move w") && this.westDoor == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        

    }

}