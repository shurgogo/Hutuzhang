package gui.listener;

import entity.Config;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import utils.GUIUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if (!GUIUtil.checkNumber(p.tfMonthBudget, "本月预算")) {
            return;
        }
        if (!GUIUtil.checkPath(p.tfMySQLPath, "Mysql安装路径")) {
            return;
        }
        ConfigService cs = new ConfigService();
        cs.update(ConfigService.getBudget(), p.tfMonthBudget.getText());
        cs.update(ConfigService.getMysqlPath(), p.tfMySQLPath.getText());

        JOptionPane.showMessageDialog(p, "修改设置成功");
    }

}
