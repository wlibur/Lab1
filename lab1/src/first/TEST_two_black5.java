package zxLab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TEST_two_black5{

	@Test
	public void test() {
		TEST_two m = new TEST_two();
		String mn="2*x+2*x*x*y+3*y*test";
		String str = "!simplify x=1y=1\r";
		StringBuilder complete=new StringBuilder();
		m.simplify(str, mn, complete);
	}

}
