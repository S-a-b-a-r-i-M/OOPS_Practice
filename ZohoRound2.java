package zoho;
import java.util.*;

public class ZohoRound2 {
	
	public static Scanner in=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int count;
		System.out.println("number of intervals");
		count=in.nextInt();
		int[][] intervals=new int[count][2];
		
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<2;j++)
			{
				intervals[i][j]=in.nextInt();
			}
		}
		
		sort(intervals);//BASE STARTING INTERVAL
		
		mergeIntervals(intervals);
		
	}

	private static void sort(int[][] intervals) {
		
		for(int i=0;i<intervals.length;i++)
		{
			for(int j=i+1;j<intervals.length;j++)
				if(intervals[i][0]>intervals[j][0])
				{
					int[] temp=intervals[i];
					intervals[i]=intervals[j];
					intervals[j]=temp;
				}
		}
	}

	private static void mergeIntervals(int[][] intervals) {
			
		int j=1,i=0;
		while(i<intervals.length)
		{
			int[] arr=new int[2];
			arr[0]=intervals[i][0];
			arr[1]=intervals[i][1];
			while(j<intervals.length && intervals[i][1]>=intervals[j][0])
			{	
				if(intervals[i][1]>intervals[j][1])
					arr[1]=intervals[i][1];
				else
					arr[1]=intervals[j][1];
				j++;
			}
			i=j++;
			System.out.println(Arrays.toString(arr));
		}
		
	}

}
