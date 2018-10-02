package main;

import java.util.ArrayList;

public class bMonster extends Monster implements Skills{
	private static final long serialVersionUID = 1L;
	private int MP;
	private int minHeavy=10;
	private int minMagic=50;
	private int mana=0;
	public bMonster(String name, String desc,String battleCry, int damage,int HP,int MP,ArrayList<Item> itemList) {
		super(name, desc,battleCry, damage, HP, itemList);
		this.MP=MP;
	}
	@Override
	public int attack() {
		System.out.println("BOSS ATTACK");
		double typeAttack = Math.random()*100;
		if(typeAttack>70) {//Account for both heavy and magic attack 
			mana = (int)Math.random()*MP;
			return magicAttack(mana);
		}else {
			return normalAttack();
		}
	}
	public int normalAttack() {
		return super.getDamage();
	}
///Implement
	@Override
	public int heavyAttack() {///////Mana will be randomly generated
		if(MP<mana || mana<minHeavy) {
			return normalAttack();
		}else if(mana>minHeavy && mana<minMagic) {
			MP-=mana;
			return super.getDamage()*(1+mana/100);
		}else {
			return magicAttack(mana);
		}
	}
	@Override
	public int magicAttack(int mana) {///////Mana will be randomly generated
		if(MP<mana || mana<minMagic) {
			return heavyAttack(); ///////////////Ensure that the monster will attack nonetheless =====> Must check for LOOP
		}else{
			MP-=mana;
			return super.getDamage()*(1+mana);
		}
	}

}
