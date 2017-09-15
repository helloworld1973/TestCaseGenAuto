package testedProg;

public class BubbleSort{
	/*
   Bubble Sort
	 */
	
	public String sort(int[] array)
	{
		String routeString=new String("");
		for (int i = 1; i < array.length;i++)
		{
			//compare 2 neighbouring elements, move the bigger one backward
			for (int j=0;j <array.length-i;j++)
			{
				if (array[j] >array[j +1])
				{
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					routeString+="@";
				}else
				{
					routeString+="#";
				}
			}
		}
		System.out.println(routeString);
		showArray(array);
		return routeString;
		
	}

	public void showArray(int [] array)
	{
		for(int i:array)
		{
			System.out.print(">"+i);
		}
		System.out.println();
	}

}
