package gameObjects;

import components.Sprite;
import components.Vector2;
import main.ContentManager;

import java.awt.*;

public class Card {
    private final Sprite spite;
    public final int power;
    public Card(ContentManager.ImageName imageName) {
        this.power = imageName.power;
        this.spite = new Sprite(imageName, 3f);
    }

    public void draw(Graphics2D g2, Vector2 position) {
        spite.draw(g2, position);
    }
}
