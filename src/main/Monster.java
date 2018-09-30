package main;

import java.util.ArrayList;

public class Monster extends All implements comActions,Cloneable{
	private static final long serialVersionUID = 1L;
	private int HP;
	private int damage;
	private String name;
	private String battleCry;
	private ArrayList<Item> itemList;
	public Monster(String name,String descr,String battleCry,int damage,int HP,ArrayList<Item> itemList) {
		super(descr);
		this.battleCry=battleCry;
		this.itemList=itemList;
		this.damage=damage;
		this.name=name;
		this.HP=HP;
	}
	public void decreaseHP(int damage) {
		System.out.println("Monster is damaged by "+damage);
		HP-=damage;
		if(HP<=0) {
			HP=0;
			System.out.println("Monster "+name+" is dead!");
			dropItem();////so when dead, drop items
			Assets.player.getLocation().numFightMove=0;
		}
	}
	@Override
	public int attack() {
		double minDamage = .75; //Express in percentage of the whole damage
		int dDamage = (int)(Math.random()*damage*(1-minDamage)+(damage*minDamage));
		return damage;
	}
	@Override
	public void dropItem(){
		
	}
	//GET SET
	public int getDamage() {
		return damage;
	}
	public String getName() {
		return name;
	}
	public int getHP() {
		return HP;
	}
	public String getBattleCry() {
		return battleCry;
	}
}
