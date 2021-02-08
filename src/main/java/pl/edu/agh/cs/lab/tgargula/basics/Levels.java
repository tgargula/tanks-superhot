package pl.edu.agh.cs.lab.tgargula.basics;

public enum Levels {

    EASY, MEDIUM, HARD, INSANE;

    public double getEnemyTankProbability() {
        return switch (this) {
            case EASY -> 0.1;
            case MEDIUM -> 0.25;
            case HARD -> 0.5;
            case INSANE -> 1;
        };
    }

    public double getPowerUpProbability() {
        return switch (this) {
            case EASY -> 0.3;
            case MEDIUM -> 0.2;
            case HARD -> 0.1;
            case INSANE -> 0.01;
        };
    }

    public double getObstacleProbability() {
        return switch (this) {
            case EASY, MEDIUM, HARD -> 0.2;
            case INSANE -> 0.5;
        };
    }

    public static boolean draw(double probability) {
        return Math.random() <= probability;
    }

}
