package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ContentManager {

    private static final String FONT_FAMILY = "Arial";
    private final Map<FontName, Font> fonts = new HashMap<FontName, Font>();
    private final Map<ImageName, BufferedImage> images = new HashMap<ImageName, BufferedImage>();

    public enum ImageName {
        CLUB_2("club2.png", 2),
        CLUB_3("club3.png", 3),
        CLUB_4("club4.png", 4),
        CLUB_5("club5.png", 5),
        CLUB_6("club6.png", 6),
        CLUB_7("club7.png", 7),
        CLUB_8("club8.png", 8),
        CLUB_9("club9.png", 9),
        CLUB_10("club10.png", 10),
        CLUB_JACK("clubJack.png", 10),
        CLUB_QUEEN("clubQueen.png", 10),
        CLUB_KING("clubKing.png", 10),
        CLUB_AS("clubAs.png", 11),
        SPADE_2("spade2.png", 2),
        SPADE_3("spade3.png", 3),
        SPADE_4("spade4.png", 4),
        SPADE_5("spade5.png", 5),
        SPADE_6("spade6.png", 6),
        SPADE_7("spade7.png", 7),
        SPADE_8("spade8.png", 8),
        SPADE_9("spade9.png", 9),
        SPADE_10("spade10.png", 10),
        SPADE_JACK("spadeJack.png", 10),
        SPADE_QUEEN("spadeQueen.png", 10),
        SPADE_KING("spadeKing.png", 10),
        SPADE_AS("spadeAs.png", 11),
        HEART_2("heart2.png", 2),
        HEART_3("heart3.png", 3),
        HEART_4("heart4.png", 4),
        HEART_5("heart5.png", 5),
        HEART_6("heart6.png", 6),
        HEART_7("heart7.png", 7),
        HEART_8("heart8.png", 8),
        HEART_9("heart9.png", 9),
        HEART_10("heart10.png", 10),
        HEART_JACK("heartJack.png", 10),
        HEART_QUEEN("heartQueen.png", 10),
        HEART_KING("heartKing.png", 10),
        HEART_AS("heartAs.png", 11),
        DIAMOND_2("diamond2.png", 2),
        DIAMOND_3("diamond3.png", 3),
        DIAMOND_4("diamond4.png", 4),
        DIAMOND_5("diamond5.png", 5),
        DIAMOND_6("diamond6.png", 6),
        DIAMOND_7("diamond7.png", 7),
        DIAMOND_8("diamond8.png", 8),
        DIAMOND_9("diamond9.png", 9),
        DIAMOND_10("diamond10.png", 10),
        DIAMOND_JACK("diamondJack.png", 10),
        DIAMOND_QUEEN("diamondQueen.png", 10),
        DIAMOND_KING("diamondKing.png", 10),
        DIAMOND_AS("diamondAs.png", 11);

        public final String fileName;
        public final int power;
        ImageName(String fileName, int power) {
            this.fileName = fileName;
            this.power = power;
        }

        }


    public enum FontName {
        BASE,
        SMALLER,
        LARGER,
        LARGEST,
    }

    public ContentManager() throws IOException {
        fonts.put(FontName.BASE, new Font(FONT_FAMILY, Font.BOLD, 32));
        fonts.put(FontName.SMALLER, new Font(FONT_FAMILY, Font.BOLD, 20));
        fonts.put(FontName.LARGER, new Font(FONT_FAMILY, Font.BOLD, 40));
        fonts.put(FontName.LARGEST, new Font(FONT_FAMILY, Font.BOLD, 50));
        loadCardsImages();
        loadFonts();
    }

    public void loadFonts() {
        fonts.put(FontName.BASE, new Font(FONT_FAMILY, Font.BOLD, 32));
        fonts.put(FontName.SMALLER, new Font(FONT_FAMILY, Font.BOLD, 20));
        fonts.put(FontName.LARGER, new Font(FONT_FAMILY, Font.BOLD, 40));
        fonts.put(FontName.LARGEST, new Font(FONT_FAMILY, Font.BOLD, 50));
    }
    public void loadCardsImages() throws IOException {
        String baseImagePath = "assets/cards/";
        for(ImageName imageName : ImageName.values()) {
            images.put(imageName, ImageIO.read(new File(baseImagePath + imageName.fileName)));
        }
    }

    public BufferedImage getImage(ImageName name) {
        return images.get(name);
    }
    public Font getFont(FontName name) {
        return fonts.get(name);
    }
    public void setFont(FontName name, Font newFont) {
        fonts.put(name, newFont);
    }
}
