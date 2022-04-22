import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SpellingChecker {
	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		readRecords(list);
		do {
			System.out.println("Type a word or sentence to check your spelling:");
			String sentence = scanner.nextLine();
			String [] words = sentence.split("\\s+");
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
						for (String element: list) {
							if (areAnagram(word, element)) {
								System.out.println("Did you mean " + element + " ?");
								String response1 = scanner.nextLine();
								if (response1.matches("[yY][eE][sS]|[yY]")) {
									System.out.println("The correct spelling of " + word + " is " + element);
									break;
								} else if (response1.matches("[nN][oO]|[nN]")) {
									System.out.println(word + " = spelled incorrectly");
								}
							}
						}
					}
			}
		}while(true);
	}
	public static <T> void readRecords(ArrayList<T> dictionary) {
		try {
			Scanner scanner = new Scanner(new File("C:\\Users\\user\\IdeaProjects\\MiniProject\\dictionary.txt"));
			while(scanner.hasNextLine()) {
				String [] tokens = scanner.nextLine().toLowerCase().split("\\s+");
				for(String token :tokens)
					dictionary.add((T) token);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean areAnagram(String str1, String str2){
		HashMap<Character,Integer> map1 = new HashMap<>();
		HashMap<Character,Integer> map2 = new HashMap<>();
		if (str1.length()!=str2.length()){
			return false;
		}
		char[] charArray1 = str1.toLowerCase().toCharArray();
		char[] charArray2 = str2.toLowerCase().toCharArray();
		for (int i=0; i<charArray1.length; i++){
			if (map1.get(charArray1[i]) == null){
				map1.put(charArray1[i],1);
			}else{
				Integer counter = map1.get(charArray1[i]);
				map1.put(charArray1[i], ++counter);
			}
		}
		for (int j=0; j<charArray2.length; j++){
			if (map2.get(charArray2[j]) == null){
				map2.put(charArray2[j],1);
			}else{
				Integer counter = map2.get(charArray2[j]);
				map2.put(charArray2[j], ++counter);
			}
		}
	return map1.equals(map2);
	}
}