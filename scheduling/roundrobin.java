//package sch;

import java.util.Scanner;

public class roundrobin {
	
	static int N;
 
	static Scanner scan=new Scanner(System.in);
		public static void roundrobin( int a[], int b[], int n,int p) 
		{ 
			
			int res = 0; 
			int resc = 0; 
 

			int res_b[] = new int[b.length]; 
			int res_a[] = new int[a.length]; 

			for (int i = 0; i < res_b.length; i++) 
			{ 
				res_b[i] = b[i]; 
				res_a[i] = a[i]; 
			} 

			// critical time of system 
			int t = 0; 

			// for store the waiting time 
			int w[] = new int[p]; 

			// for store the Completion time 
			int comp[] = new int[p]; 
			while(true)
			{
				boolean flag=true;
				for(int i=0;i<p;i++)
				{
					if(res_a[i]<=t)
					{
						if(res_a[i]<=n)
						{
							
							if(res_b[i]>0)
							{ 
								flag=false;
								if(res_b[i]>n)
								{
									t=t+n;
									res_b[i]=res_b[i]-n;
									res_a[i]=res_a[i]+n;
									
									
								}
								else
								{
									t=t+res_b[i];
									comp[i]=t-a[i];
									w[i]=t-b[i]-a[i];
									res_b[i]=0;
								}
							
								
							}
						}
						else if(res_a[i]>n)
						{
							for(int j=0;j<p;j++)
							{
								if(res_a[j]<res_a[i])
								{
									if(res_b[j]>0)
									{ 
										flag=false;
										if(res_b[j]>n)
										{
											t=t+n;
											res_b[j]=res_b[j]-n;
											res_a[j]=res_a[j]+n;
											
											
										}
										else
										{
											t=t+res_b[j];
											comp[j]=t-a[j];
											w[j]=t-b[j]-a[j];
											res_b[j]=0;
										}
									
										
									}
								}
							}
							if(res_b[i]>0)
							{ 
								flag=false;
								if(res_b[i]>n)
								{
									t=t+n;
									res_b[i]=res_b[i]-n;
									res_a[i]=res_a[i]+n;
									
									
								}
								else
								{
									t=t+res_b[i];
									comp[i]=t-a[i];
									w[i]=t-b[i]-a[i];
									res_b[i]=0;
								}
							
								
							}
							
						}
					}
					else if(res_a[i]>t)
					{
						t++;
						i--;
					}
					
				}
				if(flag)
					break;
				
			}
			 

			System.out.println("name ctime wtime"); 
			for (int i = 0; i < p; i++) { 
				System.out.println(" " + i + " " + comp[i] 
								+ " " + w[i]); 

				res = res + w[i]; 
				resc = resc + comp[i]; 
			} 

			System.out.println("Average waiting time is "
							+ (float)res / p); 
			System.out.println("Average compilation time is "
							+ (float)resc / p); 
			
		} 

		// Driver Code 
		public static void main(String args[]) 
		{ 
			int N;
			System.out.println("enter the number of processe");
			N=scan.nextInt();
			// name of the process 
			int pid[]=new int[N]; 
			int arrivaltime[] = new int[N];
			int bursttime[] =new int[N]; 
			System.out.println("enter the arrival time for processes");
	    	for(int i=0;i<N;i++)
	    	{
	    		System.out.println("arrivall time for process p"+i+" :");
	    		arrivaltime[i]=scan.nextInt();
	    	}
	    	System.out.println("enter the brust  time for processes");
	    	for(int i=0;i<N;i++)
	    	{
	    		System.out.println("brust time for process p"+i+" :");
	    		bursttime[i]=scan.nextInt();
	    	}
			// quantum time of each process 
			int q =3;
			//q=scan.nextInt();

			// cal the function for output 
			roundrobin(arrivaltime, bursttime, q,N); 
		} 
	} 



/*
enter the number of processe
5
enter the arrival time for processes
arrivall time for process p0 :
0
arrivall time for process p1 :
1
arrivall time for process p2 :
2
arrivall time for process p3 :
3
arrivall time for process p4 :
4
enter the brust  time for processes
brust time for process p0 :
8
brust time for process p1 :
1
brust time for process p2 :
3
brust time for process p3 :
2
brust time for process p4 :
6
name ctime wtime
 0 17 9
 1 3 2
 2 5 2
 3 6 4
 4 16 10
Average waiting time is 5.4
Average compilation time is 9.4


*/
