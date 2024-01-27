public class Recursions {
    // recursion method to calculate multiply of numbers ranging from start to end
    static int multiplication(int start, int end) {
        // calculation start with end point
        if (end > start) {
            // processing with var sum for understanding the steps
            int sum = end * multiplication(start, end-1);
            System.out.println("Process steps, end = "+ end +", Sum = " + sum);
            return sum;
            //when end is big return end and
            // call the function again recursively. but end point will be 1 less
        }
        else {
            return end;
        }
    }

    // recursion method to calculate sum of numbers ranging from 0 to user given number
    // use this style to write recursion. return endValue + sum (endValue-1).
    static int sum(int endValue) {
        if (endValue > 0) {
            return endValue + sum(endValue-1);
        }
        else {
            return 0; // here endValue and zero is same. endValue will be decreased to 0.
        }
    }
    

}
