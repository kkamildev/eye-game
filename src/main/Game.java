package main;

import java.awt.*;
import scenes.RulesScene;
import scenes.Scene;
import scenes.TitleScene;

public class Game {
    public App app;
    public static final ContentManager contentManager = new ContentManager();

    public final Scene titleScene, rulesScene;

    private Scene actualScene;

    public Game(App app) {
        this.app = app;
        this.titleScene = new TitleScene(this);
        this.rulesScene = new RulesScene(this);
        this.actualScene = titleScene;
    }
    public void draw(Graphics2D g2) {
        actualScene.draw(g2);
    }

    public void update() {
        actualScene.update();
    }

    public void loadScene(Scene scene) {
        this.actualScene = scene;
    }

}
