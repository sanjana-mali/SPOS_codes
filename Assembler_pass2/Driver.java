import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Driver 
{
	public static void main(String args[])throws Exception
	{
		BufferedReader symFile=new BufferedReader(new FileReader("sym.txt"));
		BufferedReader litfile=new BufferedReader(new FileReader("lit.txt"));
		BufferedReader ic=new BufferedReader(new FileReader("lc.txt"));
		
		HashMap<Integer, Integer> symtab=new HashMap<>();
		HashMap<Integer, Integer> littab=new HashMap<>();
		
		String line="";
		int i=1;
		while((line=symFile.readLine())!=null)
		{
			String sym[]=line.split("\\s+");
			symtab.put(i, Integer.parseInt(sym[1]));
			i++;
		}
		
		line="";
		i=1;
		while((line=litfile.readLine())!=null)
		{
			String lit[]=line.split("\\s+");
			littab.put(i, Integer.parseInt(lit[1]));
			i++;
		}
		String code="";
		while((line=ic.readLine())!=null)
		{
			String operands[]=line.split("\\s+");
			operands[0]=operands[0].replaceAll("[()]", "");
			String op[]=operands[0].split(",");
			if(op[0].equals("DL"))
			{
				operands[1]=operands[1].replaceAll("[()]", "");
				String parts[]=operands[1].split(",");
				code="+00 0 "+String.format("%03d", Integer.parseInt(parts[1]));
				System.out.println(code);
			}
			if(op[0].equals("IS"))
			{
				if(operands.length==1)
				{
					code="+00 0 000";
					System.out.println(code);
				}
				else
				{
					code="+"+op[1]+" ";
					for(int j=1;j<operands.length;j++)
					{
						//System.out.println(operands[0]);
						operands[j]=operands[j].replaceAll("[()]","");
						String parts[]=operands[j].split(",");
						if(parts[0].equals("S"))
						{
							code=code+symtab.get(Integer.parseInt(parts[1]));
						}
						else if(parts[0].equals("L"))
						{
							code=code+littab.get(Integer.parseInt(parts[1]));
						}
						else
						{
							code=code+operands[j]+" ";
						}
						
					}
					System.out.println(code);
				}
				
			}
		}
		
		symFile.close();
		litfile.close();
		ic.close();
	}
}
