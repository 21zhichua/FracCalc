import java.util.Scanner;
public class FracCalc {

    public static void main(String[] args)
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Fraction: ");
        String userInput = scan.nextLine();
        System.out.print(produceAnswer(userInput));


    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String firstFraction(String input) {
        // Finds the first fraction in an equation
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i,i+1).equals(" ")) {
                count++;
            }
            if (count == 1) {
                input = input.substring(0,i);
            }
        }
        return input;
    }
    public static String secondFraction(String input) {
        // Finds the second fraction in an equation
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i,i+1).equals(" ")) {
                count++;
            }
            if (count == 2) {
                input = input.substring(i+1);
            }
        }
        return input;
    }
    public static String returnOperator(String input) {
        // Finds the operator (+,-,*,/)
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i,i+1).equals(" ")) {
                count++;
            }
            if (count == 1) {
                input = input.substring(i+1,i+2);
            }
        }
        return input;
    }
    public static String breakFraction(String input) {
        // Separates equation into its constituent parts (first fraction, second fraction, operator)
        String wholeFraction = "0";
        String numerator = "0";
        String denominator = "0";
        int backslashes = 0;
        int underscores = 0;
        // First if loop looks for underscores, the other looks for backslashes
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i,i+1).equals("_")) {
                wholeFraction = input.substring(0,i);
                numerator = input.substring(i+1,input.indexOf("/"));
                denominator = input.substring(input.indexOf("/") + 1);
                underscores++;
                i = input.length() - 1;
            }
            if (input.substring(i,i+1).equals("/") && underscores == 0) {
                numerator = input.substring(0,i);
                denominator = input.substring(i+1);
                backslashes++;
            }
        }
        // If there are none of either, the denominator is one
        if (underscores == 0 && backslashes == 0) {
            wholeFraction = input;
            denominator = "1";
        }
        if (Integer.parseInt(wholeFraction) < 0) {
            numerator = "-" + numerator;
        }
        return wholeFraction + "," + numerator  + "," + denominator;
    }
    public static String calculateFraction(String input) {
        // Calculates the answer from given values
        String returnValue = "";
        String firstWholeFraction;
        String firstNumerator;
        String firstDenominator;
        String secondWholeFraction;
        String secondNumerator;
        String secondDenominator;
        // Breaks first fraction and gets independent values
        String firstFraction = breakFraction(firstFraction(input));
        Scanner scan = new Scanner(firstFraction);
        scan.useDelimiter(",");
        firstWholeFraction = scan.next();
        firstNumerator = scan.next();
        firstDenominator = scan.next();
        // Breaks second fraction and gets independent values
        String secondFraction = breakFraction(secondFraction(input));
        scan = new Scanner(secondFraction);
        scan.useDelimiter(",");
        secondWholeFraction = scan.next();
        secondNumerator = scan.next();
        secondDenominator = scan.next();
        // Finds appropriate operator to determine return value (operator is used to add numerators)
        String operator = returnOperator(input);
        firstNumerator = Integer.toString(Integer.parseInt(firstNumerator) + (Integer.parseInt(firstWholeFraction)*Integer.parseInt(firstDenominator)));
        secondNumerator = Integer.toString(Integer.parseInt(secondNumerator) + (Integer.parseInt(secondWholeFraction)*Integer.parseInt(secondDenominator)));

        firstNumerator = Integer.toString(Integer.parseInt(firstNumerator)*Integer.parseInt(secondDenominator));
        secondNumerator = Integer.toString(Integer.parseInt(secondNumerator)*Integer.parseInt(firstDenominator));
        firstDenominator = Integer.toString(Integer.parseInt(firstDenominator)*Integer.parseInt(secondDenominator));
        secondDenominator = firstDenominator;
        if (operator.equals("+")) {
            returnValue = Integer.toString(Integer.parseInt(firstNumerator) + Integer.parseInt(secondNumerator));
            returnValue = returnValue  + "/" + firstDenominator;
        } else if (operator.equals("-")) {
            returnValue = Integer.toString(Integer.parseInt(firstNumerator) - Integer.parseInt(secondNumerator));
            returnValue = returnValue  + "/" + firstDenominator;
        } else if (operator.equals("*")) {
            returnValue = Integer.toString(Integer.parseInt(firstNumerator) * Integer.parseInt(secondNumerator));
            firstDenominator = Integer.toString(Integer.parseInt(firstDenominator) * Integer.parseInt(firstDenominator));
            returnValue = returnValue  + "/" + firstDenominator;
        } else {
            returnValue = Integer.toString(Integer.parseInt(firstNumerator)*Integer.parseInt(secondDenominator));
            secondDenominator = Integer.toString(Integer.parseInt(secondNumerator) * Integer.parseInt(firstDenominator));
            returnValue = returnValue + "/" + secondDenominator;
        }
        return returnValue;
    }

    public static String reduceFraction(String fraction) {
        // Reduces the fraction (preferably into clean integers)
        Scanner input = new Scanner(fraction);
        input.useDelimiter("/");
        String numer = input.next();
        String denom = input.next();
        if (Integer.parseInt(numer) % Integer.parseInt(denom) == 0) {
            fraction = Integer.toString(Integer.parseInt(numer) / Integer.parseInt(denom));
        }
        return fraction;
    }

    public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
        String returnValue = "";
        returnValue = calculateFraction(input);
        return returnValue;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need

}
