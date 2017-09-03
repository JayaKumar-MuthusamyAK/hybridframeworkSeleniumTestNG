package practices;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import com.hybridframework_Selenium.testScripts.Keywords;
import com.hybridframework_Selenium.testUtils.TestUtils;

public class TestSample {
	
	public static void main(String[] args) {
		
		String tot = "booking.confirmationpage.roomandnights|booking.confirmationpage.servicecharge";
		String text = tot.split("\\|")[0];
		System.out.println(text);
		
		String string1 = "Rs 5,677";
		System.out.println(string1.split("Rs ")[1].replace(",", "").trim());
		
		
	}
	/*public String verifySuccessMsg(String objectkeys) throws Exception {

		String name =objectkeys;
		
		if(name.contains("|")){
			int countnum= StringUtils.countMatches(name, "|");
			for(int i=0; i<=countnum;i++){
				if(getWebElements(name.split("\\|")[i]).size()!=0){
					System.out.println(name.split("\\|")[i]);
					//getWebElement(name.split("\\|")[i]);
					Thread.sleep(3000);
					System.out.println(getWebElement(name.split("\\|")[i]).getText());
					
					if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter an Email Address")){
						Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter an Email Address");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a password")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a password");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Username and password doesn't match")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Username and password doesn't match");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Hey ")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Hey ");
						clickOnlogout(prop.getProperty("logoutOptionlink"));
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter an Email Address")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter an Email Address");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a valid Phone Number")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a valid Phone Number");
						break;
					}
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a password")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a password");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Please enter a valid Email Address")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Please enter a valid Email Address");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("The password should be minimum of 6 characters")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "The password should be minimum of 6 characters");
						break;
					}
					
					else if(getWebElement(name.split("\\|")[i]).getText().equals("Email already registered !")){
						//Keywords.xls.setCellData("Test Data", "Expected_Result", TestUtils.getNum(testCasesName, Keywords.xls,"Expected_Result"), getWebElement(name.split("\\|")[i]).getText());
						Keywords.xls.setCellDataInparticularCell(testCasesName, "Test Data", "Expected_Result", getWebElement(name.split("\\|")[i]).getText());
						Assert.assertEquals(getWebElement(name.split("\\|")[i]).getText(), "Email already registered !");
						break;
					}
				}
				}
				
		}
		else{
			getWebElement(objectkeys);
		}
		
		return "Pass";
	}*/

}
