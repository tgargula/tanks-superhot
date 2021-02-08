package pl.edu.agh.cs.lab.tgargula.widget;

import com.sun.javafx.application.PlatformImpl;

public class MainWrapper {

    public static void main(String[] args) {
        PlatformImpl.startup(() -> {
        });

        Main.main(args);
    }

}
