package ch.g_7.sublang.lang;

import ch.g_7.sublang.parsing.interpreter.IInterpreter;
import ch.g_7.sublang.parsing.lexer.ContainerElement;
import ch.g_7.sublang.parsing.lexer.Element;

public class SublangInterpreter implements IInterpreter{

	@Override
	public void interpred(ContainerElement element) {
		for(Element e : element.getElements()) {
			if(e.getValue().equals("TODO")) {
				interpred(e);
			}else {
				interpred(e);
			}
		}
	}

	@Override
	public void interpred(Element element) {

	}

}
