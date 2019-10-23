import java.util.*;

public class cal
{
	public native int add(int n1,int n2);
	public native int sub(int n1,int n2);
	public static void main(String args[])
	{
		cal t=new cal();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter 1st Number=");
		int n1=s.nextInt();
		System.out.println("Enter 2nd Number=");
		int n2=s.nextInt();

		System.out.println("1.Add \n2.Sub");
		int ch=s.nextInt();
		switch(ch)
		{
			case 1:
				System.out.println("Addition="+t.add(n1,n2));
				break;

			case 2:
				System.out.println("Subtraction="+t.add(n1,n2));
				break;
		}

	}
	static
	{
		System.loadLibrary( "cal" );
	}
}	
