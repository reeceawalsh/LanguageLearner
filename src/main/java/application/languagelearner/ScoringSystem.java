package application.languagelearner;

public class ScoringSystem {
    private int score;
    public ScoringSystem() {
        this.score = 0;
    }

    public void increaseScore() {
        this.score ++;
    }

    public void decreaseScore() {
        this.score --;
    }

    public int getScore() {
        return this.score;
    }

    public void clearScore() {
        this.score = 0;
    }
}
