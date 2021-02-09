package calculatriceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.CalcString;

class CalcStringTest {

	private CalcString c = new CalcString();
	
	@Test
	void testGetStringForScreen1() {
		c.setString("1.0");
		assertEquals("1", c.getStringForScreen());
	}

	@Test
	void testGetStringForScreen2() {
		c.setString("10.0");
		assertEquals("10", c.getStringForScreen());
	}
	
	@Test
	void testGetStringForScreen3() {
		c.setString("0.01000");
		assertEquals("0.01", c.getStringForScreen());
	}
	
	@Test
	void testGetStringForScreen4() {
		c.setString("1000");
		assertEquals("1000", c.getStringForScreen());
	}
	
	@Test
	void testGetStringForScreen5() {
		c.setString("10.01000");
		assertEquals("10.01", c.getStringForScreen());
	}
	
	@Test
	void testGetStringForScreen6() {
		c.setString("10.0100001");
		assertEquals("10.0100001", c.getStringForScreen());
	}
}
