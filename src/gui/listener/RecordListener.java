package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel rp = RecordPanel.instance;
        if (0 == rp.cbModel.cs.size()) {
            JOptionPane.showMessageDialog(rp, "暂无消费类型");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }

        if (!GUIUtil.checkNotZero(rp.tfSpend, "花费金额")) {
            return;
        }

        int spend = Integer.parseInt(rp.tfSpend.getText());
        String remark = rp.tfRemark.getText();
        Date date = rp.dpDate.getDate();
        Category category = rp.getSelectedCategory();
        new RecordService().add(spend, category, remark, date);
        JOptionPane.showMessageDialog(rp, "添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
