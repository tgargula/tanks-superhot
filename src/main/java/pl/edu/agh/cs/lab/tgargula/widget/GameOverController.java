package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private Text score;

    @FXML
    public Text getScoreText() {
        return score;
    }

    public void switchToSettings(ActionEvent e) {
        new SceneSwitchers().settingsSwitcher.accept(e);
    }

    public void switchToNewGame(ActionEvent e) {
        new SceneSwitchers().newGameSwitcher.accept(e);
    }

    public void exit(ActionEvent e) {
        ((Stage) score.getScene().getWindow()).close();
    }
}
