package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.PowerUps;

public class BulletEngine {

    private final Pane commonBulletPane;
    private final Pane bouncyBulletPane;
    private final Pane fastBulletPane;
    private final Pane strongBulletPane;
    private final Pane twoStepsPowerUpPane;
    private final Pane immortalityPane;

    private final Text bouncyBullets = new Text("1");
    private final Text fastBullets = new Text("1");
    private final Text strongBullets = new Text("1");
    private final Text twoSteps = new Text("1");
    private final Text immortality = new Text("1");

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

    public void resetImmortalityPowerUp() {
        immortalityPane.getStyleClass().remove("using");
    }

    public void resetTwoMovesPowerUp() {
        twoStepsPowerUpPane.getStyleClass().remove("using");
    }

    public void addPowerUp(PowerUps powerUp) {
        Text text;
        switch (powerUp) {
            case BOUNCY_BULLET -> text = bouncyBullets;
            case FAST_BULLET -> text = fastBullets;
            case STRONG_BULLET -> text = strongBullets;
            case IMMORTALITY -> text = immortality;
            case TWO_MOVES -> text = twoSteps;
            default -> throw new IllegalStateException("Unexpected value: " + powerUp);
        }

        text.setText(String.valueOf(Integer.parseInt(text.getText()) + 1));
    }

    public void choose(Bullets bulletType) {
        chosenBullet = bulletType;
        switch (bulletType) {
            case COMMON -> select(commonBulletPane);
            case BOUNCY -> {
                if (isAvailable(PowerUps.BOUNCY_BULLET)) select(bouncyBulletPane);
            }
            case FAST -> {
                if (isAvailable(PowerUps.FAST_BULLET)) select(fastBulletPane);
            }
            case STRONG -> {
                if (isAvailable(PowerUps.STRONG_BULLET)) select(strongBulletPane);
            }
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
            case COMMON -> choose(
                    isAvailable(PowerUps.BOUNCY_BULLET) ? Bullets.BOUNCY :
                            isAvailable(PowerUps.FAST_BULLET) ? Bullets.FAST :
                                    isAvailable(PowerUps.STRONG_BULLET) ? Bullets.STRONG : Bullets.COMMON
            );
            case BOUNCY -> choose(
                    isAvailable(PowerUps.FAST_BULLET) ? Bullets.FAST :
                            isAvailable(PowerUps.STRONG_BULLET) ? Bullets.STRONG : Bullets.COMMON
            );
            case FAST -> choose(
                    isAvailable(PowerUps.STRONG_BULLET) ? Bullets.STRONG : Bullets.COMMON
            );
            case STRONG -> choose(Bullets.COMMON);
        }
    }

    public void previousBullet() {
        switch (chosenBullet) {
            case COMMON -> choose(
                    isAvailable(PowerUps.STRONG_BULLET) ? Bullets.STRONG :
                            isAvailable(PowerUps.FAST_BULLET) ? Bullets.FAST :
                                    isAvailable(PowerUps.BOUNCY_BULLET) ? Bullets.BOUNCY : Bullets.COMMON
            );
            case BOUNCY -> choose(Bullets.COMMON);
            case FAST -> choose(
                    isAvailable(PowerUps.BOUNCY_BULLET) ? Bullets.BOUNCY : Bullets.COMMON
            );
            case STRONG -> choose(
                    isAvailable(PowerUps.FAST_BULLET) ? Bullets.FAST :
                            isAvailable(PowerUps.BOUNCY_BULLET) ? Bullets.BOUNCY : Bullets.COMMON
            );
        }
    }

    public Bullets getChosenBullet() {
        return chosenBullet;
    }

    public boolean isAvailable(PowerUps powerUp) {
        return switch (powerUp) {
            case FAST_BULLET -> Integer.parseInt(fastBullets.getText()) > 0;
            case BOUNCY_BULLET -> Integer.parseInt(bouncyBullets.getText()) > 0;
            case STRONG_BULLET -> Integer.parseInt(strongBullets.getText()) > 0;
            case TWO_MOVES -> Integer.parseInt(twoSteps.getText()) > 0;
            case IMMORTALITY -> Integer.parseInt(immortality.getText()) > 0;
        };
    }

    public void usePowerUp(PowerUps powerUp) {

        switch (powerUp) {
            case TWO_MOVES -> twoStepsPowerUpPane.getStyleClass().add("using");
            case IMMORTALITY -> immortalityPane.getStyleClass().add("using");
        }

        Text text;
        switch (powerUp) {
            case FAST_BULLET -> text = fastBullets;
            case BOUNCY_BULLET -> text = bouncyBullets;
            case STRONG_BULLET -> text = strongBullets;
            case TWO_MOVES -> text = twoSteps;
            case IMMORTALITY -> text = immortality;
            default -> throw new UnsupportedOperationException();
        }

        text.setText(String.valueOf(Integer.parseInt(text.getText()) - 1));
    }
}
