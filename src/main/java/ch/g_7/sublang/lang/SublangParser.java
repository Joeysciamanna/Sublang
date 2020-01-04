package ch.g_7.sublang.lang;

import static ch.g_7.sublang.parsing.lexer.KeyIdentifierFactory.create;
import static ch.g_7.sublang.parsing.lexer.KeyIdentifierFactory.createBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ch.g_7.sublang.parsing.lexer.KeyIdentifier;
import ch.g_7.sublang.parsing.lexer.Lexer;
import ch.g_7.sublang.parsing.parser.Parser;
import ch.g_7.sublang.util.ParsingUtil;

public class SublangParser extends Parser {

	private final static List<KeyIdentifier> KEY_IDENTIFIERS = new ArrayList<KeyIdentifier>();
	
	{
		KEY_IDENTIFIERS.add(create("^\\+", "operator"));
		KEY_IDENTIFIERS.add(create("^-", "operator"));
		KEY_IDENTIFIERS.add(create("^\\*", "operator"));
		KEY_IDENTIFIERS.add(create("^\\/", "operator"));
		KEY_IDENTIFIERS.add(create("^=", "operator"));
		KEY_IDENTIFIERS.add(create("^==", "operator"));
		KEY_IDENTIFIERS.add(create("^\\.", "operator"));
		KEY_IDENTIFIERS.add(create("^,", "operator"));
		KEY_IDENTIFIERS.add(create("^:", "operator"));
		KEY_IDENTIFIERS.add(create("^\\^", "operator"));
		KEY_IDENTIFIERS.add(create("^;", "operator"));
		
		KEY_IDENTIFIERS.add(create("^function", "key"));
		KEY_IDENTIFIERS.add(create("^\\$", "key", "lambda"));
		KEY_IDENTIFIERS.add(create("^class", "key"));
		KEY_IDENTIFIERS.add(create("^#", "key", "structure"));
		KEY_IDENTIFIERS.add(create("^interface", "key"));
		KEY_IDENTIFIERS.add(create("^abstract_class", "key"));
		KEY_IDENTIFIERS.add(create("^if", "key"));
		KEY_IDENTIFIERS.add(create("^else", "key"));
		KEY_IDENTIFIERS.add(create("^elif", "key"));
		KEY_IDENTIFIERS.add(create("^while", "key"));
		KEY_IDENTIFIERS.add(create("^return", "key"));
		KEY_IDENTIFIERS.add(create("^import", "key"));
		KEY_IDENTIFIERS.add(create("^as", "key"));
		KEY_IDENTIFIERS.add(create("^@", "key", "annotation"));
		
		KEY_IDENTIFIERS.add(create("^[0-9]*\\.[0-9]*", "float"));
		KEY_IDENTIFIERS.add(create("^[0-9]", "integer"));
		KEY_IDENTIFIERS.add(create("^\".*\"", "text"));
		KEY_IDENTIFIERS.add(create("^'.*'", "text"));

		KEY_IDENTIFIERS.add(createBlock((s)-> Pattern.matches("^\\{.*\\}", s) && ParsingUtil.isContainerFinished(s), "body"));
		KEY_IDENTIFIERS.add(createBlock((s)-> Pattern.matches("^<.*>", s) && ParsingUtil.isContainerFinished(s), "generic"));
		KEY_IDENTIFIERS.add(createBlock((s)-> Pattern.matches("^\\(.*\\)", s) && ParsingUtil.isContainerFinished(s), "constructor"));
		KEY_IDENTIFIERS.add(createBlock((s)-> Pattern.matches("^\\[.*\\]", s) && ParsingUtil.isContainerFinished(s), "selector"));

		KEY_IDENTIFIERS.add(create("^[a-zA-Z_]{1}[a-zA-Z0-9_]*", "id"));
	}
	
	public SublangParser() {
		super(new SublangFormator(), new Lexer(KEY_IDENTIFIERS));
	}

}




















