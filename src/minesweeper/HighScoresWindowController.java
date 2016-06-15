package minesweeper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HighScoresWindowController implements Initializable {

    @FXML
    Label lblTimeKinderleicht;
    @FXML
    Label lblTimeNormal;
    @FXML
    Label lblTimeSchwer;
    @FXML
    Label lblTimeMcGyver;
    @FXML
    Label lblNameKinderleicht;
    @FXML
    Label lblNameNormal;
    @FXML
    Label lblNameSchwer;
    @FXML
    Label lblNameMcGyver;
    
    private HighScoreManager hsm;
    
    public HighScoresWindowController() throws IOException{
        hsm = new HighScoreManager();
    }

    @Override
  public void initialize(URL location, ResourceBundle resources) {
        try {
            loadData();
        } catch (IOException | ClassNotFoundException ex) {
        }
  }

  public void loadData() throws IOException, FileNotFoundException, ClassNotFoundException {
    Hashtable H = hsm.loadScores();
    HighScore bestTimeMcGyver = (HighScore) H.get("MyGyver");
    HighScore bestTimeSchwer = (HighScore) H.get("Schwer");
    HighScore bestTimeNormal = (HighScore) H.get("Normal");
    HighScore bestTimeKinderleicht = (HighScore) H.get("Kinderleicht");

    lblTimeKinderleicht.setText(bestTimeKinderleicht.getScore() + " Sekunden");
    lblNameKinderleicht.setText(bestTimeKinderleicht.getName());

    lblTimeNormal.setText(bestTimeNormal.getScore() + " Sekunden");
    lblNameNormal.setText(bestTimeNormal.getName());

    lblTimeSchwer.setText(bestTimeSchwer.getScore() + " Sekunden");
    lblNameSchwer.setText(bestTimeSchwer.getName());
    
    lblTimeMcGyver.setText(bestTimeMcGyver.getScore() + " Sekunden");
    lblNameMcGyver.setText(bestTimeMcGyver.getName());
  }

}


  /**@FXML
  public void deleteHighScores() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.getButtonTypes().clear();
	alert.getButtonTypes().addAll(
	new ButtonType("Highscore löschen", ButtonBar.ButtonData.YES),
	new ButtonType("Beenden", ButtonBar.ButtonData.CANCEL_CLOSE)
	);
	alert.setTitle("Highscore");
	alert.setHeaderText("Highscore löschen");
	alert.setContentText("Bist Du sicher, dass Du es löschen möchtest?");

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get().getButtonData() == ButtonBar.ButtonData.YES)
            {
		highScores.deleteHighScores();
                loadData();
            }
    }*/