
/**
 * This class represent the main method
 *  
 * @author Lior Maimon 
 * mmn12 , Question 2
 */
import java.util.Scanner;

public class BigIntTest {
	//main method
	public static void main(String[] args) {
		BigInt bigNum1;
		BigInt bigNum2;
		
		bigNum1 = checkNum();
		System.out.println("Your number " + bigNum1 + " received\n");
		bigNum2 = checkNum();
		System.out.println("Your number " + bigNum2 + " received\n");
		System.out.println("The sum of the numbers is \n" +bigNum1+ " + " + bigNum2+ " = " + bigNum1.plus(bigNum2));
		System.out.println("The subtraction of both: \n" + bigNum1+ " - " + bigNum2 + " = " + bigNum1.minus(bigNum2) + "\n" + bigNum2 + " - " + bigNum1 +" = " + bigNum2.minus(bigNum1));
		System.out.println("The multiply of them: \n" + bigNum1 + " * " + bigNum2 + " = " + bigNum1.multiply(bigNum2));
		try{ //try to use divide method if the number we divide in is zero we will get exception
			System.out.println("First number divide by 2nd number is \n" +bigNum1 + " / " + bigNum2+ " = " + bigNum1.divide(bigNum2) );
		}
		catch(ArithmeticException e){
			System.out.println("You cannot divide in zero");
		}
		try{
			System.out.println("Second number divide by 1st number is \n" +bigNum2 + " / " + bigNum1+ " = " + bigNum2.divide(bigNum1) );
		}
		catch(ArithmeticException e){
			System.out.println("You cannot divide in zero");
		}
		System.out.println("Is bote number are equal ? " + bigNum1.equals(bigNum2));
		System.out.println("Compare 1st number to 2nd number (get positive number if 1st > 2nd , negative if opposite and 0 if equal): \n"+bigNum1+" Compare To " +bigNum2+" = "+bigNum1.compareTo(bigNum2));
		System.out.println("Compare 2nd number to 1st number: \n"+bigNum2+" Compare To " +bigNum1+" = "+bigNum2.compareTo(bigNum1));
	
	}
	//private method get input from user and check if it can represent a bigIng if it is make a new bigInt and return it to caller
	private static BigInt checkNum(){
		Scanner input = new Scanner(System.in); //create scanner to obtain input from user
		int counter = 0;
		String num;
		BigInt bigNum = null; 
		do{
			try{ //check if its valid number
				System.out.println("Please enter a string that represent ur BigInt ");
				num = input.nextLine();
				bigNum = new BigInt(num);
				counter ++;
			}
			catch(IllegalArgumentException e){
				System.out.println("that is not legal number, try again ");
			}
		}while(counter < 1);
		return bigNum;
	}

}
