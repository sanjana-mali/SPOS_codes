import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class lru
{
	public void execute(int arr[],int cap)
	{
		Deque<Integer>dq=new LinkedList();
		int miss=0,hit=0;
		for(int i=0;i<arr.length;i++)
		{
			if(dq.contains(arr[i]))
			{
				dq.remove(arr[i]);
				dq.addFirst(arr[i]);
				hit++;
			}
			else
			{
				if(dq.size()==cap)
				{
					dq.removeLast();
				}
				dq.addFirst(arr[i]);
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

}
