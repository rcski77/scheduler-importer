import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ad5labs.com.scheduler.importer.ScheduleImporter;

class ScheduleImporterTest {

	@Test
	void test() {
		// fail("Not yet implemented");

		// test constructor for class
		ScheduleImporter test1 = new ScheduleImporter();
		assertNotNull(test1);

		// test main method
		try {
			ScheduleImporter.main(null);
		} catch (IOException e) {
			e.printStackTrace();
			fail("An unexpected error occured.");
		}

		// test runImport method with valid filename
		try {
			ScheduleImporter.runImport("./working-files/workingtest.xlsx", "./working-files/workingtest.txt");
		} catch (Exception e) {
			e.printStackTrace();
			fail("An unexpected error occured.");
		}

		// test runImport method with fake files, should fail
		// it will still create fakeSave.txt but file will be empty
		try {
			ScheduleImporter.runImport("./working-files/fakefile.txt", "./working-files/fakeSave.txt");
			fail("Expected error not caught.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// test runImport method with escaped character in file names
		try {
			ScheduleImporter.runImport("./working-files/test\b.xlsx", "./working-files/test\b.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// test runImport method with invalid file names
		try {
			ScheduleImporter.runImport("./working-files/*$.txt", "./working-files/*$.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
