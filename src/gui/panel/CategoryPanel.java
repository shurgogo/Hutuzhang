package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.listener.ConfigListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel {
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
        this.addListener();

    }

    public Category getSelectedCategory() {
        int index = tCategory.getSelectedRow();
        return ctModel.cs.get(index);
    }

    public void updateData() {
        ctModel.cs = new CategoryService().list();
        tCategory.updateUI();
        tCategory.getSelectionModel().setSelectionInterval(0, 0);

        if (0 == ctModel.cs.size()) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    protected void addListener() {
        CategoryListener cl = new CategoryListener();
        bAdd.addActionListener(cl);
        bEdit.addActionListener(cl);
        bDelete.addActionListener(cl);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
