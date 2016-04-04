package org.jkd.kata.fizzbizz;

public class FizzBizz {

	public static String play(int i) {
		
		String result = "";
		
		if(i % 15 == 0) {
			result = "FizzBuzz";
		} else if(i % 3 == 0) {
			result = "Fizz";
		} else if(i % 5 == 0){
			result = "Buzz";
		}else {
			result = Integer.toString(i);
		}
		
		return result;
	}

}
