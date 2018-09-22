package main;

import java.io.Serializable;

public class TestObject implements Serializable{
	private String name;
	private int extra;
	private int extra1 =300;
	private String desc;
	public TestObject(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
		extra=100;
	}
	@Override
	public String toString() {
		return "TestObject [name=" + name + ", extra=" + extra + ", extra1=" + extra1 + ", desc=" + desc + "]";
	}
	
	
}
