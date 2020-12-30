package utils;

import java.awt.*;
import java.util.List;

import entity.Record;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import service.ReportService;

/**
 * @ClassName ChartUtil
 * @Author Joshua
 * @Date 2020/11/23
 * @Version 1.0
 */
public class ChartUtil {
    public static int max(double[] sampleValues) {
        int max = 0;
        for (double v : sampleValues) {
            if ((int) v > max) {
                max = (int) v;
            }
        }
        return max;
    }

    private static String[] sampleLabels() {
        String[] sampleLabels = new String[30];
        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5) {
                sampleLabels[i] = String.valueOf(i + 1 + "日");
            }
        }
        return sampleLabels;
    }

    private static double[] sampleValues() {
        double[] result = new double[30];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * 300);
        }
        return result;
    }

    public static JFreeChart getBarChart() {
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


    public static CategoryDataset setBarDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Record> thisMonthRecords = new ReportService().listThisMonthRecords();
        int gapSpend = 0;
        final int GAP = 5;
        for (int i = 0; i < thisMonthRecords.size(); i++) {
            gapSpend += thisMonthRecords.get(i).getSpend();
            if ((i + 1) % GAP == 0) {
                dataset.addValue(gapSpend, "", (i + 1) + "日");
                gapSpend = 0;
            }
        }
        return dataset;
    }
}
