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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;

/**
 * Module to take a .txt file that was created from ScheduleRead class
 * and make data into a JSON file
 */
public class TxtToJSON {

    private static final JSONArray matchArray = new JSONArray(); //initialize array for JSON objects
    private static final List<String> courtNum = new ArrayList<String>();

    public static void readFile(String filePath) throws IOException {
        int hour = 8;
        int minutes = 0;
        String AmPm = "AM";
        String time = hour + ":00 " + AmPm;
        boolean flag = false; //flag to start parsing lines

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String lastLine = "";
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //find first relevant line in file
                if (line.matches(".*8:00 AM.*")) {
                    flag = true;
                    courtLine(lastLine);
                }

                //convert lines to Match objects
                if (flag) {
                    lineToMatch(line, time);
                    //increment time
                    minutes += 5;
                    if (minutes == 60) {
                        minutes = 0;
                        hour++;
                        if (hour == 12) {
                        	if ("AM".equals(AmPm)) {
                                AmPm = "PM";
                            } else {
                                AmPm = "AM";
                            }
                        } else if (hour == 13) {
                            hour = 1;
                        }
                    }
                    //set new time
                    if (minutes == 0) {
                        time = hour + ":00 " + AmPm;
                    } else {
                        time = hour + ":" + minutes + " " + AmPm;
                    }
                } //end flag if

                lastLine = line;

            } //end while loop
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        //write match array to json file
        arrayToFile(filePath);

    } // end readFile method

    private static void lineToMatch(String line, String time) {
        String delimiter = ",";
        String roundPattern = "R[0-9].+M[0-9]{1,3}";
        line = line.replace("]", "");

        //turn each line into a list of games
        List<String> result = Arrays.asList(line.split(delimiter));

        //go through each line to turn each game into a Match object
        for (int i = 1; i < result.size(); i++) {
            //check if string is empty
            if (result.get(i).matches("\s+") == false) {
                //add commas around round code
                String eachGame = result.get(i).replaceAll(roundPattern, ",$0,");
                //separate line into array based on comma delimiter
                List<String> eachGameList = Arrays.asList(eachGame.split(delimiter));
                
                //remove white space around division label
                eachGameList.set(0, eachGameList.get(0).stripLeading());
                eachGameList.set(0, eachGameList.get(0).stripTrailing());

                //trim match code to just numbers
                eachGameList.set(2, eachGameList.get(2).replaceAll("\\(MC:", ""));
                eachGameList.set(2, eachGameList.get(2).replaceAll("\\)", ""));
                eachGameList.set(2, eachGameList.get(2).replaceAll(" ", ""));

                //turn game info into Match object
                Match match = new Match(time, courtNum.get(i - 1), eachGameList.get(0), eachGameList.get(1), Integer.valueOf(eachGameList.get(2)));
                matchArray.add(match); //add match to JSON array
            }
        }
    } //end lineToMatch method
    
    //reads line of court labels and creats an arraylist containing values
    private static void courtLine(String line) {
        String delimiter = ",";
        line = line.replace("]", "");
        List<String> result = Arrays.asList(line.split(delimiter));
        
        for (int i = 1; i < result.size(); i++) { //iterate through list, skipping first entry
            result.set(i, result.get(i).stripLeading()); //remove leading white space
            courtNum.add(result.get(i)); //add each item to courtNum list
        }
    } //end courtLine method

    //writes matchArray JSONArray to a json file at path
    private static void arrayToFile(String path) throws IOException {
        try (FileWriter writer = new FileWriter("./%s.json".formatted(path))) {
            //write match JSONArray to json file
            writer.write(matchArray.toJSONString());
            writer.flush();
        }
    } //end arrayToFile method
} //end class
