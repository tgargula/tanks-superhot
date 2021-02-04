package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;

public enum Bullets {
    COMMON, BOUNCY, FAST, STRONG;

    public IBullet createBullet(Position position, Direction direction) {
        return switch (this) {
            case COMMON -> new CommonBullet(position, direction);
            case BOUNCY -> new BouncyBullet(position, direction);
            case FAST -> new FastBullet(position, direction);
            case STRONG -> new StrongBullet(position, direction);
        };
    }
}
