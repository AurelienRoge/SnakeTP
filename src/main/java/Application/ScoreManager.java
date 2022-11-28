package Application;


import javafx.scene.text.Text;

//Singleton gestionnaire du système de score
public class ScoreManager {
    private static ScoreManager instance;
    private int score = 0;
    private int bestScore = 0;

    private ScoreManager(){

    }

    public static ScoreManager getScoreManager(){
        if(instance == null){
            return new ScoreManager();
        }
        return instance;
    }


    public void increaseScore(int value){
        score += value;
    }

    public void incrementScore(){
        score++;
    }

    public int getScoreValue(){
        return score;
    }

    public int getBestScore(){
        return bestScore;
    }

    public void resetScoreValue(){
        if(score > bestScore){
            bestScore = score;
        }
        score = 0;
    }
}
