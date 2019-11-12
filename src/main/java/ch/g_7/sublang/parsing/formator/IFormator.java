package ch.g_7.sublang.parsing.formator;

import java.util.List;

import ch.g_7.sublang.parsing.parser.IParser;
import ch.g_7.sublang.parsing.parser.Parser;

public interface IFormator {

	public List<Statement> format(String code, IParser parser);
	
}
