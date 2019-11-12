package ch.g_7.sublang.parsing.lexer;

import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.parser.IParser;
import ch.g_7.sublang.parsing.parser.Parser;

public interface ILexer {

	void read(Statement statement, ContainerElement element, IParser parser);
	
}
