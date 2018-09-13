package main;

public class Armor extends Item{
	private int armorHP;
	public Armor(String name, String descr, int weight,int armorHP) {
		super(name, descr, weight);
		this.armorHP=armorHP;
	}
	public int getArmorHP() {
		return armorHP;
	}
	public void damageArmor(int damage) {
		armorHP-=damage;
		if(armorHP<0) {
			armorHP=0;
		}
	}
	
	@Override////////////////////////////////////Storify
	public String toString() {
		return "Armor [armorHP=" + armorHP + "]";
	}
	
}
