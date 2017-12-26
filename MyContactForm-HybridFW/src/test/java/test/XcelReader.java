package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

public class XcelReader {
	
	private FileInputStream fis;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String filepath; 
	
	public XcelReader (String file) {
		
		filepath = file;
		try {
			fis = new FileInputStream (file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			row = sheet.getRow(0);	
			fis.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Boolean sheetExists (String sheetname) {
		
		if (workbook.getSheetIndex(sheetname) >= 0) {
			return true;
		}		
		return false;
	}
	
	public int getTotalRows (String sheetname) {
		
		if (sheetExists(sheetname)) {
			sheet = workbook.getSheet(sheetname);
			return sheet.getLastRowNum() + 1;
		}
        
		return 0;
	}
	
	public String getCellValue (String sheetname, String colname, int rownum) {
		
		if (sheetExists(sheetname)) {
			
			sheet = workbook.getSheet(sheetname);

			int columnindex = -1;
			int rowindex = rownum - 1;
			int totalrows = getTotalRows (sheetname);
			
			if (totalrows > 0)
			{
				row = sheet.getRow(0);
				for (short index = 0; index < row.getLastCellNum(); index ++) {
					if (row.getCell(index).getStringCellValue().trim().equals(colname.trim())) {
						columnindex = index;
					}		
				}
			}
			
			row = sheet.getRow(rowindex);
			if (row == null)
				return "";
			
			cell = row.getCell(columnindex);
			if (cell == null)
				return "";
			
			if (row.getCell(columnindex).getCellType() == Cell.CELL_TYPE_STRING) {
				return row.getCell(columnindex).getStringCellValue();
			}
			else if (row.getCell(columnindex).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf((int)row.getCell(columnindex).getNumericCellValue());
			}
		}
		
		return "";
	}
	
	public void setCellValue (String sheetname, String colname, int rownum, String data) throws IOException {
		
		FileInputStream fis;
		FileOutputStream fos;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		
		fis = new FileInputStream (filepath);
		workbook = new XSSFWorkbook(fis);
		
		//check if the sheet exists
		if (workbook.getSheetIndex(sheetname) >= 0) {

			sheet = workbook.getSheet(sheetname);
			
			//get rowindex
			int rowindex = rownum - 1;
			int totalrows = sheet.getLastRowNum() + 1;

			int columnindex = -1;
			//get columnindex by comparing each cell value of first row with the given column name 
			if (totalrows > 0)
			{
				row = sheet.getRow(0);
				for (short index = 0; index < row.getLastCellNum(); index ++) {
					if (row.getCell(index).getStringCellValue().trim().equals(colname.trim())) {
						columnindex = index;
					}		
				}
			}
			
			//load the row of given rownum
			row = sheet.getRow(rowindex);
			
			//get the required cell from the row
            cell = row.getCell(columnindex);
            //if cell is absent, create one
            if (cell == null)
                cell = row.createCell(columnindex);
            
            //set the value
			cell.setCellValue(data);
			
			fis.close();
			
			//commit and save
			fos = new FileOutputStream(filepath);
			workbook.write(fos);
			
			fos.close();
			
		}
	}
}
