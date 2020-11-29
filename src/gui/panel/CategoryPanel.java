package gui.panel;

import gui.model.CategoryTableModel;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();

    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    public String[] columnNames = new String[]{"分类名词", "消费次数"};

    public CategoryTableModel ctModel = new CategoryTableModel();
    public JTable tCategory = new JTable(ctModel);


    private CategoryPanel() {
        JScrollPane sp = new JScrollPane(tCategory);
        JPanel p = new JPanel();

        GUIUtil.setColor(Color.blue, bAdd, bEdit, bDelete);
        p.add(bAdd);
        p.add(bEdit);
        p.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(p, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
