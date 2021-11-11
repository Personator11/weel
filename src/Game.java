import java.util.Locale;
import java.util.Scanner;

public class Game {
    private Player p1, p2, p3;
    private Scanner sc;
    private LoadPhrase loadPhrase;


    public Game(Player pl1, Player pl2, Player pl3, LoadPhrase inWords, Scanner scan){ //runs the game and turns
        p1 = pl1;
        p2 = pl2;
        p3 = pl3;
        sc = scan;
        loadPhrase = inWords;

        boolean gamePlay = true;
        while(gamePlay){ //repeats the game untill its over
            turn(p1);
            turn(p2);
            turn(p3);
        }
    }

    private void turn(Player player){ //a method for one turn in the game
        System.out.println(loadPhrase.getPhrase());
        printScores();
        System.out.println(player.getName() + "'s turn");
        boolean run = true;
        while(run){ //asks to guess or solve and checks the answer to assign points (in player class) and end turn
            System.out.println(loadPhrase.getSolvedPhrase());
            System.out.println("Guess or solve");
            String response = sc.nextLine().toLowerCase();
            boolean isGuessed = true;
            while(isGuessed){ // checks to see if a guess is already main if a guess is mroe than 1 it is attempting to solve and therefore not guessed
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
            if (response.length() == 1){ //guessing not solving
                int guessValue = ((int)(Math.random() * 6 + 1)) * 100; //spinner
                int correct = loadPhrase.checkGuess(response); //checks if the guess is correct
                if(correct == 0) // ends turn
                    run = false;
                else {
                    player.incScore(correct * guessValue); //sends values to player to generate core
                    printScore(player);
                    System.out.println("");
                }
            }
            else { //solving not guessing
                if(response.equals(loadPhrase.getPhrase().toLowerCase())){//checks to see if the attempt to solve is correct
                    loadPhrase.resetBoard();
                    player.incScore(20000);
                    System.out.println("Play another turn?");
                    if (sc.nextLine().substring(0,1).toLowerCase().equals("y")) //restarts the game
                        turn(player);
                }
                run = false;
            }
        }
    }
    private void printScore(Player pl){
        System.out.print(pl.getName() + "'s score: " + pl.getScore() + "   ");
    }
    private void printScores(){ //prints the score
        printScore(p1);
        printScore(p2);
        printScore(p3);
        System.out.println("");
    }
}
