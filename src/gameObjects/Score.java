package gameObjects;

import components.Vector2;
import main.ContentManager;
import main.Game;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Score {

    private final String formatedDate;
    public final int points;
    public Score(LocalDateTime date, int points) {
        this.points = points;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        this.formatedDate = date.format(formatter);
    }

    public void draw(Graphics2D g2, Vector2 position) {
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        g2.drawString( formatedDate + " Punkty: " + points, position.x, position.y);
    }
}
