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
        int temp = 0;
        int minValue;
        int minIndex;

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
            System.out.println(Arrays.toString(array));
        }
    }
}
