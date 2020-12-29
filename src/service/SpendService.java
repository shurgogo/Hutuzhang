package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import utils.DateUtil;

import java.util.List;

public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO recordDAO = new RecordDAO();
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        List<Record> todayRecords = recordDAO.listToday();
        int daysOfMonth = DateUtil.daysOfMonth();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthLeft = 0;
        int dayAvgAvailable = 0;
        int daysLeftOfMonth = 0;
        int percent = 0;

        // 预算
        int monthBudget = new ConfigService().getIntBudget();
        // 月消费
        for (Record record: thisMonthRecords) {
            monthSpend += record.getSpend();
        }
        // 今日消费
        for (Record record: todayRecords) {
            todaySpend += record.getSpend();
        }
        // 距离月末天数
        daysLeftOfMonth = DateUtil.daysLeftOfMonth();
        // 日均消费
        avgSpendPerDay = monthSpend / (daysOfMonth - daysLeftOfMonth);
        // 本月剩余
        monthLeft = monthBudget - monthSpend;
        // 日均可用
        dayAvgAvailable = monthLeft / daysLeftOfMonth;
        // 计算使用比例
        percent = monthSpend * 100 / monthBudget;

        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthLeft, dayAvgAvailable, daysLeftOfMonth, percent);

    }
}
