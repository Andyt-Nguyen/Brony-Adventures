import java.io.*;
import java.util.*;

public class Room{

    private int roomID;
    private String roomName;
    private String roomDescription;
    private String defaultMsg1;
    private String defaultMsg2;
    private String defaultMsg3;
    private String defaultMsg4;
    private String defaultMsg5;
    private ArrayList<String> keywords = new ArrayList<String>();

    Room(){
        roomID = 0;
        roomName = "";
        roomDescription = "";
        defaultMsg1 = "";
        defaultMsg2 = "";
        defaultMsg3 = "";
        defaultMsg4 = "";
        defaultMsg5 = "";
    }

    Room(int roomID, String roomName, String roomDescription, String defaultMsg1, String defaultMsg2, String defaultMsg3,
         String defaultMsg4, String defaultMsg5){
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.defaultMsg1 = defaultMsg1;
        this.defaultMsg2 = defaultMsg2;
        this.defaultMsg3 = defaultMsg3;
        this.defaultMsg4 = defaultMsg4;
        this.defaultMsg5 = defaultMsg5;
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

    public void setDefaultMsg1(String defaultMsg){
        this.defaultMsg1 = defaultMsg;
    }

    public void setDefaultMsg2(String defaultMsg){
        this.defaultMsg2 = defaultMsg;
    }

    public void setDefaultMsg3(String defaultMsg){
        this.defaultMsg3 = defaultMsg;
    }

    public void setDefaultMsg4(String defaultMsg){
        this.defaultMsg4 = defaultMsg;
    }

    public void setDefaultMsg5(String defaultMsg){
        this.defaultMsg5 = defaultMsg;
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

    public String getDefaultMsg1(){
        return this.defaultMsg1;
    }

    public String getDefaultMsg2(){
        return this.defaultMsg2;
    }

    public String getDefaultMsg3(){
        return this.defaultMsg3;
    }

    public String getDefaultMsg4(){
        return this.defaultMsg4;
    }

    public String getDefaultMsg5(){
        return this.defaultMsg5;
    }

    public void addKeyword(String keyword){
        keywords.add(keyword);
    }

    public void findKeywordKitchen(Player player, String keyword, Kitchen kitchen){
        boolean isFound = false;
        for(int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equalsIgnoreCase(keyword)){
                kitchen.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.err.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void findKeywordBedroom(Player player, String keyword, Bedroom bedroom){
        boolean isFound = false;
        for(int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equalsIgnoreCase(keyword)){
                bedroom.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.err.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void findKeywordBathroom(Player player, String keyword, Bathroom bathroom){
        boolean isFound = false;
        for(int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equalsIgnoreCase(keyword)){
                bathroom.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.err.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void findKeywordHallway(Player player, String keyword, Hallway hallway){
        boolean isFound = false;
        for(int i = 0; i < keywords.size(); i++){
            if(keywords.get(i).equalsIgnoreCase(keyword)){
                hallway.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.err.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void displayRoomHelp(){
        System.out.println("\n------" + this.roomName + "------");
        for(int i = 1; i < keywords.size() + 1; i++){
            System.out.println(i + ". " + keywords.get(i));
        }
    }

}