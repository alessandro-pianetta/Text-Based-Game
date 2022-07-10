import TextBasedGame.TextBasedGame;

/**
 *
 * @author Alessandro Pianetta
 */

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextBasedGame game = new TextBasedGame();

        System.out.println("Welcome to the game! To start the game, use the command \"about\". If you don't know what commands are available, please use the command \"help\".");
        game.input();
    }
}