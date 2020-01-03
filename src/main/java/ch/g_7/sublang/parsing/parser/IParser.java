package ch.g_7.sublang.parsing.parser;

import ch.g_7.sublang.parsing.lexer.ContainerElement;

public interface IParser {

	
	void parse(String code, ContainerElement element);
	
	void parse(String code);
	
}
