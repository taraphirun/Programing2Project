package main;

public class Weapon extends Item{
	private int damage;
	private String type;
	private boolean isExplosive;
	public Weapon(String name, String descr,boolean explosive, int weight,int damage,String type) {
		super(name, descr, weight);
		this.type=type;
		this.isExplosive=explosive;
		this.damage=damage;
	}
	///GET SET
	protected void setDamage(int damage) {
		this.damage = damage;
	}
	protected int getDamage() {
		return damage;
	}
	protected String getType() {
		return type;
	}
	protected boolean isExplosive() {
		return isExplosive;
	}
}
