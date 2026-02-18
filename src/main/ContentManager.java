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
        CLUB_2("club2.png"),
        CLUB_3("club3.png"),
        CLUB_4("club4.png"),
        CLUB_5("club5.png"),
        CLUB_6("club6.png"),
        CLUB_7("club7.png"),
        CLUB_8("club8.png"),
        CLUB_9("club9.png"),
        CLUB_10("club10.png"),
        CLUB_JACK("clubJack.png"),
        CLUB_QUEEN("clubQueen.png"),
        CLUB_KING("clubKing.png"),
        CLUB_AS("clubAs.png"),
        SPADE_2("spade2.png"),
        SPADE_3("spade3.png"),
        SPADE_4("spade4.png"),
        SPADE_5("spade5.png"),
        SPADE_6("spade6.png"),
        SPADE_7("spade7.png"),
        SPADE_8("spade8.png"),
        SPADE_9("spade9.png"),
        SPADE_10("spade10.png"),
        SPADE_JACK("spadeJack.png"),
        SPADE_QUEEN("spadeQueen.png"),
        SPADE_KING("spadeKing.png"),
        SPADE_AS("spadeAs.png"),
        HEART_2("heart2.png"),
        HEART_3("heart3.png"),
        HEART_4("heart4.png"),
        HEART_5("heart5.png"),
        HEART_6("heart6.png"),
        HEART_7("heart7.png"),
        HEART_8("heart8.png"),
        HEART_9("heart9.png"),
        HEART_10("heart10.png"),
        HEART_JACK("heartJack.png"),
        HEART_QUEEN("heartQueen.png"),
        HEART_KING("heartKing.png"),
        HEART_AS("heartAs.png"),
        DIAMOND_2("diamond2.png"),
        DIAMOND_3("diamond3.png"),
        DIAMOND_4("diamond4.png"),
        DIAMOND_5("diamond5.png"),
        DIAMOND_6("diamond6.png"),
        DIAMOND_7("diamond7.png"),
        DIAMOND_8("diamond8.png"),
        DIAMOND_9("diamond9.png"),
        DIAMOND_10("diamond10.png"),
        DIAMOND_JACK("diamondJack.png"),
        DIAMOND_QUEEN("diamondQueen.png"),
        DIAMOND_KING("diamondKing.png"),
        DIAMOND_AS("diamondAs.png");

        public final String fileName;
        ImageName(String fileName) {
            this.fileName = fileName;
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
