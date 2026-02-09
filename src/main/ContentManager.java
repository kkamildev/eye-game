package main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ContentManager {

    private static final String FONT_FAMILY = "Arial";

    public enum FontName {
        BASE,
        SMALLER,
        LARGER,
        LARGEST
    }

    public ContentManager() {
        fonts.put(FontName.BASE, new Font(FONT_FAMILY, Font.BOLD, 32));
        fonts.put(FontName.SMALLER, new Font(FONT_FAMILY, Font.BOLD, 20));
        fonts.put(FontName.LARGER, new Font(FONT_FAMILY, Font.BOLD, 40));
        fonts.put(FontName.LARGEST, new Font(FONT_FAMILY, Font.BOLD, 50));
    }

    private Map<FontName, Font> fonts = new HashMap<FontName, Font>();


    public Font getFont(FontName name) {
        return fonts.get(name);
    }
    public void setFont(FontName name, Font newFont) {
        fonts.put(name, newFont);
    }
}
