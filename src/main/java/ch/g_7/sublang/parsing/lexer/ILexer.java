package ch.g_7.sublang.parsing.lexer;

import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.parser.IParser;

public interface ILexer {

	void read(Statement statement, ContainerElement element, IParser parser);
	
}
