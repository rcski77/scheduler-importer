import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;

import ad5labs.com.scheduler.importer.*;

import org.junit.jupiter.api.Test;

class ScheduleReadTest {

	@Test
	void test() throws FileNotFoundException, IOException {
		//fail("Not yet implemented");
		
		//test creating ScheduleRead obj from class
		ScheduleRead scheduleObj = new ScheduleRead();
		assertNotNull(scheduleObj);
		
		//set files to read and write
		String readPath = "./working-files/test.xlsx";
        String savePath = "./working-files/test.txt";
        
        //create map from excel file
        Map schedule = ScheduleRead.openExcel(readPath);
        System.out.println(schedule);
        
        //Check that Map schedule is created
        assertNotNull(schedule);
        //Check value at index 2 is correct
        assertEquals(schedule.get(2).toString(),"[12 Girls, 7]");
        
        //Check that numbers row in test.xlsx is parsed to strings correctly
        assertEquals(((ArrayList) schedule.get(31)).get(1), "1.0");
        //Assert object at index 31 is a valid ArrayList
        assertTrue(schedule.get(31) instanceof java.util.ArrayList);
	}

}
