package minesweeper;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    
    private final HighScoreManager hsm;
    
    public HighScoresWindowController() {
        hsm = new HighScoreManager();
    }

    @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadData();
  }

  public void loadData() {
    HighScore bestTimeKinderleicht = hsm.getScore(Level.Kinderleicht);
    HighScore bestTimeNormal = hsm.getScore(Level.Normal);
    HighScore bestTimeSchwer = hsm.getScore(Level.Schwer);
    HighScore bestTimeMcGyver = hsm.getScore(Level.McGyver);

    lblTimeKinderleicht.setText(bestTimeKinderleicht.getScore() + " secondes");
    lblNameKinderleicht.setText(bestTimeKinderleicht.getName());

    lblTimeNormal.setText(bestTimeNormal.getScore() + " secondes");
    lblNameNormal.setText(bestTimeNormal.getName());

    lblTimeSchwer.setText(bestTimeSchwer.getScore() + " secondes");
    lblNameSchwer.setText(bestTimeSchwer.getName());
    
    lblTimeMcGyver.setText(bestTimeMcGyver.getScore() + " secondes");
    lblNameMcGyver.setText(bestTimeMcGyver.getName());
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
}