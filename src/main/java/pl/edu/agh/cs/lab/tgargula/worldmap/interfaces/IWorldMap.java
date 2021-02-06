package pl.edu.agh.cs.lab.tgargula.worldmap.interfaces;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public interface IWorldMap extends IObserver {

    boolean isOccupied(Position position);

    IElement getElementAt(Position position);

    int getSize();

    ITank getPlayerTank();

}
