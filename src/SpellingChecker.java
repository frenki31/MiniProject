import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpellingChecker {
	static ArrayList<String> list = new ArrayList<>();
	//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
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		readRecords(list);
		do {
			System.out.println("Type a word or sentence to check your spelling:");
			String sentence = scanner.nextLine();
			String [] words = sentence.split("\\s+");
			for (String wordDict: list)
				for(String word: words) {
				if(Character.isUpperCase(word.charAt(0))) {
					System.out.println("Is "+word+" a specific term? (yes/no)");
					String response = scanner.nextLine();
					if (response.matches("[yY][eE][sS]|[yY]")) {
						list.add(word.toLowerCase());
					}
				}
				if (list.contains(word.toLowerCase())) {
					System.out.println(word + " = spelled correctly");
				}
				else {
					if (anagram(word, wordDict)) {
						System.out.println("Did you mean "+wordDict+"? (yes/no)");
						String response = scanner.nextLine();
						if (response.matches("[yY][eE][sS]|[yY]")) {
							System.out.println("The correct spelling of "+word+" is "+wordDict);
						}else {
							System.out.println(word + " = spelled incorrectly");
					}
				}
				}
			}
		}while(true);
	}
	public static <T> void readRecords(ArrayList<T> dictionary) {
		try {
			Scanner scanner = new Scanner(new File("C:\\Users\\user\\Desktop\\dictionary.txt"));
			while(scanner.hasNextLine()) {
				String [] tokens = scanner.nextLine().toLowerCase().split("\\s+");
				for(String token :tokens)
					dictionary.add((T) token);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean anagram(String string1,String string2) {
		int length1 = string1.length();
		int length2 = string2.length();
		if (length1 != length2)
			return false;
		Arrays.sort(string1.toCharArray());
		Arrays.sort(string2.toCharArray());
		for(int i=0; i<length1; i++)
			if (string1.charAt(i) != string2.charAt(i)) 
				return false;
		return true;
	}
}
