package scenes;

import components.Button;
import components.Vector2;
import main.ContentManager;
import main.Game;

import java.awt.*;

public class LoseScene extends Scene{

    private final Button exitButton, retryButton;
    private final boolean pointsExceeded;
    public LoseScene(Game game, boolean pointsExceeded) {
        super(game);
        this.pointsExceeded = pointsExceeded;
        this.retryButton = new Button(new Vector2(10, 190), new Vector2(200, 80), "Powtórz", ContentManager.FontName.LARGER);
        this.exitButton = new Button(new Vector2(10, 300), new Vector2(200, 80), "Wyjście", ContentManager.FontName.LARGER);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.drawString("Przegrana!", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        if(pointsExceeded) {
            g2.drawString("Przekroczyłeś punkty", 10, 130);
        } else {
            g2.drawString("Miałeś za mało punktów", 10, 130);
        }
        exitButton.draw(g2);
        retryButton.draw(g2);
    }

    @Override
    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
        if(retryButton.checkClicked()) {
            game.loadGame();
            game.loadScene(game.mainScene);
        }
    }
}
