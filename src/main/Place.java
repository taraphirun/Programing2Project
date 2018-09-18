package main;

import java.util.*;

public class Place extends All{
	private Map<String,Item> itemList= new HashMap<String,Item>();
	private Map<String,Monster> monsterList= new HashMap<String,Monster>();////////This will contain the type of monster that can dwell here==>Might be set to a default list
	private int monProbab;//number between 0-100
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
//METHOD
	protected int numMonster() {
	
		double probab = Math.random()*100;
		double min=100-monProbab;
			
		if(probab>min) {
			if(probab>90){
				monProbab*=0.8;//To decrease monster probability each time a place is visited/fight off
				return 4;
			}else if(probab>70) {
				monProbab*=0.8;
				return 3;
			}else if(probab>50) {
				monProbab*=0.9;
				return 2;
			}else if(probab>min) {
				monProbab*=0;
				return 1;
			}
		}
		return 0;
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
