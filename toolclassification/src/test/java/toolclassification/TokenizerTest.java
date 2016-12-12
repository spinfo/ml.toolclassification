package toolclassification;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenizerTest {

	@Test
	public void test() {
		String toTokenize = "Das soll mal, wenn es geht, tokenisiert werden 123.";
		String[] split = toTokenize.split("\\W");
		for (String string : split) {
			System.out.println(string);
		}
		
	}

}
