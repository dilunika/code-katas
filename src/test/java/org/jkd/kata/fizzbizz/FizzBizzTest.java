package org.jkd.kata.fizzbizz;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class FizzBizzTest {

	@Test
	public void shouldReturnOneWhenOneIsPassed() throws Exception {
		
		assertThat(FizzBizz.play(1), is(Integer.toString(1)));
		
	}
	
	@Test
	public void shouldReturnTwoWhenTwoIsPassed() throws Exception {
		
		assertThat(FizzBizz.play(2), is(Integer.toString(2)));
		
	}

	@Test
	public void shouldReturnFizzWhenPassedNumberIsMutiplierOfThree() throws Exception {
		
		assertThat(FizzBizz.play(3), is("Fizz"));
		assertThat(FizzBizz.play(6), is("Fizz"));
		assertThat(FizzBizz.play(9), is("Fizz"));
		assertThat(FizzBizz.play(201), is("Fizz"));
		
	}
	
	@Test
	public void shouldReturnBuzzWhenPassedNumberIsMutiplierOfFive() throws Exception {
		
		assertThat(FizzBizz.play(5), is("Buzz"));
		assertThat(FizzBizz.play(10), is("Buzz"));
		assertThat(FizzBizz.play(25), is("Buzz"));
		assertThat(FizzBizz.play(125), is("Buzz"));
		
	}
	
	@Test
	public void shouldReturnFizzBuzzWhenPassedNumberIsMutiplierOfThreeAndFive() throws Exception {
		
		assertThat(FizzBizz.play(15), is("FizzBuzz"));
		assertThat(FizzBizz.play(30), is("FizzBuzz"));
		assertThat(FizzBizz.play(45), is("FizzBuzz"));
		assertThat(FizzBizz.play(60), is("FizzBuzz"));
		
	}
	
	
}
