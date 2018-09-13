package main;

public class Item extends All {
	//DATA MEMBERS
	private int weight;
	private String name;
	
	public Item( String name,String descr,int weight) {
		super(descr);
		this.name=name;
		this.weight=weight;
		
	}
	
	//toString
	@Override
	public String toString() {
		return "Name :"+name+" Weight :" + weight;
	}
	
	///GET SET
	protected int getWeight() {
		return weight;
	}

	protected String getName() {
		return name;
	}

	protected void setWeight(int weight) {
		this.weight = weight;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
}
