package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Base.TestBase;

public class ExcelReading extends TestBase{
	//public static FileInputStream fis;
	public static Workbook wb;
	public static Sheet st;
	public static Row row;
	

	public static void setExcelPath(String path) throws IOException
	{
		
		fis = new FileInputStream(path);
		 wb = new XSSFWorkbook(fis);
	}
	
	public static int getRowCount(String SheetName) 
	{
		 st = wb.getSheet(SheetName);
		 int rowcount = st.getLastRowNum()+1;
		 
		 return rowcount;
	}
	
	public static int getColumnCount(String SheetName) 
	{
		st = wb.getSheet(SheetName);
		 row =st.getRow(1);
		 int columnCount = row.getLastCellNum();
		
		return columnCount;
		 
	}
	
	
	public static String getSpecificCellData(String SheetName, int rowNum, int cellNum)
	{
		
		if(rowNum<=0 || cellNum<=0)
		{
			return "give correct rowNum, CellNum";
			
		}
		
		String cellData =st.getRow(rowNum-1).getCell(cellNum-1).getStringCellValue();
		
		return cellData;
		
		
	}
	
	public static Object[][] get2DArrayOFCellsData(String SheetName, int rowNum, int cellNum)
	{
		Object[][] data = new Object[rowNum-1][cellNum];
		
		for(int i=2 ; i<=rowNum ; i++)
		{
			
			for(int j=1; j<=cellNum ; j++)
			{
				
				data[i-2][j-1] = ExcelReading.getSpecificCellData("Sheet1", i, j);
				
			}
			
		}
		return data;
	}
	
}
