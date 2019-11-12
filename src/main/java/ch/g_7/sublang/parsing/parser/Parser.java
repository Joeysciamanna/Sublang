package ch.g_7.sublang.parsing.parser;

import ch.g_7.sublang.parsing.formator.IFormator;
import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.lexer.ContainerElement;
import ch.g_7.sublang.parsing.lexer.ILexer;

public class Parser implements IParser {

	private final IFormator defaultFormator;
	private final ILexer defaultLexer;
	private ContainerElement rootElement;
	
	public Parser(IFormator formator, ILexer lexer) {
		this.defaultFormator = formator;
		this.defaultLexer = lexer;
		this.rootElement = new ContainerElement("root", "code", -1);
	}
	
	@Override
	public void parse(String code) {
		parse(code, rootElement);
	}

	@Override
	public void parse(String code, ContainerElement element) {
		for (Statement statement : defaultFormator.format(code, this)) {
			System.out.println("--------------------------------------------");
			System.out.println(statement.getCode());
			System.out.println("--------------------------------------------");
			statement.shiftLines(element.getLine());
			defaultLexer.read(statement, element, this);
		}
	}
	
	public IFormator getDefaultFormator() {
		return defaultFormator;
	}
	
	public ILexer getDefaultLexer() {
		return defaultLexer;
	}

	public ContainerElement getRootElement() {
		return rootElement;
	}
}
