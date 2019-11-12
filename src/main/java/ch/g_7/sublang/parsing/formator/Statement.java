package ch.g_7.sublang.parsing.formator;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Statement {

	
	private final SortedMap<Integer, String> lines;
	
	public Statement() {
		this.lines = new TreeMap<>( (l1, l2) -> l1 - l2 );
	}


	public String getCode() {
		return lines.values().stream().collect(Collectors.joining());
	}
	
	public int getLine(String key) {
		int matchingChars = 0;
		int foundLine = -1;
		int checkIndex = 0;
		
		for (Entry<Integer, String> line : lines.entrySet()) {
			
			for (int ci = 0; ci<line.getValue().length(); ci++) {
				char c = line.getValue().charAt(ci);
				
				if(c == key.charAt(checkIndex)) {
					checkIndex++;
					if(checkIndex > matchingChars) {
						matchingChars = checkIndex;
						foundLine = line.getKey();
					}
					if(checkIndex == key.length()) {
						return foundLine;
					}
				}else {
					checkIndex = 0;
				}
				
			}
		}
		
		return foundLine;
		
	}
	
	public void addLine(String line, int lineNumber) {
		lines.put(lineNumber, line);
	}
	

	public Map<Integer, String> getLines() {
		return lines;
	}
	
	public void shiftLines(int amount) {
		SortedMap<Integer, String> newLines = new TreeMap<>();
		for (Entry<Integer, String> entry : lines.entrySet()) {
			newLines.put(entry.getKey() + amount, entry.getValue());
		}
		lines.clear();
		for (Entry<Integer, String> entry : newLines.entrySet()) {
			lines.put(entry.getKey(), entry.getValue());
		}
	}
	
}