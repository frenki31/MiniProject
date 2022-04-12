import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class MorseCode {
	private static Scanner scanner;
	private static String sentence;
	private static String input1 = "";
	private static String output1 = "";
	private static String input2 = "";
	private static String output2 = "";
    private static HashMap map; //Dictionary with english letters and numbers with corresponding morse code
    private static BufferedReader bufferedReader;
	private static int response = 0;
	
	public static void main(String[] args) throws IOException {
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	   	scanner = new Scanner(System.in);
		Character[] english = {'a','b','c','d','e','f',
					           'g','h','i','j','k','l',
					           'm','n','o','p','q','r',
					           's','t','u','v','w','x',
			                   'y','z','1','2','3','4',
			                   '5','6','7','8','9','0',' '}; //array of all english letters and numbers
			
		String[] morse = {".-","-...","-.-.","-..",".","..-.",
					      "--.","....","..",".---","-.-",".-..",
					      "--","-.","---",".--.","--.-",".-.",
			              "...","-","..-","...-",".--","-..-",
		     	          "-.--","--..",".----","..---","...--","....-",
				          ".....","-....","--...","---..","----.","-----"," "}; //array of morse code
		map = CreateMap(english, morse); //Dictionary created with arrays above
		System.out.println("|------->Welcome to Morse Code Converter<-------|");
		
		do {
			menu();
			while(!scanner.hasNextInt()) { //If the input is not a number this loop will go until
				//the user inputs a number
				System.err.println("ERROR! Type one of the numbers to choose an option.");
				scanner.next();
			}
			response = scanner.nextInt();
			if(response == 1) {
				engToCode(map);
				displayQuestion();
			}
			else if (response == 2) {
				codeToEng(map);
				displayQuestion();
			}
			else if(response == 3) {
				System.out.println("\nThe Morse Code: ");
				displayArray(morse);
				displayQuestion();
			}
			else if(response == 4) {
				map = CreateMap(english, morse);
				System.out.println("\nEnglish letters and numbers: ");
				displayArray(english);
				displayQuestion();
			}
			else if(response == 5) {
				System.out.println("Thank you for using the Morse Code Converter!");
				break;	
			}else {
				System.err.println("Invalid input! Choose a number between 1-5");
				continue;
			}
		}while(response!=5);
	}
	/**
	 * Method displayQuestion is called every time after the user completes a cycle
	 */
	public static void displayQuestion() {
		System.out.println("\nDo you want to try something else? (Y/N)");
		String answer = scanner.next();
		if (answer.matches("[yY][eE][sS]|[yY]")) { //the user inputs YES/yes/YeS/yES etc.
			System.out.println("|------->Welcome to Morse Code Converter<-------|");
		}else if (answer.matches("[nN]|[nN][oO]")) { //the user input NO/no/No/nO
			System.out.println("Thank you for using the Morse Code Converter!");
			System.exit(0);
		}else {
			System.out.println("Invalid input! Answer the question as requested.");
			scanner.next();
		}
	}
	/**
	 * Generic method displayArray is used to print out arrays of different types
	 * @param array - generic parameter T
	 */
	public static <T> void displayArray(T[] array) {
    	for(int i=0; i<array.length; i++)
    		System.out.printf(((i+1) % 6 != 0) ? "%s " : "%s%n", array[i]); //if the index modulus 6 is 0
    	//then it is asked to go to the next line
    }

	public static void menu() {
    	System.out.println("| Choose one option from the menu below         |");
    	System.out.println("| 1) Convert from English to Morse Code         |");		
	   	System.out.println("| 2) Convert from Morse Code to English         |");
	   	System.out.println("| 3) Generate Morse Code                        |");
	   	System.out.println("| 4) Generate English letters and numbers       |");
	   	System.out.println("| 5) Exit                                       |");
    	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	/**
	 * Generic method CreateMap gets values from two arrays and puts them as keys and values on a hash map
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> HashMap CreateMap(T[] key, T[] value) {
    	HashMap<T, T> map = new HashMap<>();
    	for(int i=0; i< key.length; i++)
	   		map.put(key[i], value[i]);
    	return map;
	}
	/**
	 * Generic method codeToEng compares the values entered from the user and those on the dictionary     
	 * @param map
	 * @throws IOException
	 */
	public static <T> void codeToEng(HashMap<T, T> map) throws IOException{
    	System.out.println("Enter the morse code but after every letter enter a space: ");
	   	sentence = bufferedReader.readLine();
	   	String[] morseLetters = sentence.split(" ");
	   	for (int i=0; i< morseLetters.length; i++) { 
	   		for (T element: map.keySet()) { //iterate through the key set of the map
	   			if (morseLetters[i].equals(map.get(element))) { //if morse letter entered is the same as that in the map
	   				output2 += element + " ";
	   				input2 += morseLetters[i]+ " ";
	   			}
	    	}
	   	}
	    System.out.println(input2+"converted to english is: " + output2);
	    output2 = "";
	    input2 = "";
	}
	/**
	 * Generic method engToCode compares the values entered from the user and keys on the dictionary
	 * @param map
	 * @throws IOException
	 */
	public static <T> void engToCode(HashMap<T, T> map) throws IOException {
    	System.out.println("Enter the word or sentence you want to convert into morse code: ");
    	sentence = bufferedReader.readLine();
	   	sentence = sentence.toLowerCase();
	   	for (int i=0; i< sentence.length(); i++) {
	   		for (T element: map.keySet()) {
	   			if(element.equals(sentence.charAt(i))) { //if the key in dictionary is the same as morse letter entered by the user
	   				output1 += map.get(sentence.charAt(i)) + " ";
	   		   		input1 += sentence.charAt(i)+ " ";
	   			}
	   		}	
	   		
	    }
	   	System.out.println(input1+"converted to morse code is: " + output1);
	   	output1 = "";
	   	input1 = "";
	}
}
