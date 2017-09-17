package acoAlgo;

import java.util.Random;

/**
 * distance[][]一旦确定不再改变，迭代过程中更新pheromone[][]
 *
 * 
 */
public class CityGraph 
{

    private static double distance[][];
    private static double pheromone[][];
    private static int num;
    private static double ratio01=0.3;//0-1
    Random random = new Random();

    public CityGraph(int num) 
    {
        this.num = num;
        initDistance();
        initPheronome();
    }

    private void initDistance() 
    {
        distance = new double[num][num];
        //distance是一个对称阵，且对角元素设为无穷大；对角线元素不会被用到，如果算法正确
        for (int i = 0; i < num; i++) 
        {
                for (int j = i; j < num; j++) 
                {
                    if (i == j) 
                    {
                        distance[i][j] = 0;
                    } else 
                    {
                        distance[i][j] = distance[j][i] = Math.random()>ratio01?1:0; //产生各点之间的距离=绝对值(i-j)
                    }
                }
        }
       printlndistance();
    }

    private void initPheronome() {
        pheromone = new double[num][num];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                pheromone[i][j] = 0.1;
            }
        }
    }

    public static double getDistance(int i, int j) { //注意i,j和城市之间的对应关系
        return distance[i][j];
    }

    public static double getPheronmone(int i, int j) {
        return pheromone[i][j];
    }
    
    public static double[] getCities(int i){
        return pheromone[i];
    }
    
    //需要修改
    public static void setPhero(int i,int j ,double Q,long t){
        pheromone[i][j]+=Q-t/100* pheromone[i][j];
    }
    
    
    private void printlndistance() {
        //System.out.printf("%8s","");
        for (int i = 0; i < num; i++) 
        {
            //System.out.printf("%5s",i);
        }
        //System.out.println();
        for (int i = 0; i < num; i++) 
        {
            //System.out.printf("%5s",i);
            for (int j = 0; j < num; j++) 
            {
                //System.out.printf("%5s",(int)distance[i][j]);
            }
            //System.out.println();
        }
    }
}
