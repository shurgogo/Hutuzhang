package gui.panel;

import entity.Record;
import gui.listener.ToolBarListener;
import utils.CenterPanel;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }

    public static MainPanel instance = new MainPanel();

    public JToolBar tb = new JToolBar();

    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    public CenterPanel workingPanel = new CenterPanel(0.8);;

    private MainPanel() {
        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "recover.png", "恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false); // toolbar不能浮动

        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);

        addListener();
    }

    private void addListener() {
        ToolBarListener tbListener = new ToolBarListener();
        bSpend.addActionListener(tbListener);
        bRecord.addActionListener(tbListener);
        bCategory.addActionListener(tbListener);
        bReport.addActionListener(tbListener);
        bConfig.addActionListener(tbListener);
        bBackup.addActionListener(tbListener);
        bRecover.addActionListener(tbListener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
