package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.ImageView;

public abstract class AbstractVisible implements IVisible {

    protected ImageView imageView;

    protected AbstractVisible(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

}
