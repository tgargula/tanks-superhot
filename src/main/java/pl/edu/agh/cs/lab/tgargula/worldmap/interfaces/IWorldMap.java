package pl.edu.agh.cs.lab.tgargula.worldmap.interfaces;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;

public interface IWorldMap extends IObserver {

    IElement getElementAt(Position position);

    int getSize();

}