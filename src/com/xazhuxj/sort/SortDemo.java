package com.xazhuxj.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SortDemo {
    public static void main(String[] args) {
//        int[] arr = {2, 8, -1, 3, -5};
//        int[] arr = {1, 2, 3, 4, 5};
//        int max = 80000;
        int max = 5;

        int[] arr = new int[max];
        for (int i = 0; i <max ; i++) {
            arr[i] = (int) (Math.random()*max*10000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + date1Str);

        //Sort.bubbleSort(arr);
        Sort.selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
    }
}
