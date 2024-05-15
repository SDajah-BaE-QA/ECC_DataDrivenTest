package bankAlEtihadECC.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bankAlEtihadECC.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "details-button")
	private WebElement detailsButton;
	@FindBy(id = "proceed-link")
	private WebElement proceedLink;
	@FindBy(id = "loginContainer:txtUsername")
	private WebElement userName;
	@FindBy(id = "loginContainer:txtUserPasswordforshow")
	private WebElement userPassord;
	@FindBy(id = "loginContainer:Login")
	private WebElement loginButton;
	// @FindBy(linkText="https://10.2.12.42:8085/UNION/HeaderDispatchAction.do?action=logout")
	@FindBy(id = "logout") // xpath="//html/body/table/tbody/tr/td[2]/div/div/ul/li[1]/a")
	private WebElement logoutBtn;
	@FindBy(id = "btnCollaps")
	private WebElement collaps;
	@FindBy(id = "txt_id_1010000_10")
	private WebElement outwardTab;

//	<a href="https://10.2.12.42:8085/UNION/HeaderDispatchAction.do?action=logout" style="height:32px" target="_top" id="logout" xpath="1">
//    <span>Logout</span> 

	public OutwardPage loginApplication(Object url, Object userLoginName, Object userPass) throws IOException {
		driver.get(url.toString());
		userName.sendKeys(userLoginName.toString());
		userPassord.sendKeys(userPass.toString());
		loginButton.click();
		return new OutwardPage(driver);

	}

//	
//	{public void goTo(String url)
//		driver.get(url);
////		detailsButton.click();
////		proceedLink.click();
////		detailsButton.click();
////		proceedLink.click();
//		
//	}
//	

}
