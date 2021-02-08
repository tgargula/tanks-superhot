package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.PowerUps;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.BulletEngine;

public enum Bullets {
    COMMON, BOUNCY, FAST, STRONG;

    public static Bullets get(BulletEngine engine, ITank tank) {
        if (tank instanceof PlayerTank) {
            Bullets chosenBullet = engine.getChosenBullet();
            if (!chosenBullet.equals(COMMON)) {
                engine.usePowerUp(Bullets.parseBullets(engine.getChosenBullet()));
                engine.choose(Bullets.COMMON);
            }
            return chosenBullet;
        }
        return getRandom();
    }

    private static Bullets getRandom() {
        double random = Math.random();
        return random < 0.7 ? COMMON : random < 0.8 ? BOUNCY : random < 0.9 ? FAST : STRONG;
    }

    private static PowerUps parseBullets(Bullets powerUp) {
        return switch (powerUp) {
            case STRONG -> PowerUps.STRONG_BULLET;
            case FAST -> PowerUps.FAST_BULLET;
            case BOUNCY -> PowerUps.BOUNCY_BULLET;
            default -> throw new UnsupportedOperationException("Unable to parse " + powerUp + " to Bullets!");
        };
    }

    public IBullet createBullet(Position position, Direction direction) {
        return switch (this) {
            case COMMON -> new CommonBullet(position, direction);
            case BOUNCY -> new BouncyBullet(position, direction);
            case FAST -> new FastBullet(position, direction);
            case STRONG -> new StrongBullet(position, direction);
        };
    }
}
