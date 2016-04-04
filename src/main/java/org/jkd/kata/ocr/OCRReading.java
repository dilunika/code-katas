package org.jkd.kata.ocr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dilunika
 *
 */

public class OCRReading extends ArrayList<String> {

	private static final long serialVersionUID = -9113423456158919955L;

	private static final Map<String, Integer> DIGITS = new HashMap<>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OCRReading.class);

	private List<Integer> numbers;

	public OCRReading(List<String> scanedReading) {
		super(scanedReading);
		numbers = new ArrayList<>();
	}

	public boolean isValid() {
		return !isEmpty() && size() == 3 && eachLinesCharacterLengthIs(27) && digitsAreValid();
	}

	@Override
	public boolean add(String e) {
		if (this.size() == 3) {
			throw new IllegalStateException("OCRReading should only have 3 lines.");
		}
		return super.add(e);
	}

	private boolean eachLinesCharacterLengthIs(int i) {

		for (String line : this) {
			if (line.length() != i) {
				return false;
			}
		}

		return true;
	}

	private boolean digitsAreValid() {
		
		return digitsAreValidIterativeVersion();
	}

	private boolean digitsAreValidIterativeVersion() {
		int i = 0; 
		while (i < 27) {
			int j = i + 3;
			String digit = get(0).substring(i, j) + get(1).substring(i, j) + get(2).substring(i, j);
			LOGGER.debug("Decoded Digit -> {}", digit);
			i = j;
			if(DIGITS.containsKey(digit)){
				numbers.add(DIGITS.get(digit));
				LOGGER.debug("Numbers Decoded -> {}" + numbers);
			} else {
				return false;
			}
		}

		return true;
	}
	


	/* 
	 *    
	 *   |
	 *   |  1 -> '','','','','','|','','','|' 
	 *  _ 
	 *  _|
	 * |_   2 -> '','_','','','_','|','|','_',''
	 *  _  
	 *  _|
	 *  _|  3 -> '','_','','','_','|','','_','|' 
	 * 
	 *  |_|
	 *    | 4 -> '','','','|','_','|','','','|'
	 *   _ 
	 *  |_           
	 *   _| 5 -> '','_','','|','_','','','_','|'
	 *   _  
	 *  |_ 
	 *  |_| 6 -> '','_','','|','_','','|','_','|'  
	 *   _ 
	 *    |
	 *    | 7 -> '','_','','','','|','','','|'
	 *   _  
	 *  |_|
	 *  |_| 8 -> '','_','','|','_','|','|','_','|'
	 *   _
	 *  |_|
	 *   _| 9 -> '','_','','|','_','|','','_','|'
	 *   _
	 *  | |
	 *  |_| 0 -> '','_','','|','','|','|','_','|' 
	 *
	 * */
	
	static {
		DIGITS.put("     |  |", 1);
		DIGITS.put(" _  _||_ ", 2);
		DIGITS.put(" _  _| _|", 3);
		DIGITS.put("   |_|  |", 4);
		DIGITS.put(" _ |_  _|", 5);
		DIGITS.put(" _ |_ |_|", 6);
		DIGITS.put(" _   |  |", 7);
		DIGITS.put(" _ |_||_|", 8);
		DIGITS.put(" _ |_| _|", 9);
		DIGITS.put(" _ | ||_|", 0);
	}
}
