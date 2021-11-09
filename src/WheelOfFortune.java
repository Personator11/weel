import java.util.Locale;
import java.util.Scanner;

public class WheelOfFortune {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player 1, enter your name");
        Player p1 = new Player(scanner.nextLine());
        System.out.println("Player 2, enter your name");
        Player p2 = new Player(scanner.nextLine());
        System.out.println("Player 3, enter your name");
        Player p3 = new Player(scanner.nextLine());
        LoadPhrase board = new LoadPhrase();
        new Game(p1, p2, p3, board, scanner);
    }
}
