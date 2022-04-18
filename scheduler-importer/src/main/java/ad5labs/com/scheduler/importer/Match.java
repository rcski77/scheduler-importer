/*
* Programmer	: Adam Rykse
* Language	: Java
* Purpose	: Import and parse an excel file with tournament schedule
* Assignment	: Software Testing Project
* Date		: April 15, 2022
* Course	: CIS 613
 */
package ad5labs.com.scheduler.importer;

import org.json.simple.JSONObject;

/**
 *
 * @author rcski
 */
public class Match{
    
    private JSONObject obj = new JSONObject();
    
    //private String time, court, division, matchid;
    //private int code;
    
    public Match() {
        //null constructor
    }
    
    public Match(String time, String court, String division, String matchid, int code) {
        this.obj.put("time", time);
        this.obj.put("court", court);
        this.obj.put("division", division);
        this.obj.put("matchid", matchid);
        this.obj.put("code", code);
        this.obj.put("official", null);
    }
    
    @Override
    public String toString() {
        return this.obj.toString();
    }
    
    public String get(String key) {
    	if (obj.get(key) == null) {
    		return "null";
    	} else {
    		return obj.get(key).toString();
    	}
    }
    
}
