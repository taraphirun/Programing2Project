package main;

import java.util.*;

public class Place extends All{
	private String placeName;
	private boolean isMonsterNest;
	private int numWeapon;
	private int numIngredient;
	private ArrayList<Item> itemList;
	private ArrayList<Monster> monsterList;
	private String direction;
	private boolean isStageConnected = false;
//CONSTRUCTORS
	public Place(String name,String descr,String direction,int numWeapon,int numIngredient,boolean isMonsterNest,boolean isConnected) {
		super(descr);
		this.placeName = name;
		this.direction=direction;
		this.numWeapon=numWeapon;
		this.numIngredient=numIngredient;
		isStageConnected =isConnected;
	}
//METHOD
//	protected int numMonster() {
//		double probab = Math.random()*100;
//		double min=100-monProbab;
//			
//		if(probab>min) {
//			if(probab>90){
//				monProbab*=0.8;//To decrease monster probability each time a place is visited/fight off
//				return 4;
//			}else if(probab>70) {
//				monProbab*=0.8;
//				return 3;
//			}else if(probab>50) {
//				monProbab*=0.9;
//				return 2;
//			}else if(probab>min) {
//				monProbab*=0;
//				return 1;
//			}
//		}
//		return 0;
//	}
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

	protected void setMonsterList(ArrayList<Monster> monsterList) {
		this.monsterList = monsterList;
	}

	@Override
	public String toString() {
		return super.toString()+"Place [itemList=" + itemList + ", monsterList=" + monsterList +"Direction"+direction +"]";
	}
	
}
