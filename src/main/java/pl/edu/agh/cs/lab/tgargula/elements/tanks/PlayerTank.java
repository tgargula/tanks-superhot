package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.CommonBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;

import java.util.HashSet;
import java.util.Set;

public class PlayerTank extends AbstractTank {

    public PlayerTank(Position position, int durability) {
        super(position, durability, new ImageView("/images/player_tank3.png"));
    }

    public int getDurability() {
        return currentDurability;
    }

    public void addHeart() {
        currentDurability++;
    }

    public Set<Position> safeZone() {
        Set<Position> safePositions = new HashSet<>();
        for (int i = position.x - 2; i <= position.x + 2; i++)
            for (int j = position.y - 2; j <= position.y + 2; j++)
                safePositions.add(Position.of(i, j));
        return safePositions;
    }
}
