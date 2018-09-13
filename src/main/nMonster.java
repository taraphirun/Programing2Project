package main;

import java.util.ArrayList;

public class nMonster extends Monster{

	public nMonster(String descr, String name, int damage, ArrayList<Item> itemList) {
		super(descr, name, damage, itemList);
	}

	@Override
	public int attack() {
		return super.getDamage();
	}

}
