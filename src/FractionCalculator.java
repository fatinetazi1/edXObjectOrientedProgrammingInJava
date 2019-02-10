import java.util.Scanner;

public class FractionCalculator {

    public static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        System.out.println("This program is a fraction calculator.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");

        while(true){
            System.out.println("----------------------------------------------------------------------------------");

            String operation = getOperation(input);

            if (operation.equalsIgnoreCase("q")) {
                System.exit(0);
            }

            Fraction input1 = getFraction();
            Fraction input2 = getFraction();

            Fraction output = new Fraction(1,1);

            if (operation.equals("=")) {
                boolean result = input1.equals(input2);
                System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + result);
                continue;
            } else if (operation.equals("-")) {
                output = input1.subtract(input2);
            } else if (operation.equals("*")) {
                output = input1.multiply(input2);
            } else if (operation.equals("/")) {
                output = input1.divide(input2);
            } else {
                output = input1.add(input2);
            }

            output.toLowestTerms();
            if(output.getDenominator()%output.getDenominator() == 0){
                int outInt = output.getNumerator()/output.getDenominator();
                System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + outInt);
                continue;
            }

            System.out.println(input1.toString() + " " + operation + " " + input2.toString() + " = " + output);
        }
    }

    public static String getOperation(Scanner input){
        System.out.println("Please enter an operation (+, -, /, *, = or Q to quit): ");
        String operation = input.nextLine();
        while (!operation.equals("+") && !operation.equals("-") && !operation.equals("/")
                && !operation.equals("*") && !operation.equals("=") && !operation.equalsIgnoreCase("q")){
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operation = input.nextLine();
        }
        return operation;
    }

    public static boolean validFraction(String input) {
        if (input.contains("/")) {
            String[] inputParts = input.split("/");
            if (inputParts[0].matches("-?\\d+") && inputParts[1].matches("-?\\d+")) {
                if (Integer.parseInt(inputParts[1]) > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (input.matches("-?\\d+")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static Fraction getFraction(){
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String inputData = input.nextLine();

        while(!validFraction(inputData)){
            System.out.print("Invalid fraction. Please enter a fraction (a/b) or (a), where a and b are integers and b is not zero: ");
            inputData = input.nextLine();
        }

        if(inputData.contains("/")){
            String[] inputParts = inputData.split("/");
            int numInput = Integer.parseInt(inputParts[0]);
            int denInput = Integer.parseInt(inputParts[1]);
            return new Fraction(numInput, denInput);
        } else{
            return new Fraction(Integer.parseInt(inputData));
        }
    }

}
