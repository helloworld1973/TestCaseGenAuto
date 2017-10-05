package acoAlgo;

import java.util.ArrayList;

import javax.lang.model.element.Element;

public class ACO 
{

	private static final String String = null;
	private CityGraph weight_distance;
	private int citynum = 36;//triangle:24 (3*8 generate 0~255  three number)  
	                         //bubbleSort:30 (5*6 generate 0~63 five number)
	                         //binarySearch:36(5*6+1*6 generate 0~63 six number)
	private int p = 1000;//interation times
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

	//initial 
	private void Init_Distance() 
	{
		weight_distance = new CityGraph(citynum);
	}

	//initial pares
	private void Init_paras() 
	{
		bestLength = Double.MAX_VALUE;
		bestTour = "";
	}

	/**
	 * P< the max interations
	 */
	private void Init_Ants() 
	{ //each interation is a new start, no last interation's info
		ants = null;
		ants = new ANT[antNum];
		for (int i = 0; i < antNum; i++) 
		{


			ants[i] = new ANT(citynum, alpha, beta);
		}
	}


	private void MovetoNextCity() {
		for (int i = 0; i < antNum; i++) {
			ants[i].chooseNextCity();
			//            ants[i].updatePheromone();//信息素更新在一次迭代结束
			//            ants[i].calRoad();
		}
	}

	//record the best result right now
	private void findBestRoad() {

		for (int i = 0; i < antNum; i++) {

			if (bestLength > ants[i].getRoadLength()) {
				bestLength = ants[i].getRoadLength();
				bestTour = ants[i].getRoad();
				bestAnt = i;
			}
		}
	}
	//based on update score to update 
	
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

	
	private String iterator(String weWantRoute) 
	{
		Init_Distance();
		Init_paras();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < p; i++) 
		{//after each iteration,the results will update 
			System.out.println("The" + i + "th  iterations：");
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
		
		
/*        //bubbleSort
		int routeLength=0;
		for (int j = 1; j < 5;j++)
		{
			for (int m=0;m <5-j;m++)
			{
				routeLength++;
			}
		}
		Tools tools=new Tools();
		aList=tools.genWeWantRoute(routeLength);
		//bubbleSort 
*/		

		

		/*//binarySearch
		double arrInt=5.0;
		double d=Math.ceil(arrInt/2);
		int totalCompareNum=(int) d;
		Tools tools=new Tools();
		
		for(int j=totalCompareNum;j>1;j--)
		{
			ArrayList<String> aListTemp=new ArrayList<>();
			aListTemp=tools.genWeWantRoute(j-1);
			for(int i=0;i<aListTemp.size();i++)
			{
				String aString=aListTemp.get(i);
				aList.add(aString+"$");
			}
		}
		aList.remove(2);
		aList.remove(2);
		aList.add("$");
		//binarySearch
*/		

		
		

		//triangle
		aList.add("000");aList.add("001");aList.add("11");aList.add("22");aList.add("33");
		//triangle
		
		
		for(int i=0;i<aList.size();i++)
		{
			ACO aco = new ACO();
			String iString=aco.iterator(aList.get(i));
			if(iString!="!")
			{
				//i--;
			}else
			{
				System.out.println("find!");
			}
		}
		
	}
}
