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
import java.util.ArrayList;

public class LeaderBoardScene extends Scene {

    private static final String connectionString = "mong";
    private final ArrayList<Score> scores;
    private String errorText;
    private final Button closeButton;

    public LeaderBoardScene(Game game) {
        super(game);
        this.scores = new ArrayList<Score>();
        this.errorText = "";
        this.closeButton = new Button(new Vector2(app.getWidth() / 2 - 100, app.getHeight() - 100), new Vector2(200, 80), "Powrót", ContentManager.FontName.LARGER);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.drawString("Wyniki", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));

        g2.setStroke(new BasicStroke(4));
        g2.drawRect(20, 120, app.getWidth() - 40, app.getHeight() - 250);
        closeButton.draw(g2);
    }

    @Override
    public void update() {
        super.update();
        if(closeButton.checkClicked()) {
            game.loadScene(game.titleScene);
        }
    }

    public void loadFromDatabase() {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase("eye-game");
            MongoCollection<Document> col = database.getCollection("scores");
            ArrayList<Document> foundRows = col.find().into(new ArrayList<>());
            for(Document row : foundRows) {
                scores.add(new Score(row.get("date").toString(), row.getInteger("points")));
            }
        } catch (Exception e) {
            this.errorText = "Bład połączenia z bazą danych";
        }
    }
}
