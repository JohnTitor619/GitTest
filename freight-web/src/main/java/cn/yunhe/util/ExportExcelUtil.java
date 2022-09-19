package cn.yunhe.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExportExcelUtil {
	private CellStyle cs;
	public File getExcelTplFile(String path, String filePath) throws Exception {
		String classDir = null;
		String fileBaseDir = null;
		File file = null;
		file = new File(path+filePath);
		if (!file.exists()) {
			throw new Exception("模板文件不存在！");
		}
		return file;
	}

	public Workbook getWorkbook(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new ImportExcelUtil().getWorkbook(fis, file.getName());
		return wb;
	}


	public Sheet getSheet(Workbook wb, String sheetName) throws Exception {
		cs = setSimpleCellStyle(wb);
		Sheet sheet = wb.getSheet(sheetName);
		return sheet;
	}

	public Row createRow(Sheet sheet) throws Exception {
		int lastRow = sheet.getLastRowNum() + 1;
		Row row = sheet.createRow(lastRow);
		return row;
	}

	public Cell createCell(Row row, int CellNum) throws Exception {
		Cell cell = row.createCell(CellNum);
		cell.setCellStyle(cs);
		return cell;
	}


	public CellStyle setSimpleCellStyle(Workbook wb) {
		CellStyle cs = wb.createCellStyle();

		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);

		cs.setAlignment(CellStyle.ALIGN_CENTER);

		return cs;
	}

}
