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
	private Monster fightingMonster;
	private String direction;
	private boolean isVisited=false;
	private boolean isStageConnected = false;
	protected int numFightMove=0;
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
//GET 
	protected ArrayList<Item> getItemList() {
		return itemList;
	}
	protected Item takeFrom(String str) {
		if(!itemList.isEmpty()) {
			for(int i=0;i<itemList.size();i++) {
				String firstName =  itemList.get(i).getName().split(" ")[0];
				if(firstName.equalsIgnoreCase(str)) {
					return itemList.remove(i);
				}
			}
		}
		System.out.println("There are no "+str+" here.");
		return null;
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
	protected void addItem(Item x) {
		itemList.add(x);
	}
	protected void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
	}
	protected void setFightingMonster(Monster x) {
		this.fightingMonster=x;
	}
//GET 
	protected Monster getFightingMonster() {
		return fightingMonster;
	}
	protected int getSize() {
		return itemList.size();
	}
	protected String getDirection() {
		return direction;
	}
	protected boolean isVisited() {
		return isVisited;
	}
	protected void visited() {
		isVisited =true;
	}
	protected String getName() {
		return placeName;
	}
	protected void resetFMove() {
		this.numFightMove=0;
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
