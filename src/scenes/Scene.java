package scenes;

import main.App;

import java.awt.*;

public abstract class Scene {
    protected final App app;
    public Scene(App app) {
        this.app = app;
    }
    public void draw(Graphics2D g2) {

    }
    public void update() {

    }
}
