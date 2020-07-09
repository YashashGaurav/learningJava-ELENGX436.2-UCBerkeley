import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        try{
            int printCount = scanner.nextInt();
            
            for(int count = 0; count< printCount; count++){
                System.out.println("Hello, Yashash!");
            }
        }

        finally {
            scanner.close();
        }
    }
}
