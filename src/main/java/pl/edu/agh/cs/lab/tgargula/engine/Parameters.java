package pl.edu.agh.cs.lab.tgargula.engine;

public final class Parameters {

    private final int obstacleX;
    private final int enemiesX;
    private final int powerUpX;

    private int obstacleCounter = 0;
    private int enemiesCounter = 0;
    private int powerUpCounter = 0;

    public Parameters(int obstacleX, int enemiesX, int powerUpX) {
        this.obstacleX = obstacleX;
        this.enemiesX = enemiesX;
        this.powerUpX = powerUpX;
    }

    public boolean isObstacleCertain() {
        return obstacleCounter >= obstacleX;
    }

    public boolean isEnemyCertain() {
        return enemiesCounter >= enemiesX;
    }

    public boolean isPowerUpCertain() {
        return powerUpCounter >= powerUpX;
    }

    public void incrementCounters() {
        obstacleCounter++;
        enemiesCounter++;
        powerUpCounter++;
    }

    public void resetObstacleCounter() {
        obstacleCounter = 0;
    }

    public void resetEnemiesCounter() {
        enemiesCounter = 0;
    }

    public void resetPowerUpCounter() {
        powerUpCounter = 0;
    }

}
