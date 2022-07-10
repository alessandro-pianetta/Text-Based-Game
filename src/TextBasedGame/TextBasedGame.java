package TextBasedGame;

import java.util.Scanner;

/**
 *
 * @author Alessandro Pianetta
 */

public class TextBasedGame {
    private final Scanner textInput = new Scanner(System.in);
    // Flag that checks if the about method has been called.
    private boolean gameStarted = false;
    // Flag that checks if the quit method has been called and confirmed.
    private boolean exitGame = false;
    // Flag that checks if the user has used the open method on the mailbox already.
    private boolean hasKey = false;
    // Flag that checks if the user has used the examine method on the door already.
    private boolean hasExaminedDoor = false;


    /**
     * Primary method that the user interacts with. User can put in one of six commands. If an unknown command is entered, asks for clarification and tries again.
     * If the user enters "about", the game will begin.
     * If the user enters "help", the game will display the six possible commands.
     * If the user enters "open" or "examine", it will check if the game has been officially initiated. If not, will tell the user to use "about" command. If so, will prompt the user for input.
     * If the user enters "quit", the game will prompt the user for "y" or "yes" responses. If it sees them, it will thank the user and quit. If it doesn't, the game continues.
     * The function runs recursively to allow continuous input without having to manually call game.input() over and over again. The base case is based on the exitGame flag, which is toggled on by the quit method.
     */
    public void input() {
        String item;
        String command = textInput.next();
        switch (command) {
            case "about":
                intro();
                break;
            case "help":
                help();
                break;
            case "open":
                if (gameStarted) {
                    System.out.println("What would you like to open?");
                    item = textInput.next();
                    open(item);
                } else {
                    System.out.println("There's nothing around here to open! Try learning more >>ABOUT<< the game.");
                }
                break;
            case "examine":
                if (gameStarted) {
                    System.out.println("What would you like to examine?");
                    item = textInput.next();
                    examine(item);
                } else {
                    System.out.println("There's nothing around here to examine! Try learning more >>ABOUT<< the game.");
                }
                break;
            case "quit":
                System.out.println("Are you sure you want to quit? (Y/N)");
                String response = textInput.next();
                if (response.equals("y") || response.equals("yes")) {
                    quit();
                }
                break;
            default:
                System.out.println("Sorry, something went wrong. Please re-enter your command. To see all available commands, please use the command \"help\".");
                break;
        }
        
        if (!exitGame) {
            System.out.println("What do you want to do next?");
            input();
        }
    }

    /**
     * Initiates the game. Sets gameStarted flag to true so that examine and open methods can't be used before the game officially starts.
     */
    private void intro() {
        System.out.println("You are standing in an open field west of a white house, with a boarded front door. There is a small mailbox here.");
        System.out.println("You can now >OPEN< or >>EXAMINE<< something.");
        gameStarted = true;
    }

    /**
     * Shows available input() commands for the user to use.
     */
    private void help() {
        System.out.println("*** COMMAND HINTS ***");
        System.out.println("\"about\": Starts game");
        System.out.println("\"help\": Opens help menu");
        System.out.println("\"open\": Opens item in game");
        System.out.println("\"examine\": Takes a closer look at something in game");
        System.out.println("\"quit\": Quits game");
    }

    /**
     * Allows the user to quit the game. Sets exitGame flag to true so that the base case of the input method is reached.
     */
    private void quit() {
        System.out.println("Thanks for playing!");
        exitGame = true;
    }

    /**
     * Opens the item passed through via user input. If the user doesn't pass in "mailbox" or "door", will show default message.
     *
     * If user chooses "mailbox", the game will check for the hasKey flag. If true, it will say that the user can't find anything more and then return.
     * If false, the user will find the key and set the hasKey flag to true.
     * If the user has previously used the examine action on the door, will also display a hint on what to do next.
     *
     * If the user chooses "door", the game will check for the hasKey flag. If true, it will unlock the door but not open it.
     * If false, the user will jiggle the doorknob and nothing else will happen.
     * @param item the item for the user to open in-game
     */
    private void open(String item) {
        System.out.println("Your hand reaches out towards the " + item + ".");
        if (item.equals("mailbox")) {
            if (hasKey) {
                System.out.println("You find nothing else of note in the mailbox.");
                return;
            }
            System.out.println("You reach in and pull out a key.");
            hasKey = true;
            if (hasExaminedDoor) {
                System.out.println("You have a good feeling that the key will be able to unlock the doorknob.");
            }
        } else if (item.equals("door")) {
            if (hasKey) {
                System.out.println("You walk over to the door and put the key in the doorknob. The door unlocks, but the boards are blocking you from opening it completely.");
                System.out.println("You decide to leave.");
            } else {
                System.out.println("You walk over to the door and jiggle the doorknob. Nothing happens.");
            }
        } else {
            System.out.println("Nothing happens. You decide to try something else.");
        }
    }

    /**
     * Examines the item passed through via user input. If the user doesn't pass in "mailbox" or "door", will show default message.
     *
     * If user chooses "mailbox", the game will say that the user opens the mailbox door and sees something in the back.
     *
     * If the user chooses "door", the game will say that the user noticed the doorknob, and will then set the hasExaminedDoor flag to true.
     * Then, the method will check for the hasKey flag. If true, it will provide a hint on what to do next.
     * @param item the item for the user to examine in-game
     */
    private void examine(String item) {
        System.out.println("You move in closer to get a better look at the " + item + ".");
        if (item.equals("mailbox")) {
            System.out.println("The door of the mailbox is cracked open just a bit. You see something in the back.");
        } else if (item.equals("door")) {
            System.out.println("The door is boarded up tightly with just a doorknob sticking out.");
            hasExaminedDoor = true;
            if (hasKey) {
                System.out.println("Maybe the key you found in the mailbox will unlock this door?");
            }
        } else {
            System.out.println("You find nothing of interest.");
        }
    }
}
