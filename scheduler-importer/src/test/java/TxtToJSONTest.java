import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import ad5labs.com.scheduler.importer.TxtToJSON;
import org.junit.jupiter.api.Test;

class TxtToJSONTest {

	// most methods in TxtToJSON class are private and can't be directly tested

	@Test
	void test() {

		// test class constructor
		TxtToJSON testClass = new TxtToJSON();
		assertNotNull(testClass);

		// set files to read and write
		String readPath = "./working-files/JSONtest.txt";
		String readFail = "./working-files/fakeJSON.txt";
		String readEscaped = "./working-files/fakeJSON.txt" + "\b";
		String readInvalid = "./working-files/*$.txt";

		// test reading an actual file and writing to a JSON file
		try {
			TxtToJSON.readFile(readPath);
		} catch (Exception e) {
			// e.printStackTrace();
			fail("A testing error occured that should not have.");
		}

		// this test should fail since file doesn't exist
		try {
			TxtToJSON.readFile(readFail);
			fail("Test didn't catch file not found error.");
		} catch (IOException e) {
			// e.printStackTrace();
		}

		// this test should fail with escaped characters
		try {
			TxtToJSON.readFile(readEscaped);
			fail("Test didn't catch file not found error.");
		} catch (IOException e) {
			// e.printStackTrace();
		}

		// this test should fail since filename has invalid characters
		try {
			TxtToJSON.readFile(readInvalid);
			fail("Test didn't catch file not found error.");
		} catch (IOException e) {
			// e.printStackTrace();
		}

	}

}
