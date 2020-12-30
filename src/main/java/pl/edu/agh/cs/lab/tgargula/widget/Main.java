package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Pane(), 1000, 1000));
        stage.show();
    }

}
