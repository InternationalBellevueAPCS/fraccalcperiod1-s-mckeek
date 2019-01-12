import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    	Scanner console = new Scanner(System.in);
		System.out.println("Please input an equation.");
		String input = console.nextLine();
		System.out.println(produceAnswer(input));
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	//Declare variables
    			int answerNumerator = 0;
    			int answerDenominator = 0;
    			int answerWhole = 0;
    			
    			String[] inputList = input.split(" ");
    			String firstValue = inputList[0];
    			String operator = inputList[1];
    			String secondValue = inputList[2];
    			int[] firstFraction = readValue(firstValue);
    			int[] secondFraction = readValue(secondValue);
    			//Addition and Subtraction Math
    			if(operator.equals("+")) {
    				answerNumerator = (firstFraction[0] * secondFraction[1]) + (secondFraction[0] * firstFraction[1]);
    				answerDenominator = firstFraction[1] * secondFraction[1];
    			} else if(operator.equals("-")) {
    				answerNumerator = (firstFraction[0] * secondFraction[1]) - (secondFraction[0] * firstFraction[1]);
    				answerDenominator = firstFraction[1] * secondFraction[1];
    			}
    		

    			//Only switches the denominator if the operator is multiplying or dividing.
    				
//    			int temp = firstFraction[1];
//    			firstFraction[1] *= secondFraction[1];
//    			secondFraction[1] *= temp;
    			
    			else if(operator.equals("*")) {
   					answerNumerator = firstFraction[0] * secondFraction[0];
   					answerDenominator = firstFraction[1] * secondFraction[1];
   				} else if(operator.equals("/")) {
   					answerNumerator = firstFraction[0] * secondFraction[1];
   					answerDenominator = secondFraction[0] * firstFraction[1];
   				}
    			
    			
    			
    			return mixedFrac(answerNumerator, answerDenominator);
    			
        
    }
    

    // TODO: Fill in the space below with helper methods
    public static int[] readValue(String value) {
		//Declare variables
		int wholeNum = 0;
		int numerator = 0;
		int denominator = 0;
		
		//Checks to see if value is a fraction
		if(value.contains("/")) {
			String[] valueList = value.split("/");
			String numeratorStr = valueList[0];
			denominator = Integer.parseInt(valueList[1]);
			
			//Checks to see if there is a mixed fraction
			if(numeratorStr.contains("_")) {
				//Separates the whole number and numerator
				String[] numeratorList = numeratorStr.split("_");
				wholeNum = Integer.parseInt(numeratorList[0]);
				numerator = Integer.parseInt(numeratorList[1]);
			} else {
				numerator = Integer.parseInt(numeratorStr);
			}
		} else {
			wholeNum = Integer.parseInt(value);
			int[] WholeFrac = {wholeNum, 1};
			return WholeFrac;
		}
		//Converts the value to an improper fraction
		numerator += denominator*wholeNum;
		//returns the improper fraction
		int[] fraction = {numerator, denominator};
		return fraction;
		
		
		
	}
	public static String mixedFrac(int numerator, int denominator) {
		String response = "";
		int wholeNum = numerator/denominator;
		if(numerator%denominator == 0) {
			response = "" + wholeNum;
		}
		else if(Math.abs(numerator) >= denominator) {
			numerator = numerator%denominator;
			int divisor = greatestCommonDivisor(numerator, denominator);
			numerator /= divisor;
			denominator /= divisor;
			if(numerator < 0 || denominator < 0) {
				if(numerator < 0 && denominator < 0) {
					wholeNum *= -1;
					numerator = Math.abs(numerator);
					denominator = Math.abs(denominator);
				}
				else {
				wholeNum = Math.abs(wholeNum);
				wholeNum *= -1;
				numerator = Math.abs(numerator);
				denominator = Math.abs(denominator);
				}
				
			}
			
		    response = wholeNum + "_" + numerator + "/" + denominator;
		} else {
			int divisor = greatestCommonDivisor(numerator, denominator);
			numerator /= divisor;
			denominator /= divisor;
			if(numerator < 0 || denominator < 0) {
				if(numerator < 0 && denominator < 0) {
					denominator = Math.abs(denominator);
					numerator = Math.abs(numerator);
				}
				else {
					numerator = Math.abs(numerator);
					denominator = Math.abs(denominator);
					numerator *= -1;
				}
			}
		response = numerator + "/" + denominator;
		
		
		}
		
		return response;
	}
	
	

    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
