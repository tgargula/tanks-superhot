package pl.edu.agh.cs.lab.tgargula.elements;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractVisible;

public class Heart extends AbstractVisible {

    public Heart() {
        super(new ImageView("/images/heart.png"));
    }

}
