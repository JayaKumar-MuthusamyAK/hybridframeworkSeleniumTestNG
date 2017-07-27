package practices;

import com.hybridframework_Selenium.excelReader.Xls_Reader;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class excelSetValueTest {
	
	public static void main(String[] args) {
		
		Xls_Reader datatable = new Xls_Reader(System.getProperty("user.dir")+"/src/main/java/com/hybridframework_Selenium/testdata/Test Suite1.xlsx");
		datatable.setCellData("Test Data", "Status", TestUtils.getNum("loginTestwithDifferentscenarios", datatable, "Status"), "aaaa");
		
	}

}
