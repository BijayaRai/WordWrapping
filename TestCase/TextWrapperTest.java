import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TextWrapperTest {
	TextwrapperMain test= new TextwrapperMain();
	@Test(timeout=3000)
	
	public void stringOrderTest() {
			
		String text="Triometric creates unique end user monitoring products for high-value Web"
				+ " applications, and offers unrivalled expertise in performance consulting. ";
		int width=20;
		
		List<String> actualOutput= test.wrapText(text, width);
		String expectedOutput="Triometric creates "
				+ "unique end user "
				+ "monitoring products "
				+ "for high-value Web "
				+ "applications, and "
				+ "offers unrivalled "
				+ "expertise in "
				+ "performance "
				+ "consulting.";
		
		assertEquals(actualOutput, expectedOutput);
	}

	@Test
	public void readingFromFile() throws FileNotFoundException {
	
		
		String text = test.readText(new BufferedReader(new FileReader("textToRead")));
		
	
		String expectedString="Triometric creates unique end user monitoring products for high-value Web applications, and offers unrivalled expertise in performance consulting.";
		
		assertEquals(text, expectedString);
	}
	
	@Test
	public void exceptionThrwoingMainFuncation () throws Exception
	{	
		test.main(new String[] {"textToRead","5"});
	
	}
	
	@Test
	public void exceptingThrwoingMainFuncation() 
	{	
		 
		try {
		    
		test.main(new String[] {"textToReadu", "20"});
		test.main(new String[] {"textToRead", "a"});
		test.main(new String[] {});
		test.main(new String[] {"textToReadu"});
		test.main(new String[] { ,});
		   }
		   catch (Exception e) {
		     Assert.fail("Exception " + e);
		   }
	
	}
	
}
