package com.xazhuxj.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SortDemo {
    public static void main(String[] args) {
//        int[] arr = {2, 8, -1, 3, -5};
//        int[] arr = {1, 2, 3, 4, 5};
        int max = 100000;

        int[] arr = new int[max];
        for (int i = 0; i <max ; i++) {
            arr[i] = (int) (Math.random()*max*10000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + date1Str);

//        Sort.bubbleSort(arr); //10万个数据，大约在20秒左右
//        Sort.selectSort(arr);   //10万个数据，大约在4~5秒左右
//        Sort.insertSort(arr);   //10万个数据，大约在1~2秒左右
//        Sort.shellSort0(arr);   //交换法 10万个数据，插入法大约在9~10秒左右
        Sort.shellSort(arr);   //移位法 10万个数据，插入法大约在1秒左右

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + date2Str);
    }
}
