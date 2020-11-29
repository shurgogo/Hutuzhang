package utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * @ClassName GUIUtil
 * @Author Joshua
 * @Date 2020/11/23
 * @Version 1.0
 */
public class GUIUtil {
    // 校验文字不能为空
    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    // 校验是否是整数
    public static boolean checkNumber(JTextField tf, String input) {
        if (!checkEmpty(tf, input)) {
            return false;
        }
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " 需要是整数");
            tf.grabFocus();
            return false;
        }
    }

    // 校验是否为零
    public static boolean checkNotZero(JTextField tf, String input){
        if (!checkEmpty(tf, input)) {
            return false;
        }
        String text = tf.getText().trim();
        if (checkNumber(tf, text)) {
            if (0 == Integer.parseInt(text)) {
                JOptionPane.showMessageDialog(null, input + " 不能为零");
                tf.grabFocus();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    // 给组件（们）设置相同颜色
    public static void setColor(Color color, JComponent... cs) {
        for (JComponent c: cs) {
            c.setForeground(color);
        }
    }

    // 设置图标
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon(new File(fileName).getAbsolutePath());
        i.setImage(i.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
        b.setIcon(i);
        b.setPreferredSize(new Dimension(30, 65));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    // 使用水晶皮肤
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 显示面板
    public static void showPanel(JPanel p, double stretchRate) {
        useLNF();
        JFrame f = new JFrame();
        f.setSize(650, 450);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(stretchRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

}
