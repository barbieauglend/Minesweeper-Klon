package minesweeper;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class HighScore {
  Hashtable<String, ArrayList<String>> scores;
  
  public HighScore() {
    scores = new Hashtable<String, ArrayList<String>>();

    if (!new File("HighScores.xml").exists()) {
      initializeFile();
    } else {
      loadFile();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadFile() {
    XMLDecoder d;
    try {
      d = new XMLDecoder(new BufferedInputStream(new FileInputStream("HighScores.xml")));
      Object result = d.readObject();
      this.scores = (Hashtable<String, ArrayList<String>>) result;
      d.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err
          .println("An error occured while reading the file HighScores.xml. It seems corrupted. The file will be reset.");
      initializeFile();
    }


  }

  public void deleteHighScores() {
    initializeFile();
  }

  private void initializeFile() {
    scores.put("Kinderleicht", new ArrayList<String>(Arrays.asList("", "999")));
    scores.put("Normal", new ArrayList<String>(Arrays.asList("", "999")));
    scores.put("Schwer", new ArrayList<String>(Arrays.asList("", "999")));
    scores.put("McGyver", new ArrayList<String>(Arrays.asList("", "999")));

    saveHighScores();
  }

  public void setHighScore(Level level, String playerName, int time) {
    scores.put(level.toString(), new ArrayList<String>(Arrays.asList(playerName, Integer.toString(time))));
    saveHighScores();
  }

  public boolean isHighestScoreForLevel(Level level, int time) {
    ArrayList<String> highestScore = scores.get(level.toString());
    return time < Integer.parseInt(highestScore.get(1));
  }

  public void saveHighScores() {
    FileOutputStream fos;
    try {
      fos = new FileOutputStream("HighScores.xml");
      XMLEncoder e = new XMLEncoder(fos);
      e.writeObject(scores);
      e.close();
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
  }
    public ArrayList<String> getHighestScoreForLevel(Level level) {
    return scores.get(level.toString());
  }
}