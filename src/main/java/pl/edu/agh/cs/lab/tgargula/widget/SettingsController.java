package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;

public class SettingsController {

    private final SceneSwitchers sceneSwitchers = new SceneSwitchers();

    @FXML
    private Slider mapSizeSlider;

    @FXML
    private Slider levelSlider;

    @FXML
    private void initialize() {
        levelSlider.setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double aDouble) {
                return switch (aDouble.intValue()) {
                    case 1 -> "EASY";
                    case 2 -> "MEDIUM";
                    case 3 -> "HARD";
                    case 4 -> "INSANE";
                    default -> "";
                };
            }

            @Override
            public Double fromString(String s) {
                return switch (s) {
                    case "EASY" -> 1.;
                    case "MEDIUM" -> 2.;
                    case "HARD" -> 3.;
                    case "INSANE" -> 4.;
                    default -> 0.;
                };
            }
        });
    }

    public void startGame(ActionEvent e) {
        SceneController.setLevel((int) levelSlider.getValue());
        SceneController.setMapSize((int) mapSizeSlider.getValue());
        sceneSwitchers.newGameSwitcher.accept(e);
    }
}
