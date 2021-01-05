package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public interface IElement extends IObservable {

    Position getPosition();

    ImageView getImageView();

}
