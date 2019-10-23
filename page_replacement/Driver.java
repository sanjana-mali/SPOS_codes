import java.util.Scanner;

public class Driver 
{
	static Scanner scan=new Scanner(System.in);
	
	public static void main(String args[])
	{
		int n;
		System.out.println("enter the number of pages");
		n=scan.nextInt();
		int arr[]=new int[n];
		System.out.println("enter the pages");
		for(int i=0;i<n;i++)
		{
			arr[i]=scan.nextInt();
		}
		int size;
		size=scan.nextInt();
		//fcfs f=new fcfs();
		//f.execute(arr,size);
		optimal f=new optimal();
		f.execute(arr,size);
		
	}
	

}
