package minesweeper;

import java.io.Serializable;

public class HighScore implements Serializable {
    
    private final int score;
    private final String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public HighScore(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
    public String toString(HighScore score){
        String hs = Integer.toString(score.getScore());
        return score.getName() + "  "  + hs;
    }
}