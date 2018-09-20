package main;

import java.util.ArrayList;

public class Monster extends All implements comActions{
	private int HP;
	private int damage;
	private String name;
	private ArrayList<Item> itemList;
	public Monster(String descr, String name,int damage,int HP,ArrayList<Item> itemList) {
		super(descr);
		this.itemList=itemList;
		this.damage=damage;
		this.name=name;
		this.HP=HP;
	}
	public void decreaseHP(int damage) {
		HP-=damage;
		if(HP<=0) {
			HP=0;
			System.out.println("Monster "+name+" is dead!");
			dropItem();////so when dead, drop items
		}
	}
	@Override
	public int attack() {
		return damage;
	}
	public int normalAttack() {
		return damage;
	}
	@Override
	public ArrayList<Item> dropItem(){
		return itemList;
	}
	//GET SET
	public int getDamage() {
		return damage;
	}
}
