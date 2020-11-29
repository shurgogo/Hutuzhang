package test;

import utils.DateUtil;

public class TestDateUtil {
    public static void main(String[] args) {
        System.out.println(DateUtil.today());
        System.out.println(DateUtil.monthBegin());
        System.out.println(DateUtil.monthEnd());
        System.out.println(DateUtil.daysOfMonth());
        System.out.println(DateUtil.daysLeftOfMonth());
    }
}
