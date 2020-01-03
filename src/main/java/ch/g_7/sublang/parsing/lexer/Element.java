package ch.g_7.sublang.parsing.lexer;

import ch.g_7.sublang.parsing.parser.IParser;

public class Element {

	protected final String name;
	protected final String value;
	protected final int line;
	
	public Element(String name, String value, int line) {
		this.name = name;
		this.value = value;
		this.line = line;
	}

	public void read(IParser parser){}
	
	@Override
	public String toString() {
		return name + ": " + value;
	}
	
	public int getLine() {
		return line;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	

	
}