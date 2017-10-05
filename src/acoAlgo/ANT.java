package acoAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import testedProg.BinarySearch;
import testedProg.BubbleSort;
import testedProg.Triangle;

/**
 *
 * author: Jason Ye
 */
public class ANT {

    private List<Integer> Tabu;
    private List<Integer> Allowed;
    private double[][] Delta;
    private int currentCity;
    private int citynum;
    private double roadLength;
    private Random random;
    private double alpha;
    private double beta;

    ANT(int citynum, double alpha, double beta) 
    {
        this.citynum = citynum;
        this.alpha = alpha;
        this.beta = beta;
        roadLength = 0.0;
        Tabu = new ArrayList<Integer>();
        Allowed = new ArrayList<Integer>();
        random = new Random();
        for (int i = 0; i < citynum; i++) {
            Allowed.add(i);
        }
        Delta = new double[citynum][citynum];
        InitCurrentCity();
    }

    private void InitCurrentCity() 
    {
        currentCity = random.nextInt(citynum);
        Tabu.add(Allowed.get(currentCity));
        Allowed.removeAll(Tabu);
    }

    //use roulette selection to choose. For each ant, every directions have the same posibility
    public void chooseNextCity() {
        while (Allowed.size() > 0) 
        {
//          getAllowed();

            double[] nextcities = CityGraph.getCities(currentCity);
            //get the reachable cities from the current cities
            //delete the unreachable cities
            //statistic  roulette selection to choose
            int tempcity = nextcities.length - 1;
            double all_p = 0.0;
            for (int i = 0; i < nextcities.length; i++) 
            {
                all_p += nextcities[i];
            }
            while (true) 
            {
                double p = all_p * random.nextDouble();
                double temp = 0.0;
                for (int i = 0; i < nextcities.length; i++) 
                {
                    temp += nextcities[i];
                    if (p < temp) 
                    {
                        tempcity = i;
                        break;
                    }
                }
                if (!(tempcity == currentCity) && !Tabu.contains(tempcity)) 
                {
                    break;
                }
            }
            roadLength += CityGraph.getDistance(currentCity, tempcity);           
            currentCity = tempcity;
            Tabu.add(currentCity);
//            System.out.println(currentCity);
          Allowed.removeAll(Tabu);
        }
         roadLength += CityGraph.getDistance(Tabu.get(currentCity), Tabu.get(0));          
    }

    public double getRoadLength() {
        return roadLength;
    }

    public String getRoad() {
        String p = "";
        for (int i = 0; i < citynum; i++) {
            p += Tabu.get(i) + ";";
        }
        return p;
    }
    
        public void  getAllowed() {
        String p = "";
        for (int i = 0; i < Allowed.size(); i++) {
            p += Allowed.get(i) + ";";
        }
        //System.out.println(p);
    }
        
    public String updatePheromone(double Q,long t,String weWantRoute)
{
        Q = Q/roadLength;
        
        String string2="";//the route(the ant walks through) is binary structure
        for(int j=0;j<Tabu.size();j++)
        {
        	if(j!=Tabu.size()-1)
        	{
        	int first=Tabu.get(j);
        	int second=Tabu.get(j+1);
        	string2+=(int)(CityGraph.getDistance(first, second))+"";
        	}
        }
        string2=string2+(int)(CityGraph.getDistance(Tabu.size()-1, 0))+"";
        
        
/*        //binarySearch
        ArrayList<String> aList=new ArrayList<>();
        for(int m=0;m<6;m++)
        {
            int first=m*6;
        	aList.add(string2.substring(first, first+6));
        }
        String value=aList.get(aList.size()-1);
        aList.remove(aList.size()-1);

        Tools tools=new Tools();
        int valueFind=Integer.parseInt(tools.deCode2to10(value));
        
        int[] intArray=new int[aList.size()];

        for(int n=0;n<aList.size();n++)
        {
        	intArray[n]=Integer.parseInt(tools.deCode2to10(aList.get(n)));
        }
        
        
        BubbleSort bubbleSort=new BubbleSort();
        bubbleSort.sort(intArray);
        BinarySearch binarySearch=new BinarySearch();
        String realRoute=binarySearch.binarySearch(intArray,valueFind);
        double score=tools.calScore(weWantRoute, realRoute);
        System.out.println("SCORE:  "+score);
        if(score==1.0)
        {
        	for(int o=0;o<intArray.length;o++)
        	{
        		System.out.println(intArray[o]);
        	}
        	System.out.println("***************************");
    		System.out.println(valueFind);
            return "!";
        }  
        
      //binarySearch
*/        
              
        
/*        //bubbleSort
        ArrayList<String> aList=new ArrayList<>();
        for(int m=0;m<5;m++)
        {
            int first=m*6;
        	aList.add(string2.substring(first, first+6));
        }

        Tools tools=new Tools();
        int[] intArray=new int[aList.size()];
        int[] notChangeIntArray=new int[aList.size()];
        for(int n=0;n<aList.size();n++)
        {
        	intArray[n]=Integer.parseInt(tools.deCode2to10(aList.get(n)));
        }
        
        BubbleSort bubbleSort=new BubbleSort();
        notChangeIntArray=intArray.clone();
        String realRoute=bubbleSort.sort(intArray);
        double score=tools.calScore(weWantRoute, realRoute);
        System.out.println("SCORE:  "+score);
        if(score==1.0)
        {
        	for(int o=0;o<notChangeIntArray.length;o++)
        	{
        		System.out.println(notChangeIntArray[o]);
        	}
            return "!";
        }
        //bubbleSort      
*/        
 
        
        
 
        //triangle
        String aString=string2.substring(0, 8);
        String bString=string2.substring(8, 16);
        String cString=string2.substring(16, 24);
        
        Tools tools=new Tools();
        int a=Integer.parseInt(tools.deCode2to10(aString));
        int b=Integer.parseInt(tools.deCode2to10(bString));
        int c=Integer.parseInt(tools.deCode2to10(cString));
        Triangle triangle=new Triangle();
        String realRoute=triangle.judge(a, b, c);
        double score=tools.calScore(weWantRoute, realRoute);
        if(score==1.0)
        {
        	System.out.println("!!!!!!!!!!!!!!!!!!!!"+a+"!!!!!!!!!!!!"+b+"!!!!!!!!!!!!!!!!"+c);
            return "!";
            
        }
        //triangle
        
        
        
        Q=Q*score;
        
        for(int i=0;i<Tabu.size()-1;i++)
        {
            CityGraph.setPhero(Tabu.get(i), Tabu.get(i+1), Q,t);
        }
         CityGraph.setPhero(Tabu.get(Tabu.size()-1), Tabu.get(0), Q,t);
         return "not ready";
    }
         
}
