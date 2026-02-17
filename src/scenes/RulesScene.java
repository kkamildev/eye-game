package scenes;

import components.MultilineText;
import components.Vector2;
import main.ContentManager;
import main.Game;
import java.awt.*;
import components.Button;

public class RulesScene extends Scene{

    private final MultilineText rules;

    private final Button closeButton;

    public RulesScene(Game game) {
        super(game);
        this.closeButton = new Button(new Vector2(app.getWidth() / 2 - 100, app.getHeight() - 100), new Vector2(200, 80), "Powrót", Game.contentManager.getFont(ContentManager.FontName.LARGER));
        String[] rulesText = {"Celem gry jest zdobyć 21 punktów lub jak najbliżej tej wartości ale nie przekroczyć",
            "Gracz może dopierać karty i zdobywać punkty w każdej chwili można przestać dobierać",
                "",
                "Wygrywa ten gracz/krupier który będzie jak najbliżej wartości 21",
                "Automatycznie przegrywa się kiedy przekroczy się 21 lub że ma się perskie oczko 2 x AS",
                "",
                "Wartości punktowe poszczególnych kart:",
                "- AS 11 punktów 2 x ASY (22 punkty) natychmiastowa wygrana",
                "- Król(K), Królowa(Q), Walet(J) wszyscy po 10 punktów",
                "- karty od 2 - 10 ilość punktów taka jak numer karty",
                "",
                "Gracz zawsze zaczyna jako pierwszy"
        };
        this.rules = new MultilineText(rulesText, new Vector2(40, 160), Game.contentManager.getFont(ContentManager.FontName.SMALLER), Color.white, 30);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, app.getWidth(), app.getHeight());

        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.LARGEST));
        g2.setColor(Color.WHITE);
        g2.drawString("Zasady gry w oczko", 10, 80);
        g2.setFont(Game.contentManager.getFont(ContentManager.FontName.SMALLER));

        g2.setStroke(new BasicStroke(4));
        g2.drawRect(20, 120, app.getWidth() - 40, app.getHeight() - 250);

        rules.draw(g2);
        closeButton.draw(g2);
    }

    @Override
    public void update() {
        if(closeButton.checkClicked()) {
            game.loadScene(game.titleScene);
        }
    }
}
