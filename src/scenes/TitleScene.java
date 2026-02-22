package scenes;

import components.Button;
import components.Sprite;
import components.Vector2;
import main.ContentManager;
import main.Game;

import java.awt.*;

public class TitleScene extends Scene{

    private final Button startButton;
    private final Button rulesButton;
    private final Button exitButton;

    private final Sprite[] cardsSprites;
    private float animationValue = 0;

    public TitleScene(Game game) {
        super(game);
        cardsSprites = new Sprite[]{
                new Sprite(ContentManager.ImageName.CLUB_AS, new Vector2(500, 50), 3f),
                new Sprite(ContentManager.ImageName.SPADE_AS, new Vector2(650, 50), 3f),
                new Sprite(ContentManager.ImageName.DIAMOND_AS, new Vector2(800, 50), 3f),
                new Sprite(ContentManager.ImageName.HEART_AS, new Vector2(950, 50), 3f)
        };
        startButton = new Button(new Vector2(50, 170), new Vector2(200, 80), "Zagraj", ContentManager.FontName.LARGER);
        rulesButton = new Button(new Vector2(50, 280), new Vector2(200, 80), "Zasady", ContentManager.FontName.LARGER);
        exitButton = new Button(new Vector2(50, 390), new Vector2(200, 80), "Wyjście", ContentManager.FontName.LARGER);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.drawString("Gra w oczko", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));
        startButton.draw(g2);
        rulesButton.draw(g2);
        exitButton.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));
        g2.drawString("Stworzona przez:", 30, 550);
        g2.drawString("Kamila Kijaka", 30, 580);
        for (Sprite sprite : cardsSprites) {
            sprite.draw(g2);
        }
    }

    @Override
    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
        if(rulesButton.checkClicked()) {
            game.loadScene(game.rulesScene);
        }
        if(startButton.checkClicked()) {
            game.loadScene(game.mainScene);
        }
        for(int i = 0;i<cardsSprites.length;i++) {
            if(i % 2 == 0) {
                cardsSprites[i].position.y = (int)(Math.cos(animationValue) * 100) + app.getHeight() / 2 - 100;
            } else {
                cardsSprites[i].position.y = (int)(-Math.cos(animationValue) * 100) + app.getHeight() / 2 - 100;
            }
        }
        animationValue += 0.05f;
    }
}
