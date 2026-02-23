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
import java.util.Timer;
import java.util.TimerTask;

public class MainScene extends Scene{
    private final Button takeCardButton, passButton;
    private final Player you, dealer;

    private boolean cardTakingDisabled;


    private final ArrayList<Card> cardStack;
    private final Timer dealerPlaceCardTimer;

    public MainScene(Game game) {
        super(game);
        this.cardStack = new ArrayList<Card>();
        fillCardStack();
        this.dealer = new Player();
        this.you = new Player(true);
        this.dealerPlaceCardTimer = new Timer();
        this.cardTakingDisabled = false;
        this.takeCardButton = new Button(new Vector2(game.app.getWidth() / 2 - 300, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Dobierz", ContentManager.FontName.LARGER);
        this.passButton = new Button(new Vector2(game.app.getWidth() / 2 + 100, game.app.getHeight() / 2 - 40),
                new Vector2(200, 80), "Pasuj", ContentManager.FontName.LARGER);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(you.turn) {
            g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
            g2.drawString("Twoja kolej", 5, 70);
            if(!cardTakingDisabled) {
                takeCardButton.draw(g2);
                passButton.draw(g2);
            }
        } else {
            g2.drawString("Kolej krupiera", 5, 70);
        }
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        g2.drawString("Punkty: " + this.you.getPoints(), 5, 110);
        you.draw(g2, new Vector2(20, 600));
        dealer.draw(g2, new Vector2(20, 300));
    }

    @Override
    public void update() {
        if(passButton.checkClicked() && !cardTakingDisabled) {
            you.turn = false;
            dealer.turn = true;
            activateDealerLogic();
        }
        if(takeCardButton.checkClicked() && !cardTakingDisabled) {
            this.you.addCardToDeck(cardStack.getFirst());
            cardStack.removeFirst();
            if(this.you.getPoints() == 21) {
                // win
                game.loadScene(new WinScene(game, false), 2000);
                cardTakingDisabled = true;
            } else if(this.you.getPoints() > 21) {
                if(this.you.checkPersianEye()) {
                    game.loadScene(new WinScene(game, true), 2000);
                } else {
                    game.loadScene(new LoseScene(game, true), 2000);
                }
                cardTakingDisabled = true;
            }
        }
    }
    private void fillCardStack() {
        ImageName[] cardImages = ImageName.values();
        this.cardStack.clear();
        for(ImageName imageName : cardImages) {
            this.cardStack.add((int)(Math.random() * this.cardStack.size()), new Card(imageName));
        }
    }

    private void activateDealerLogic() {
        this.dealerPlaceCardTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(dealer.checkChance() > Math.random() || dealer.getPoints() <= 11) {
                    dealer.addCardToDeck(cardStack.getFirst());
                    cardStack.removeFirst();
                    if(dealer.getPoints() == 21) {
                        // lose
                        game.loadScene(new LoseScene(game, false), 2000);
                    } else if(dealer.getPoints() > 21) {
                        if(dealer.checkPersianEye()) {
                            game.loadScene(new LoseScene(game, false), 2000);
                        } else {
                            game.loadScene(new WinScene(game, false), 2000);
                        }
                    }
                } else {
                    dealerPlaceCardTimer.cancel();
                    if(dealer.getPoints() >= you.getPoints()) {
                        game.loadScene(new LoseScene(game, false), 2000);
                    } else {
                        game.loadScene(new WinScene(game, false), 2000);
                    }
                }
            }
        }, 0, 1000);
    }
}
