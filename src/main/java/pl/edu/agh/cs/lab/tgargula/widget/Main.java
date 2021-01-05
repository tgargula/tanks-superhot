package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Root.fxml"));

        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(this.getClass().getResource("/style/style.css").toExternalForm());
        scene.getRoot().getStyleClass().add("scene");
        scene.getRoot().requestFocus();

        stage.setScene(scene);
        stage.show();
    }

}
