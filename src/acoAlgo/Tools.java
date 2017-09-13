package acoAlgo;

public class Tools {
	
	public String enCode10to2(Integer i)
	{
		String string2=Integer.toBinaryString(i);
		return string2;
		
	}

	
	public String deCode2to10(String string2)
	{
		String string10=Integer.valueOf(string2,2).toString();
		return string10;
	}

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

}
