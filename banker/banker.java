//package Bankers_Algo;

import java.util.*;


public class banker
{
	static int avail[],allocation[][],need[][],max[][],m,n;
	
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		m=s.nextInt();
		System.out.println("Enter the number of resources:");
		n=s.nextInt();
		allocation=new int[m][n];
		need=new int[m][n];
		max=new int[m][n];
		avail=new int[n];
		System.out.println("Enter the Allocation matrix:");
		for(int i=0;i<m;i++)
		{
			System.out.print("p"+i+": ");
			for(int j=0;j<n;j++)
			{
				allocation[i][j]=s.nextInt();
			}
			System.out.println();
		}
		
		System.out.println("Enter the Max matrix:");
		for(int i=0;i<m;i++)
		{
			System.out.print("p"+i+": ");
			for(int j=0;j<n;j++)
			{
				max[i][j]=s.nextInt();
			}
			System.out.println();
		}
		
		System.out.println("Enter available resources:");
		for(int i=0;i<n;i++)
		{
			avail[i]=s.nextInt();
		}
		
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				need[i][j]=max[i][j]-allocation[i][j];
				System.out.print(need[i][j]+"\t");
			}
			System.out.println();
		}
		int count=0;
		boolean finish[]=new boolean[m];
		boolean found=true;
		System.out.println("Safe Sequence:");
		while(found==true && count<m)
		{
			found=false;
			for(int i=0;i<m;i++)
			{
				if(finish[i]==false)
				{
					int j;
					for(j=0;j<n;j++)
					{
						if(need[i][j]>avail[j])
						{
							break;
						}
					}
					if(j==n)
					{
						finish[i]=true;
						for(j=0;j<n;j++)
						{
							avail[j]+=allocation[i][j];
						}
						found=true;
						System.out.print("p"+i+"-> ");
						count++;
						break;
					}
				}
			}
		}
		if(count<m)
		{
			System.out.println("\nResources cannot be allocated to all the processes!");
		}
		s.close();
	}
	
}

/*
enter number of process
5
enter number of resources
3
enter allocation matrix
0
1 0
2 0 0
3 0 2
2 1 1
0 0 2
enter maximum matrix
7 5 3
3 2 2
9 0 2
2 2 2
4 3 3
enter available
3 3 2
need matrix
 7 4 3
 1 2 2
 6 0 0
 0 1 1
 4 3 1
	 p1-->	 p3-->	 p4-->	 p0-->	 p2-->
*/



