//SESSION 51:
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	//hàm đếm số hàng
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
	}
	//hàm đếm số ô
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		//đếm trong hàng có bao nhiêu ô
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	//hàm lấy dữ liệu trong ô excel
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		//mở file
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			//cách 1:
			//data = cell.toString();
			//cách 2:
			DataFormatter formatter = new DataFormatter();
			//returns the formatted value of a cell as a String regardless of the cell type (trả về String dù dữ liệu ở trong ô là thuộc dạng nào..)
			data = formatter.formatCellValue(cell);
		}catch (Exception e) {
			//khi dữ liệu trong ô trống (-->null) sẽ trả về ngoại lệ 
			data = "";
		}
		//đóng file, sheet đã mở
		wb.close();
		fi.close();
		//trả về dữ liệu
		return data;
	}
	/**
	 * Muốn lấy nhiều dữ liệu 
	 * ---> gọi hàm getCellData lặp lại trong vòng lặp
	 * @throws IOException 
	 */
	//hàm thiết lập dữ liệu cho ô excel
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File xlfile = new File(path);
		//if file not exists then create new file
		if(!xlfile.exists()) {
			wb = new XSSFWorkbook(fi);
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		if(wb.getSheetIndex(sheetName) == -1) {
			wb.createSheet(sheetName);
			ws = wb.getSheet(sheetName);
		}
		//if row not exists then create new row
		if(ws.getRow(rownum)==null) {
			ws.createRow(rownum);
			row = ws.getRow(rownum);
			
			cell = row.createCell(colnum);
			cell.setCellValue(data);
			
			fo = new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}
		
	}
	
	//hàm đổ màu cho ô excel
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.createSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		//bật mode writing
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
}
