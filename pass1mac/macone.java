//package macone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class macone {
	public static void main(String args[]) throws IOException 
	{
		BufferedReader br=new BufferedReader(new FileReader("input"));
		FileWriter mntfile=new FileWriter("mnt");
		FileWriter mdtfile=new FileWriter("mdt");
		FileWriter kpdtfile=new FileWriter("kpdt");
		FileWriter pntfile=new FileWriter("pnt");
		HashMap<String,Integer>pntab=new HashMap();
		int kp=0,pp=0,kpdtptr=0,paramno=0,f=0,flag=0,mdtptr=1;
		String line="",name="";
		while((line=br.readLine())!=null)
		{
			if(line.equalsIgnoreCase("MACRO"))
			{
				flag=1;
				line=br.readLine();
				String parts[]=line.split("\\s+");
				name=parts[0];
				for(int i=1;i<parts.length;i++)
				{
					parts[i]=parts[i].replaceAll("[&,]", "");
					if(parts[i].contains("="))
					{
						f=1;
						String arr[]=parts[i].split("=");
						pntab.put(arr[0], ++paramno);
						if(arr.length>1)
						{
							kpdtfile.write(arr[0]+"\t"+arr[1]+"\n");
						}
						else
						{
							kpdtfile.write(arr[0]+"\t - \n");
						}
						++kp;
					}
					else
					{
						pntab.put(parts[i], ++paramno);
						pp++;
					}
				}
				mntfile.write(parts[0]+"\t"+pp+"\t"+kp+"\t"+mdtptr+"\t"+(f==0?kpdtptr:kpdtptr+1)+"\n");
				kpdtptr=kpdtptr+kp;
				
			}
			else if(line.equals("MEND"))
			{
				mdtptr++;
				mdtfile.write("MEND");
				kp=pp=paramno=flag=0;
				pntfile.write("macroname="+name+"\n");
				for(Entry e:pntab.entrySet())
				{
					pntfile.write(e.getKey()+"\t");
				}
				pntfile.write("\n");
				pntab.clear();
			}
			else if(flag==1)
			{
				String parts[]=line.split("\\s+");
				String code="";
				for(int i=0;i<parts.length;i++)
				{
					if(parts[i].contains("&"))
					{
						parts[i]=parts[i].replaceAll("[&,]", "");
						code=code+"(P ,"+pntab.get(parts[i])+")"+"\t";
					}
					else
					{
						code=code+parts[i]+"\t";
					}
				}
				mdtptr++;
				mdtfile.write(code+"\n");
			}
		}
		mntfile.close();
		mdtfile.close();
		pntfile.close();
		kpdtfile.close();
		br.close();
		
		
	}
}
