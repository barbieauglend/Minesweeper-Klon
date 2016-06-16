package minesweeper;

import java.io.IOException;
import java.net.URL;
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

	public HighScoresWindowController() throws IOException {
		hsm = new HighScoreManager();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			System.out.println("load");
			loadData();
		} catch (IOException ex) {
		}
	}

	public void loadData() throws IOException {
		hsm.update();
		lblTimeKinderleicht.setText(hsm.getScore("Kinderleicht") + " Sekunden");
		lblNameKinderleicht.setText(hsm.getName("Kinderleicht"));

		lblTimeNormal.setText(hsm.getScore("Normal") + " Sekunden");
		lblNameNormal.setText(hsm.getName("Normal"));

		lblTimeSchwer.setText(hsm.getScore("Schwer") + " Sekunden");
		lblNameSchwer.setText(hsm.getName("Schwer"));

		lblTimeMcGyver.setText(hsm.getScore("McGyver") + " Sekunden");
		lblNameMcGyver.setText(hsm.getName("McGyver"));
	}

}

/** keine Zeit mehr leider
 * @FXML public void deleteHighScores() { Alert alert = new
 *       Alert(Alert.AlertType.NONE); alert.getButtonTypes().clear();
 *       alert.getButtonTypes().addAll( new ButtonType("Highscore löschen",
 *       ButtonBar.ButtonData.YES), new ButtonType("Beenden",
 *       ButtonBar.ButtonData.CANCEL_CLOSE) ); alert.setTitle("Highscore");
 *       alert.setHeaderText("Highscore löschen"); alert.setContentText(
 *       "Bist Du sicher, dass Du es löschen möchtest?");
 *
 *       Optional<ButtonType> result = alert.showAndWait(); if
 *       (result.get().getButtonData() == ButtonBar.ButtonData.YES) {
 *       highScores.deleteHighScores(); loadData(); } }
 */
