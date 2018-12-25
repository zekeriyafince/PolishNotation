package polishnotation;

import java.util.Scanner;

/**
 *
 * @author Zekeriya
 */
public class PolishNotation {

    
    public static void main(String[] args) {
        
        System.out.println("* + * + 1 2 + 3 4 5 6");
//        System.out.println("* + * + 100 2 + 3 4 5 6");
//        System.out.println("* + * + 100 2 + 3 54 5 6");
//        System.out.println("* + * + 11 2 + 1 4 7 2");
//        System.out.println("* + * + 7 3 - 2 5 8 9");
        String input;

        Scanner inputKlavye = new Scanner(System.in);
        System.out.print("Program input : ");
        input = inputKlavye.nextLine();
        System.out.println(input);
        
        ClientPolish client = new ClientPolish(input);
        client.IfadeHesapla();
        
        

        
        
    }
    
}
