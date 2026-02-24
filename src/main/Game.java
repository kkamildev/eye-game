package main;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import scenes.*;

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

    public Scene titleScene, rulesScene, mainScene, leaderBoardScene;

    private Scene actualScene;

    public Game(App app) {
        this.app = app;
        loadGame();
        this.actualScene = titleScene;
    }
    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        actualScene.draw(g2);
    }

    public void update() {
        actualScene.update();
    }

    public void loadGame() {
        this.titleScene = new TitleScene(this);
        this.rulesScene = new RulesScene(this);
        this.mainScene = new MainScene(this);
        this.leaderBoardScene = new LeaderBoardScene(this);
    }

    public void loadScene(Scene scene) {
        this.actualScene = scene;
    }

    public void loadScene(Scene scene, int millisecondsDelay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                actualScene = scene;
                timer.cancel();
            }
        }, millisecondsDelay, 1000);
    }

}
