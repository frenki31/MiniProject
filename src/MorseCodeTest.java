import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MorseCodeTest {

	private static Scanner scanner;

	public static <T> void main(String[] args) throws IOException {
		int response;
		MorseCode morseCode = new MorseCode();
		scanner = new Scanner(System.in);
	   	HashMap<T, T> map = new HashMap<>();
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
		map = morseCode.CreateMap(english, morse); //Dictionary created with arrays above
		System.out.println("|------->Welcome to Morse Code Converter<-------|");
		
		do {
			morseCode.menu();
			while(!scanner.hasNextInt()) { //If the input is not a number this loop will go until
				//the user inputs a number
				System.err.println("ERROR! Type one of the numbers to choose an option.");
				scanner.next();
			}
			response = scanner.nextInt();
			if(response == 1) {
				morseCode.engToCode(map);
				morseCode.displayQuestion();
			}
			else if (response == 2) {
				morseCode.codeToEng(map);
				morseCode.displayQuestion();
			}
			else if(response == 3) {
				System.out.println("\nThe Morse Code: ");
				morseCode.displayArray(morse);
				morseCode.displayQuestion();
			}
			else if(response == 4) {
				System.out.println("\nEnglish letters and numbers: ");
				morseCode.displayArray(english);
				morseCode.displayQuestion();
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

}
