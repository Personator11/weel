public class Player {
    private String name;
    private int score;

    public Player(String inName){
        name = inName;
        score = 0;
    }

    public String getName(){
        return name;
    } //acker

    public void incScore(int inScore){
        score += inScore;
    } //mooter

    public int getScore(){
        return score;
    } //mutator
}
