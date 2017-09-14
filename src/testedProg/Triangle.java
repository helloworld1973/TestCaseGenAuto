package testedProg;

public class Triangle 
{
		
	static String EQUILATERAL = "equilateral";
	static String ISOSCELES = "isosceles";
	static String SCALENE = "scalene";
	String routeString=new String("");
	
	public String judge(int a,int b,int c)
	{
		//n=0 means scalene,n=1 means isosceles,n=2 means equilateral
		int n=0;
		if(a==b)
		{
			routeString+="00";
			n=1;
			if(a==c){
				routeString+="0";
				n=2;
				//System.out.println("000");
			}else
			{
				routeString+="1";
				//System.out.println("001");
			}
		}
		else if(a==c){
			routeString+="11";
			n=1;		
			//System.out.println("11");
		}
		else if(b==c){
			routeString+="22";
			n=1;
			//System.out.println("22");
		}
		else {
			routeString+="33";
			//System.out.println("33");
		}
		
		/*if(n==0){
			System.out.println("SCALENE");
		}
		else if(n==1){
			System.out.println("ISOSCELES");
		}
		else{
			System.out.println("EQUILATERAL");
		}*/
		return routeString;
	}

}
