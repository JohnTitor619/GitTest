package cn.yunhe.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ImportExcelUtil {
	
	private final static String excel2003L =".xls";
	private final static String excel2007U =".xlsx";
	

	public  List<List<Object>> getListByExcel(InputStream in,String fileName) throws Exception{
		List<List<Object>> list = null;

		Workbook work = this.getWorkbook(in,fileName);
		if(null == work){
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		
		list = new ArrayList<List<Object>>();
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if(sheet==null){continue;}

			for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum()+1; j++) {
				row = sheet.getRow(j);
				if(row==null||row.getFirstCellNum()==j){continue;}
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(this.getCellValue(cell));
				}
				list.add(li);
			}
		}
		return list;
	}

	public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if(excel2003L.equals(fileType)){
			wb = new HSSFWorkbook(inStr);
		}else if(excel2007U.equals(fileType)){
			wb = new XSSFWorkbook(inStr);
		}else{
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}


	public  Object getCellValue(Cell cell){
		Object value = null;
		DecimalFormat df = new DecimalFormat("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		DecimalFormat df2 = new DecimalFormat("0.00");
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if("General".equals(cell.getCellStyle().getDataFormatString())){
				value = df.format(cell.getNumericCellValue());
			}else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
				value = sdf.format(cell.getDateCellValue());
			}else{
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
	

}
