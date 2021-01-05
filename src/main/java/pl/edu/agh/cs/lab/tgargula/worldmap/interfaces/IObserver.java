package pl.edu.agh.cs.lab.tgargula.worldmap.interfaces;

import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;

public interface IObserver {

    void observe(IElement element);

    void stopObserving(IElement element);

}
