package acoAlgo;

import java.util.ArrayList;

public class ACO 
{

	private static final String String = null;
	private CityGraph weight_distance;
	private int citynum = 24;
	private int p = 1000;//迭代次数
	private double bestLength;
	private String bestTour;
	private int antNum = 100;
	private int bestAnt = Integer.MAX_VALUE;
	private ANT[] ants;
	private double alpha = 1.0;
	private double beta = 5.0;
	private double Q = 1000;
	private long startTime;
	private long endTime;

	//初始化城市信息，假设为非对称TSP问题
	private void Init_Distance() 
	{
		weight_distance = new CityGraph(citynum);
	}

	//参数初始化
	private void Init_paras() 
	{
		bestLength = Double.MAX_VALUE;
		bestTour = "";
	}

	/**
	 * P<最大迭代次数
	 */
	private void Init_Ants() 
	{ //每次循环的每只蚂蚁都是新蚂蚁，没有残留信息
		ants = null;
		ants = new ANT[antNum];
		for (int i = 0; i < antNum; i++) 
		{


			ants[i] = new ANT(citynum, alpha, beta);
		}
	}

	//对每只蚂蚁按概率选择移动到下一个节点，直至遍历全部节点
	//当一只蚂蚁结束遍历时，应该更新全局信息素矩阵
	//计算每只蚂蚁的路径长度，
	private void MovetoNextCity() {
		for (int i = 0; i < antNum; i++) {
			ants[i].chooseNextCity();
			//            ants[i].updatePheromone();//信息素更新在一次迭代结束
			//            ants[i].calRoad();
		}
	}

	//记录当前最好解
	private void findBestRoad() {

		for (int i = 0; i < antNum; i++) {

			if (bestLength > ants[i].getRoadLength()) {
				bestLength = ants[i].getRoadLength();
				bestTour = ants[i].getRoad();
				bestAnt = i;
			}
		}
		//System.out.println("当前最优解是：" + bestTour);
		//System.out.println("该路径下的最低消耗：" + bestLength);
	}
	//按更新方程修改轨迹长度

	private String updatePheromone(String weWantRoute) {

		for (int i = 0; i < antNum; i++) 
		{
			String flag= ants[i].updatePheromone(Q, (endTime - startTime) / 1000,weWantRoute);
			if(flag=="!")
			{
				return "!";
			}
		}
		return "ao";

	}

	/**
	 * 迭代结束
	 */
	private String iterator(String weWantRoute) {
		Init_Distance();
		Init_paras();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < p; i++) 
		{//一次迭代即更新了一次解空间
			System.out.println("第" + i + "次迭代：");
			Init_Ants();
			MovetoNextCity();
			findBestRoad();
			endTime = System.currentTimeMillis();
			String flag2= updatePheromone(weWantRoute);
			if(flag2=="!")
				return "!";
		}
		return "no";
	}

	public static void main(String[] args) 
	{
		ArrayList<String> aList=new ArrayList<>();
		aList.add("000");aList.add("001");aList.add("11");aList.add("22");aList.add("33");
		ACO aco = new ACO();
		for(int i=0;i<aList.size();i++)
		{
			String iString=aco.iterator(aList.get(i));
			if(iString!="!")
			{
				i--;
			}
		}
		
	}
}
