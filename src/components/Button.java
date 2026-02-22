package components;

import java.awt.*;
import main.App;
import main.ContentManager;
import main.Game;

public class Button {
    public Vector2 position;
    public Vector2 size;
    public String text;
    private final Font font;
    public Button(Vector2 position, Vector2 size, String text, ContentManager.FontName fontName) {
        this.position = position;
        this.size = size;
        this.text = text;
        this.font = Game.contentManager.getFont(fontName);
    }

    public void draw(Graphics2D g2) {
        g2.setStroke(new BasicStroke(3));
        if(checkCollider()) {
            g2.setColor(new Color(33, 33, 33));
        } else {
            g2.setColor(Color.BLACK);
        }
        g2.fillRect(position.x, position.y, size.x, size.y);
        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(this.text, position.x + 20, position.y + size.y - 20);
        g2.drawRect(position.x, position.y, size.x, size.y);
    }
    public boolean checkClicked() {
        return App.mousePressed && !App.prevMousePressed && checkCollider();
    }

    private boolean checkCollider() {
        return position.y <= App.mouseY && position.x <= App.mouseX && position.y + size.y >= App.mouseY && position.x + size.x >= App.mouseX;
    }
}
