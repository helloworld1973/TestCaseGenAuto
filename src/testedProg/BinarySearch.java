package testedProg;

public class BinarySearch {
	/**
	 * Binary Search
	 * 
	 * @param arr: an integer array
	 * @param key: find the key
	 * @return
	 */
	public static String binarySearch(int[] arr, int key) 
	{
		String routeString=new String("");
		int n = arr.length;
		int low, high, mid;
		low = 0;
		high = n - 1;
		while (low <= high) 
		{
			mid = (low + high) / 2;
			if (arr[mid] == key) 
			{
				routeString+="$";
				return routeString;
			} else if (arr[mid] > key) 
			{
				routeString+="#";
				high = mid - 1;
			} else 
			{
				routeString+="@";
				low = mid + 1;
			}
		}
		return routeString;
	}
	
	
/*	public static void main(String[] args) 
	{
		int key = 10;
		int[] arr = { 1, 2, 4, 5, 6, 6, 6, 7, 10, 123, 232, };
		int pos = BinarySearch.binarySearch(arr, key);
		if (pos > 0) {
			System.out.println("Done!the position of the key is:" + pos);
		} else {
			System.out.println("the key is not exit!");
		}
	}*/


}

