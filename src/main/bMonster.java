package main;

import java.util.ArrayList;

public class bMonster extends Monster implements Skills{
	private int MP;
	private int minHeavy=10;
	private int minMagic=50;
	public bMonster(String descr, String name, int damage,int MP,ArrayList<Item> itemList) {
		super(descr, name, damage, MP, itemList);
		this.MP=MP;
	}
	@Override
	public int attack() {
		double typeAttack = Math.random()*100;
		if(typeAttack>75) {//Account for both heavy and magic attack 
			int mana = (int)Math.random()*MP;
			return magicAttack(mana);
		}else {
			return normalAttack();
		}
	}
///Implement
	@Override
	public int heavyAttack(int mana) {///////Mana will be randomly generated
		if(MP<mana || mana<minHeavy) {
			return normalAttack();
		}else if(mana>minHeavy && mana<minMagic) {
			MP-=mana;
			return super.getDamage()*(1+mana/2);
		}else {
			return magicAttack(mana);
		}
	}
	@Override
	public int magicAttack(int mana) {///////Mana will be randomly generated
		if(MP<mana || mana<minMagic) {
			return heavyAttack(mana); ///////////////Ensure that the monster will attack nonetheless =====> Must check for LOOP
		}else{
			MP-=mana;
			return super.getDamage()*(1+mana);
		}
	}

}
