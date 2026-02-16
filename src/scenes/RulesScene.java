package scenes;

import main.App;
import main.ContentManager;
import main.Game;

import java.awt.*;

public class RulesScene extends Scene{
    public RulesScene(Game game) {
        super(game);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.setColor(Color.WHITE);
        g2.drawString("Zasady gry w oczko", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));
    }

    @Override
    public void update() {
        super.update();
    }
}
