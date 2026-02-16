package main;

import java.awt.*;
import components.Button;
import components.Vector2;
import scenes.RulesScene;
import scenes.Scene;
import scenes.TitleScene;

public class Game {
    private final App app;
    private static final ContentManager contentManager = new ContentManager();

    private final Scene titleScene, rulesScene;

    private Scene actualScene;

    private final Button startButton;
    private final Button rulesButton;
    private final Button exitButton;

    public Game(App app) {
        this.app = app;
        this.titleScene = new TitleScene(app);
        this.rulesScene = new RulesScene(app);
        this.actualScene = titleScene;

        startButton = new Button(new Vector2(50, 170), new Vector2(200, 80), "Zagraj", contentManager.getFont(ContentManager.FontName.LARGER));
        rulesButton = new Button(new Vector2(50, 280), new Vector2(200, 80), "Zasady", contentManager.getFont(ContentManager.FontName.LARGER));
        exitButton = new Button(new Vector2(50, 390), new Vector2(200, 80), "Wyjście", contentManager.getFont(ContentManager.FontName.LARGER));
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());

        g2.setFont(contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.setColor(Color.WHITE);
        g2.drawString("Gra w oczko", 10, 80);
        g2.setFont(contentManager.getFont(ContentManager.FontName.SMALLER));
        startButton.draw(g2);
        rulesButton.draw(g2);
        exitButton.draw(g2);
    }

    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
    }
}
