import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;


import java.io.*;

public class ParserTest {

	@Rule
   	 public TemporaryFolder folder = new TemporaryFolder();

	@Before
   	 public void setUp() throws IOException {
		File file = folder.newFile("src/main/lab1.txt");
		Parser parser = new Parser(file);
		parser.mapped_results(file);

		String s = FileUtils.readFileToString(file);
	} 

	@Test
	public void TestLab1() {
		 HashMap<String, Object> hm = new HashMap<>();
		 hm.put("code", "C100");
		 hm.put("result", 20.0);
		 hm.put("format", "float");
		 hm.put("comment", "Comment for C100 result");

		String[] mapped = s.split("\n");
	
		 assertEquals("checking hash map result", hm, calculate(mapped));	

		assertThat(s, containsString("C100"));
		assertThat(s, containsString("Comment for C100 result"));
		
		assertThat(parser, instanceOf(LaboratoryTestResult.class));
		
		assertThat(hm.get("code"), instanceOf(String));
		assertThat(hm.get("result"), instanceOf(Float));
		assertThat(hm.get("format"), instanceOf(String));
		assertThat(hm.get("comment"), instanceOf(String));
	}
}