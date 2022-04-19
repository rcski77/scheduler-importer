import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;

@RunWith(JUnitPlatform.class)
@SelectClasses({MatchTest.class, ScheduleReadTest.class, TxtToJSONTest.class})

public class JUnitTestSuite {

}
