package scenes;

import components.Button;
import components.Vector2;
import main.ContentManager;
import main.Game;

import java.awt.*;

public class WinScene extends Scene{

    private final boolean persianEye;

    private final Button exitButton, retryButton;
    public WinScene(Game game, boolean persianEye) {
        super(game);
        this.persianEye = persianEye;
        this.retryButton = new Button(new Vector2(10, 190), new Vector2(200, 80), "Powtórz", ContentManager.FontName.LARGER);
        this.exitButton = new Button(new Vector2(10, 300), new Vector2(200, 80), "Wyjście", ContentManager.FontName.LARGER);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.drawString("Wygrałeś!!!", 10, 80);

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        if(persianEye) {
            g2.drawString("Uzyskano Perskie oczko", 10, 130);
        } else {
            g2.drawString("Uzyskano najbliżej 21 punktów", 10, 130);
        }
        exitButton.draw(g2);
        retryButton.draw(g2);
    }

    @Override
    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
    }
}
