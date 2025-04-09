//SESSION: 51

package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;
//(phục vụ viết tc003)
//contain only dataproviders method (many..)
public class DataProviders {
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		//taking xl file from testData
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		//creating an object for xlUtility (vì dùng public static nên không cần)
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		//created for two dimension array which can stored
		String logindata[][] = new String[totalrows][totalcols];
		for (int i=1; i<=totalrows; i++) {
			for (int j=0; j<totalcols; j++) {
				//lưu giá trị [1][0] vào vị trí [0][0]
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		//returning two dimension array
		return logindata;
		
	}
	
	//DataProvider 2
	
	//DataProvider 3
}
