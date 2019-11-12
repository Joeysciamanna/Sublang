package ch.g_7.sublang.lang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.g_7.sublang.parsing.formator.IFormator;
import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.parser.IParser;
import ch.g_7.sublang.util.ParsingUtil;

public class SublangFormator implements IFormator {

	public List<Statement> format(String code, IParser parser) {

		List<Statement> statements = new ArrayList<>();
		Statement last = new Statement();
		StringBuilder statementLine = new StringBuilder();
		
		int i = 1;
		char escChar = '\0';
		
		LinkedList<Character> blockChars = new LinkedList<>();
		int blockLevel = 0;

		for (String line : code.split(System.getProperty("line.separator"))) {
			
			if (ParsingUtil.containsUnescaped(line, "//")) {
				line = line.substring(0, ParsingUtil.indexOfUnescaped(line, "//"));
			}
			
			for(int ci = 0; ci<line.length(); ci++){
				char c = line.charAt(ci);
				
				if(escChar != '\0' || !Character.isWhitespace(c) || c == ' ') {
					statementLine.append(c);
					
					if ((c == '"' || c == '\'') && escChar == '\0'){
						escChar = c;
					}else if (c == escChar ){
						escChar = '\0';
					}else if(c == ';' &&  escChar == '\0' && blockLevel == 0){
						
						last.addLine(statementLine.toString().strip(), i);
						statementLine.setLength(0);
						statements.add(last);
						last = new Statement();
						
					}else if((c == '{' || c == '(' || c == '[') && escChar == '\0'){
						blockLevel++;
						blockChars.offerFirst(c=='{' ? '}' : (c=='[' ? ']' : ')'));
					}else if((c == '}' || c == ')' || c == ']') && escChar == '\0' && !blockChars.isEmpty() && blockChars.peek().equals(c)){
						blockLevel--;
						blockChars.poll();
					}else if((c == '}' || c == ')' || c == ']') && escChar == '\0' && (blockChars.isEmpty() || !blockChars.peek().equals(c))) {
						throw new RuntimeException(c + " was never opened on line " + i);
					}
				}
				
			}
			if(!statementLine.toString().isBlank()) {
				last.addLine(statementLine.toString().strip(), i);
			}
			

			
			statementLine.setLength(0);
			
			
			i++;
		}
		
		if(statements.isEmpty() && !last.getCode().isBlank()) {
			statements.add(last);
		}
		
		if(blockLevel != 0) {
			throw new RuntimeException("To many " + ( blockLevel > 0 ? "opened" : "closed" ) + " brackets");
		}
		if(escChar !='\0') {
			throw new RuntimeException("Invalid number of \" or '");
		}
		return statements;
	}

	public String formatStatement(String statement) {
		return statement.replace("\t", "").replace("\n", "").replace("\r", "").strip();
	}

}