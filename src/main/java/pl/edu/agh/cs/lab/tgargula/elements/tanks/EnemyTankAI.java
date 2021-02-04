package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;

public class EnemyTankAI {

    private final EnemyTank tank;

    public EnemyTankAI(EnemyTank tank) {
        this.tank = tank;
    }

    public Direction bestShootDirection(PlayerTank playerTank) {
        return tank.getPosition().bestShootDirection(playerTank.getPosition());
    }

    public Direction bestMoveDirection(PlayerTank playerTank) {
        return tank.getPosition().bestMoveDirection(playerTank.getPosition());
    }
}
