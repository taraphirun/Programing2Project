package main;

import java.io.Serializable;

public abstract class All implements Serializable {
	private String Descr;

	public All(String descr) {
		Descr = descr;
	}

	protected String getDescr() {
		return Descr;
	}

	@Override
	public String toString() {
		return "All [Descr=" + Descr + "]";
	}
	
}
