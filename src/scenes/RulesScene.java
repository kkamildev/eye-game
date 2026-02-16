package scenes;

import components.Vector2;
import main.App;
import main.ContentManager;
import main.Game;
import java.awt.*;
import components.Button;

public class RulesScene extends Scene{


    private final Button closeButton;

    public RulesScene(Game game) {
        super(game);
        this.closeButton = new Button(new Vector2(app.getWidth() / 2 - 100, app.getHeight() - 100), new Vector2(200, 80), "Powrót", Game.contentManager.getFont(ContentManager.FontName.LARGER));
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.setColor(Color.WHITE);
        g2.drawString("Zasady gry w oczko", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));

        g2.setStroke(new BasicStroke(4));
        g2.drawRect(20, 120, app.getWidth() - 40, app.getHeight() - 250);


        closeButton.draw(g2);
    }

    @Override
    public void update() {
        if(closeButton.checkClicked()) {
            game.loadScene(game.titleScene);
        }
    }
}
