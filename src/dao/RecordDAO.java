package dao;

import entity.Record;
import utils.DBUtil;
import utils.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {
    public int getTotal() {
        int total = 0;

        String sql = "select count(*) from config";
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
            System.out.println("total:" + total);

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return total;
    }

    public void add(Record record) {
        String sql = "insert into record values(null, ?, ?, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.date2sql(record.getDate()));
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                record.setId(id);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void update(Record record) {
        String sql = "update record set spend = ?, cid = ?, comment = ?, date = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.date2sql(record.getDate()));
            ps.setInt(5, record.getId());
            ps.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "delete from record where id = " + id;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public Record get(int id) {
        Record record = null;
        String sql = "select * from record where id = " + id;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                record = new Record();
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return record;
    }

    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Record> list(int start, int count) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record order by id desc limit ?, ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Record record = new Record();

                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);

                records.add(record);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return records;
    }

    public List<Record> list(int cid) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where cid = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Record record = new Record();

                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                Date date = rs.getDate("date");
                String comment = rs.getString("comment");

                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);

                records.add(record);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return records;
    }

    public List<Record> listToday() {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.date2sql(DateUtil.today()));
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Record record = new Record();

                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");

                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(DateUtil.today());

                records.add(record);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return records;
    }

    public List<Record> list(Date date) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.date2sql(date));
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Record record = new Record();

                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");

                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);

                records.add(record);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return records;
    }

    public List<Record> listThisMonth() {
        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }

    public List<Record> list(java.util.Date start, java.util.Date end) {
        List<Record> records = new ArrayList<>();
        String sql = "select * from record where date >= ? and date <= ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.date2sql(start));
            ps.setDate(2, DateUtil.date2sql(end));
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Record record = new Record();

                int id = rs.getInt("id");
                int spend = rs.getInt("spend");
                int cid = rs.getInt("cid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                record.setId(id);
                record.setSpend(spend);
                record.setCid(cid);
                record.setComment(comment);
                record.setDate(date);

                records.add(record);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return records;
    }
}
