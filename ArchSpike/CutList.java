package ArchSpike;
import java.lang.String;
import java.util.*;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class CutList {
	
	private static ArrayList<Wood> List = new ArrayList<Wood>();

	public static void main(String FileLoc) throws IOException
	{
		File CutList = new File(FileLoc);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(CutList));   
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow Row;
		
		int r =1;
		
		while((Row = sheet.getRow(r))!=null && Row.getLastCellNum()>0)
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