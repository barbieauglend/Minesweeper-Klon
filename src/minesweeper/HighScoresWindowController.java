package minesweeper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HighScoresWindowController implements Initializable {

  @FXML
  Button btnOk;
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
  
  Level level;
  HighScore highScores;
  private Stage _stage;
  
  public HighScoresWindowController(String playerName, int time) {
    if (playerName == null) {
      setHighScore(level, "default", time);
    }
    setHighScore(level, playerName, time);
  }
  
  public HighScoresWindowController() {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    btnOk.setOnAction(e -> {
      Stage stage = ((Stage) btnOk.getScene().getWindow());
      stage.close();
    });

    loadData();
  }

  public void loadData() {
    ArrayList<String> bestTimeKinderleicht = highScores.getHighestScoreForLevel(Level.Kinderleicht);
    ArrayList<String> bestTimeNormal = highScores.getHighestScoreForLevel(Level.Normal);
    ArrayList<String> bestTimeSchwer = highScores.getHighestScoreForLevel(Level.Schwer);
    ArrayList<String> bestTimeMcGyver = highScores.getHighestScoreForLevel(Level.McGyver);

    lblTimeKinderleicht.setText(bestTimeKinderleicht.get(1) + " secondes");
    lblNameKinderleicht.setText(bestTimeKinderleicht.get(0));

    lblTimeNormal.setText(bestTimeNormal.get(1) + " secondes");
    lblNameNormal.setText(bestTimeNormal.get(0));

    lblTimeSchwer.setText(bestTimeSchwer.get(1) + " secondes");
    lblNameSchwer.setText(bestTimeSchwer.get(0));
    
    lblTimeMcGyver.setText(bestTimeMcGyver.get(1) + " secondes");
    lblNameMcGyver.setText(bestTimeMcGyver.get(0));
  }

  @FXML
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
    }

  private void setHighScore(Level level, String playerName, int time) {
    highScores.setHighScore(level, playerName, time);
  }
  
  public void SetPrimaryStage(Stage stage)
    {
	_stage = stage;
	_stage.setTitle("Highscores");
    }

}