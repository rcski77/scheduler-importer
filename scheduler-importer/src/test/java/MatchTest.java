import static org.junit.jupiter.api.Assertions.*;
import ad5labs.com.scheduler.importer.*;

import org.junit.jupiter.api.Test;

class MatchTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		
		//test null constructor
		Match nullMatch = new Match();
		//assert object exists
		assertNotNull(nullMatch);
		//assert toString function works correctly
		assertEquals(nullMatch.toString(), "{}");
		
		
		//test assigned constructor
		Match test2 = new Match("8:00 AM", "Court 1", "18 Open", "R1AM1", 51234);
		//assert object exists
		assertNotNull(test2);
		//assert toString works correctly on object with values
		assertEquals(test2.toString(), "{\"division\":\"18 Open\",\"code\":51234,\"official\":null,\"time\":\"8:00 AM\",\"court\":\"Court 1\",\"matchid\":\"R1AM1\"}");
		//assert key value pairs operate as expected
		assertEquals(test2.get("division"),"18 Open");
		assertEquals(test2.get("code"),"51234");
		assertEquals(test2.get("official"),"null");
		assertEquals(test2.get("time"),"8:00 AM");
		assertEquals(test2.get("court"),"Court 1");
		assertEquals(test2.get("matchid"),"R1AM1");
		
	}

}
