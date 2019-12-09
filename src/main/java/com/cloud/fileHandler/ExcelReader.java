/*
 * 
 */

package com.cloud.fileHandler;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cloud.core.*;

import static java.sql.Types.NUMERIC;

public class ExcelReader
{
	public static LinkedHashMap<String,String[]> GetTestCasesRepo;
	private static Workbook testDataWorkBook = getWorkBook("./TestData/TestData.xls");
	private static Workbook workBook = null;
	private static XSSFSheet sheet = null;
	private XSSFCell cell = null;
	private XSSFRow row   =null;



	/** This function will return the workbook
	 * @param FilePath
	 * @return
	 */
	private static Workbook getWorkBook(String FilePath)
	{

		try
		{
			FileInputStream InputStream = new FileInputStream(FilePath);
			Log.info("File read from: "+FilePath);
			if(FilePath.endsWith("xls")){ workBook = new HSSFWorkbook(InputStream); }
			else if(FilePath.endsWith("xlsx")){
				workBook = new XSSFWorkbook(InputStream);
			}
		}
		catch(Exception E)
		{
			Log.error(E.toString());

		}
		return workBook;
	}

	// returns the row count in a sheet
	private int getRowCount(String sheetName){
		int index = workBook.getSheetIndex(sheetName);

		if(index==-1)
			return 0;
		else{
			sheet = (XSSFSheet) workBook.getSheetAt(index);
			int number=(sheet.getLastRowNum() +1);
			return number;
		}

	}

	/** This function will read the workbook and return the test data required for test case
	 * @param workBook
	 * @return
	 */
	public static Map<String, String> getTestData(Workbook workBook)
	{
		Map<String,String> TestData = new LinkedHashMap<String,String>();
		try
		{
			String Key = null;
			String Value = null;
			int getRowCount = 0;
			int getColCount = 0;
			int NoOfSheets = workBook.getNumberOfSheets();

			for(int i=0;i<NoOfSheets;i++)
			{
				Sheet CurrentSheet = workBook.getSheetAt(i);
				getRowCount = CurrentSheet.getLastRowNum();
				for(int j=1;j<getRowCount;j++)
				{
					getColCount = CurrentSheet.getRow(0).getPhysicalNumberOfCells();
					for (int k = 1; k < getColCount; k++) 
					{
						Key = getCellValue(CurrentSheet.getRow(0).getCell(k));
						Value = getCellValue(CurrentSheet.getRow(j).getCell(k));
						Log.info("Read Test Data, "+Key+", "+Value);
						TestData.put(Key, Value);
					}
				}
			}
		}
		catch(Exception E)
		{
			Log.error("Error occured while getting Test Data");
			Log.error(E.toString());
		}
		return TestData;
	}

	// returns the data from a cell
	private String getCellData(String sheetName, int rowNum, int colNum){
		try{
			String cellValue = null;
			if(rowNum <=0)
				return "";

			int index = workBook.getSheetIndex(sheetName);

			if(index==-1)
				return "";


			sheet = (XSSFSheet) workBook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";

			cell = row.getCell(colNum);


			if(cell!=null) {
				switch(cell.getCellTypeEnum()) {
                    case NUMERIC:
                        Double tempVal = cell.getNumericCellValue();
                        cellValue = String.valueOf(tempVal);
                        break;
                    default:
                        cellValue = cell.getStringCellValue();
                        break;
				}
			}

			return cellValue;
		}
		catch(Exception e){

			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}


	/** This function will return the cell type
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell)
	{
		String Value = null;
		try
		{
			switch (cell.getCellTypeEnum()) 
			{
			case STRING:
				Value = cell.getStringCellValue().trim();			

			case NUMERIC:
				Double Temp = cell.getNumericCellValue();
				Value = Integer.toString(Temp.intValue()).trim();

			case BOOLEAN:
				Log.info("Current read cell is of Boolean type");
				break;

			default:
				break;
			}
		}
		catch(Exception E)
		{
			Log.error("Exception while reading cell type: "+E.toString());
		}
		return Value;
	}

	public LinkedHashMap<String, String[]>getTestCases(String FilePath,String SheetName)
	{
		getWorkBook(FilePath);
		LinkedHashMap<String, String[]> testCaseRep = new LinkedHashMap<>();
		Log.info("Test case repository reading started, FilePath: "+ FilePath);
		for (int RowItr = 2; RowItr <= getRowCount(SheetName)  ; RowItr++)
		{
			String testId =  getCellData(SheetName,RowItr,0);
			String getCat = getCellData(SheetName, RowItr,1);
			String getTestCase = getCellData(SheetName, RowItr,2);
			String getTestDesc = getCellData(SheetName, RowItr,3);
			Log.info("TestCaseDoc, "+testId+", "+getCat+", "+getTestCase);
			String[] TempArray = new String[]{getCat,getTestCase,getTestDesc};
			testCaseRep.put(testId,TempArray);
		}
		Log.info("Test case repository reading completed");
		return testCaseRep;
	}

}
