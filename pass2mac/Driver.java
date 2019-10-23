import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver 
{
	public static void main(String args[])throws Exception
	{
		BufferedReader mdtFile=new BufferedReader(new FileReader("mdt.txt"));
		BufferedReader kpdtFile=new BufferedReader(new FileReader("kpdt.txt"));
		BufferedReader mntFile=new BufferedReader(new FileReader("mnt.txt"));
		BufferedReader ir=new BufferedReader(new FileReader("intermediate.txt"));
		FileWriter out=new FileWriter("out.txt");
		
		HashMap<String, MNTEntry> mnt=new HashMap<>();
		HashMap<Integer, String> aptab=new HashMap<>();
		HashMap<String, Integer> aptabInverse=new HashMap<>();
		
		ArrayList<String> mdt=new ArrayList<>();
		ArrayList<String> kpdt=new ArrayList<>();
		
		String line="";
		while((line=mdtFile.readLine())!=null)
		{
			mdt.add(line);
		}
		
		while((line=kpdtFile.readLine())!=null)
		{
			kpdt.add(line);
		}
		
		while((line=mntFile.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			mnt.put(parts[0], new MNTEntry(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
		}
		
		int pp=0,kp=0,mdtp=0,kpdtp=0;
		String code="";
		while((line=ir.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			if(mnt.containsKey(parts[0]))
			{
				pp=mnt.get(parts[0]).getPp();
				kp=mnt.get(parts[0]).getKp();
				mdtp=mnt.get(parts[0]).getMdtp();
				kpdtp=mnt.get(parts[0]).getKpdtp();
				int paramNo=1;
				
				for(int i=0;i<pp;i++)
				{
					parts[i]=parts[i].replaceAll(",", "");
					aptab.put(paramNo,parts[paramNo]);
					aptabInverse.put(parts[paramNo], paramNo);
					paramNo++;
				}
				
				int j=kpdtp-1;
				for(int i=0;i<kp;i++)
				{
					String arr[]=kpdt.get(j).split("\\s+");
					aptab.put(paramNo,arr[1]);
					aptabInverse.put(arr[0], paramNo);
					j++;
					paramNo++;
				}
				
				for(int i=pp+1;i<parts.length;i++)
				{
					parts[i]=parts[i].replaceAll("[&,]", "");
					String arr[]=parts[i].split("=");
					aptab.put(aptabInverse.get(arr[0]), arr[1]);
				}
				
				int i=mdtp-1;
				
				while(!mdt.get(i).equalsIgnoreCase("MEND"))
				{
					String arr[]=mdt.get(i).split("\\s+");
					code="+";
					for(int k=0;k<arr.length;k++)
					{
						if(arr[k].contains("(P,"))
						{
							arr[k]=arr[k].replaceAll("[^0-9]", "");
							code=code+aptab.get(Integer.parseInt(arr[k]))+"\t";
						}
						else
						{
							code=code+arr[k]+"\t";
						}
					}
					System.out.println(code);
					out.write(code+"\n");
					i++;
				}
			}
			else
			{
				code=line;
				System.out.println(code);
				out.write(code+"\n");
			}
		}
		
		mdtFile.close();
		kpdtFile.close();
		mntFile.close();
		ir.close();
		out.close();
	}
}
