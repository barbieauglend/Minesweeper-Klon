package minesweeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Properties;

public final class HighScoreManager implements Serializable {

	private final Properties scores;
	private final InputStream in;

	public HighScoreManager() throws IOException {
		scores = new Properties();
		File f = new File("highscores.properties");
		if (!f.exists()) {
			initialScores(new FileOutputStream("highscores.properties"));
		}
		in = new FileInputStream("highscores.properties");
		scores.load(in);
	}

	public void initialScores(FileOutputStream out) throws IOException {
		scores.setProperty("KinderleichtSpieler", "could be you");
		scores.setProperty("KinderleichtScore", "9999");
		scores.setProperty("NormalSpieler", "could be you");
		scores.setProperty("NormalScore", "9999");
		scores.setProperty("SchwerSpieler", "could be you");
		scores.setProperty("SchwerScore", "9999");
		scores.setProperty("McGyverSpieler", "could be you");
		scores.setProperty("McGyverScore", "9999");
		scores.store(out, null);
	}

	public void addScore(String levelName, String levelScore, String levelSpieler) throws IOException {
		int aktuell = Integer.parseInt(getScore(levelName));
		int newScore = Integer.parseInt(levelScore);
		if (newScore < aktuell) {
			scores.setProperty(levelName + "Spieler", levelSpieler);
			scores.setProperty(levelName + "Score", levelScore);
		}

		scores.store(new FileOutputStream("highscores.properties"), null);
	}

	public String getScore(String levelName) {
		return scores.getProperty(levelName + "Score");
	}

	public String getName(String levelName) {
		return scores.getProperty(levelName + "Spieler");
	}

	public void update() throws IOException {
		scores.load(in);
	}
}
