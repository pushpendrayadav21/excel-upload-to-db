package com.pusp.excelintegration.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.pusp.excelintegration.model.ProductCategory;

@Component
public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	  static String SHEET = "CATEGORY";

	  public static boolean hasExcelFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<ProductCategory> excelTo(InputStream is) throws  Exception{
	    
	      Workbook workbook = new XSSFWorkbook(is);

	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();

	      List<ProductCategory> categories = new ArrayList<>();

	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();

	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }

	        Iterator<Cell> cellsInRow = currentRow.iterator();

	        ProductCategory category = new ProductCategory();

	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();

	          switch (cellIdx) {
				/*
				 * case 0: category.setId((int)currentCell.getNumericCellValue()); break;
				 */

	          case 0:
	        	  category.setName(currentCell.getStringCellValue());
	            break;

	          default:
	            break;
	          }

	          cellIdx++;
	        }

	        categories.add(category);
	      }

	      workbook.close();

	      return categories;
	    
	  }
	}