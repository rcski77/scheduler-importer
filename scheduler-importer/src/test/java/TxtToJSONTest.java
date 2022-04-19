import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import ad5labs.com.scheduler.importer.TxtToJSON;
import org.junit.jupiter.api.Test;

class TxtToJSONTest {

	@Test
	void test() {

		// test class constructor
		TxtToJSON testClass = new TxtToJSON();
		assertNotNull(testClass);

		// set files to read and write
		String readPath = "./working-files/JSONtest.txt";
		//String readFail = "./working-files/fake.xlsx";
		
		try {
			TxtToJSON.readFile(readPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
