package ch.g_7.sublang.parsing.lexer;

import java.util.List;

import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.parser.IParser;

public class Lexer implements ILexer {
	
	private final List<KeyIdentifier> keyIdentifiers;

	public Lexer(List<KeyIdentifier> keyIdentifiers) {
		this.keyIdentifiers = keyIdentifiers;
	}

	public void read(Statement statement, ContainerElement element, IParser parser) {

		String code = statement.getCode() + " ";
		Element fitting = null;
		boolean found = false;
		int startIndex = 0;
		String key = "";
		for (int i = 1; i <= code.length(); i++) {
			key = code.substring(startIndex, i);

			if (key.startsWith(" ")) {
				i--;
				startIndex++;
				continue;
			}

			found = false;
			for (KeyIdentifier keyIdentifier : keyIdentifiers) {
				if (keyIdentifier.matches(key)) {
					fitting = keyIdentifier.getElement(key, statement.getLine(key));
					found = true;
					break;
				}
			}
			if (!found && fitting != null) {
				fitting.read(parser);
				element.addElement(fitting);
				fitting = null;
				i--;
				startIndex = i;
			}
		}
		if (!found && !key.trim().isEmpty()) {
			throw new RuntimeException("No key matching '" + key + "' found on line " + statement.getLine(key));
		}

		
	}


}