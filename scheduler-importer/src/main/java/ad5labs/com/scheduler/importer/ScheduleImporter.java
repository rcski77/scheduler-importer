/*
* Programmer	: Adam Rykse
* Language	: Java
* Purpose	: Import and parse an excel file with tournament schedule
* Assignment	: Software Testing Project
* Date		: April 15, 2022
* Course	: CIS 613
 */
package ad5labs.com.scheduler.importer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Module to import an excel file with a tournament schedule Turn into readable
 * object for ref-scheduler project Currently only works for a single day
 * schedule, need to add date functionality Schedule also must start at 8am or
 * times will not be correct in JSON
 */
public class ScheduleImporter {

	public static void main(String[] args) throws IOException {

		// run entire import to json file workload
		runImport("./working-files/scheduleimport.xlsx", "./working-files/schedule.txt");
	}

	public static void runImport(String readPath, String savePath) {

		// read excel schedule report and turn into a .txt file
		Map<Integer, List<String>> schedule = null;

		try {
			schedule = ScheduleRead.openExcel(readPath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			ScheduleRead.writeMaptoFile(schedule, savePath);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// read text file created previously and write data to json file
		try {
			TxtToJSON.readFile(savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
