package gui.panel;

import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static RecordPanel instance = new RecordPanel();

    public JLabel lSpend = new JLabel("花费(￥)");
    public JLabel lCategory = new JLabel("分类");
    public JLabel lRemark = new JLabel("备注");
    public JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<String> cbCategory = new JComboBox<String>(cbModel);
    public JTextField tfRemark = new JTextField();
    public JXDatePicker dpDate = new JXDatePicker(new Date());

    public JButton bRecord = new JButton("记一笔");

    private RecordPanel() {
        GUIUtil.setColor(ColorUtil.gray, lSpend, lCategory, lRemark, lDate);
        GUIUtil.setColor(ColorUtil.blue, bRecord);

        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.NORTH);
        this.add(south(), BorderLayout.SOUTH);
    }


    private Component center() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 2, 60, 60));

        p.add(lSpend);
        p.add(tfSpend);
        p.add(lCategory);
        p.add(cbCategory);
        p.add(lRemark);
        p.add(tfRemark);
        p.add(lDate);
        p.add(dpDate);

        return p;
    }

    private Component south() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        p.add(bRecord, BorderLayout.CENTER);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
