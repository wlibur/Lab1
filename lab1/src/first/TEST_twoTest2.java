package zxLab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TEST_twoTest2 {

	@Test
	public void test() {
		TEST_two m = new TEST_two();
		m.expression("3*x*y + 2*x*x + 4*y*y+5*y");
	}

}
