//package scheduling;

import java.util.Scanner;

public class fcfs 
{
	static Scanner scan=new Scanner(System.in);
    void input()
    {
    	int pn;
    	System.out.println("enter the no of processes");
    	pn=scan.nextInt();
    	int at[]=new int[pn];
    	int bt[]=new int[pn];
    	int ct[]=new int[pn];
    	int wt[]=new int[pn];
    	int tt[]=new int[pn];
    	int sumt=0;
    	int sumw=0;
    	System.out.println("enter the arrival time for processes");
    	for(int i=0;i<pn;i++)
    	{
    		System.out.println("arrivall time for process p"+i+" :");
    		at[i]=scan.nextInt();
    	}
    	System.out.println("enter the brust  time for processes");
    	for(int i=0;i<pn;i++)
    	{
    		System.out.println("brust time for process p"+i+" :");
    		bt[i]=scan.nextInt();
    	}
    	ct[0]=bt[0];
    	for(int i=1;i<pn;i++)
    	{
    		ct[i]=ct[i-1]+bt[i];//completion time
    	}
    	for(int i=0;i<pn;i++)
    	{
    		tt[i]=ct[i]-at[i];//tt=ct-at;
    		sumt+=tt[i];
    	}
    	for(int i=0;i<pn;i++) {
    		wt[i]=tt[i]-bt[i];//wt=tt-bt;
    		sumw+=wt[i];
    	}
    	float avgtt=sumt/pn;
    	float avgwt=sumw/pn;
    	System.out.println("P\tAT\tBT\tCT\tTT\tWT");
    	for(int i=0;i<pn;i++)
    	{
    		System.out.println("P"+i+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tt[i]+"\t"+wt[i]);
    	}
    	System.out.println("AVG TT"+avgtt);
    	System.out.println("AVG wt"+avgwt);
    	
    }
	public static void main(String args[])
	{
		fcfs f=new fcfs();
		f.input();
		
	}
}

