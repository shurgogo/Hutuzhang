package gui.panel;

import dao.RecordDAO;
import entity.Record;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.editor.ChartEditor;
import org.jfree.chart.editor.ChartEditorManager;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import service.ReportService;
import utils.ChartUtil;
import utils.ColorUtil;
import utils.GUIUtil;

import java.awt.*;
import java.util.List;

public class ReportPanel extends ChartPanel{
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();

    private ReportPanel() {
        super(ChartUtil.getBarChart());
    }

    public void updateData() {
        CategoryPlot plot = (CategoryPlot)instance.getChart().getPlot();
        plot.setDataset(ChartUtil.setBarDataset());
        instance.updateUI();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }
}
