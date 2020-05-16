package com.xazhuxj.sparseArray;

public class SparseArrayDemo {
    public static void main(String[] args) {
        //创建一个二维数组表示棋盘
        int nCols = 11, nRows = 11;
        //0 没有棋子， 1-黑子， 2-白子
        int[][] chessArray = new int[nRows][nCols];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        //输出原始的二位数组
        System.out.println("原始二维数组的内容");
        SparseArray.printArray(chessArray);

        //将二维数组转为稀疏数组
        SparseArray sparseArray = new SparseArray();
        sparseArray.toSparseArray(chessArray);
        sparseArray.print();

        //将稀疏数组转为二维数组
        int[][] chessArray2 = sparseArray.to2DArray();
        System.out.println("将稀疏数组转为二维数组的内容");
        SparseArray.printArray(chessArray2);
    }
}

class SparseArray {
    private  int[][] sparseArray; //稀疏数组

    public SparseArray(){
    }

    //将2维数组转化为稀疏数组
    public void toSparseArray(int[][] chessArray){
        int nRows = chessArray.length;
        int nCols = chessArray[0].length;

        int sum = 0; //记录有效数据的个数
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if(chessArray[i][j] != 0){
                    sum++;
                }
            }
        }

        this.sparseArray = new int[sum + 1][3];
        this.sparseArray[0][0] = nRows;   //原始数组的行
        this.sparseArray[0][1] = nCols;  //原始数组的列
        this.sparseArray[0][2] = sum;
        //再次遍历，记录数据
        int n = 1; //注意，此处由于稀疏数组中的第一行已经有数据了，所以从第2行开始
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if(chessArray[i][j] != 0){
                    this.sparseArray[n][0] = i;  //原始数组的行
                    this.sparseArray[n][1] = j;  //原始数组的列
                    this.sparseArray[n][2] = chessArray[i][j];
                    n++;
                }
            }
        }
    }

    //将稀疏数组转换为二维数组
    public int[][] to2DArray(){
        //将稀疏数组转为二维数组
        int[][] array = new int[ sparseArray[0][0] ][ sparseArray[0][1] ];
        for (int i=1; i<= sparseArray[0][2]; i++){
            array[ sparseArray[i][0] ][ sparseArray[i][1]  ] = sparseArray[i][2] ;
        }
        return array;
    }

    //输出稀疏数组的内容
    public void print(){
        System.out.println("~~~稀疏数组的内容~~~");
        for (int[] row : this.sparseArray){
            System.out.printf("%d\t%d\t%d\n", row[0], row[1], row[2]);
        }
    }

    //输出二维数组的内容
    public static void printArray(int[][] array){
        for (int[] row : array){
            for(int data : row)
                System.out.printf("%d\t", data);
            System.out.println();
        }
    }
}