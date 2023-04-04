package battleship;

import java.util.Scanner;

public class Game {
    private int rows;
    private int cols;
    private int kiniseis;
    private Player humanPlayer;
    private Player computerPlayer;

    public void init(){
        Scanner scanner = new Scanner(System.in);

        // sel. 1 arxikopoiisi
        humanPlayer = new HumanPlayer("alexPap");
        computerPlayer = new ComputerPlayer("pc");

        humanPlayer.initField(13, 11);
        computerPlayer.initField(13, 11);
    }

    public void placeShips(){
        humanPlayer.placeShips(computerPlayer.getField());
        System.out.println("\n\n");
        computerPlayer.placeShips(humanPlayer.getField());
        System.out.println("\n\nAll ships are set!!\n");
    }

    public void play(){
        int rounds = 1;
        while(humanPlayer.hasWon() == false && computerPlayer.hasWon() == false){
            System.out.println("Round " + rounds);
            humanPlayer.play();
            
            computerPlayer.play();
            
            rounds++;
        }
       // System.out.println(humanPlayer.getField().toStringWithShips());
       // System.out.println(computerPlayer.getField().toStringWithShips());
    }
    
    
    public void showResult(){
        System.out.println("Player " + humanPlayer.getName() + " score: " + humanPlayer.getScore());
        System.out.println("Player " + computerPlayer.getName() + " score: " + computerPlayer.getScore());
        
        if (humanPlayer.hasWon()) System.out.println("Player " + humanPlayer.getName() + " wins!");
        if (computerPlayer.hasWon()) System.out.println("Player " + computerPlayer.getName() + " wins!");
    }
}