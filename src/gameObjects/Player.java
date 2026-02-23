package gameObjects;

import components.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    public boolean turn;
    private int points;
    private final ArrayList<Card> playerCards;

    public Player(boolean turn) {
        this.playerCards = new ArrayList<>();
        this.points = 0;
        this.turn = turn;
    }
    public Player() {
        this(false);
    }

    public void draw(Graphics2D g2, Vector2 position) {
        for(Card card : playerCards) {
            card.draw(g2, position);
            position.x+= 100;
        }
    }

    public void addCardToDeck(Card card) {
        playerCards.add(card);
        this.points = calculatePoints();
    }

    private int calculatePoints() {
        int points = 0;
        for(Card card : playerCards) {
            points+= card.power;
        }
        return points;
    }

    public boolean checkPersianEye() {
        if(points == 22) {
            return playerCards.size() == 2;
        }
        return false;
    }
    public float checkChance() {
        if(calculatePoints() == 0) {
            return 1f;
        }
        return 1f - (calculatePoints() / 21f);
    }

    public int getPoints() {
        return this.points;
    }
}
