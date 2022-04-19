/*
* Programmer	: Adam Rykse
* Language	: Java
* Purpose	: Import and parse an excel file with tournament schedule
* Assignment	: Software Testing Project
* Date		: April 15, 2022
* Course	: CIS 613
 */
package ad5labs.com.scheduler.importer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Module to import an excel file with a tournament schedule Turn into readable
 * object for ref-scheduler project
 * Currently only works for a single day schedule, need to add date functionality
 * Schedule also must start at 8am or times will not be correct in JSON
 */
public class ScheduleImporter {

    public static void main(String[] args) throws IOException {
    	
    	//set read and save file paths
        String readPath = "./working-files/scheduleimport.xlsx";
        String savePath = "./working-files/schedule.txt";
        
        //read excel schedule report and turn into a .txt file
        Map<Integer, List<String>> schedule = ScheduleRead.openExcel(readPath);
        ScheduleRead.writeMaptoFile(schedule, savePath + "\b");
        
        //read text file created previously and write data to json file
        TxtToJSON.readFile(savePath);
        
    }

}
