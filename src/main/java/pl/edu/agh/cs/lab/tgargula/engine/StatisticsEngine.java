package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pl.edu.agh.cs.lab.tgargula.elements.Heart;

public class StatisticsEngine {

    private final Pane lifePane;
    private final Text score;

    public StatisticsEngine(Pane lifePane, Text score) {
        this.lifePane = lifePane;
        this.score = score;
    }

    public void initialize(int playerDurability) {
        for (int i = 0; i < playerDurability; i++)
            addHeart();
    }

    public void addHeart() {
        this.lifePane.getChildren().add(new Heart().getImageView());
    }

    public void removeHeart() {
        this.lifePane.getChildren().remove(0);
    }

    public void updateScore(int points) {
        this.score.setText(String.valueOf(Integer.parseInt(this.score.getText()) + points));
    }

}
