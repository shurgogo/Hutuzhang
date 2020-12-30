package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel {
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
    public JComboBox<Category> cbCategory = new JComboBox<Category>(cbModel);
    public JTextField tfRemark = new JTextField();
    public JXDatePicker dpDate = new JXDatePicker(new Date());

    public JButton bRecord = new JButton("记一笔");

    private RecordPanel() {
        GUIUtil.setColor(ColorUtil.gray, lSpend, lCategory, lRemark, lDate);
        GUIUtil.setColor(ColorUtil.blue, bRecord);

        this.setLayout(new BorderLayout());
        this.add(center(), BorderLayout.NORTH);
        this.add(south(), BorderLayout.SOUTH);

        addListener();
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

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput() {
        tfSpend.setText("0");
        tfRemark.setText("");
        if (0 != cbModel.cs.size()) {
            cbCategory.setSelectedIndex(0);
        }
        dpDate.setDate(new Date());
    }

    @Override
    void addListener() {
        RecordListener rl = new RecordListener();
        bRecord.addActionListener(rl);
    }

    public Category getSelectedCategory() {
        return (Category) cbModel.getSelectedItem();
    }
}
