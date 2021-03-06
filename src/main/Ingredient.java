package main;

public class Ingredient extends Item{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int HP;
	private int MP;
	private boolean isConsumable;
	public Ingredient(String name, String descr, int weight,int HP,int MP,boolean isConsumable) {
		super(name, descr, weight);
		this.HP=HP;
		this.MP=MP;
		this.isConsumable=isConsumable;
	}

	public int getHP(){
		return HP;
	}
	public int getMP() {
		return MP;
	}
	public boolean isConsumable() {
		return isConsumable;
	}

	@Override
	public String toString() {
		return super.toString()+" Ingredient [HP=" + HP + ", MP=" + MP + ", isConsumable=" + isConsumable + "]";
	}
	
}
