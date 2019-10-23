//package scheduling;

import java.util.Scanner;



class Schedule2{

	void input(){
		int pno;
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter1 no of processess");
		
		pno = sc.nextInt();	
		int at[] = new int[pno];
		int bt[] = new int[pno];
		int ct[] = new int[pno];
		int tat[] = new int[pno];
		int wt[] = new int[pno];
		int k[] = new int[pno];
		
		for(int i=0;i<pno;i++){
			System.out.println("Enter Arrival time for P:"+i);
			at[i]= sc.nextInt();
		}

		for(int i=0;i<pno;i++){
			System.out.println("Enter bursst time for P:"+i);
			bt[i]= sc.nextInt();
		}
		
		for(int i=0;i<pno;i++){
			k[i] = bt[i];
		}
		
		
		int complete = 0;
		int st=0;
		int f[] = new int[pno];
		while(true)
		{
			int pos=pno,min=999;
			if(complete == pno)
				break;
			for(int i=0;i<pno;i++)
			{
			     if((at[i]<=st) && (f[i]==0) && (bt[i]<min)){
			    	    min =bt[i];
			    	    pos =i;
			     }
			}
			if(pos==pno)
				st++;
			else
			{
				bt[pos]--;
				st++;
				if(bt[pos]==0)
				{
					ct[pos] = st;
					f[pos] =1;
					complete++;
				}
			}
		}
		
		for(int i=0;i<pno;i++){
			tat[i] = ct[i] - at[i];	
		}
		for(int i=0;i<pno;i++){
			wt[i] = tat[i] - k[i];	
		}
		
      System.out.println("\tPID\tAT\tBT\tCT\tTAT\tWT");
		
		for(int i=0;i<pno;i++)
		{
			System.out.println("\tP"+i+"\t"+at[i]+"\t"+k[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
		}
		
		
	}
}


public class SRTF {

	public static void main(String[] args) {
		Schedule2 s = new Schedule2();
		s.input();
	}
}

/*
Enter1 no of processess
6
Enter Arrival time for P:0
0
Enter Arrival time for P:1
1
Enter Arrival time for P:2
2
Enter Arrival time for P:3
3
Enter Arrival time for P:4
4
Enter Arrival time for P:5
5
Enter bursst time for P:0
7
Enter bursst time for P:1
5
Enter bursst time for P:2
3
Enter bursst time for P:3
1
Enter bursst time for P:4
2
Enter bursst time for P:5
1
	PID	AT	BT	CT	TAT	WT
	P0	0	7	19	19	12
	P1	1	5	13	12	7
	P2	2	3	6	4	1
	P3	3	1	4	1	0
	P4	4	2	9	5	3
	P5	5	1	7	2	1
*/
