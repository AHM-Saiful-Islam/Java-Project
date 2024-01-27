import java.lang.invoke.StringConcatFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Java Practice!");
        //variable
        int number = 10;
        int div = 2;
        double result = number/div;
        System.out.println(result);
        // min value of variable
        int myMinIntValue = Integer.MIN_VALUE;
        long myMinLongValue = Long.MIN_VALUE;
        short myMInShortValue = Short.MIN_VALUE;
        byte myMinByteValue = Byte.MIN_VALUE;
        System.out.println("INT: " + myMinIntValue + "\nLong: " + myMinLongValue);
        System.out.println("Byte: " + myMinByteValue + "\nShort: " + myMInShortValue);
        System.out.println("Max-Byte: " + Byte.MAX_VALUE + "\nMax-Short: " + Short.MAX_VALUE);
        // type casting
        // widening (automatic)- converting a smaller type to a larger type size
        //byte -> short -> char -> int -> long -> float -> double
        int intValue = 10;
        double doubleValue = intValue;
        System.out.println(intValue + "\n" + doubleValue);
        // narrowing casting (Manual) - converting a larger type to a smaller size type
        //double -> float -> long -> int -> char -> short -> byte
        double doubleValue2 = 10.56d;
        int intValue2 = (int) doubleValue2; // manual casting
        System.out.println(intValue2 + "\n" + doubleValue2);
        // string method
        String text = "My name is Saif";
        System.out.println(text.indexOf("is")); // 1st index of is
        // backslash escape
        String backSlashExample = "This is a \"backslash\". It makes writing safe.";
        System.out.println(backSlashExample);
        //carriage return is not working correctly
        String txt1 = "Hi\rWorld!";
        System.out.println(txt1);
        // tab /t is working
        String txt2 = "Hello\tWorld!";
        System.out.println(txt2);
        // backspace
        String txt3 = "Hel\blo World!";
        System.out.println(txt3);

        // switch use case
        int month = 12;
        switch(month) {
            case 1:
                System.out.println("This is January, 2024");
                break;
            case 2:
                System.out.println("This is Feb, 2024");
                break;
            case 3:
                System.out.println("This is March, 2024");
                break;
            case 4:
                System.out.println("This is April, 2024");
                break;
            case 5:
                System.out.println("This is May, 2024");
                break;
            case 6:
                System.out.println("This is June, 2024");
                break;
            case 7:
                System.out.println("This is July, 2024");
                break;
            case 8:
                System.out.println("This is August, 2024");
                break;
            case 9:
                System.out.println("This is September, 2024");
                break;
            case 10:
                System.out.println("This is Oct, 2024");
                break;
            case 11:
                System.out.println("This is Nov, 2024");
                break;
            case 12:
                System.out.println("This is December, 2024");
                break;
            default:
                System.out.println("Wrong entry!, Write between 1-12. ");
        }

        // for each loop
        String [] goodName = {"Naima","Ferdaus", "ratul", "rob"};
        for(String name: goodName) {
            System.out.println(name);
        }

        // declares an array of integers
        int[] anArray;
        // allocates memory for 10 integers
        anArray = new int[10];
        // initialize first element
        anArray[0] = 100;
        // initialize second element
        anArray[1] = 200;

        String [] animalName = {"horse", "duck", "dog", "Parrot"};
        // print horse
        System.out.println(animalName[0]);
        // change dog into cat
        animalName[2] = "cat";
        animalName[1] = "whale";
        System.out.println("Animal List: ");
        for(String animal: animalName){
            System.out.println(animal);
        }

        // multi dimentional array- To access the elements of the myNumbers array, specify two indexes:
        // one for the array, and one for the element inside that array.
        int[][] digits = {{1, 2, 3, 4, 5}, {3, 4, 6}};
        digits[0][2] = 6; //change array value
        System.out.println(digits[0][2]); // 0 is 0 number array. there are 2 array; 0, 1. And 1 is element of index 1 of array 0.
        // get the element of multi dimentional array with for loop
        System.out.println("\nDigit List: ");
        for(int i = 0; i < digits.length; i++){
            for(int j = 0; j < digits[i].length; j++){
                System.out.println(digits[i][j]);
            }
        }
        System.out.println("\nDigit List with for each loop: ");
        // get the element of array with for each loop
        for(int i = 0; i < digits.length; i++){
            for(int digit: digits[i]) {
                System.out.println(digit);
            }
        }
        


    }
}