package ch.g_7.sublang.parsing.lexer;

import java.util.ArrayList;
import java.util.List;

import ch.g_7.sublang.parsing.parser.IParser;
import ch.g_7.sublang.parsing.parser.Parser;

public class ContainerElement extends Element {

	private List<Element> elements;
	
	public ContainerElement(String name, String value, int line) {
		super(name, value, line);
		this.elements = new ArrayList<>();
	}
	
	@Override
	public void read(IParser parser) {
		if(value.length()>2){
			parser.parse(value.substring(1, value.length()-1), this);
		}
	}
	
	public void addElement(Element element) {
		elements.add(element);
	}
	
	@Override
	public String toString() {
		StringBuilder childStrings = new StringBuilder();
		for (Element element : elements) {
			for(String line : element.toString().split("\n")){
				childStrings.append("\t" + line + "\n");
			}
		}	
		return name + ": {\n" + childStrings.toString() + "}";
	}
	
	public List<Element> getElements() {
		return elements;
	}
	

}
