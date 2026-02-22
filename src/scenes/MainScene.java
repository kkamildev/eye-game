package scenes;

import components.Button;
import components.Vector2;
import gameObjects.Card;
import gameObjects.Player;
import main.ContentManager;
import main.ContentManager.ImageName;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class MainScene extends Scene{
    private final Button takeCardButton, passButton;
    private final Player you, dealer;

    private final ArrayList<Card> cardStack;

    public MainScene(Game game) {
        super(game);
        this.cardStack = new ArrayList<Card>();
        fillCardStack();
        this.dealer = new Player();
        this.you = new Player(true);
        this.takeCardButton = new Button(new Vector2(game.app.getWidth() / 2 - 300, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Dobierz", ContentManager.FontName.LARGER);
        this.passButton = new Button(new Vector2(game.app.getWidth() / 2 + 100, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Pasuj", ContentManager.FontName.LARGER);
    }


    private void fillCardStack() {
        ImageName[] cardImages = ImageName.values();
        this.cardStack.clear();
        for(ImageName imageName : cardImages) {
            this.cardStack.add((int)(Math.random() * this.cardStack.size()), new Card(imageName));
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        g2.setColor(Color.WHITE);
        if(you.turn) {
            g2.drawString("Twoja kolej", 5, 70);
            g2.drawString("Punkty: " + this.you.getPoints(), 5, 110);
            takeCardButton.draw(g2);
            passButton.draw(g2);
        }
        you.draw(g2, new Vector2(20, 600));
    }

    @Override
    public void update() {
        super.update();
        if(passButton.checkClicked()) {
            you.turn = false;
        }
        if(takeCardButton.checkClicked()) {
            this.you.addCardToDeck(cardStack.getFirst());
            cardStack.removeFirst();
            if(this.you.getPoints() == 21) {
                // win
            } else if(this.you.getPoints() > 21) {
                if(this.you.checkPersianEye()) {
                    // win
                } else {
                    // lose
                }
            }
        }
    }
}
