package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameOverController {

    @FXML
    private Text score;

    @FXML
    public Text getScoreText() {
        return score;
    }
}
