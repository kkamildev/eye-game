package scenes;

import components.Button;
import components.Vector2;
import gameObjects.Card;
import main.ContentManager;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class MainScene extends Scene{
    private boolean yourTurn;
    private final Button takeCardButton, passButton;
    private int points;

    private final ArrayList<Card> cardStack;

    public MainScene(Game game) {
        super(game);
        this.cardStack = new ArrayList<Card>();
        fillCardStack();
        this.yourTurn = true;
        this.points = 0;
        this.takeCardButton = new Button(new Vector2(game.app.getWidth() / 2 - 300, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Dobierz", ContentManager.FontName.LARGER);
        this.passButton = new Button(new Vector2(game.app.getWidth() / 2 + 100, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Pasuj", ContentManager.FontName.LARGER);
    }


    private void fillCardStack() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        g2.setColor(Color.WHITE);
        if(yourTurn) {
            g2.drawString("Twoja kolej", 5, 70);
            g2.drawString("Punkty: " + points, 5, 110);
            takeCardButton.draw(g2);
            passButton.draw(g2);
        }
    }

    @Override
    public void update() {
        super.update();
        if(passButton.checkClicked()) {
            yourTurn = false;
        }
    }
}
