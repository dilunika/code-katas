package org.jkd.kata.ocr;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.jkd.kata.ocr.AccountNumber;
import org.jkd.kata.ocr.OCRReading;
import org.junit.Test;

public class KataOCRTest {

	private static final List<String> VALID_ACC_NO_READING = Arrays.asList( "    _  _     _  _  _  _  _ ",
																			"  | _| _||_||_ |_   ||_||_|",
																			"  ||_  _|  | _||_|  ||_| _|"
															   			  );
	private static final List<String> INVALID_ACC_NO_READING_1 = Arrays.asList( "  _  _     _  _  _  _  _ ",
																				"  | _| _||_||_ |_   ||_||_|",
																				"  ||_  _|  | _||_|  ||_| _|"
			  																  );
	
	private static final List<String> INVALID_ACC_NO_READING_2 = Arrays.asList( "    _  _     _  _  _  _  _ ",
																				"  | _| _||_||_ |_   ||_||_|",
																				"_ ||_  _|  | _||_|  ||_| _|"
			  															  	  );

 
    @Test
	public void createInvalidatedAccountNumberIfItDoesNotHaveRequiredNumberOfCharacters() throws Exception {
		
    	OCRReading ocrReading = new OCRReading(INVALID_ACC_NO_READING_1);
    	AccountNumber accountNumber = AccountNumber.createFromOCRReading(ocrReading);
    	assertThat(accountNumber.isValid(), is(false));
	}
    
    @Test
	public void createInvalidAccountNumberWhenDigitOneIsInvalid() throws Exception {
		
    	OCRReading ocrReading = new OCRReading(INVALID_ACC_NO_READING_2);
    	AccountNumber accountNumber = AccountNumber.createFromOCRReading(ocrReading);
    	assertThat(accountNumber.isValid(), is(false));
	}
    
    @Test
	public void createInvalidAccountNumberWhenSomeDigitIsInvalid() throws Exception {
		
    	String line = VALID_ACC_NO_READING.get(0);
    	int randomIndex = ThreadLocalRandom.current().nextInt(9);
    	line = line.replace(line.charAt(randomIndex), '?');
    	
    	List<String> corruptedReading = Arrays.asList(line, VALID_ACC_NO_READING.get(1), VALID_ACC_NO_READING.get(2));
    	
    	OCRReading ocrReading = new OCRReading(corruptedReading);
    	AccountNumber accountNumber = AccountNumber.createFromOCRReading(ocrReading);
    	assertThat(accountNumber.isValid(), is(false));
	}
    
    @Test
	public void createValidAccountNumberForValidOCRReading() throws Exception {
		
    	OCRReading ocrReading = new OCRReading(VALID_ACC_NO_READING);
    	AccountNumber accountNumber = AccountNumber.createFromOCRReading(ocrReading);
    	assertThat(accountNumber.isValid(), is(true));
	}

}
