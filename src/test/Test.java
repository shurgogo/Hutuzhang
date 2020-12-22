package test;

import dao.ConfigDAO;
import dao.DAO;
import entity.Category;
import entity.Config;
import utils.GUIUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Test
 * @Author Joshua
 * @Date 2020/11/23
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
//        Collections.sort(integers, (c2, c1) -> c2-c1);
        Comparator<Category> c = (c1, c2) -> c1.recordNumber-c2.recordNumber;
        Collections.sort(integers, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(integers);
    }
    Integer i;

}
