import java.io.*;

public class Room{

    private int roomID;
    private String roomDescription;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomDescription = 0;
    }

    Room(int roomID, String roomDescription){
        this.roomID = roomId;
        this.roomDescription = roomDescription;
    }

    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    public void setDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    public int getRoomID(){
        return this.roomID;
    }

    public String getDescription(){
        return this.roomDescription;
    }

    public void addKeyword(String keyword){
        keywords.add(keyword);
    }


}