package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IObserver;

public interface IObservable {

    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void update();

}
