package utils;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName CenterPanel
 * @Author Joshua
 * @Date 2020/11/23
 * @Version 1.0
 */


// 主要用于做测试等，居中面板
public class CenterPanel extends JPanel {
    private double rate;
    private JComponent c;
    private boolean stretch;

    public CenterPanel(double rate, boolean stretch) {
        this.rate = rate;
        this.stretch = stretch;
        this.setLayout(null);
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }

    public void repaint() {
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();

            if (stretch) {
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            } else {
                c.setSize(componentSize);
            }
            c.setLocation(
                    containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2
            );
        }
        super.repaint();
    }

    public void show(JComponent p) {
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        this.add(p);
        this.updateUI();
    }

    public static void main(String[] args) {

    }
}
