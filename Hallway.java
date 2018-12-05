import java.io.Serializable;
import java.util.*;

public class Hallway extends Room implements Serializable {

    private int lamp;
    private int window;
    private int closet;
    private int atticPanel;
    private int statue;
    private boolean lampOn;
    private boolean windowOpen;
    private boolean isScouted;
    private boolean panelOpen;
    private boolean closetOpen;
    private boolean closetFightComplete;
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
                            if(windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou open the window!");
                                windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(windowOpen){
                                System.out.println("\nYou close the window.");
                                windowOpen = false;
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nThis window is already closed.");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(windowOpen){
                                System.out.println("\nYou decide to jump out the window..");
                                System.out.println("As you are jumping out, your foot gets tripped up on the window ceil.");
                                System.out.println("You fall two stories landing on your head causing instant death...");
                                Menus.displayGameOver(player);
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You see nothing in the distance.");
                                found = true;
                            }else if(!windowOpen){
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
                            if(windowOpen){
                                System.out.println("\nThis window is already open..");
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou open the window!");
                                windowOpen = true;
                                found = true;
                            }
                        }else if(windowActions[i].equals("close")){
                            if(windowOpen){
                                System.out.println("\nYou shut the window.");
                                windowOpen = false;
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nThis window is already closed!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("jump out")){
                            if(windowOpen && !isScouted){
                                System.out.println("\nYou get ready to jump out the window.." );
                                System.out.println("As you run towards the window you hear noises down below..");
                                System.out.println("You stop at the window and look down..");
                                System.out.println("You see a stable that is loaded with what seems to be demonic ponies..");
                                System.out.println("\nMaybe jumping out isn't the best idea..");
                                found = true;
                            }else if(windowOpen && isScouted){
                                System.out.println("\nWhy would you want to jump out this window knowing what's out there..");
                                found = true;
                            }else if(!windowOpen){
                                System.out.println("\nYou can't jump out of a closed window!");
                                found = true;
                            }
                        }else if(windowActions[i].equals("look out")){
                            if(windowOpen){
                                System.out.println("\nYou look outside the window and search all around.");
                                System.out.println("You see a stable filled with demonic ponies..");
                                isScouted = true;
                                found = true;
                            }else if(!windowOpen){
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
                //{"examine" , "open", "close", "listen", "knock", "look inside"
                for(int i = 0; i < closetActions.length; i++){
                    if(userAction.equals(closetActions[i])){
                        if(closetActions[i].equals("examine")){
                            System.out.println("\nIt's a closet.. probably used for storage.");
                            found = true;
                        }else if(closetActions[i].equals("open")){
                            if(!closetFightComplete && !closetOpen){
                                this.closetFight(player);
                                if(player.getHp() <= 0){
                                    Menus.displayGameOver(player);
                                    center = true;
                                    break;
                                }
                                closetFightComplete = true;
                                found = true;
                            }else if(closetFightComplete && closetOpen){
                                System.out.println("\nThe closet door is already open.");
                                System.out.println("You should probably shut so you don't have to smell that dead carcass later!");
                                found = true;
                            }else if(!closetOpen && closetFightComplete){
                                System.out.println("\nYou re-open the closet door and see the demon ponies dead carcass!");
                                found = true;
                            }
                        }else if(closetActions[i].equals("close")){
                            if(closetOpen){
                                System.out.println("\nYou shut the closet door, leaving the carcass of the dead pony in there!");
                                found = true;
                            }else if(!closetOpen){
                                System.out.println("\nThe closet door is already closed!");
                                found = true;
                            }
                        }else if(closetActions[i].equals("listen")){
                            if(!closetFightComplete && !closetOpen){
                                System.out.println("\nYou put your ear next to the door, is sounds like something on the other side is breathing.");
                                found = true;
                            }else if(closetFightComplete && !closetOpen){
                                System.out.println("\nYou put your ear next to the door and hear nothing..");
                                System.out.println("That carcass in there is silently rotting away.");
                                found = true;
                            }else if (closetOpen){
                                System.out.println("\nThe closet door is open.. why listen if you can look inside!?");
                                found = true;
                            }
                        }else if(closetActions[i].equals("knock")){
                            if(!closetFightComplete && !closetOpen){
                                System.out.println("\nYou knock on the door..");
                                System.out.println("Right after you hear something ram into the door!");
                                found = true;
                            }else if(closetFightComplete && !closetOpen){
                                System.out.println("\nYou knock on the door..");
                                System.out.println("But you hear nothing..");
                                found = true;
                            }else if (closetOpen){
                                System.out.println("\nThe closet door is open.. you cannot knock on it..");
                                found = true;
                            }
                        }else if(closetActions[i].equals("look inside")){
                            if(closetOpen){
                                System.out.println("\nYou look inside the closet but all you see is the dead carcass of the demonic pony you slayed..");
                                found = true;
                            }else if (!closetOpen){
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
        if(keyword.startsWith("move")){
            moveRoom(player, keyword);
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
            }

        }
        if(ponyHealth <= 0){
            System.out.println("\nVery nice job, you have killed the pony.");
            System.out.println("You stash the dead carcass into the closet that it came from.");
        }
    }
}