import java.util.Scanner;
import java.math.*;
import java.lang.*;

public class HexAndBinaryDecoder {
    /**
     * Takes individual characters and converts them to their hex value. It sets any character other than
     * 0-9 and a-f, equal to zero. Is used in hexStringDecode.
     */
    public static short hexCharDecode(char hexChar) {
        short out;

        //make all input characters capitalized
        char Upper = Character.toUpperCase(hexChar);

        //converting character into ascii designation
        int ascii = (int) Upper;
        int decVal = 0;

        //case for the numbers between 0-9
        if (ascii > 47 && ascii < 58) {
            decVal = ascii - 48;
        //case for the letters from a-f
        } else if (ascii > 64 && ascii < 71) {
            decVal = ascii - 55;
        }
        out = (short) decVal;
        return out;
    }

    /**Converts entire hex string into a decimal value stored in the long integer type. It uses hexCharDecode to convert
     * the individual characters into their hex value.
     */
    public static long hexStringDecode(String hex) {
        //Initialize counter and output variables
        int ctr = 0;
        long out = 0;
        char[] test = new char[2];

        //catches the x0
        StringBuilder sb = new StringBuilder(hex);
        if(hex.length()>2) {
            sb.getChars(0, 2, test, 0);
            if (test[0] == 'x' && test[1] == '0') {
                sb.delete(0, 2);
            }
        }
        //reversing the input so it can be put into the for loop in the correct order
        String reverse = sb.reverse().toString();
        //for loop for adding the hex digits to get a decimal output
        for (char ch : reverse.toCharArray()) {
            out += hexCharDecode(ch) * Math.pow(16, ctr);
            ctr++;
        }
        return out;
    }

    /**Converts a binary string into a decimal value stored in a long variable type. It ignores any character, in the
     * input string, that is not 1. All other characters are set equal to zero. Thus it ignores any values in the front
     * of the binary string that are not 1.
     */
    public static long binaryStringDecode(String bin) {
        StringBuilder sb = new StringBuilder(bin);
        int ctr = 0;
        long out = 0;
        int charVal;
        String reverse = sb.reverse().toString();
        for (char ch : reverse.toCharArray()) {
            charVal = (int) ch;
            if (charVal == 49) {
                charVal = 1;
            } else {
                charVal = 0;
            }
            out += charVal * Math.pow(2, ctr);
            ctr++;
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int choice = 0;
        String input;
        while (true) {
            System.out.println("Choose an option: \n" + "1. Decode a hex string.\n" + "2. Decode a binary string.\n" + "3. Convert binary to hex.\n" + "4. Quit.");
            try {
                choice = userInput.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Input not an Integer");
                userInput.next();
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Please enter the hex string:");
                    input = userInput.next();
                    System.out.println("Result: " + hexStringDecode(input));
                    break;
                case 2:
                    System.out.println("Please enter the binary string:");
                    input = userInput.next();
                    System.out.println("Result: " + binaryStringDecode(input));
                    break;
                case 4:
                    System.out.println("Quitting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose an option between 1 and 4");
                    break;
            }
        }
    }
}