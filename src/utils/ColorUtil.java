package utils;

import java.awt.Color;

/**
 * @ClassName ColorUtil
 * @Author Joshua
 * @Date 2020/11/23
 * @Version 1.0
 */
public class ColorUtil {
    public static Color blue = Color.decode("#3399FF");
    public static Color gray = Color.decode("#999999");
    public static Color background = Color.decode("#eeeeee");
    public static Color warning = Color.decode("#FF3333");

    public static Color getByPercentage(int per) {
        if (per > 100) {
            per = 100;
        }
        int r = 51;
        int g = 255;
        int b = 51;
        float rate = per / 100f;
        r = (int) ((255 - r) * rate + r);
        g = g - r + 51;
        Color color = new Color(r, g, b);
        return color;
    }

}
