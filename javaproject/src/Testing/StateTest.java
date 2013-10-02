package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import State.StateHolder;;

public class StateTest {

	@Test
	public void zeroTest() {
		assertEquals("Default state at runtime should be 0",0,StateHolder.images());
	}
	@Test
	public void emptyTest(){
		StateHolder.next();
		assertEquals("State Should be one",1,StateHolder.images());
		StateHolder.empty();
		assertEquals("State should be ZeroState after empty",0,StateHolder.images());
	}
	@Test
	public void nextTest() {
		StateHolder.empty();
		assertEquals("State should be ZeroState after empty",0,StateHolder.images());
		StateHolder.next();
		assertEquals("State should be OneState after a single next",1,StateHolder.images());
		StateHolder.next();
		assertEquals("State should be FourState after another next",4,StateHolder.images());
		StateHolder.next();
		assertEquals("State should be OneState after another next",1,StateHolder.images());
	}
}
