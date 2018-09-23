package main;

import java.io.Serializable;
import java.util.*;

public class Place extends All implements Serializable{

	private static final long serialVersionUID = 1L;
	private String placeName;
	private boolean isMonsterNest;
	private boolean isWeapon;
	private boolean isIngredient;
	private ArrayList<Item> itemList=new ArrayList<Item>();
	private ArrayList<Monster> monsterList = new ArrayList<Monster>();
	private String direction;
	private boolean isStageConnected = false;
//CONSTRUCTORS
	public Place(String name,String descr,String direction,boolean isWeapon,boolean isIngredient,boolean isMonsterNest,boolean isConnected) {
		super(descr);
		this.placeName = name;
		this.direction=direction;
		this.isWeapon=isWeapon;
		this.isIngredient=isIngredient;
		isStageConnected =isConnected;
	}
	//When hero pick item from the place
	public Item takeFrom(Item x) {
		if(itemList.contains(x)) {
			int index = itemList.indexOf(x);
			itemList.remove(index);
			return itemList.get(index);
		}
		return null;
	}
//GET 
	protected ArrayList<Item> getItemList() {
		return itemList;
	}

	protected ArrayList<Monster> getMonsterList() {
		return monsterList;
	}
	protected boolean isConnected() {
		return isStageConnected;
	}
//SET
	protected void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	protected void addItemList(ArrayList<Item> nItemList) {
		for(int i=0;i<nItemList.size();i++) {
			itemList.add(nItemList.get(i));
		}
	}
	protected void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
	}
//GET 
	protected int getSize() {
		return itemList.size();
	}
	protected void checkInventory() {
		for(Item x:itemList) {
			System.out.println(x);
		}
	}
	@Override
	public String toString() {
		return super.toString()+"Place [itemList=" + itemList + ", monsterList=" + monsterList +"Direction"+direction +"]";
	}
	
}
