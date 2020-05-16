package com.xazhuxj.recursion;

/**
 * 迷宫
 */
public class Labyrinth {
    public static void main(String[] args) {
        int[][] map = new  int[8][7];
        //使用1表示强
        for (int i = 0; i <7 ; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i <8 ; i++) {
            map[i][0] =1;
            map[i][6] =1;
        }

        //设置挡板， 1表示
        map[3][1] = 1;
        map[3][2] =1;
        map[2][2] =1;
        //输出地图
        System.out.println("~~~~~原地图~~~~~");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        //输出新的地图
        System.out.println("~~~~~标识过的地图~~~~~");
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路，策略不一样，则路线也不一样
     * 说明
     * 1. map 表示地图
     * 2. i,j 表示从地图的那个位置开始出发(1,1)
     * 3. 如果小球能到map[6][5]位置，则说明通路找到
     * 4. 约定：当map[i][j] 为0 表示该点没有走过 1-墙 2-通路，可以走 3-该点已经走过，但走不通
     * 5. 在走迷宫时，需要确定一个策略(方法） 下->右->上->左，如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 开始找的位置
     * @param j 开始找的位置
     * @return 是否成功
     * TODO 求最短路径
     */
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){ // 道路已经找到 ok
            return true;
        }else{
            if(map[i][j] == 0 ){ //如果当前这个点还没有走过
                //按照策略  下->右->上->左 走
                map[i][j] = 2; //假定该点是可以走通
                if(setWay(map, i+1, j)){  //向下走
                    return true;
                }else if(setWay(map, i, j+1)){ //向右走
                    return true;
                }else if(setWay(map, i-1, j)){ //向上走
                    return true;
                }else if(setWay(map, i, j-1)){ //向左走
                    return true;
                }else{
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{//如果map[i][j] != 0, 可能是1， 2， 3
                return false;
            }
        }
    }
}
