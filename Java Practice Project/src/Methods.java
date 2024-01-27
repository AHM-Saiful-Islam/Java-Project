public class Methods {
    static void animal(String name, String sound) {
        System.out.println("Animal is " + name + " and its sound is " + sound);
    }

    static double addition(double number1, double number2) {
        return number1 + number2;
    }

    static void checkAge(int age) {
        // if age is less than 18, deny access
        if (age < 18) {
            System.out.println("Access Denied!!");
        }
        // if age is equal or more than 18, grant access
        if (age >= 18) {
            System.out.println("Access Granted!!");
        }
    }

    // method overloading  - multiple method in same name
    // Multiple methods can have the same name as long as the number
    // and/or type of parameters are different.

    // addition method which parameter's type is different
    static int addition(int number1, int number2) {
        return number1 + number2;
    }

    // addition method which parameter's number is different
    static int addition(int number1, int number2, int number3) {
        return number1 + number2 + number3;
    }

    // scope - access. check which var can be accessed from where
    static void checkScope() {
        // Outer loop
        for (int i = 1; i <= 2; i++) {
            System.out.println("Outer loop can access i, i = " + i);

            // Inner loop
            for (int j = 1; j < 2; j++) {
                System.out.println("Inner loop can access j, j = " + j);

                // Accessing the variable from the outer loop
                System.out.println(" Inner loop can also access i from outer loop, i = " + i);
            }

            // The following line would result in a compilation error
            System.out.println("outer loop trying to access \'j from inner loop\' will create compilation error ");
            // inner loop can not be accessed from outside
        }
    }



}