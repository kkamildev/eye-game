package scenes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import components.Button;
import components.Vector2;
import gameObjects.Score;
import main.ContentManager;
import main.Game;
import org.bson.Document;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Sorts.descending;

public class WinScene extends Scene{

    private final boolean persianEye;

    private final Button exitButton, retryButton, menuButton;

    private String savingResultText = "";

    public WinScene(Game game, boolean persianEye, int playerPoints) {
        super(game);
        this.persianEye = persianEye;
        this.retryButton = new Button(new Vector2(10, 240), new Vector2(200, 80), "Powtórz", ContentManager.FontName.LARGER);
        this.menuButton = new Button(new Vector2(10, 350), new Vector2(200, 80), " Menu", ContentManager.FontName.LARGER);
        this.exitButton = new Button(new Vector2(10, 460), new Vector2(200, 80), "Wyjście", ContentManager.FontName.LARGER);
        saveToDatabase(new Score(LocalDateTime.now(), playerPoints));
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.drawString("Wygrana!", 10, 80);

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
        if(persianEye) {
            g2.drawString("Uzyskano Perskie oczko", 10, 130);
        } else {
            g2.drawString("Uzyskano najbliżej 21 punktów", 10, 130);
        }
        if(!savingResultText.isEmpty()) {
            g2.drawString(savingResultText, 10, 180);
        }
        menuButton.draw(g2);
        exitButton.draw(g2);
        retryButton.draw(g2);
    }

    @Override
    public void update() {
        if(exitButton.checkClicked()) {
            app.shutdown();
        }
        if(retryButton.checkClicked()) {
            game.loadGame();
            game.loadScene(game.mainScene);
        }
        if(menuButton.checkClicked()) {
            game.loadGame();
            game.loadScene(game.titleScene);
        }
    }

    private void saveToDatabase(Score score) {
        new Thread(() -> {
            try{
                MongoClient client = MongoClients.create("mongodb://localhost:27017");
                MongoDatabase database = client.getDatabase("eye-game");
                MongoCollection<Document> col = database.getCollection("scores");
                Document toInsert = new Document();
                toInsert.append("date", Instant.now());
                toInsert.append("points", score.points);
                col.insertOne(toInsert);
                this.savingResultText = "Zapisano wynik wygranej";
            } catch (Exception e) {
                this.savingResultText = "Błąd połączenia z bazą danych";
            }
        }).start();
    }
}
