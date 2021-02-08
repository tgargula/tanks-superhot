package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.IObserver;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractElement extends AbstractVisible implements IElement {

    protected final List<IObserver> observers = new LinkedList<>();
    protected Position position;

    protected AbstractElement(Position position, ImageView imageView) {
        super(imageView);
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void update() {
        observers.forEach(observer -> observer.changePosition(this));
    }

    @Override
    public void destroy() {
        observers.forEach(observer -> observer.stopObserving(this));
    }

}
