package com.xazhuxj.sort;

import javax.swing.*;
import java.util.Arrays;

public class Sort {
    /**
     * 冒泡排序 O(n^2)
     * @param array
     */
    public static void bubbleSort(int[] array){
        int temp = 0;
        boolean flag = false;
        for(int i=0; i<array.length-1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {//感觉实际上是大数在向后排
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
//            System.out.println(Arrays.toString(array));

            if(!flag){ //没有交换的事情发生，已经完成排序了
                break;
            }else{
                flag = false;//继续
            }
        }
    }

    /**
     * 选择排序 O(n^2)
     * @param array
     */
    public static void selectSort(int[] array){
        int minValue, minIndex;

        for(int i=0; i<array.length-1; i++) {
            minValue = array[i];
            minIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }

            if(minIndex != i){
                array[minIndex] = array[i];
                array[i] = minValue;
            }
//            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 插入排序 O(n^2)
     * 思想
     * 一个有序数组+无序数组， 无序数组向有序数组中插值
     * 对极端情况 【1 2 3 4 5 6 1】，效率不高，可改用shell排序
     * @param array
     */
    public static void insertSort(int[] array){
        int insertValue, insertIndex;

        for(int i=1; i<array.length; i++) {
            insertValue = array[i]; //待插入的初始值
            insertIndex = i-1;      //待插入的位置

            //给insertValue找到插入的位置
            //说明
            //1. insertIndex >=0 保证不越界
            //2. 当 insertValue < array[insertIndex]， 待插入的数，还没有找到插入位置
            //3. 此时需要将 array[insertIndex] 后移
            while(insertIndex >=0 && insertValue < array[insertIndex]){
                array[insertIndex+1] = array[insertIndex];
                insertIndex--;
            }

            //当退出while循环时，说明插入的位置找到， insertIndex+1
            //if(insertIndex+1 != i) //该句判断是否需要
            array[insertIndex+1] = insertValue;
//            System.out.println(Arrays.toString(array));
        }
    }
//    这种方法不可行
//    public static void shellSort0(int[] arr) {
//        int temp;
//        for (int step = arr.length / 2; step > 0; step /= 2) {
//            for (int i = step; i < arr.length; i++) {
//                for (int j = i - step; j >= 0; j -= step) {//此处为交换法,效率不高,导致该方法不可采用
//                    if (arr[j] > arr[j + step]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + step];
//                        arr[j + step] = temp;
//                    }
//                }
//            }
////            System.out.println("After shell sort :" + Arrays.toString(arr));
//        }
//    }

    public static void shellSort(int[] arr) {
        int temp, j;
        // 增量gap,并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第step个元素，逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length; i++) {
                j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
//			System.out.println("After shell sort :" + Arrays.toString(arr));
        }
    }

    /**
     * 基数排序是桶排序的扩展
     * 分配式排序(distribution sort or bucker sor or bin sort)
     * 该方法适用于大于0的整数排序
     * int arr[] = {53, 3, 542, 748, 14, 214}
     * @param arr
     */
    public static void radixSort(int[] arr) {

        //先得到数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = (max + "").length();

        //定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候,数据溢出,则每一个一维数组(桶),大小定位arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length]; //每行代表一个桶

        int[] bucketElementCounts = new int[10]; //一维数组记录每个桶中每次放入的元素个数

        for(int i=0, n=1; i<maxLength; i++, n*=10) {
            //第i轮
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = (arr[j]/n) % 10; //对个位数用10求模取余运算,得个位上的数字

                //个位上的数字是几,就放入几号桶,前边的 bucket[digitOfElement] 就是桶
                // bucketElementCounts[digitOfElement] 表示相应的几号桶的计数器
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++; //记完数后,计数器+1
            }

            //从桶中取回数据
            int index = 0; //用于记录数据数组的下标
            for (int k = 0; k < bucketElementCounts.length; k++) { //轮询每个桶
                if (bucketElementCounts[k] > 0) { //桶中元素个数是否 >0
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];//取出桶中所有元素
                        bucket[k][l] = 0;//桶中数据清除
                    }
                    bucketElementCounts[k] = 0;//该桶数据取完,桶的计数也置为0
                }
            }
//            System.out.println("第"+ i+1 + "轮, 对个位的排序处理 arr =" + Arrays.toString(arr));
        }
        /*
        //第1轮
        for(int j=0; j<arr.length; j++){
            int digitOfElement = ( arr[j]/1 ) % 10; //对个位数用10求模取余运算,得个位上的数字

            //个位上的数字是几,就放入几号桶,前边的 bucket[digitOfElement] 就是桶
            // bucketElementCounts[digitOfElement] 表示相应的几号桶的计数器
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; //记完数后,计数器+1
        }

        //从桶中取回数据
        int index = 0; //用于记录数据数组的下标
        for(int k=0; k<bucketElementCounts.length; k++){ //轮询每个桶
            if(bucketElementCounts[k] > 0){ //桶中元素个数是否 >0
                for(int l=0; l<bucketElementCounts[k]; l++){
                    arr[index++] = bucket[k][l];//取出桶中所有元素
                    bucket[k][l] =0;//桶中数据清除
                }
                bucketElementCounts[k] = 0;//该桶数据取完,桶的计数也置为0
            }
        }
        System.out.println("第1轮, 对个位的排序处理 arr =" + Arrays.toString(arr));

        //第2轮
        for(int j=0; j<arr.length; j++){
            int digitOfElement = ( arr[j]/10 ) % 10; //对十位数用10求模取余运算,得个位上的数字

            //个位上的数字是几,就放入几号桶,前边的 bucket[digitOfElement] 就是桶
            // bucketElementCounts[digitOfElement] 表示相应的几号桶的计数器
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; //记完数后,计数器+1
        }

        //从桶中取回数据
        index = 0; //用于记录数据数组的下标
        for(int k=0; k<bucketElementCounts.length; k++){ //轮询每个桶
            if(bucketElementCounts[k] > 0){ //桶中元素个数是否 >0
                for(int l=0; l<bucketElementCounts[k]; l++){
                    arr[index++] = bucket[k][l];//取出桶中所有元素
                    bucket[k][l] =0;//桶中数据清除
                }
                bucketElementCounts[k] = 0;//该桶数据取完,桶的计数也置为0
            }
        }
        System.out.println("第2轮, 对个位的排序处理 arr =" + Arrays.toString(arr));

        //第3轮
        for(int j=0; j<arr.length; j++){
            int digitOfElement = ( arr[j]/(10*10) ) % 10; //对百位数用10求模取余运算,得个位上的数字

            //个位上的数字是几,就放入几号桶,前边的 bucket[digitOfElement] 就是桶
            // bucketElementCounts[digitOfElement] 表示相应的几号桶的计数器
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++; //记完数后,计数器+1
        }

        //从桶中取回数据
        index = 0; //用于记录数据数组的下标
        for(int k=0; k<bucketElementCounts.length; k++){ //轮询每个桶
            if(bucketElementCounts[k] > 0){ //桶中元素个数是否 >0
                for(int l=0; l<bucketElementCounts[k]; l++){
                    arr[index++] = bucket[k][l];//取出桶中所有元素
                    bucket[k][l] =0;//桶中数据清除
                }
                bucketElementCounts[k] = 0;//该桶数据取完,桶的计数也置为0
            }
        }
        System.out.println("第3轮, 对个位的排序处理 arr =" + Arrays.toString(arr));
        */
    }
}


