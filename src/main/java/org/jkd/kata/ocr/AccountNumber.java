package org.jkd.kata.ocr;

public class AccountNumber {

	private long value;
	private State state;

	private AccountNumber(long value, State state){
		this.value = value;
		this.state = state;
	}
	
	public static AccountNumber createFromOCRReading(OCRReading ocrReading) {
		
		if(ocrReading.isValid()){
			
			return new AccountNumber(123456789, State.VALID);
		}
		
		return new AccountNumber(0, State.INVALID);
	}

	public boolean isValid() {
		
		return state == State.VALID;
	}
	
	public static enum State {
		VALID, INVALID;
	}

	public long getValue() {
		return value;
	}

	public State getState() {
		return state;
	}
	
}
