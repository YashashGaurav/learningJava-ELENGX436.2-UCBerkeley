package ModuleOneToSix;

import java.util.Scanner;

public class StringReverser {

	public static void main(String[] args)  {

		Scanner scanner = new Scanner(System.in);
		
		try {
			String inputString = scanner.nextLine();
			
			var outputString = reverse(inputString);
			
			System.out.println("Reversed string: " + outputString);
			
		} finally {
			scanner.close();
		}
		
	}
	
	private static String reverse(String input) {
		if(input.length() <= 1) {
			return input;
		}
		
		return( 
			input.charAt(input.length() - 1) + 
			reverse(input.substring(0, input.length() - 1))
		);
	}

}
