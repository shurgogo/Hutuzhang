package gui.model;

import dao.CategoryDAO;
import entity.Category;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    public List<Category> cs = new CategoryDAO().list();
    Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty()) {
            c = cs.get(0);
        }
    }
//    public CategoryComboBoxModel() {
//        cs.add("餐饮");
//        cs.add("交通");
//        cs.add("住宿");
//        cs.add("花费");
//        c = cs.get(0);
//    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Object getSelectedItem() {

        if (!cs.isEmpty()) {
            return c;
        } else {
            return null;
        }
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
