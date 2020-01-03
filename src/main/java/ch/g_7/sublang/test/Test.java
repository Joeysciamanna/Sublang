package ch.g_7.sublang.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import ch.g_7.sublang.lang.SublangInterpreter;
import ch.g_7.sublang.lang.SublangParser;
import ch.g_7.sublang.parsing.formator.Statement;
import ch.g_7.sublang.parsing.lexer.Lexer;

public class Test {

	public static void main(String[] args) throws IOException {
		new Test().run();
	}
	
	
	public void run(){
		InputStream inputStream = getClass().getResourceAsStream("/script.sublang");
		String text = null;
		Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
		scanner.useDelimiter("\\A");
		text = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
		
		
		
		SublangParser parser = new SublangParser();
		parser.parse(text);
		
		System.out.println(parser.getRootElement());
		
		SublangInterpreter interpreter = new SublangInterpreter();
		interpreter.interpred(parser.getRootElement());
		
//		Formator formator = new Formator();
//		List<Statement> statements = formator.format(text);
//		statements.stream().map((s)->s.getLine()).collect(Collectors.joining("\n"));
//		System.out.println();
//
//		System.out.println(text);
//		System.out.println("-------------------------------------------------");
//		Lexer lexer = new Lexer(new KeyIdentifierSet());
//		lexer.parse(text);
//		System.out.println(lexer.getRootElement());
	}
}