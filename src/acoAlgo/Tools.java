package acoAlgo;

import java.util.ArrayList;

public class Tools {
	
	public String enCode10to2(Integer i)
	{
		String string2=Integer.toBinaryString(i);
		return string2;
	}
	//encode from decimalism to binary encode
	
	public String deCode2to10(String string2)
	{
		String string10=Integer.valueOf(string2,2).toString();
		return string10;
	}
	//decode from binary to decimalism
	
    public double calScore(String a,String b)
    {//compare 2 string,and calculate the different as the score.
    	int sameChars=0;
    	int totalChars=0;
    	char[] aChars=a.toCharArray();
    	char[] bChars=b.toCharArray();
    	if(aChars.length>bChars.length)
    	{
    		totalChars=aChars.length;
    		for(int i=0;i<bChars.length;i++)
    		{
    			if(bChars[i]==aChars[i])
    			{
    				sameChars++;
    			}
    		}
    	}else
    	{
    		totalChars=bChars.length;
    		for(int i=0;i<aChars.length;i++)
    		{
    			if(aChars[i]==bChars[i])
    			{
    				sameChars++;
    			}
    		}
    	}
    	return (double)sameChars/(double)totalChars;
    }
    //calculate how many differents are there between the route we want and the current interation generates
    //use the differents to cal the score
    
    public ArrayList<String> genWeWantRoute(int routeLength)
    {
    	ArrayList<String> aList=new ArrayList<>();//@#
		String aString="@";
		String bString="#";
		aList.add(aString);aList.add(bString);
    	for(int i=1;i<routeLength;i++)
    	{
    		int sizeNow=aList.size();
	         for(int j=0;j<sizeNow;j++)
	         {
	        	 aList.add(aList.get(j));
	         }
	         for(int k=0;k<aList.size()/2;k++)
	         {
	        	 String original=aList.get(k);
	        	 aList.set(k, original+"@");
	         }
	         for(int s=aList.size()/2;s<aList.size();s++)
	         {
	        	 String original=aList.get(s);
	        	 aList.set(s, original+"#");
	         }
    	}
    	for(int m=1;m<aList.size();m++)
    	{
    		System.out.println(aList.get(m));
    	}
		return aList;
    }
    //automatically generate route we want
}
