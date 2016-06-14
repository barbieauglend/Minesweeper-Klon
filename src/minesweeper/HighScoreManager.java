package minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class HighScoreManager implements Serializable {
        
    private Hashtable<Level, HighScore> scores;

    public HighScoreManager() {
        scores = new Hashtable<Level, HighScore>(); 
    }

    public void addScore(Level level, HighScore s) throws IOException {
        HighScore highscore = getScore(level);
        if(s.getScore() > highscore.getScore()){
        scores.put(level, s);
        saveScores();
        }
    }

    public HighScore getScore(Level level) {
        return scores.get(level);
    }

    private void saveScores() throws IOException {
        FileOutputStream fos = new FileOutputStream("minesweeper/images/highscore.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(scores);
        oos.close();
        fos.close();
    }
    
    public Hashtable <Level, HighScore> loadScores() throws IOException, ClassNotFoundException {
        FileInputStream reader = new FileInputStream("minesweeper/images/highscore.txt");
        ObjectInputStream buffer = new ObjectInputStream(reader);
        Object obj=buffer.readObject();
        scores = (Hashtable <Level, HighScore>)obj;
        buffer.close();
        reader.close();
        return scores;
    }
}
    
