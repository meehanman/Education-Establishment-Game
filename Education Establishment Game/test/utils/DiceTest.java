package utils;

import static org.junit.Assert.*;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiceTest {
	
	Dice dice;
	int[] values;
	Random rand;
	
	@Before
	public void setUp(){
		values = EasyMock.createMock(int[].class);
		rand = EasyMock.createMock(Random.class);
		dice = new Dice();
	}
	
	@After
	public void tearDown(){
		values = null;
		rand = null;
		dice = null;
	}

	@Test
	public void testGetValues() {
		values = new int[] {1,2};
		EasyMock.expect(dice.getValues()).andReturn(EasyMock.aryEq(values));
		EasyMock.replay(dice);
		
		assertArrayEquals(new int[]{1,2}, dice.getValues());
	}

	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testRollDice() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDoubles() {
		fail("Not yet implemented");
	}

}
