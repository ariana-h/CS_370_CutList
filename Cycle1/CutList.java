package Cycle1;
import java.lang.String;
import java.util.*;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class CutList {
	
	private static ArrayList<Wood> List = new ArrayList<Wood>();

	public static void main(String args) throws IOException
	{
		//String FileLoc = args;
		File CutList = new File(args);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(CutList));   
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow Row;
		
		
		if (!List.isEmpty())
		{
			List.clear();
		}
		
		
		int r =1;
		
		while((Row = sheet.getRow(r))!=null && (Row.getCell(2).getNumericCellValue()>0 
		    && Row.getCell(3).getNumericCellValue()> 0 && Row.getCell(4).getNumericCellValue()>0))
		{
			if(Row.getCell(5) != null) {
				List.add(new Wood(
						Row.getCell(0).getStringCellValue(),
						Row.getCell(1).getStringCellValue(),
						Row.getCell(2).getNumericCellValue(),
						Row.getCell(3).getNumericCellValue(),
						Row.getCell(4).getNumericCellValue(),
						Row.getCell(5).getStringCellValue()));
			}
				
			else {
				List.add(new Wood(
						Row.getCell(0).getStringCellValue(),
						Row.getCell(1).getStringCellValue(),
						Row.getCell(2).getNumericCellValue(),
						Row.getCell(3).getNumericCellValue(),
						Row.getCell(4).getNumericCellValue()));
			}
			
				r++;
		}
		r--;
	}
	
	
	public static ArrayList<Wood> GetWood()
	{
		return List;
	}


}