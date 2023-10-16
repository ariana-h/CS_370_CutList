package ArchSpike;


import java.lang.String;
import java.util.*;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class Algorithm {
	

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the File path: ");
		String FileLoc = sc.next();
		FileLoc = FileLoc.replaceAll("\"", "");
		sc.close();
		
		
		File CutList = new File(FileLoc);
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(CutList));   
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow Row;
		
		int r =1;
		
		ArrayList<Wood> List = new ArrayList<Wood>();
		
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
		
		for(Wood W : List)
		{
			System.out.println("Mainboard: "+W.GetWoodtype());
			System.out.println("Grain: "+W.GetGrain());
			System.out.println("Length: "+W.GetLength());
			System.out.println("Width: "+W.GetWidth());
			System.out.println("Amount: "+W.GetAmount());
			System.out.println("Name: "+W.GetName());
			System.out.println("\n");
		}
		
		List.get(6).RotateWood(List.get(6));
		
		Wood W = List.get(6);
		System.out.println("Mainboard: "+W.GetWoodtype());
		System.out.println("Grain: "+W.GetGrain());
		System.out.println("Length: "+W.GetLength());
		System.out.println("Width: "+W.GetWidth());
		System.out.println("Amount: "+W.GetAmount());
		System.out.println("Name: "+W.GetName());
		System.out.println("\n");
	}


}
