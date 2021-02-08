package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;

public class BulletEngine {

    private final Pane commonBulletPane;
    private final Pane bouncyBulletPane;
    private final Pane fastBulletPane;
    private final Pane strongBulletPane;
    private final Pane twoStepsPowerUpPane;
    private final Pane immortalityPane;

    private final Text bouncyBullets = new Text("0");
    private final Text fastBullets = new Text("0");
    private final Text strongBullets = new Text("0");
    private final Text twoSteps = new Text("0");
    private final Text immortality = new Text("0");

    private Bullets chosenBullet = Bullets.COMMON;

    public BulletEngine(Pane commonBulletPane, Pane bouncyBulletPane, Pane fastBulletPane, Pane strongBulletPane,
                        Pane twoStepsPowerUpPane, Pane immortalityPane) {
        this.commonBulletPane = commonBulletPane;
        this.bouncyBulletPane = bouncyBulletPane;
        this.fastBulletPane = fastBulletPane;
        this.strongBulletPane = strongBulletPane;
        this.twoStepsPowerUpPane = twoStepsPowerUpPane;
        this.immortalityPane = immortalityPane;

        initialize();
    }

    private void initialize() {
        commonBulletPane.getChildren().addAll(
                new Text("1"),
                new ImageView("images/common_bullet.png"),
                new Text(Character.toString('\u221E'))
        );
        bouncyBulletPane.getChildren().addAll(
                new Text("2"),
                new ImageView("images/bouncy_bullet.png"),
                bouncyBullets
        );
        fastBulletPane.getChildren().addAll(
                new Text("3"),
                new ImageView("images/fast_bullet.png"),
                fastBullets
        );
        strongBulletPane.getChildren().addAll(
                new Text("4"),
                new ImageView("images/strong_bullet.png"),
                strongBullets
        );
        twoStepsPowerUpPane.getChildren().addAll(
                new Text("R"),
                new Text("TWO STEPS"),
                twoSteps
        );
        immortalityPane.getChildren().addAll(
                new Text("F"),
                new Text("IMMORTALITY"),
                immortality
        );
    }

    public void addBullet(Bullets bulletType) {
        Text text;
        switch (bulletType) {
            case BOUNCY -> text = bouncyBullets;
            case FAST -> text = fastBullets;
            case STRONG -> text = strongBullets;
            default -> throw new IllegalStateException("Unexpected value: " + bulletType);
        }

        text.setText(String.valueOf(Integer.parseInt(text.getText()) + 1));
    }

    public void choose(Bullets bulletType) {
        chosenBullet = bulletType;
        switch (bulletType) {
            case COMMON -> select(commonBulletPane);
            case BOUNCY -> select(bouncyBulletPane);
            case FAST -> select(fastBulletPane);
            case STRONG -> select(strongBulletPane);
        }
    }

    private void select(Pane pane) {
        commonBulletPane.getStyleClass().remove("selected-pane");
        bouncyBulletPane.getStyleClass().remove("selected-pane");
        fastBulletPane.getStyleClass().remove("selected-pane");
        strongBulletPane.getStyleClass().remove("selected-pane");
        pane.getStyleClass().add("selected-pane");
    }

    public void nextBullet() {
        switch (chosenBullet) {
            case COMMON -> choose(Bullets.BOUNCY);
            case BOUNCY -> choose(Bullets.FAST);
            case FAST -> choose(Bullets.STRONG);
            case STRONG -> choose(Bullets.COMMON);
        }
    }

    public void previousBullet() {
        switch (chosenBullet) {
            case COMMON -> choose(Bullets.STRONG);
            case BOUNCY -> choose(Bullets.COMMON);
            case FAST -> choose(Bullets.BOUNCY);
            case STRONG -> choose(Bullets.FAST);
        }
    }

    public Bullets getChosenBullet() {
        return chosenBullet;
    }
}
