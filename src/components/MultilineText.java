package components;


import java.awt.*;

public class MultilineText {
    public Vector2 position;
    public Vector2 size;
    private final String[] rows;
    public Font font;
    public Color color;
    public int spacing;
    public MultilineText(String[] rows, Vector2 position, Font font, Color color, int spacing) {
        this.rows = rows;
        this.position = position;
        this.font = font;
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(this.color);
        g2.setFont(font);
        for(int i = 0;i<rows.length;i++) {
            g2.drawString(this.rows[i], position.x, position.y + spacing * i);
        }
    }

    public void changeRow(int index, String text) {
        if(index >= 0 && index < rows.length) {
            rows[index] = text;
        }
    }
}
