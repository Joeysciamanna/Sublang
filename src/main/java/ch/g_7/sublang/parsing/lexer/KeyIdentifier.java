package ch.g_7.sublang.parsing.lexer;

import java.util.regex.Pattern;

public class KeyIdentifier {
	
	
	private Filter filter;
	private Provider provider;

	public KeyIdentifier(Filter filter, Provider provider) {
		this.filter = filter;
		this.provider = provider;
	}

	public KeyIdentifier(String regex, Provider provider) {
		this((s)->Pattern.matches(regex, s), provider);
	}
	
	public boolean matches(String statement) {
		return filter.matches(statement);
	}


	public Element getElement(String statement, int line) {
		return provider.getElement(statement, line);
	}


	
	@FunctionalInterface
	public static interface Provider {
		Element getElement(String statement, int line);
	}

	
	@FunctionalInterface
	public static interface Filter {
		boolean matches(String statement);
	}
	
}