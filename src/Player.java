public class Player {
    private String name;
    private int score;

    public Player(String inName){//sets player score to 0 at start
        name = inName;
        score = 0; 
    }

    public String getName(){ //accessor
        return name;
    } //acker

    public void incScore(int inScore){ //mutator
        score += inScore;
    } //mooter

    public int getScore(){ //mutator
        return score;
    } //mutator
}
