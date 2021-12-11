package com.lms.test.utils18;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xxsf.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import java.lang.Math;
public class ReadExcel_Supriya_AllDataTypes
{      static XSSFWorkbook workbook;
	   static XSSFSheet sheet;
	   static FileInputStream ip;
	  public static Object[][] read(String File, String SheetName) throws IOException,NullPointerException
	  
       { 
		  ip = new FileInputStream(File);
		 XSSFWorkbook wb = new XSSFWorkbook(ip);
		 XSSFSheet sh =wb.getSheet(SheetName);
		 Row row1 = sh.getRow(1);
		 int rowCount = sh.getLastRowNum()-sh.getFirstRowNum();
	     int ColCount = row1.getLastCellNum();
		 Object [][] excelDataType = new Object[rowCount][ColCount];
		 Object [][] excelDataValuereturn=new Object[rowCount][ColCount];
          for(int i=1;i<=rowCount;i++)
		
			{
              for(int j=0;j<ColCount;j++)
			
				{
					
				excelDataType[i-1][j]=sh.getRow(i).getCell(j).getCellType();
						
				        if (excelDataType[i-1][j]==CellType.NUMERIC)
						{
							Double a=sh.getRow(i).getCell(j).getNumericCellValue();
							Math.round(a);
					        excelDataValuereturn[i-1][j]=(int)Math.round(a);
					    }
		
						if (excelDataType[i-1][j]==CellType.STRING)
						{
							
								excelDataValuereturn[i-1][j]=sh.getRow(i).getCell(j).getStringCellValue();
							
						 }
		
	
						if (excelDataType[i-1][j]==CellType.BOOLEAN)
						{
							
							excelDataValuereturn[i-1][j]=sh.getRow(i).getCell(j).getBooleanCellValue();
						
						}}}return excelDataValuereturn;
	}
	
}
