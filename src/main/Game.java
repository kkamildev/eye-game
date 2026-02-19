package main;

import java.awt.*;
import java.io.IOException;

import scenes.MainScene;
import scenes.RulesScene;
import scenes.Scene;
import scenes.TitleScene;

public class Game {
    public App app;
    public static final ContentManager contentManager;

    static {
        try {
            contentManager = new ContentManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final Scene titleScene, rulesScene, mainScene;

    private Scene actualScene;

    public Game(App app) {
        this.app = app;
        this.titleScene = new TitleScene(this);
        this.rulesScene = new RulesScene(this);
        this.mainScene = new MainScene(this);
        this.actualScene = titleScene;
    }
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        actualScene.draw(g2);
    }

    public void update() {
        actualScene.update();
    }

    public void loadScene(Scene scene) {
        this.actualScene = scene;
    }

}
