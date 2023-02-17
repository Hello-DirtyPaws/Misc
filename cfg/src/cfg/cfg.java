package cfg;

public class cfg 
{

	public static void main(String[] args) 
	{
		
	}
	
	int binary_search(int a[], int low, int high, int target) 
	{
		while (low <= high) 
		{
			int middle = low + (high - low)/2;
			if (target < a[middle])
				high = middle - 1;
			else if (target > a[middle])
				low = middle + 1;
			else
				return middle;
		}
		return -1; // return -1 if target is not found in a[low, high]
	}

}
