package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public final class SceneSwitchers {

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public final Consumer<Integer> gameOverSwitcher = points -> {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameOver.fxml"));
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            root.getScene().getStylesheets().clear();
            root.getScene().getStylesheets().add(this.getClass().getResource("/style/game_over.css").toExternalForm());
            ((GameOverController) loader.getController()).getScoreText().setText("Score: " + points);
        } catch (IOException | NullPointerException e) {
            stage.close();
        }
    };

    public final Consumer<ActionEvent> newGameSwitcher = e -> {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Root.fxml"));
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            root.getScene().getStylesheets().clear();
            root.getScene().getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
            root.getStyleClass().add("scene");
            root.requestFocus();
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
            stage.close();
        }
    };

    public final Consumer<ActionEvent> settingsSwitcher = e -> {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Settings.fxml"));
        try {
            Parent root = loader.load();
            stage.getScene().setRoot(root);
            root.getScene().getStylesheets().clear();
            root.getScene().getStylesheets().add(this.getClass().getResource("/style/settings.css").toExternalForm());
            root.getStyleClass().add("scene");
        } catch (IOException | NullPointerException exception) {
            stage.close();
        }
    };

}
