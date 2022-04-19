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
		
		//test creating ScheduleRead obj from class
		ScheduleRead scheduleObj = new ScheduleRead();
		assertNotNull(scheduleObj);
		
		//set files to read and write
		String readPath = "./working-files/test.xlsx";
        String savePath = "./working-files/test.txt";
        String readFail = "./working-files/fake.xlsx";
        String saveFail = "./working-files/*.txt";
        
        //catch file not found exception
        try {
        	ScheduleRead.openExcel(readFail);
        	fail("Exception not thrown");
        }
        catch (Exception e) {
        	assertNotNull(e);
        }
        
      //catch file not found exception with escaped character
        try {
        	ScheduleRead.openExcel(readPath + "\b");
        	fail("Exception not thrown");
        }
        catch (Exception e) {
        	assertNotNull(e);
        }
        
        //create map from excel file
        Map schedule = ScheduleRead.openExcel(readPath);
        
        //Check that Map schedule is created
        assertNotNull(schedule);
        //Check value at index 2 is correct
        assertEquals(schedule.get(2).toString(),"[12 Girls, 7]");
        
        //Check that numbers row in test.xlsx is parsed to strings correctly
        assertEquals(((ArrayList) schedule.get(31)).get(1), "1.0");
        //Assert object at index 31 is a valid ArrayList
        assertTrue(schedule.get(31) instanceof java.util.ArrayList);
        
        //test writing created map to a txt file
        ScheduleRead.writeMaptoFile(schedule, savePath);
        
        //use illegal characters in file name
        try {
        	ScheduleRead.writeMaptoFile(schedule, saveFail);
        }
        catch (Exception e) {
        	System.out.println(e);
        }
        
      //use escaped characters in file name
        try {
        	ScheduleRead.writeMaptoFile(schedule, savePath + "\b");
        }
        catch (Exception e) {
        	System.out.println(e);
        }
	}

}
