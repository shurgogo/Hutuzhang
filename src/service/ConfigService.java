package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {
    private static final String budget = "Budget";
    private static final String mysqlPath = "MysqlPath";
    private static final String default_budget = "500";

    static ConfigDAO dao = new ConfigDAO();

    public static String getBudget() {
        return budget;
    }

    public static String getMysqlPath() {
        return mysqlPath;
    }

    static {
        init();
    }

    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    public static void init(String key, String value) {
        Config config = dao.getByKey(key);
        if (null == config) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }

    public String get(String key) {
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        try{
            int i = Integer.parseInt(value);
            config.setValue(String.valueOf(i));
        } catch (NumberFormatException nfe) {
            config.setValue(value);
        }
        dao.update(config);
    }

    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }

}
