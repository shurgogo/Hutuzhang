package dao;

import entity.Config;
import utils.DBUtil;
import utils.GUIUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigDAO {

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

    public void add(Config config) {
        String sql = "insert into config values(null, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void update(Config config) {
        String sql = "update config set key_ = ?, value = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.setInt(3, config.getId());
            ps.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "delete from config where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public Config get(int id) {
        Config config = null;
        String sql = "select * from config where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                config = new Config();
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setId(id);
                config.setKey(key);
                config.setValue(value);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return config;
    }

    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Config> list(int start, int count) {
        List<Config> configs = new ArrayList<>();
        String sql = "select * from config order by id asc limit ?, ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Config config = new Config();

                int id = rs.getInt("id");
                String key = rs.getString("key_");
                String value = rs.getString("value");
                config.setId(id);
                config.setKey(key);
                config.setValue(value);

                configs.add(config);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return configs;
    }

    public Config getByKey(String key) {
        Config config = null;
        String sql = "select * from config where key_ = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                config = new Config();
                int id = rs.getInt("id");
                String value = rs.getString("value");
                config.setId(id);
                config.setKey(key);
                config.setValue(value);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return config;
    }
}
