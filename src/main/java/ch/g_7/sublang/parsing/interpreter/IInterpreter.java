package ch.g_7.sublang.parsing.interpreter;

import ch.g_7.sublang.parsing.lexer.ContainerElement;
import ch.g_7.sublang.parsing.lexer.Element;

public interface IInterpreter {

	public void interpred(ContainerElement element);
	
	public void interpred(Element element);
}
