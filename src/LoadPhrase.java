import java.util.*;
import java.io.File;
public class LoadPhrase {
    private String phrase = "";
    private String solvedPhrase = "";
    private String guessedString = "";
    private int wheelSpinValue;
    public String getGuessed(){
        return guessedString;
    }

    public LoadPhrase() {
        phrase = createBoard();
    }

    public void resetBoard(){
        phrase = createBoard();
    }

    public String createBoard() //creates board
    {
        guessedString = "";
        String tempPhrase = "";
        solvedPhrase = "";
        int numOfLines = 0;
        try
        {
            Scanner sc = new Scanner(new File("words.txt"));
            while (sc.hasNextLine())
            {
                tempPhrase = sc.nextLine().trim();
                numOfLines++;
            }
        } catch(Exception e) { System.out.println("Error reading or parsing words.txt"); }

        int randomInt = (int) ((Math.random() * numOfLines) + 1);

        try
        {
            int count = 0;
            Scanner sc = new Scanner(new File("words.txt"));
            while (sc.hasNextLine())
            {
                count++;
                String temp = sc.nextLine().trim();
                if (count == randomInt)
                {
                    tempPhrase = temp;
                }
            }
        } catch (Exception e) { System.out.println("Error reading or parsing words.txt"); }

        for (int i = 0; i < tempPhrase.length(); i++)
        {
            if (tempPhrase.substring(i, i + 1).equals(" "))
            {
                solvedPhrase += "  ";
            }
            else
            {
                solvedPhrase += "_ ";
            }
        }
        //System.out.println(solvedPhrase);
        return tempPhrase;
    }

    public int checkGuess(String guess){
        int numCorrect = 0;
        if (solvedPhrase.contains(guess))
            return 0;
        if (phrase.contains(guess)){
            for (int i = 0; i < phrase.length(); i++){
                if(phrase.substring(i,i + 1).equals(guess)){
                    String temp = everyOther(solvedPhrase);
                    numCorrect++;
                    solvedPhrase = expandString(temp.substring(0,i) + guess + temp.substring(i+1));
                }
            }
        }
        return numCorrect;
    }

    private String everyOther(String in){
        String str = "";
        for (int i = 0; i < in.length(); i++){
            if (i % 2 == 0)
                str += in.substring(i,i+1);
        }
        return str;
    }

    private String expandString(String in){
        String str = "";
        for (int i = 0; i < in.length(); i++){
            str += in.substring(i,i+1) + " ";
        }
        return str.substring(0,str.length() - 1);
    }

    public String getSolvedPhrase(){
        return solvedPhrase;
    }
    public String getPhrase(){
        return phrase;
    }
    public String getGuessedString() {
        return guessedString;
    }
    public void addGuessed(String guess){
        guessedString += guess;
    }
    public boolean ifGuessed(String guess){
        if(guessedString.contains(guess)){
            return true;
        } else{
            return false;
        }
    } //returns true if current guess has already been used
}




