package main;

public class Consumable extends Item{
	private int HP;
	private int MP;
	public Consumable(String name, String descr, int weight,int HP,int MP) {
		super(name, descr, weight);
		this.HP=HP;
		this.MP=MP;
	}

	public int getHP(){
		return HP;
	}
	public int getMP() {
		return MP;
	}
}
