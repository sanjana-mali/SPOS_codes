import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class pass1 
{
	ArrayList<Table> littab=new ArrayList<>();
	HashMap<String, Table> symtab=new HashMap<>();
	ArrayList<Integer> pooltab=new ArrayList<>();
	public static void main(String args[])throws Exception
	{
		pass1 p=new pass1();
		p.passOne();
	}
	
	void passOne()throws Exception
	{
		Mnemonic lookup=new Mnemonic();
		pooltab.add(0);
		BufferedReader br=new BufferedReader(new FileReader("input.asm"));
		int lc=0;
		String line="",code="";
		int symptr=0,litptr=0,poolptr=0,litIndex=0;
		while((line=br.readLine())!=null)
		{
			String parts[]=line.split("\\s+");
			
			if(!parts[0].equals(""))
			{
				if(symtab.containsKey(parts[0]))
				{
					symtab.put(parts[0], new Table(parts[0],lc,symtab.get(parts[0]).getIndex()));
				}
				else
				{
					symtab.put(parts[0], new Table(parts[0], lc, ++symptr));
				}
			}
			
			if(parts[1].equalsIgnoreCase("START"))
			{
				if(parts.length>2)
				{
					lc=Integer.parseInt(parts[2]);
					code="(AD,01) (C,"+parts[2]+")";
				}
				else
				{
					lc=0;
					code="(AD,01)";
				}
				System.out.println(code);
			}
			
			if(parts[1].equalsIgnoreCase("ORIGIN"))
			{
				lc=expr(parts[2]);
				code="(AD,03) ";
				if(parts[2].contains("+"))
				{
					String arr[]=parts[2].split("\\+");
					code=code+"(S,"+symtab.get(arr[0]).getIndex()+")+"+arr[1];
				}
				else if(parts[2].contains("-"))
				{
					String arr[]=parts[2].split("\\-");
					code=code+"(S,"+symtab.get(arr[0]).getIndex()+")-"+arr[1];
				}
				else
				{
					code=code+"(S,"+symtab.get(parts[2]).getIndex()+")";
				}
				System.out.println(code);
			}
			
			if(parts[1].equalsIgnoreCase("EQU"))
			{
				int ind=expr(parts[2]);
				code="(AD,04) ";
				if(parts[2].contains("+"))
				{
					String arr[]=parts[2].split("\\+");
					code=code+"(S,"+symtab.get(arr[0]).getIndex()+")+"+arr[1];
				}
				else if(parts[2].contains("-"))
				{
					String arr[]=parts[2].split("\\-");
					code=code+"(S,"+symtab.get(arr[0]).getIndex()+")-"+arr[1];
				}
				else
				{
					code=code+"(S,"+symtab.get(parts[2]).getIndex()+")";
				}
				System.out.println(code);
				if(symtab.containsKey(parts[0]))
				{
					symtab.put(parts[0], new Table(parts[0],ind,symtab.get(parts[0]).getIndex()));
				}
				else
				{
					symtab.put(parts[0], new Table(parts[0], ind, ++symptr));
				}
			}
			
			if(parts[1].equalsIgnoreCase("LTORG") || parts[1].equalsIgnoreCase("END"))
			{
				if(parts[1].equalsIgnoreCase("LTORG"))
				{
					code="(AD,05)";
				}
				if(parts[1].equalsIgnoreCase("END"))
				{
					code="(AD,02)";
				}
				System.out.println(code);
				int ptr=pooltab.get(poolptr);
				for(int i=ptr;i<litptr;i++)
				{
					littab.set(i, new Table(littab.get(i).getSymbol(), lc,littab.get(i).getIndex()));
					code="(DL,01) (C,"+littab.get(i).getSymbol()+")";
					lc++;
					System.out.println(code);
				}
				poolptr++;
				pooltab.add(litptr);
			}
			
			if(parts[1].equalsIgnoreCase("DC"))
			{
				lc++;
				parts[2]=parts[2].replaceAll("'", "");
				code="(DL,01) (C,"+parts[2]+")";
				System.out.println(code);
			}
			
			if(parts[1].equalsIgnoreCase("DS"))
			{
				lc=lc+Integer.parseInt(parts[2]);
				code="(DL,02) (C,"+parts[2]+")";
				System.out.println(code);
			}
			
			if(lookup.getType(parts[1]).equals("IS"))
			{
				lc++;
				code="(IS,"+String.format("%02d", lookup.getValue(parts[1]))+")";
				for(int i=2;i<parts.length;i++)
				{
					parts[i]=parts[i].replaceAll(",", "");
					if(lookup.getType(parts[i]).equals("REG"))
					{
						code=code+" ("+lookup.getValue(parts[i])+")";
					}
					else
					{
						if(parts[i].contains("="))
						{
							parts[i]=parts[i].replaceAll("[=']", "");
							litptr++;
							litIndex++;
							littab.add(new Table(parts[i],-1,litIndex));
							//System.out.println(littab);
							code=code+" (L,"+litIndex+")";
						}
						else if(symtab.containsKey(parts[i]))
						{
							code=code+" (S,"+symtab.get(parts[i]).getIndex()+")";
						}
						else
						{
							symtab.put(parts[i], new Table(parts[i],-1,++symptr));
							code=code+" (S,"+symtab.get(parts[i]).getIndex()+")";
						}
					}
				}
				System.out.println(code);
			}
		}
		br.close();
		System.out.println(symtab);
		System.out.println(littab);
	}
	
	int expr(String s)
	{
		int temp=0;
		if(s.contains("+"))
		{
			String parts[]=s.split("\\+");
			if(symtab.containsKey(parts[0]))
			{
				temp=symtab.get(parts[0]).getAddress()+Integer.parseInt(parts[1]);
			}
		}
		else if(s.contains("-"))
		{
			String parts[]=s.split("\\-");
			if(symtab.containsKey(parts[0]))
			{
				temp=symtab.get(parts[0]).getAddress()-Integer.parseInt(parts[1]);	
			}
		}
		else if(symtab.containsKey(s))
		{
			temp=symtab.get(s).getAddress();
		}
		else
		{
			temp=Integer.parseInt(s);
		}
		return temp;		
	}
}
