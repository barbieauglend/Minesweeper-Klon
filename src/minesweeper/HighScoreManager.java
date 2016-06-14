package minesweeper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class HighScoreManager implements Serializable {
        
    private Hashtable<Level, HighScore> scores;
    File file;

    public HighScoreManager() {
        scores = new Hashtable<Level, HighScore>(); 
        file = new File("minesweeper/images/highscore.txt");
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
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(scores.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        
        /**FileOutputStream fos = new FileOutputStream("minesweeper/images/highscore.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(scores);
        oos.close();
        fos.close();*/
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
    
