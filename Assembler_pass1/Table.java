
public class Table 
{
	String symbol;
	int address,index;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Table(String symbol, int address, int index) {
		super();
		this.symbol = symbol;
		this.address = address;
		this.index = index;
	}
	public Table(String symbol, int address) {
		super();
		this.symbol = symbol;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Table [" + symbol + ", address=" + address + ", index=" + index + "]";
	}
	
	
}
