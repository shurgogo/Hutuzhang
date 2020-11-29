package gui.panel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.ChartUtil;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends ChartPanel{
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();;

    private ReportPanel() {
        super(getBarChart());
    }

    // 为了使用super()，必须为静态方法。
    private static JFreeChart getBarChart() {
        CategoryDataset dataset = setBarDataset();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "月消费报表", // 图表标题
                "日期", // 文件夹轴的显示标签
                "花费", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );
        //从这里開始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  //垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.removeLegend();
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setPaint(ColorUtil.blue);
        return chart;
    }


    private static CategoryDataset setBarDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "月消费报表", "1");
        dataset.addValue(200, "月消费报表", "2");
        dataset.addValue(300, "月消费报表", "3");
        dataset.addValue(400, "月消费报表", "4");
        dataset.addValue(500, "月消费报表", "5");
        return dataset;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
