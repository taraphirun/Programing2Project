package main;

import java.util.*;

public class Place extends All{
	private Map<String,Item> itemList= new HashMap<String,Item>();
	private Map<String,Monster> monsterList= new HashMap<String,Monster>();////////This will contain the type of monster that can dwell here==>Might be set to a default list
	private int monProbab;
	private String direction;
	private boolean isStageConnected = false;
//CONSTRUCTORS
	
	
	public Place(String descr,String direction,int monProbab) {
		super(descr);
		this.direction=direction;
		this.monProbab=monProbab;
		
	}
	public Place(String descr,String direction,int monProbab,boolean isConnected) {
		super(descr);
		this.direction=direction;
		this.monProbab=monProbab;
		isStageConnected =isConnected;
	}
	/////Constructor for testing purpose
	public Place(String descr,boolean isConnected) {
		super(descr);
		this.isStageConnected=isConnected;
	}
	public Place(String descr) {
		super(descr);
	}
//GET 
	protected Map<String, Item> getItemList() {
		return itemList;
	}

	protected Map<String, Monster> getMonsterList() {
		return monsterList;
	}
	protected boolean isStageConnect() {
		return isStageConnected;
	}
//SET
	protected void setItemList(Map<String, Item> itemList) {
		this.itemList = itemList;
	}

	protected void setMonsterList(Map<String, Monster> monsterList) {
		this.monsterList = monsterList;
	}

	@Override
	public String toString() {
		return super.toString()+"Place [itemList=" + itemList + ", monsterList=" + monsterList + "]";
	}
	
}
