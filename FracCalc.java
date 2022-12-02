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
                i = input.length() - 1;
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
        String wholeFraction = "0";
        String numerator = "0";
        String denominator = "0";
        int backslashes = 0;
        int underscores = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i,i+1).equals("_")) {
                wholeFraction = input.substring(0,input.indexOf("_"));
                numerator = input.substring(input.indexOf("_") + 1,input.indexOf("/"));
                denominator = input.substring(input.indexOf("/") + 1);
                underscores++;
            }
            if (input.substring(i,i+1).equals("/") && underscores == 0) {
                numerator = input.substring(0,i);
                denominator = input.substring(i+1);
                backslashes++;
            }
        }
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
        Scanner scanner = new Scanner(firstFraction);
        scanner.useDelimiter(",");
        firstWholeFraction = scanner.next();
        firstNumerator = scanner.next();
        firstDenominator = scanner.next();
        // Breaks second fraction and gets independent values
        String secondFraction = breakFraction(secondFraction(input));
        scanner = new Scanner(secondFraction);
        scanner.useDelimiter(",");
        secondWholeFraction = scanner.next();
        secondNumerator = scanner.next();
        secondDenominator = scanner.next();
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
        // Reduces a fraction into a simplified form
        Scanner input = new Scanner(fraction);
        input.useDelimiter("/");
        String nume = input.next();
        String deno = input.next();
        System.out.println(Integer.parseInt(deno));
        for (int i = 2; i <= Math.abs(Integer.parseInt(deno)); i++) {
            if (Integer.parseInt(nume) % i == 0 && Integer.parseInt(deno) % i == 0) {
                nume = Integer.toString(Integer.parseInt(nume)/i);
                deno = Integer.toString(Integer.parseInt(deno)/i);
                i = 1;
            }
        }
        if (Integer.parseInt(nume) % Integer.parseInt(deno) == 0) {
            fraction = Integer.toString(Integer.parseInt(nume) / Integer.parseInt(deno));
        } else if (Integer.parseInt(nume)/Integer.parseInt(deno) == 0) {
            if (Integer.parseInt(deno) < 0) {
                fraction = Integer.toString(Integer.parseInt(nume) * -1) + "/" + Math.abs(Integer.parseInt(deno));
            } else {
                fraction = nume + "/" + Math.abs(Integer.parseInt(deno));
            }
        } else {
            String newWhole = Integer.toString(Integer.parseInt(nume)/Integer.parseInt(deno));
            String newNumerator = Integer.toString(Integer.parseInt(nume) % Integer.parseInt(deno));
            fraction = newWhole + "_" + Math.abs(Integer.parseInt(newNumerator)) + "/" + Math.abs(Integer.parseInt(deno));
        }
        return fraction;
    }

    public static String produceAnswer(String input)
    {
        // Returns simplified answer form of equation
        // TODO: Implement this function to produce the solution to the input
        String returnValue = "";
        returnValue = calculateFraction(input);
        returnValue = reduceFraction(returnValue);
        return returnValue;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need

}
