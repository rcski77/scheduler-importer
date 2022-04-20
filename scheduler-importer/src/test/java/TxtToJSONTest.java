import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import ad5labs.com.scheduler.importer.TxtToJSON;
import org.junit.jupiter.api.Test;

class TxtToJSONTest {
	
	//most methods in TxtToJSON class are private and can't be directly tested

	@Test
	void test() {

		// test class constructor
		TxtToJSON testClass = new TxtToJSON();
		assertNotNull(testClass);

		// set files to read and write
		String readPath = "./working-files/JSONtest.txt";
		String readFail = "./working-files/fakeJSON.txt";
		
		//test reading an actual file and writing to a JSON file
		try {
			TxtToJSON.readFile(readPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			fail("A testing error occured that should not have.");
		}
		
		//this test should fail since file doesn't exist
		try {
			TxtToJSON.readFile(readFail);
			fail("Test didn't catch file not found error.");
		} catch (IOException e) {;
			//e.printStackTrace();
			//System.out.println("Test file not found.");
		}
		
	}

}
