package service;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Date;

public class RecordService {
    RecordDAO recordDAO = new RecordDAO();
    public void add(int spend, Category category, String remark, Date date) {
        Record record = new Record();
        record.setSpend(spend);
        record.setCid(category.getId());
        record.setRemark(remark);
        record.setDate(date);
        recordDAO.add(record);
    }
}
