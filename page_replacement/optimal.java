import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class optimal 
{
	public void execute(int arr[],int cap)
	{
		Deque<Integer>dq=new LinkedList();
		int miss=0,hit=0;
		for(int i=0;i<arr.length;i++)
		{
			if(dq.contains(arr[i]))
			{
				hit++;
			}
			else
			{
				if(dq.size()==cap)
				{
					dq.remove(far(arr,i,dq));
				}
				dq.addLast(arr[i]);
				miss++;
				
			}
			Iterator<Integer>itr=dq.iterator();
			while(itr.hasNext())
			{
				System.out.print(itr.next());
			}
			System.out.println();
		}
		System.out.println("misses"+miss+"\nhits"+hit);
		}
	

	private int far(int[] arr, int cur, Deque<Integer> dq)
	{
		int ind=0,max = 0;
		HashMap<Integer,Integer>hm=new HashMap();
		Iterator<Integer>itr=dq.iterator();
		while(itr.hasNext())
		{
			hm.put(itr.next(), arr.length);
		}
		for(int i=cur;i<arr.length;i++)
		{
			if(hm.containsKey(arr[i])&&hm.get(arr[i])>i)
			{
				hm.replace(arr[i], hm.get(arr[i]), i);
			}
		}
		for(Entry<Integer,Integer>entry:hm.entrySet())
		{
			if(entry.getValue()>max)
			{
				max=entry.getValue();
				ind=entry.getKey();	
			}
		}
		
		return ind;
	}
}
