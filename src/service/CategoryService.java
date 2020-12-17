package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDao = new RecordDAO();

    public List<Category> list() {
        List<Category> categories = categoryDAO.list();
        for (Category category: categories) {
            List<Record> records = recordDao.list(category.getId());
            category.recordNumber = records.size();
        }
        categories.sort((c1, c2) -> c2.recordNumber - c1.recordNumber);
//        Collections.sort(categories, (c1, c2) -> c2.recordNumber - c1.recordNumber);
        return categories;
    }

    public void add(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDAO.add(category);
    }

    public void update(int id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryDAO.update(category);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

}
