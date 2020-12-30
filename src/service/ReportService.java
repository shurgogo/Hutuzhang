package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.RecordDAO;
import entity.Record;
import utils.DateUtil;

public class ReportService {
    public int getDaySpend(Date date, List<Record> thisMonthRecords) {
        int daySpend = 0;
        for (Record record: thisMonthRecords) {
            if (record.getDate().equals(date)) {
                daySpend += record.getSpend();
            }
        }
        return daySpend;
    }

    public List<Record> listThisMonthRecords() {
        RecordDAO recordDAO = new RecordDAO();
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        List<Record> result = new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < DateUtil.daysOfMonth(); i++) {
            Record record = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date date = c.getTime();
            int daySpend = getDaySpend(date, thisMonthRecords);
            record.setSpend(daySpend);
            result.add(record);
        }
        return result;
    }
}
