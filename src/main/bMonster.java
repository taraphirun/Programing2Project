package main;

import java.util.ArrayList;

public class bMonster extends Monster implements Skills{
	private int MP;
	private int minHeavy=10;
	private int minMagic=50;
	public bMonster(String descr, String name, int damage, ArrayList<Item> itemList,int MP) {
		super(descr, name, damage, itemList);
		this.MP=MP;
	}

	@Override
	public int attack() {
		return (int)(super.getDamage()*1.1);
	}
///Implement
	
	
	
	@Override
	public int heavyAttack(int mana) {///////Mana will be randomly generated
		if(MP<mana || mana<minHeavy) {
			return attack();
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
