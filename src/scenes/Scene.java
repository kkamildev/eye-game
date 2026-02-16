package scenes;

import main.App;
import main.Game;

import java.awt.*;

public abstract class Scene {
    protected final App app;
    protected final Game game;
    public Scene(Game game) {
        this.game = game;
        this.app = game.app;
    }
    public void draw(Graphics2D g2) {

    }
    public void update() {

    }
}
