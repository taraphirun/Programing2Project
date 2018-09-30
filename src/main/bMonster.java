package main;

import java.util.ArrayList;

public class bMonster extends Monster implements Skills{
	private static final long serialVersionUID = 1L;
	private int MP;
	private int minHeavy=10;
	private int minMagic=50;
	public bMonster(String descr, String name,String battleCry, int damage,int MP,ArrayList<Item> itemList) {
		super(descr, name,battleCry, damage, MP, itemList);
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
	public int normalAttack() {
		return super.getDamage();
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
	@Override
	public int heavyAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

}
