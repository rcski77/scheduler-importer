/*
* Programmer	: Adam Rykse
* Language	: Java
* Purpose	: Import and parse an excel file with tournament schedule
* Assignment	: Software Testing Project
* Date		: April 15, 2022
* Course	: CIS 613
 */
package ad5labs.com.scheduler.importer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Module to import an excel file with a tournament schedule 
 * and Turn into readable object for ref-scheduler project
 */
public class ScheduleRead {
    
     public static Map openExcel(String fileLocation) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        data.get(i).add(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        } //end for Loop
        return data;
    } //end openExcel method
    
    public static void writeMaptoFile(Map<Integer, List<String>> map, String outputPath) throws IOException {
        File file = new File(outputPath);
        BufferedWriter bf = null;
        
        try {
            //create new buffered writer
            bf = new BufferedWriter(new FileWriter(file));
            
            //iterate through map
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                bf.write(entry.getKey() + ":" + entry.getValue());
                
                //new line
                bf.newLine();
            }
            //close writer
            bf.flush();
        }
        catch (IOException e) {
            //e.printStackTrace();
        	throw e;
        }
        finally {
            try {
                bf.close();
            }
            catch (Exception e) {
                //catch here
            	throw e;
            }
        }
    } //end writeMaptoFile method
    
}
