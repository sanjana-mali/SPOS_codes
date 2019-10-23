import java.util.HashMap;

public class Mnemonic 
{
	HashMap<String,Integer> AD,IS,REG,DL;
	public Mnemonic()
	{
		AD=new HashMap<>();
		IS=new HashMap<>();
		REG=new HashMap<>();
		DL=new HashMap<>();
		
		AD.put("START",01);
		AD.put("END",02);
		AD.put("ORIGIN",03);
		AD.put("EQU",04);
		AD.put("LTORG",05);

		IS.put("STOP",00);
		IS.put("ADD",01);
		IS.put("SUB",02);
		IS.put("MULT",03);
		IS.put("MOVER",04);
		IS.put("MOVEM",05);
		IS.put("COMP",06);
		IS.put("BC",07);
		IS.put("DIV",8);
		IS.put("READ",9);
		IS.put("PRINT",10);
		
		REG.put("AREG",1);
		REG.put("BREG",2);
		REG.put("BREG",3);
		REG.put("CREG",4);
		
		DL.put("DC",01);
		DL.put("DS",02);
	}
	
	String getType(String s)
	{
		s=s.toUpperCase();
		if(AD.containsKey(s))
		{
			return "AD";
		}
		if(IS.containsKey(s))
		{
			return "IS";
		}
		if(DL.containsKey(s))
		{
			return "DL";
		}
		if(REG.containsKey(s))
		{
			return "REG";
		}
		
		return "";
	}
	
	int getValue(String s)
	{
		s=s.toUpperCase();
		if(AD.containsKey(s))
		{
			return AD.get(s);
		}
		if(IS.containsKey(s))
		{
			return IS.get(s);
		}
		if(DL.containsKey(s))
		{
			return DL.get(s);
		}
		if(REG.containsKey(s))
		{
			return REG.get(s);
		}
		
		return 0;
	}
}
