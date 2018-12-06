import java.io.Serializable;
import java.util.*;

public class Hallway extends Room implements Serializable {

    private int lamp;
    private int window;
    private int closet;
    private int atticPanel;
    private int statue;
    private boolean hallwayVisited;
    private boolean lampOn;
    private boolean windowOpen;
    private boolean isScouted;
    private boolean panelOpen;
    private boolean closetOpen;
    private boolean closetFightComplete;
    private boolean panelFightComplete;
    private boolean finalFightComplete;
    private List<String> hallwayKeywords = new ArrayList<String>();
    private static String[] lampActions = {"examine" , "punch", "turn on", "turn off"};
    private static String[] windowActions = {"examine" , "open", "close", "jump out", "look out"};
    private static String[] closetActions = {"examine" , "open", "close", "listen", "knock", "look inside"};
    private static String[] panelActions = {"examine" , "open", "close", "look inside"};
    private static String[] statueActions = {"examine" , "punch", "kick", "imitate", "smell"};

    Hallway(){
        super();
        this.lamp = 0;
        this.window = 0;
        this.closet = 0;
        this.atticPanel = 0;
        this.statue = 0;
    }

    Hallway(int roomID, String roomName, String roomDescription, String roomUniques, int northDoor, int southDoor, int eastDoor, int westDoor,
    int lamp, int window, int closet, int atticPanel, int statue){
        super(roomID, roomName, roomDescription, roomUniques, northDoor, southDoor, eastDoor, westDoor);
        this.lamp = lamp;
        this.window = window;
        this.closet = closet;
        this.atticPanel = atticPanel;
        this.statue = statue;
        this.lampOn = false;
        this.windowOpen = false;
        this.panelOpen = false;
        this.closetOpen = false;
        this.isScouted = false;
        this.closetFightComplete = false;
        this.panelFightComplete = false;
        this.finalFightComplete = false;
        this.hallwayVisited = false;
    }

    public void setLamp(int lamp){
        this.lamp = lamp;
    }

    public void setWindow(int window){
        this.window = window;
    }

    public void setCloset(int closet){
        this.closet = closet;
    }

    public void setAtticPanel(int atticPanel){
        this.atticPanel = atticPanel;
    }

    public void setStatue(int statue){
        this.statue = statue;
    }

    public int getCloset(){
        return this.closet;
    }

    public int getAtticPanel(){
        return this.atticPanel;
    }

    public int getStatue(){
        return this.statue;
    }

    public int getLamp(){
        return this.lamp;
    }
    
    public int getWindow(){
        return this.window;
    }

    public void setHallwayVisited(Boolean visit){
        this.hallwayVisited = visit;
    }

    public Boolean getHallwayVisited(){
        return this.hallwayVisited;
    }

    public Boolean getFinalFight(){
        return this.finalFightComplete;
    }

    public void addKeyword(String keyword){
        this.hallwayKeywords.add(keyword);
    }

    public void findKeyword(Player player, String keyword){
        boolean isFound = false;
        for(int i = 0; i < this.hallwayKeywords.size();i++){
            if(this.hallwayKeywords.get(i).toLowerCase().startsWith(keyword)){
                this.performAction(player, keyword);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("The game does not recognize the word, " + keyword + "!");
        }
    }

    public void performAction(Player player, String keyword){
        boolean userChoice;
        boolean center = false;
        String userAction;
        if(keyword.startsWith("walk to l") && this.lamp == 1){
            userAction = IR5.getString("\nYou walk up to the lamp. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayLampActions();
                    found = true;
                }
                for(int i = 0; i < lampActions.length; i++){
                    if(userAction.equals(lampActions[i])){
                        if(lampActions[i].equals("examine")){
                            System.out.println("\nIt's a pony shaped lamp!");
                            found = true;
                        }else if(lampActions[i].equals("punch")){
                            System.out.println("\nYou're scared to brake something that seems somewhat valuable.");
                            System.out.println("You wouldn't want any bad karma coming your way!");
                            found = true;
                        }else if(lampActions[i].equals("turn on")){
                            if(this.lampOn){
                                System.out.println("\nThis lamp is already turned on!");
                                found = true;
                            }else if(!this.lampOn){
                                System.out.println("\nYou turn on the lamp!");
                                found = true;
                                this.lampOn = true;
                            }
                        }else if(lampActions[i].equals("turn off")){
                            if(this.lampOn){
                                System.out.println("\nYou turn the lamp off!");
                                this.lampOn = false;
                                found = true;
                            }else if(!this.lampOn){
                                System.out.println("\nThis lamp is already off!");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
                
            }
            System.out.println("\nYou return to the center of the room.");
        }
        if(keyword.startsWith("walk to w") && this.window == 1 && this.getRoomID() == 2){
            userAction = IR5.getString("\nYou walk up to the window. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayWindowActions();
                    found = true;
                }
                for(int i = 0; i < windowActions.length; i++){
                    if(userAction.equals(windowActions[i])){
                        if(windowActions[i].equals("examine")){
                            System.out.println("\nIt's a window on the north side of the room. It looks like it could be opened or closed.");
                            found = true;
                        }else if(windowActions[i].equals("open")){
                            if(this.windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou open the window!");
                                this.windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(this.windowOpen){
                                System.out.println("\nYou close the window.");
                                this.windowOpen = false;
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nThis window is already closed.");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(this.windowOpen){
                                System.out.println("\nYou decide to jump out the window..");
                                System.out.println("As you are jumping out, your foot gets tripped up on the window ceil.");
                                System.out.println("You fall two stories landing on your head causing instant death...");
                                Menus.displayGameOver(player);
                                center = true;
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(this.windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You see nothing in the distance.");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't see much through this closed window.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to w") && this.window == 1 && this.getRoomID() == 5){
            userAction = IR5.getString("\nYou walk up to the window. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayWindowActions();
                    found = true;
                }
                for(int i = 0; i < windowActions.length; i++){
                    if(userAction.equals(windowActions[i])){
                        if(windowActions[i].equals("examine")){
                            System.out.println("\nIt's a window on the west side of the room. It looks like it could be opened or closed.");
                            found = true;
                        }else if(windowActions[i].equals("open")){
                            if(this.windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou open the window!");
                                this.windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(this.windowOpen){
                                System.out.println("\nYou shut the window.");
                                this.windowOpen = false;
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nThis window is already closed!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(windowOpen && !isScouted){
                                System.out.println("\nYou get ready to jump out the window.. you take a few steps back.." );
                                System.out.println("As you run towards the window you hear noises down below..");
                                System.out.println("You stop at the window and look down..");
                                System.out.println("You see a stable that is loaded with what seems to be demonic ponies..");
                                System.out.println("\nMaybe jumping out isn't the best idea..");
                                found = true;
                            }else if(this.windowOpen && this.isScouted){
                                System.out.println("\nWhy would you want to jump out this window knowing what's out there..");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(this.windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You see a stable filled with demonic ponies..");
                                this.isScouted = true;
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't see much through this closed window.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to st") && this.statue == 1 && this.getRoomID() == 5){
            userAction = IR5.getString("\nYou walk up to the statue. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayStatueActions();
                    found = true;
                }
                for(int i = 0; i < statueActions.length; i++){
                    if(userAction.equals(statueActions[i])){
                        if(statueActions[i].equals("examine")){
                            System.out.println("\nIt's a statue made of gold that resembles you.");
                            System.out.println("\nThat's not spookey at all...");
                            found = true;
                        }else if(statueActions[i].equals("punch")){
                            System.out.println("\nYou must really hate yourself..");
                            System.out.println("You punch the statue!");
                            System.out.println("I guess punching solid gold doesn't feel too good!");
                            System.out.println("\nYou lose a bit of healh in this process..");
                            player.decreaseHp(5);
                            found = true;
                        }else if(statueActions[i].equals("kick")){
                            System.out.println("\nYou kick the solid gold statue..");
                            System.out.println("You must really hate yourself.");
                            found = true;
                        }else if(statueActions[i].equals("imitate")){
                            System.out.println("\nYou make a pose similar to the statue that looks like you!");
                            System.out.println("The only difference is this statue's worth more than your bank account.");
                            found = true;
                        }else if(statueActions[i].equals("smell")){
                            System.out.println("\nThis statue smells like pure gold and something else that's familiar..");
                            System.out.println(".....");
                            System.out.println("You realize it smells sorta like you.");
                            found = true;
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to cl") && this.closet == 1){
            userAction = IR5.getString("\nYou walk up to the closet. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayClosetActions();
                    found = true;
                }
                for(int i = 0; i < closetActions.length; i++){
                    if(userAction.equals(closetActions[i])){
                        if(closetActions[i].equals("examine")){
                            System.out.println("\nIt's a closet.. probably used for storage.");
                            found = true;
                        }else if(closetActions[i].equals("open")){
                            if(!this.closetFightComplete && !this.closetOpen){
                                this.closetFight(player);
                                if(player.getHp() <= 0){
                                    Menus.displayGameOver(player);
                                    center = true;
                                    break;
                                }
                                this.closetOpen = true;
                                this.closetFightComplete = true;
                                found = true;
                            }else if(this.closetFightComplete && this.closetOpen){
                                System.out.println("\nThe closet door is already open.");
                                System.out.println("You should probably shut so you don't have to smell that dead carcass later!");
                                found = true;
                            }else if(!this.closetOpen && this.closetFightComplete){
                                System.out.println("\nYou re-open the closet door and see the demon ponies dead carcass!");
                                this.closetOpen = true;
                                found = true;
                            }
                        }else if(closetActions[i].equals("close")){
                            if(this.closetOpen){
                                System.out.println("\nYou shut the closet door, leaving the carcass of the dead pony in there!");
                                this.closetOpen = false;
                                found = true;
                            }else if(!this.closetOpen){
                                System.out.println("\nThe closet door is already closed!");
                                found = true;
                            }
                        }else if(closetActions[i].equals("listen")){
                            if(!this.closetFightComplete && !this.closetOpen){
                                System.out.println("\nYou put your ear next to the door, is sounds like something on the other side is breathing.");
                                found = true;
                            }else if(this.closetFightComplete && !this.closetOpen){
                                System.out.println("\nYou put your ear next to the door and hear nothing..");
                                System.out.println("That carcass in there is silently rotting away.");
                                found = true;
                            }else if (this.closetOpen){
                                System.out.println("\nThe closet door is open.. why listen if you can look inside!?");
                                found = true;
                            }
                        }else if(closetActions[i].equals("knock")){
                            if(!this.closetFightComplete && !this.closetOpen){
                                System.out.println("\nYou knock on the door..");
                                System.out.println("Right after you hear something ram into the door!");
                                found = true;
                            }else if(this.closetFightComplete && !this.closetOpen){
                                System.out.println("\nYou knock on the door..");
                                System.out.println("But you hear nothing..");
                                found = true;
                            }else if (this.closetOpen){
                                System.out.println("\nThe closet door is open.. you cannot knock on it..");
                                found = true;
                            }
                        }else if(closetActions[i].equals("look inside")){
                            if(this.closetOpen){
                                System.out.println("\nYou look inside the closet but all you see is the dead carcass of the demonic pony you slayed..");
                                found = true;
                            }else if (!this.closetOpen){
                                System.out.println("\nThe door is closed, there is no way for you too look inside unless it's open.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to w") && this.window == 1 && this.getRoomID() == 6){
            userAction = IR5.getString("\nYou walk up to the window. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayWindowActions();
                    found = true;
                }
                for(int i = 0; i < windowActions.length; i++){
                    if(userAction.equals(windowActions[i])){
                        if(windowActions[i].equals("examine")){
                            System.out.println("\nIt's a window on the east side of the room. It looks like it could be opened or closed.");
                            found = true;
                        }else if(windowActions[i].equals("open")){
                            if(this.windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou open the window!");
                                this.windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(this.windowOpen){
                                System.out.println("\nYou shut the window.");
                                this.windowOpen = false;
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nThis window is already closed!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(this.windowOpen){
                                System.out.println("\nI don't think jumping out is a good idea..");
                                System.out.println("Maybe you should just look out of it.");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(this.windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You look straight down and see a stable of demonic ponies.");
                                found = true;
                            }else if(!this.windowOpen){
                                System.out.println("\nYou can't see much through this closed window.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to att") && this.atticPanel == 1){
            userAction = IR5.getString("\nYou walk under the attic panel. Choose an action.(Help for list of commands)").toLowerCase().trim();
            if(userAction.startsWith("cent")){
                center = true;
            }
            while(!center){
                boolean found = false;
                if(userAction.startsWith("hel")){
                    displayPanelActions();
                    found = true;
                }
                for(int i = 0; i < panelActions.length; i++){
                    if(userAction.equals(panelActions[i])){
                        if(panelActions[i].equals("examine")){
                            System.out.println("\nIt's an attic panel.. who knows what's inside..");
                            found = true;
                        }else if(panelActions[i].equals("open")){
                            if(!this.panelFightComplete && !this.panelOpen){
                                this.panelFight(player);
                                if(player.getHp() <= 0){
                                    Menus.displayGameOver(player);
                                    center = true;
                                    break;
                                }
                                this.panelOpen = true;
                                this.panelFightComplete = true;
                                found = true;
                            }else if(this.panelFightComplete && this.panelOpen){
                                System.out.println("\nThe attic panel is already open.");
                                found = true;
                            }else if(!this.panelOpen && this.panelFightComplete){
                                System.out.println("\nYou re-open the attic panel to find a bunch of junk..");
                                this.panelOpen = true;
                                found = true;
                            }
                        }else if(panelActions[i].equals("close")){
                            if(this.panelOpen){
                                System.out.println("\nYou shut the attic panel.");
                                this.panelOpen = false;
                                found = true;
                            }else if(!this.panelOpen){
                                System.out.println("\nThe attic pannel is already shut!");
                                found = true;
                            }   
                        }else if(panelActions[i].equals("look inside")){
                            if(this.panelOpen){
                                System.out.println("\nYou look inside the attic but all you see is junk..");
                                found = true;
                            }else if (!this.panelOpen){
                                System.out.println("\nThe panel is shut, there is no way for you too look inside unless it's open.");
                                found = true;
                            }
                        }
                    }
                }
                if(!found){
                    System.err.println("\nSorry this command cannot be used here!");
                }
                userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
            }  

            System.out.println("\nYou return to the center of the room.");
        } 
        if(keyword.startsWith("walk to st") && this.statue == 1 && this.getRoomID() == 10){
            if(!this.finalFightComplete){
                this.finalFight(player);
                if(player.getHp() <= 0){
                    Menus.displayGameOver(player);
                }else if(player.getHp() > 0){
                    this.finalFightComplete = true;
                }
            }else if(this.finalFightComplete){
                userAction = IR5.getString("\nYou walk up to the statue. Choose an action.(Help for list of commands)").toLowerCase().trim();
                if(userAction.startsWith("cent")){
                    center = true;
                }
                while(!center){
                    boolean found = false;
                    if(userAction.startsWith("hel")){
                        displayStatueActions();
                        found = true;
                    }
                    for(int i = 0; i < statueActions.length; i++){
                        if(userAction.equals(statueActions[i])){
                            if(statueActions[i].equals("examine")){
                                System.out.println("\nThis was the statue that came to life and attacked you..");
                                System.out.println("Luckily you killed it.");
                                found = true;
                            }else if(statueActions[i].equals("punch")){
                                System.out.println("\nYou punch the motionless object..");
                                found = true;
                            }else if(statueActions[i].equals("kick")){
                                System.out.println("\nYou kick the motionless object..");
                            }else if(statueActions[i].equals("imitate")){
                                System.out.println("\nYou lay on the ground and play dead imitating what you killed.");
                            }else if(statueActions[i].equals("smell")){
                                System.out.println("\nIt really doesn't smell like much.. yet.");
                            }
                        }
                    }
                    if(!found){
                        System.err.println("\nSorry this command cannot be used here!");
                    }
                    userAction = IR5.getString("\nChoose next action.").toLowerCase().trim();
                    if(userAction.startsWith("cent")){
                        center = true;
                    }
                }  

                System.out.println("\nYou return to the center of the room.");
            } 
        }
        if(keyword.startsWith("move")){
            moveRoom(player, keyword);
        }
        if(keyword.startsWith("move n") && this.getRoomID() == 10){
            finalRoomMove(player, keyword);
        }
    }
    

    public void displayRoomHelp(){
        System.out.println("\n---------" + this.getRoomName() + "---------");
        System.out.println("\n" + this.getDescription());
        System.out.println("Here are a few words you can enter to do things around the room..");
        System.out.println("");
        for(int i = 1; i < this.hallwayKeywords.size() + 1; i++){
            System.out.println("- " + this.hallwayKeywords.get(i - 1));
        }
        System.out.println("\n-------------------------------");
    }

    public void displayLampActions(){
        System.out.println("\nHere are some commands you can use on the lamp!");
        System.out.println("**********************************************************");
        for(int i = 0; i < lampActions.length; i++){
            System.out.println("- " + lampActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayWindowActions(){
        System.out.println("\nHere are some commands you can use on the window!");
        System.out.println("**********************************************************");
        for(int i = 0; i < windowActions.length; i++){
            System.out.println("- " + windowActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayPanelActions(){
        System.out.println("\nHere are some commands you can use on the attic panel!");
        System.out.println("**********************************************************");
        for(int i = 0; i < panelActions.length; i++){
            System.out.println("- " + panelActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayClosetActions(){
        System.out.println("\nHere are some commands you can use on the closet!");
        System.out.println("**********************************************************");
        for(int i = 0; i < closetActions.length; i++){
            System.out.println("- " + closetActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void displayStatueActions(){
        System.out.println("\nHere are some commands you can use on the statue!");
        System.out.println("**********************************************************");
        for(int i = 0; i < statueActions.length; i++){
            System.out.println("- " + statueActions[i]);
        }
        System.out.println("- center");
        System.out.println("**********************************************************");
    }

    public void closetFight(Player player){
        int ponyHealth = 20;
        String userChoice;
        int ponyDamage;
        int userDamage;
        System.out.println("As you open up the door you're attacked by a demonic pony..");
        while(ponyHealth > 0 && player.getHp() > 0){
            System.out.println("**********Choose Attack*********");
            System.out.println("* - Punch                      *");
            System.out.println("* - Kick                       *");
            if(player.getKnife() == 1){
                System.out.println("* -  Stab(knife)                 *");
            }
            System.out.println("********************************");
            userChoice = IR5.getString("\n").toLowerCase().trim();
            if(userChoice.startsWith("pu")){
                userDamage = IR5.getRandomNumber(3, 5);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your hands to punch it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("ki")){
                userDamage = IR5.getRandomNumber(4, 6);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your feet to kick it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 1){
                userDamage = IR5.getRandomNumber(12, 15);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to stab it with the knife you picked up!, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 0){
                System.out.println("\nNice try, you don't have a nice you dirty experienced player.");
            }else{
                System.err.println("\nSorry the command " + userChoice + " doesn't work.");
            }

        }
        if(ponyHealth <= 0){
            System.out.println("\nVery nice job, you have killed the pony.");
            System.out.println("You stash the dead carcass into the closet that it came from.");
            player.addToPoints(10);
        }
    }

    public void panelFight(Player player){
        int ponyHealth = 20;
        String userChoice;
        int ponyDamage;
        int userDamage;
        System.out.println("As you open up the attic panel a demonic pony jumps out and attacks you!");
        while(ponyHealth > 0 && player.getHp() > 0){
            System.out.println("**********Choose Attack*********");
            System.out.println("* - Punch                      *");
            System.out.println("* - Kick                       *");
            if(player.getKnife() == 1){
                System.out.println("* - Stab(knife)                 *");
            }
            System.out.println("********************************");
            userChoice = IR5.getString("\n").toLowerCase().trim();
            if(userChoice.startsWith("pu")){
                userDamage = IR5.getRandomNumber(3, 5);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your hands to punch it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("ki")){
                userDamage = IR5.getRandomNumber(4, 6);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your feet to kick it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 1){
                userDamage = IR5.getRandomNumber(12, 15);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to stab it with the knife you picked up!, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(5, 10);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 0){
                System.out.println("\nNice try, you don't have a nice you dirty experienced player.");
            }else{
                System.out.println("\nSorry the command " + userChoice + " doesn't work here.");
            }

        }
        if(ponyHealth <= 0){
            System.out.println("\nVery nice job, you have killed the pony.");
            System.out.println("You leave the dead carcass where you killed it, you better get out of this place before it begins to smell!");
            player.addToPoints(10);
        }
    }

    public void finalFight(Player player){
        int ponyHealth = 50;
        String userChoice;
        int ponyDamage;
        int userDamage;
        System.out.println("You approach the statue.. All of the sudden it comes to life and and attacks!");
        System.out.println("It's a demonic pony but this one has 3 horns and is slightly bigger!");
        while(ponyHealth > 0 && player.getHp() > 0){
            System.out.println("\n**********Choose Attack*********");
            System.out.println("* - Punch                      *");
            System.out.println("* - Kick                       *");
            if(player.getKnife() == 1){
                System.out.println("* - Stab(knife)                 *");
            }
            System.out.println("********************************");
            userChoice = IR5.getString("\n").toLowerCase().trim();
            if(userChoice.startsWith("pu")){
                userDamage = IR5.getRandomNumber(3, 5);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your hands to punch it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(10, 12);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("ki")){
                userDamage = IR5.getRandomNumber(3, 5);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to use your feet to kick it, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(10, 12);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 1){
                userDamage = IR5.getRandomNumber(12, 15);
                ponyHealth -= userDamage;
                if(ponyHealth < 0){
                    ponyHealth = 0;
                }
                System.out.println("\nYou decide to stab it with the knife you picked up!, you deal " + userDamage + " damage to the pony.");
                System.out.println("The pony has " + ponyHealth + " hp remaining.");
                if(ponyHealth > 0){
                    ponyDamage = IR5.getRandomNumber(10, 12);
                    player.decreaseHp(ponyDamage);
                    System.out.println("\nThe pony attacks back buckshotting you with it's hind legs!");
                    System.out.println("You take " + ponyDamage + " damage.");
                    System.out.println("\nYou have " + player.getHp() + " hp remaining.");
                }
            }else if(userChoice.startsWith("st") && player.getKnife() == 0){
                System.out.println("\nNice try, you don't have a nice you dirty experienced player.");
            }else{
                System.out.println("\nSorry the command " + userChoice + " doesn't work here.");
            }

        }
        if(ponyHealth <= 0){
            System.out.println("\nVery nice job, you have killed the pony.");
            System.out.println("You leave the dead carcass where you killed it, you better get out of this place before worse things happen!");
            player.addToPoints(25);
        }
    }

    public void finalRoomMove(Player player, String keyword){
        if(keyword.startsWith("move n") && this.getNorthDoor() != 0){
            if(player.getKey() == 1 && getFinalFight()){
                player.addToPoints(player.getHp());
                player.addToPoints(100);
                Menus.displayGameWon(player);
                player.setHp(0);
            }else if(player.getKey() == 0){
                System.err.println("\nSorry this door is locked, you need to find the key.");  
            }else if(!getFinalFight()){
                System.err.println("\nMaybe you should check out the statue in this room!");
            }
        }
        if(keyword.startsWith("move s") && this.getSouthDoor() != 0){
            player.setLocation(this.getSouthDoor());
        }else if(keyword.startsWith("move s") && this.getSouthDoor() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.startsWith("move e") && this.getEastDoor() != 0){
            player.setLocation(this.getEastDoor());
        }else if(keyword.startsWith("move e") && this.getEastDoor() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
        if(keyword.startsWith("move w") && this.getWestDoor() != 0){
            player.setLocation(this.getWestDoor());
        }else if(keyword.startsWith("move w") && this.getWestDoor() == 0){
            System.err.println("\nYou cannot go this direction.");
        }
    }
}