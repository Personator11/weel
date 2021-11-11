import java.util.*;
import java.io.File;
public class LoadPhrase {
    private String phrase = "";
    private String solvedPhrase = "";
    private String guessedString = "";
    public LoadPhrase() { //gets a correct answer loaded
        phrase = createBoard(); 
    }

    public void resetBoard(){ //gets a correct answer loaded
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

    public int checkGuess(String guess){ //checks to see if solvedphrase is equal to the phras (correct answer)
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

    private String everyOther(String in){ //removes spaces between each letter
        String str = "";
        for (int i = 0; i < in.length(); i++){
            if (i % 2 == 0)
                str += in.substring(i,i+1);
        }
        return str;
    }

    private String expandString(String in){ //add spaces between each letter
        String str = "";
        for (int i = 0; i < in.length(); i++){
            str += in.substring(i,i+1) + " ";
        }
        return str.substring(0,str.length() - 1);
    }

    public String getSolvedPhrase(){ //accessor
        return solvedPhrase;
    }
    public String getPhrase(){ //accessor
        return phrase;
    }
    public void addGuessed(String guess){ //mutator
        guessedString += guess;
    }
    public boolean ifGuessed(String guess){ //checks if letter was repeated
        if(guessedString.contains(guess)){
            return true;
        } else{
            return false;
        }
    } //returns true if current guess has already been used
}




