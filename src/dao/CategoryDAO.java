package dao;

import entity.Category;
import utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO{

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

    public void add(Category category) {
        String sql = "insert into category values(null, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                category.setId(id);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void update(Category category) {
        String sql = "update category set name = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "delete from category where id = " + id;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public Category get(int id) {
        Category category = null;
        String sql = "select * from category where id = " + id;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                category = new Category();
                String name = rs.getString("name");
                category.setId(id);
                category.setName(name);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return category;
    }

    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Category> list(int start, int count) {
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category order by id desc limit ?, ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next()) {
                Category category = new Category();

                int id = rs.getInt("id");
                String name = rs.getString("name");
                category.setId(id);
                category.setName(name);

                categories.add(category);
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return categories;
    }
}
