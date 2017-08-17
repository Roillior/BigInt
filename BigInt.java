
/**
 * This class represent a Big Integer 
 * implement interface Comparable
 * 
 * @author Lior Maimon
 * mmn12 , Question 2
 */
import java.util.ArrayList;

public class BigInt implements Comparable {
	//instance variables
	private ArrayList<Integer> bigNumber = new ArrayList<Integer>(); //represent the big number
	private int negFlag = 0; //represent the number sign -> 0 for + , 1 for -
	/**
	 * construct a new BigInt with given string represent the number
	 * @throws IllegalArgumentException in case that entered unvalid number
	 * @param String bigInt - represent the number
	 */
	public BigInt(String bigInt) throws IllegalArgumentException{
		for(int i = (bigInt.length() - 1); i >= 0; i--){
			if(Character.isDigit(bigInt.charAt(i))){
				bigNumber.add(((int)(bigInt.charAt(i)) - 48)); //get asci value of the number and change it to his real value -> asci - 48
			}
			else if((i == 0) && ((bigInt.charAt(i) == '-' ) || (bigInt.charAt(i) == '+'))){
				if((bigInt.charAt(i) == '-' )){
					negFlag = 1;
				}
			}
			else {throw new IllegalArgumentException();}
		}
		if(bigInt.length() == 0){
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Return the sign of the number + or - (represent by 0 or 1 )
	 * @return an integer 0 or 1 
	 */
	public int getNegFlag(){
		return negFlag;
	}
	/**
	 * set the number sign
	 * @param sign - represent the sign of the number + or -
	 */
	public void setNegFlag(int sign){ // 0 for positive number , 1 for negative number
		negFlag = sign;
	}
	/**
	 * Return a pointer to the current big number
	 * @return an address to ArrayList<Integer> that represent the number (with out his sign)
	 */
	public ArrayList<Integer> getBigNumber(){
		return bigNumber;
	}
	/**
	 * method that get BigInt as parameter and add it to the number that called her
	 * @param num - BigInt represent the number we want to add
	 * @return in success return a new BigInt that represent the sum of those two numbers
	 */
	public BigInt plus(BigInt num){
		BigInt result = new BigInt("0");
		ArrayList<Integer> res = result.getBigNumber();
		int carrier = 0;
		int signCase;
		clearLeadZero(this); //clear leading zeroes from the numbers
		clearLeadZero(num);
		res.clear();
		if((this.negFlag == 0) && (num.getNegFlag() == 0)){ // case 0 both numbers are positive
			signCase = 0;
		}
		else if((this.negFlag == 0) && (num.getNegFlag() != 0)){ //case 1 we add negative number to positive number
			signCase = 1;
		}
		else if((this.negFlag != 0) && (num.getNegFlag() == 0)){ //case 2 we add positive number to negative number
			signCase = 2;
		}
		else signCase = 3; //case 3 both numbers are negative
			
		switch(signCase){
		case 0:
		case 3:
			if(this.bigNumber.size() >= num.getBigNumber().size()){
				int i;
				for(i = 0; i < num.getBigNumber().size(); i++){
					res.add(((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) % 10);
					if(((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) != (((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) % 10) ){
						carrier = 1; //when add two number between 0 - 9 the biggest carrier can be 1
					}
					else carrier = 0;
				}
				for(; i < this.bigNumber.size(); i++){ //keep adding the rest of the number to the result
					res.add((this.bigNumber.get(i) + carrier) % 10);
					if((this.bigNumber.get(i) + carrier) != ((this.bigNumber.get(i) + carrier) % 10) ){
						carrier = 1;
					}
					else carrier = 0;
				}
				if(carrier == 1){
					res.add(1);
					carrier = 0 ;
				}
			}
			else {
				int i;
				for(i = 0; i < this.bigNumber.size(); i++){
					res.add(((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) % 10);
					if(((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) != (((this.bigNumber.get(i) + num.getBigNumber().get(i)) + carrier) % 10) ){
						carrier = 1;
					}
					else carrier = 0;
				}
				for(; i < num.getBigNumber().size(); i++){ //keep adding the rest of the number to the result
					res.add((num.getBigNumber().get(i) + carrier) % 10);
					if((num.getBigNumber().get(i) + carrier) != ((num.getBigNumber().get(i) + carrier) % 10)){
						carrier = 1;
					}
					else carrier = 0;
				}
				if(carrier == 1){
					res.add(1);
					carrier = 0 ;
				}
			}
			if(signCase == 3){ //if its case 3 we need to get negative number 
				result.setNegFlag(1);
			}
			break;
		case 1:
		case 2:
			int temp;
			if(signCase == 2){ //if its case 2 change sign to numbers 
				this.setNegFlag(0);
				num.setNegFlag(1);
			}
			if(this.bigNumber.size() >= num.getBigNumber().size()){
					if(this.compareTo(num) < 0){ //check if we subtract number that bigger then ours then we need to get negative number
						int i;
						for(i = 0; i < num.getBigNumber().size(); i++){
							temp = ((num.getBigNumber().get(i) - this.bigNumber.get(i)) + carrier); //big number - small number
							if(temp < 0){
								res.add(temp + 10);
								carrier = -1;
							}
							else{
								res.add(temp);
								carrier = 0;
							}	
						}
						for(; i < this.bigNumber.size(); i++){ //keep adding the rest of the number to the result
							temp = (this.bigNumber.get(i) + carrier);
							if(temp < 0){
								res.add(temp + 10);
								carrier = -1;
							}
							else{
								res.add(temp);
								carrier = 0;
							}
						}
						result.setNegFlag(1); //mark the number as negative number
			    	}
					else{
						int i;
						for(i = 0; i < num.getBigNumber().size(); i++){
							temp = ((this.bigNumber.get(i) - num.getBigNumber().get(i)) + carrier); //first number is bigger we will get positive number
							if(temp < 0){
								res.add(temp + 10);
								carrier = -1;
							}
							else{
								res.add(temp);
								carrier = 0;
							}		
						}
						for(; i < this.bigNumber.size(); i++){ //keep adding the rest of the number to the result
							temp = (this.bigNumber.get(i) + carrier); 
							if(temp < 0){
								res.add(temp + 10);
								carrier = -1;
							}
							else{
								res.add(temp);
								carrier = 0;
							}
						}
					}
			}
			else{
				int i;
				result.setNegFlag(1); //mark the number as negative number
				for(i = 0; i < this.bigNumber.size(); i++){
					temp = ((num.getBigNumber().get(i) - this.bigNumber.get(i)) + carrier); //big number - small number
					if(temp < 0){
						res.add(temp + 10);
						carrier = -1;
					}
					else{
						res.add(temp);
						carrier = 0;
					}	
				}
				for(; i < num.getBigNumber().size(); i++){ //keep adding the rest of the number to the result
					temp = (num.getBigNumber().get(i)  + carrier);
					if(temp < 0){
						res.add(temp + 10);
						carrier = -1;
					}
					else{
						res.add(temp);
						carrier = 0;
					}	
				}
			}
			if(signCase == 2){ //if its case 2 change sign to numbers 
				this.setNegFlag(1);
				num.setNegFlag(0);
				if(result.getNegFlag() == 0){
					result.setNegFlag(1);
				}
				else result.setNegFlag(0);
			}
			break;
			default: System.out.println("Some error occure");
			break;
		}
		clearLeadZero(result); //clear all leading zeroes
		return result;	
	}
	/**
	 * method that get BigInt and subtract it from the BigInt that called her
	 * @param num - BigInt represent the number we want to reduce
	 * @return in success return a new BigInt represent the subtraction of the 2 numbers
	 */
	public BigInt minus(BigInt num){
		BigInt result = new BigInt("0");
		if(num.getNegFlag() == 0){ //change num sign for using plus method
			num.setNegFlag(1);
		}
		else num.setNegFlag(0);
		result = this.plus(num);// call function plus to make minus(we change num sign)
		if(num.getNegFlag() == 0){ //change back num sign to the original sign
			num.setNegFlag(1);
		}
		else num.setNegFlag(0);
		return result;
	}
	/**
	 * method that get BigInt and multiply it in the number that called her
	 * @param num - represent a BigInt
	 * @return in success return new BigInt represent the multiply of both numbers
	 */
	public BigInt multiply(BigInt num){
		BigInt result = new BigInt("0");
		BigInt temporary = new BigInt("0");
		int carrier = 0;
		ArrayList<Integer> temp = temporary.getBigNumber();
		temp.clear();
		clearLeadZero(this); //clear leading zeroes from the numbers
		clearLeadZero(num);
		for(int i = 0; i < this.bigNumber.size(); i++){ //multiply each number in all the digits of the given parameter and add them all together while taking care of there position
			for(int j = 0; j < num.getBigNumber().size(); j++){
				temp.add((int)(((((this.bigNumber.get(i) * num.getBigNumber().get(j)) * Math.pow(10, i)) + carrier) % 10)));
				carrier = ((int)(((((((this.bigNumber.get(i) * num.getBigNumber().get(j)) * Math.pow(10, i)) + carrier))) - ((((this.bigNumber.get(i) * num.getBigNumber().get(j) * Math.pow(10, i)) + carrier) % 10))) / 10));
			}
			if(carrier != 0){//if carrier is not zero add it to the number
				while(carrier != 0){
					temp.add((carrier % 10));
					carrier = (carrier / 10);
				}
			}
			result = result.plus(temporary);
			temp.clear(); //clean the template
		}
		if((this.getNegFlag() == 1 && num.getNegFlag() == 1) || (this.getNegFlag() == 0 && num.getNegFlag() == 0)){ //check for sign
			result.setNegFlag(0);
		}
		else result.setNegFlag(1);
		return result;	
	}
	/**
	 * method that get BigInt and divide it in the number that called her
	 * @throws ArithemeticException - if try to divide by zero
	 * @param num - represent a BigInt
	 * @return in success return new BigInt represent the division of both numbers
	 */
	public BigInt divide(BigInt num) throws ArithmeticException{
		int res = 0;
		BigInt result;
		BigInt temporary = new BigInt("0");
		clearLeadZero(this); //clear leading zeroes from the numbers
		clearLeadZero(num);
		if((num.getBigNumber().size() == 1) && (num.getBigNumber().get(0) == 0) ){ //if number is zero throw exception 
			throw new ArithmeticException();
		}
		while(temporary.compareTo(this) < 0){ //
			temporary = temporary.plus(num);
			res ++;
		}
		if(temporary.compareTo(this) > 0){
			res = res - 1;
		}
		result = new BigInt((String.valueOf(res)));
		
		if((this.getNegFlag() == 1 && num.getNegFlag() == 1) || (this.getNegFlag() == 0 && num.getNegFlag() == 0)){ //check for sign
			result.setNegFlag(0);
		}
		else result.setNegFlag(1);
		
		return result;
	}
	/**
	 * method toString
	 */
	@Override
	public String toString(){
		clearLeadZero(this); //clear leading zeroes from the numbers
		String sign = "";
		String num;
		if(this.getNegFlag() == 1){
			sign = "-";
		}
		num = sign;
		for(int i = (this.bigNumber.size() - 1); i >= 0; i--){
			num = num + this.bigNumber.get(i);
		}
		return num;
	}
	/**
	 * method equals
	 */
	@Override
	public boolean equals(Object obj){
		if((obj instanceof BigInt) && (this.compareTo(((BigInt)obj)) == 0)){
			return true;
		}
		return false;
	}
	/**
	 * method compareTo
	 * @throws IllegalArgumentException - in case object is not BigInt
	 */
	@Override
	public int compareTo(Object obj) throws IllegalArgumentException {
		int res = 0 ;
		if(!(obj instanceof BigInt)){ //check for right input
			System.out.println("method comareTo have to recive a BigInt as argument");
			throw new IllegalArgumentException();
		}
		clearLeadZero(this); //clear leading zeroes from the numbers
		clearLeadZero(((BigInt)obj));
		if(this.bigNumber.size() < ((BigInt)obj).getBigNumber().size()){
			res = -1;
		}
		else if(this.bigNumber.size() > ((BigInt)obj).getBigNumber().size()){
			res = 1;
		}
		else{
			for(int i = (this.bigNumber.size() - 1); i >= 0; i--){ //run on numbers from most important digit to less important one
				if(this.bigNumber.get(i) > ((BigInt)obj).getBigNumber().get(i)){ //if digit is bigger then our number is bigger
					res = 1;
					break;
				}
				else if(this.bigNumber.get(i) < ((BigInt)obj).getBigNumber().get(i)){ //if digit is smaller then our number is smaller
					res = -1;
					break;
				}
			} //if not both then res stay 0 and number are equals
		}		
		return res;
	}
	//private method clearLeadZero - clear all the zeros that come a head in a number - example -> from 000000324 will become 324
	private void clearLeadZero(BigInt num){ 
		for(int i = (num.bigNumber.size() - 1); i > 0; i--){ //run on all digit of the big integer except the most less important one
			if(num.bigNumber.get(i) == 0 ){
				num.bigNumber.remove(i);
			}	
			else break; //when find first digit that not equal to zero break
		}
	}
}
