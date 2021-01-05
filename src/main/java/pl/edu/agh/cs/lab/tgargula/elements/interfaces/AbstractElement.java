package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IObserver;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractElement implements IElement {

    protected final List<IObserver> observers = new LinkedList<>();
    protected final ImageView imageView;

    protected Position position;

    protected AbstractElement(Position position, ImageView imageView) {
        this.position = position;
        this.imageView = imageView;
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
    public ImageView getImageView() {
        return imageView;
    }
}
