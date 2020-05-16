package com.xazhuxj.recursion;

/**
 * 8皇后问题，一共92种方法
 */
public class Queens8 {
    int max = 8; //定义多少个皇后

    static int count = 0; //统计算法总数

    /**
    * 保存皇后放置位置的结果，比如 array = [0, 4, 7, 5, 2, 6, 1, 3]
    * 下表代表行， 值代表列 */
    private int[] array = new int[max];

    public static void main(String[] args) {
        Queens8 queens8 = new Queens8();
        queens8.check(0);
        System.out.println("一共有" + count + "个解法");
    }

    //放置第n个皇后
    //特别注意：check是每一次递归时，进入到check都有for循环
    private void check(int n){
        if(n == max ){ //n=8,其实8个皇后就已然放置好了
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i <max ; i++) {
            //先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            //判断当前放置第n个皇后道i列时，是否冲突
            if(judge(n)){ //不冲突，接着放第 n+1 个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，通过循环就在该行下一列的位置上继续放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
        }
    }

    private boolean judge(int n){
        for (int i = 0; i <n ; i++) {
            /**
             * 由于array采用数组下标代表行，相应的值代表列
             * 因此同行判断：两个数的 下标相等 则同行，在此，由于n代表下标，每次在变化，所以没必要判断
             * 同列判断：array的值代表列，其值是否就可以判断是否同列， 下边的第一个条件
             * 同斜线判断，由于是正方形，斜线方程为 y=x， 其Δx=Δy，所以判断 行的差值 =? 列的差值 即可，下式的第二个条件
             */
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
