package ch.g_7.sublang.parsing.lexer;

import ch.g_7.sublang.parsing.lexer.KeyIdentifier.Filter;

public class KeyIdentifierFactory {

	public static KeyIdentifier create(Filter filter, String key){
		return new KeyIdentifier(filter, (s, l) -> new ContainerElement(key, s, l));
	}
	
	public static KeyIdentifier create(String pattern, String key, String value){
		return new KeyIdentifier(pattern, (s, l) -> new Element(key, value, l));
	}
	
	public static KeyIdentifier create(String pattern, String key){
		return new KeyIdentifier(pattern, (s, l) -> new Element(key, s, l));
	}
	
	public static KeyIdentifier createBlock(String pattern, String key){
		return new KeyIdentifier(pattern, (s, l) -> new ContainerElement(key, s, l));
	}

	public static KeyIdentifier createBlock(Filter filter, String key){
		return new KeyIdentifier(filter, (s, l) -> new ContainerElement(key, s, l));
	}
	

}
