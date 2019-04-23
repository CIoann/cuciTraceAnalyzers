package DataStructures;

public class Task {

	private int amount;
	private int begin; 
	private String name;
	
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public Task(int amount, String name) {
		super();
		this.amount = amount;
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addAmount() {
		amount++;
		
	}
}
