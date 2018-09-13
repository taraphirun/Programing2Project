package main;

public class Weapon extends Item{
	private int damage;
	public Weapon(String name, String descr, int weight,int damage) {
		super(name, descr, weight);
		this.damage=damage;
	}
	
	
	
	///GET SET
	protected int getDamage() {
		return damage;
	}


}
