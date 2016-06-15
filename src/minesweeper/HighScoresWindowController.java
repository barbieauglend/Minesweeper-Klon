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
    
    private final HighScoreManager hsm;
    
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
    HighScore bestTimeKinderleicht = hsm.getScore("Kinderleicht");
    HighScore bestTimeNormal = hsm.getScore("Normal");
    HighScore bestTimeSchwer = hsm.getScore("Schwer");
    HighScore bestTimeMcGyver = hsm.getScore("McGyver");

    lblTimeKinderleicht.setText(bestTimeKinderleicht.getScore() + " secondes");
    lblNameKinderleicht.setText(bestTimeKinderleicht.getName());

    lblTimeNormal.setText(bestTimeNormal.getScore() + " secondes");
    lblNameNormal.setText(bestTimeNormal.getName());

    lblTimeSchwer.setText(bestTimeSchwer.getScore() + " secondes");
    lblNameSchwer.setText(bestTimeSchwer.getName());
    
    lblTimeMcGyver.setText(bestTimeMcGyver.getScore() + " secondes");
    lblNameMcGyver.setText(bestTimeMcGyver.getName());
  }
  
  public void loadData(Level level) throws IOException, FileNotFoundException, ClassNotFoundException{
    Hashtable H = hsm.loadScores();    
    
    if(level == Level.Kinderleicht){
        HighScore bestTimeKinderleicht = hsm.getScore("Kinderleicht");
        lblTimeKinderleicht.setText(Integer.toString(bestTimeKinderleicht.getScore()) + " secondes");
        lblNameKinderleicht.setText(bestTimeKinderleicht.getName());
    }
    
    if(level == Level.Normal){
        HighScore bestTimeNormal = hsm.getScore("Normal");
        lblTimeNormal.setText(Integer.toString(bestTimeNormal.getScore()) + " secondes");
        lblNameNormal.setText(bestTimeNormal.getName());
    }
    
    if(level == Level.Schwer){
        HighScore bestTimeSchwer = hsm.getScore("Schwer");
        lblTimeSchwer.setText(Integer.toString(bestTimeSchwer.getScore()) + " secondes");
        lblNameSchwer.setText(bestTimeSchwer.getName());
    }
    
    if(level == Level.McGyver){
        HighScore bestTimeMcGyver = hsm.getScore("McGyver");
        lblTimeSchwer.setText(Integer.toString(bestTimeMcGyver.getScore()) + " secondes");
        lblNameSchwer.setText(bestTimeMcGyver.getName());
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
}