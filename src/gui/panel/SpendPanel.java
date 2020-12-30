package gui.panel;

/**
 * @ClassName SpendPanel
 * @Author Joshua
 * @Date 2020/10/6
 * @Version 1.0
 */

import gui.page.SpendPage;
import service.SpendService;
import utils.CircleProgressBar;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel();

    //    非常规单例模式，不涉及并发访问。
//    private static SpendPanel sp;
//    public static SpendPanel getInstance() {
//        if (null == sp) {
//            sp = new SpendPanel();
//        }
//        return sp;
//    }

    public JLabel lMonthSpend = new JLabel("本月消费");
    public JLabel lTodaySpend = new JLabel("今日消费");
    public JLabel lAvgSpendPerDay = new JLabel("日均消费");
    public JLabel lMonthLeft = new JLabel("本月剩余");
    public JLabel lDayAvgAvailable = new JLabel("日均可用");
    public JLabel lMonthLeftDay = new JLabel("距离月末");

    public JLabel vMonthSpend = new JLabel("￥2300");
    public JLabel vTodaySpend = new JLabel("￥25");
    public JLabel vAvgSpendPerDay = new JLabel("￥120");
    public JLabel vMonthLeft = new JLabel("￥2084");
    public JLabel vDayAvgAvailable = new JLabel("￥389");
    public JLabel vMonthLeftDay = new JLabel("15天");

    CircleProgressBar cpb;

    private SpendPanel() {
        this.setLayout(new BorderLayout());
        cpb = new CircleProgressBar();
        cpb.setBackgroundColor(ColorUtil.blue);

        GUIUtil.setColor(ColorUtil.gray, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable, lMonthLeftDay,
                vAvgSpendPerDay, vMonthLeft, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blue, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(center2(), BorderLayout.CENTER);
        return p;
    }

    private Component center2() {
        return cpb;
    }

    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));

        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));

        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthLeft);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        SpendPage spendPage = new SpendService().getSpendPage();
        vMonthSpend.setText(spendPage.getMonthSpend());
        vTodaySpend.setText(spendPage.getTodaySpend());
        vAvgSpendPerDay.setText(spendPage.getAvgSpendPerDay());
        vMonthLeft.setText(spendPage.getMonthLeft());
        vDayAvgAvailable.setText(spendPage.getDayAvgAvailable());
        vMonthLeftDay.setText(spendPage.getMonthLeftDay());
        cpb.setProgress(spendPage.getPercent());
        if (spendPage.isOverSpend()) {
            vMonthLeft.setForeground(ColorUtil.warning);
            vMonthSpend.setForeground(ColorUtil.warning);
            vTodaySpend.setForeground(ColorUtil.warning);
        } else {
            vMonthLeft.setForeground(ColorUtil.gray);
            vMonthSpend.setForeground(ColorUtil.blue);
            vTodaySpend.setForeground(ColorUtil.blue);
        }
        cpb.setForegroundColor(ColorUtil.getByPercentage(spendPage.getPercent()));
    }

    @Override
    void addListener() {

    }
}
