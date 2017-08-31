package testedProg;

public class Triangle {
		
	static String EQUILATERAL = "equilateral";
	static String ISOSCELES = "isosceles";
	static String SCALENE = "scalene";
	
	public String judge(int a,int b,int c){
		//n=0 means scalene,n=1 means isosceles,n=2 means equilateral
		int n=0;
		if(a==b){
			n=1;
			if(a==c){
				n=2;
			}
		}
		else if(a==c){
			n=1;		
		}
		else if(b==c){
			n=1;
		}
		
		if(n==0){
			return SCALENE;
		}
		else if(n==1){
			return ISOSCELES;
		}
		else{
			return EQUILATERAL;
		}
	}
}
