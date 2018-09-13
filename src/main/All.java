package main;

public abstract class All {
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
