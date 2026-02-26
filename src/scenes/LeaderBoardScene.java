package scenes;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Sorts.*;
import components.Button;
import components.Vector2;
import gameObjects.Score;
import main.ContentManager;
import main.Game;
import org.bson.Document;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
        g2.drawString("Wyniki (zwycięstwa)", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));

        g2.setStroke(new BasicStroke(4));
        g2.drawRect(20, 120, app.getWidth() - 40, app.getHeight() - 250);
        if(!errorText.isEmpty()) {
            g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
            g2.drawString("Wystąpił problem przy pobieraniu wyników", 50, 180);
        } else {
            if(scores.isEmpty()) {
                g2.setFont(Game.contentManager.getFont(ContentManager.FontName.BASE));
                g2.drawString("Brak wyników", 50, 180);
            } else {
                for(int i = 0;i<scores.size();i++) {
                    scores.get(i).draw(g2, new Vector2(50, 180 + 50 * i));
                }
            }
        }
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
        this.errorText = "";
        scores.clear();
        new Thread(() -> {
            LocalDateTime localDateTime = null;
            Date date;
            try{
                MongoClient client = MongoClients.create("mongodb://localhost:27017");
                MongoDatabase database = client.getDatabase("eye-game");
                MongoCollection<Document> col = database.getCollection("scores");
                ArrayList<Document> foundRows = col.find().sort(descending("date")).limit(10).into(new ArrayList<>());
                for (Document row : foundRows) {
                    date = row.getDate("date");
                    if (date != null) {
                        localDateTime = date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                        scores.add(new Score(localDateTime, row.getInteger("points")));
                    }
                }
                client.close();
            } catch (Exception e) {
                this.errorText = "Błąd połączenia z bazą danych";
            }
        }).start();

    }
}
