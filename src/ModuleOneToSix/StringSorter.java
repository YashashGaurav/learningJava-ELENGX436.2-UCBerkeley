package ModuleOneToSix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringSorter {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int numberOfInputStrings = Integer.parseInt(br.readLine());
		
		String[] inputStringArray = new String[numberOfInputStrings];
		
		for(int stringCount = 0; stringCount < numberOfInputStrings; stringCount++) {
			inputStringArray[stringCount] = br.readLine();
		}
		
		bubbleSort(inputStringArray);
		
		System.out.printf("%nResults:%n");
		for(int stringCount = 0; stringCount < numberOfInputStrings; stringCount++) {
			System.out.println(inputStringArray[stringCount]);			
		}
		
	}
	
	private static void bubbleSort(String[] stringArray) 
    { 
        int n = stringArray.length; 
        
        for (int i = 0; i < n-1; i++) {
        	for (int j = 0; j < n-i-1; j++) {
        		if (stringArray[j].compareTo(stringArray[j+1]) > 0) { 
        			// swap 
        			String temp = stringArray[j]; 
        			stringArray[j] = stringArray[j+1]; 
        			stringArray[j+1] = temp; 
        		}             	
        	}        	
        }
    }

}
