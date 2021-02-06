package pl.edu.agh.cs.lab.tgargula.elements;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;

public class Fire extends AbstractElement {
    public Fire(Position position) {
        super(position, new ImageView("images/fire.png"));
    }
}
