package minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

public final class HighScoreManager implements Serializable {
        
    private Hashtable<String, HighScore> scores;
    File file;

    @SuppressWarnings("Convert2Diamond")
    public HighScoreManager() throws IOException {
        scores = new Hashtable<String, HighScore>(); 
        file = new File("highscore.txt");
        initialScores();
    }
    
    public void initialScores() throws IOException{
        HighScore joker = new HighScore("not you", 9999);
        scores.put("Kinderleicht", joker);
        scores.put("Normal", joker);
        scores.put("Schwer", joker);
        scores.put("McGyver", joker);
        saveScores();
    }

    public void addScore(String level, HighScore s) throws IOException {
        HighScore highscore = getScore(level);
        if(s.getScore() < highscore.getScore()){
            scores.put(level, s);
        }
        saveScores();
    }

    public HighScore getScore(String level) {
        return scores.get(level);
    }

    private void saveScores() throws IOException {
        /*try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(scores.toString());
        }catch (Exception e){
        }*/
        
        FileOutputStream fos = new FileOutputStream(file);
            if(!file.exists()){
            file.createNewFile();
                }
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(scores);
        }
    
    public Hashtable loadScores() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Hashtable h = (Hashtable)in.readObject();
        return h;
    }
}
    
