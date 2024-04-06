package io.github.srdjanv.tweakedlib.api.waila;
import net.minecraft.client.resources.I18n;

public class Styling {
    public static final String STARTLOC = "{*";
    public static final String ENDLOC = "*}";
    public static String stylifyString(String text) {
        while (text.contains(STARTLOC) && text.contains(ENDLOC)) {
            int start = text.indexOf(STARTLOC);
            int end = text.indexOf(ENDLOC);
            if (start < end) {
                // Translation is needed
                String left = text.substring(0, start);
                String middle = text.substring(start + 2, end);
                middle = I18n.format(middle).trim();
                String right = text.substring(end+2);
                text = left + middle + right;
            } else {
                break;
            }
        }
        return text;
    }

}
