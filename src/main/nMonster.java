package main;

import java.util.ArrayList;

public class nMonster extends Monster implements Monster_AI{

	public nMonster(String descr, String name, int damage,int HP, ArrayList<Item> itemList) {
		super(descr, name, damage,HP, itemList);
	}

	@Override
	public int attack() {
		return super.getDamage();
	}

	@Override
	public void fightOrFlight() {
		
	}

}
