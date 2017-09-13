package acoAlgo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import testedProg.Triangle;

/**
 *
 * 
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

    //轮盘赌选择,对于第一只蚂蚁，每个方向上的可能性是一样的，先归一化信息素矩阵
    public void chooseNextCity() {
        while (Allowed.size() > 0) 
        {
//          getAllowed();

            double[] nextcities = CityGraph.getCities(currentCity);//得到从当前城市可到达的下一城市，可根据CityGraph形式改变
            //去除不可到达城市
            //轮盘赌选择
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
        System.out.println(p);
    }
        
    public String updatePheromone(double Q,long t)
{
        Q = Q/roadLength;
        //后面的部分需要修改
        String string2="";//一只蚂蚁走过的路径为二进制结构
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
        
        String aString=string2.substring(0, 8);
        String bString=string2.substring(8, 16);
        String cString=string2.substring(16, 24);
        
        Tools tools=new Tools();
        int a=Integer.parseInt(tools.deCode2to10(aString));
        int b=Integer.parseInt(tools.deCode2to10(bString));
        int c=Integer.parseInt(tools.deCode2to10(cString));
        Triangle triangle=new Triangle();
        String realRoute=triangle.judge(a, b, c);
        String weWantRoute="22";//000 && 001 && 11 && 22 && 33 
        double score=tools.calScore(weWantRoute, realRoute);
        if(score==1.0)
        {
        	System.out.println("!!!!!!!!!!!!!!!!!!!!"+a+"!!!!!!!!!!!!"+b+"!!!!!!!!!!!!!!!!"+c);
            return "!";
        }
        Q=Q*score;
        
        for(int i=0;i<Tabu.size()-1;i++)
        {
            CityGraph.setPhero(Tabu.get(i), Tabu.get(i+1), Q,t);
        }
         CityGraph.setPhero(Tabu.get(Tabu.size()-1), Tabu.get(0), Q,t);
         return "not ready";
    }
         
}
