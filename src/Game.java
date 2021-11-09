import java.util.Locale;
import java.util.Scanner;

public class Game {
    private Player p1, p2, p3;
    private Scanner sc;
    private LoadPhrase loadPhrase;


    public Game(Player pl1, Player pl2, Player pl3, LoadPhrase inWords, Scanner scan){
        p1 = pl1;
        p2 = pl2;
        p3 = pl3;
        sc = scan;
        loadPhrase = inWords;

        boolean gamePlay = true;
        while(gamePlay){
            turn(p1);
            turn(p2);
            turn(p3);
        }
    }

    public void turn(Player player){
        System.out.println(loadPhrase.getPhrase());
        printScores();
        System.out.println(player.getName() + "'s turn");
        boolean run = true;
        while(run){
            System.out.println(loadPhrase.getSolvedPhrase());
            System.out.println("Guess or solve");
            String response = sc.nextLine().toLowerCase();
            boolean isGuessed = true;
            while(isGuessed){
                if((response.length() == 1)){
                    isGuessed = loadPhrase.ifGuessed(response);
                    if(isGuessed){
                        System.out.println("Already guessed, try again");
                        response = sc.nextLine().toLowerCase();
                    } else{
                        loadPhrase.addGuessed(response);
                    }
                } else{
                    isGuessed = false;
                }

            }
            if (response.length() == 1){ //guessing
                int guessValue = ((int)(Math.random() * 6 + 1)) * 100;
                int correct = loadPhrase.checkGuess(response);
                if(correct == 0)
                    run = false;
                else {
                    player.incScore(correct * guessValue);
                    printScore(player);
                    System.out.println("");
                }
            }
            else {
                if(response.equals(loadPhrase.getPhrase().toLowerCase())){
                    loadPhrase.resetBoard();
                    player.incScore(20000);
                    System.out.println("Play another turn?");
                    if (sc.nextLine().substring(0,1).toLowerCase().equals("y"))
                        turn(player);
                }
                run = false;
            }
        }
    }
    private void printBoard(){
        System.out.println(loadPhrase.getSolvedPhrase());
    }
    private void printScore(Player pl){
        System.out.print(pl.getName() + "'s score: " + pl.getScore() + "   ");
    }
    private void printScores(){
        printScore(p1);
        printScore(p2);
        printScore(p3);
        System.out.println("");
    }
}
