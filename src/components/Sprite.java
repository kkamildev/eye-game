package components;

import main.ContentManager;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage image;
    public Vector2 position;
    public float scale;
    private Vector2 imageSize;
    public Sprite(ContentManager.ImageName imageName, Vector2 position, float scale) {
        setImageName(imageName);
        this.position = position;
        this.scale = scale;
    }
    public Sprite(ContentManager.ImageName imageName, float scale) {
        setImageName(imageName);
        this.position = null;
        this.scale = scale;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, position.x, position.y, (int)(this.imageSize.x * scale), (int)(this.imageSize.y * scale), null);
    }
    public void draw(Graphics2D g2, Vector2 position) {
        g2.drawImage(image, position.x, position.y, (int)(this.imageSize.x * scale), (int)(this.imageSize.y * scale), null);
    }

    public void setImageName(ContentManager.ImageName imageName) {
        this.image = Game.contentManager.getImage(imageName);
        this.imageSize = new Vector2(this.image.getWidth(), this.image.getHeight());
    }
}
