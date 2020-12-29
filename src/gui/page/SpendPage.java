package gui.page;

public class SpendPage {
    // 本月消费
    public String monthSpend;
    // 今日消费
    public String todaySpend;
    // 日均消费
    public String avgSpendPerDay;
    // 本月剩余
    public String monthLeft;
    // 日均可用
    public String dayAvgAvailable;
    // 距离月末
    public String monthLeftDay;
    // 使用比例
    public int percent;
    // 是否超支
    public boolean overSpend;

    public SpendPage() {
    }

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthLeft, int dayAvgAvailable, int monthLeftDay, int percent) {
        this.overSpend = monthLeft < 0;
        this.monthSpend = "￥" + monthSpend;
        this.todaySpend = "￥" + todaySpend;
        this.avgSpendPerDay = "￥" + avgSpendPerDay;
        this.monthLeft = overSpend ? "超支" + Math.abs(monthLeft) : "￥" + monthLeft;
        this.dayAvgAvailable = "￥" + dayAvgAvailable;
        this.monthLeftDay = monthLeftDay + "天";
        this.percent = percent;
    }

    public String getMonthSpend() {
        return monthSpend;
    }

    public String getTodaySpend() {
        return todaySpend;
    }

    public String getAvgSpendPerDay() {
        return avgSpendPerDay;
    }

    public String getMonthLeft() {
        return monthLeft;
    }

    public String getDayAvgAvailable() {
        return dayAvgAvailable;
    }

    public String getMonthLeftDay() {
        return monthLeftDay;
    }

    public int getPercent() {
        return percent;
    }

    public boolean isOverSpend() {
        return overSpend;
    }
}
