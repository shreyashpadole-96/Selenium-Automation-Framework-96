package Com.DemoWebShop.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtility {
	public String readDataFromSpecificCell(String Sheet1,int rowNum,int cellNum) throws FileNotFoundException, IOException {
		XSSFWorkbook workbook= new XSSFWorkbook(new FileInputStream(FrameworkConstants.excelFilePath));
	
		String celldata = workbook.getSheet(Sheet1).getRow(rowNum).getCell(cellNum).getStringCellValue();
	
		workbook.close();
		return celldata;
	}  
	@DataProvider(name = "RegisterDetails")
	public Object getRegisterData() throws FileNotFoundException, IOException {
		XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(FrameworkConstants.excelFilePath));
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		
		Object[][] data=new Object[rowCount][6];
		DataFormatter formatter=new DataFormatter();
		int count=0;
		for (int i = 1; i<=rowCount; i++) {
			Row row = sheet.getRow(1);
			//check wether row is empty
			if (row==null ||
			     formatter.formatCellValue(row.getCell(1)).isEmpty()) {
				continue;
			}
			for (int j = 1; j<=6; j++) {
			 data[i-1][j-1] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
					 //sheet.getRow(i).getCell(j);
			}
			count++;
		}
		workbook.close();
	    //fis.close();
		//Return only records containing data
		Object[][] finalData=new Object[count][6];
		for (int i = 0; i < count; i++) {
			finalData[i]=data[i];
			
		}
		return finalData;
	}
}
