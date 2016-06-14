package minesweeper;

import java.io.Serializable;
import java.util.Hashtable;

public class HighScore implements Serializable {
    
    private int score;
    private String name;

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
}