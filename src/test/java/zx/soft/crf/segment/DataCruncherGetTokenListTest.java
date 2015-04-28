package zx.soft.crf.segment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataCruncherGetTokenListTest {

	@Test
	public void testGetTokenListWithDelimiter() {
		String tokenString = "South 1/2 Greenwood Avenue, |3";
		String delimit = ",\t/ -():.;'?#`&\"_";
		String impDelimit = ",";
		String[] tokens = DataCruncher.getTokenList(tokenString, delimit, impDelimit);

		assertEquals(tokens.length, 7);
		assertEquals(tokens[1], "1");
		assertEquals(tokens[5], ",");
	}

	@Test
	public void testGetTokenList() {
		String tokenString = "West Goldfield Avenue, |3";
		String delimit = ",\t/ -():.;'?#`&\"_";
		String impDelimit = ",";
		String[] tokens = DataCruncher.getTokenList(tokenString, delimit, impDelimit);

		assertEquals(tokens.length, 5);
		assertEquals(tokens[1], "goldfield");
		assertEquals(tokens[4], "|3");
	}
}
