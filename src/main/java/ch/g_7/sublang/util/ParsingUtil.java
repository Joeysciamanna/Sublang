package ch.g_7.sublang.util;

import java.util.ArrayList;
import java.util.List;

public final class ParsingUtil {

	
	public static List<String> splitUnescapet(String s, char splitter){
		List<String> strings = new ArrayList<>();
		StringBuilder partBuilder = new StringBuilder();
		char escChar = '\0';
		for(int i = 0; i<s.length(); i++){
			char c = s.charAt(i);
			
			partBuilder.append(c);
			if ((c == '"' || c == '\'') && escChar == '\0'){
				escChar = c;
			}else if (c == escChar){
				escChar = '\0';
			}else if(c == splitter && escChar == '\0'){
				strings.add(partBuilder.toString());
				partBuilder.setLength(0);
			}
			
		}
		strings.add(partBuilder.toString());
		return strings;
	}
	
	
	
	public static List<String> splitUnescapetSameLevel(String s, char splitter){
		List<String> strings = new ArrayList<>();
		StringBuilder partBuilder = new StringBuilder();
		char escChar = '\0';
		int blockLevel = 0;
		for(int i = 0; i<s.length(); i++){
			char c = s.charAt(i);
			
			partBuilder.append(c);
			if ((c == '"' || c == '\'') && escChar == '\0' && blockLevel == 0){
				escChar = c;
			}else if (c == escChar ){
				escChar = '\0';
			}else if(c == splitter && escChar == '\0' && blockLevel == 0){
				strings.add(partBuilder.toString());
				partBuilder.setLength(0);
			}else if((c == '{' || c == '(' || c == '[') && escChar == '\0'){
				blockLevel++;
			}else if((c == '}' || c == ')' || c == ']') && escChar == '\0'){
				blockLevel--;
			}
			
		}
		strings.add(partBuilder.toString());
		return strings;
	}
	
	
	public static boolean isContainerFinished(String s){
		return getContainerLevel(s) == 0;
	}
	
	public static int getContainerLevel(String s){
		char escChar = '\0';
		int blockLevel = 0;
		for(int i = 0; i<s.length(); i++){
			char c = s.charAt(i);
			
			if ((c == '"' || c == '\'') && escChar == '\0' && blockLevel == 0){
				escChar = c;
			}else if (c == escChar ){
				escChar = '\0';
			}else if((c == '{' || c == '(' || c == '[') && escChar == '\0'){
				blockLevel++;
			}else if((c == '}' || c == ')' || c == ']') && escChar == '\0'){
				blockLevel--;
			}
			
		}
		return blockLevel;
	}
	
	public static int indexOfUnescaped(String s, String find) {
		char escChar = '\0';
		int checkIndex = 0;
		int foundIndex = -1;
		for(int i = 0; i<s.length(); i++){
			char c = s.charAt(i);
			
		
			
			if (c == find.charAt(checkIndex) && escChar == '\0'){
				if(checkIndex == 0) {
					foundIndex = i;
				}
				checkIndex++;
				if(checkIndex == find.length()) {
					break;
				}
			}else if ((c == '"' || c == '\'') && escChar == '\0'){
				escChar = c;
				checkIndex = 0;
				foundIndex = -1;
			}else if (c == escChar ){
				escChar = '\0';
				checkIndex = 0;
				foundIndex = -1;
			}else  {
				checkIndex = 0;
				foundIndex = -1;
			}
			
		}
		return foundIndex;
	}
	
	
	
	public static boolean containsUnescaped(String s, String find) {
		return indexOfUnescaped(s, find) != -1;
	}
	
	public static int indexOfUnescapedSameContainer(String previousCode, String codeToSearch, String find) {
		for (int i = 1; i<=codeToSearch.length(); i++) {
			String part = codeToSearch.substring(0, i);
			String fullCode = previousCode+part;
			if(isContainerFinished(fullCode) && containsUnescaped(part, find)) {
				return part.lastIndexOf(find);
			}
		}
		return -1;
	}
	
	
	
	
}


