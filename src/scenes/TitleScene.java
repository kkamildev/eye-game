package scenes;

import components.Button;
import components.Vector2;
import main.App;
import main.ContentManager;
import main.Game;

import java.awt.*;

public class TitleScene extends Scene{

    private final Button startButton;
    private final Button rulesButton;
    private final Button exitButton;

    public TitleScene(Game game) {
        super(game);
        startButton = new components.Button(new Vector2(50, 170), new Vector2(200, 80), "Zagraj", Game.contentManager.getFont(ContentManager.FontName.LARGER));
        rulesButton = new components.Button(new Vector2(50, 280), new Vector2(200, 80), "Zasady", Game.contentManager.getFont(ContentManager.FontName.LARGER));
        exitButton = new Button(new Vector2(50, 390), new Vector2(200, 80), "Wyjście", Game.contentManager.getFont(ContentManager.FontName.LARGER));
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.setColor(Color.WHITE);
        g2.drawString("Gra w oczko", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));
        startButton.draw(g2);
        rulesButton.draw(g2);
        exitButton.draw(g2);
    }

    @Override
    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
        if(rulesButton.checkClicked()) {
            game.loadScene(game.rulesScene);
        }
    }
}
