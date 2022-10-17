package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class addcategorypage {

	WebDriver driver;

	public addcategorypage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//input[@name='categorydata']")
	WebElement ADDNAME_ELEMENT;

	@FindBy(how = How.XPATH, using = "//input[@value='Add category']")
	WebElement ADDCATEGORYBUTTON_ELEMENT;
	
	@FindBy(how = How.XPATH, using = "//body[contains(text(),'The category you want to add already exists:')]")
	WebElement VALIDATEADDCATEGORY_ELEMENT;
	
//	@FindBy(how = How.NAME, using= "due_month" )
//	WebElement CLICKDROPDOWNBUTTON;
	//@FindBy(how = How.CSS,using = "*[name='due_month']") WebElement CLICKDROPDOWNBUTTON;
	@FindBy(how=How.NAME, using= "due_month")WebElement CLICKDROPDOWNBUTTON_ELEMENT;
	
	public List <String> categoryList(){
		List <String> alllist = new ArrayList <String>();
		List<WebElement> c = driver.findElements(By.xpath("//a[@title='Remove this category']"));
		
		for(int i = 0; i<c.size(); i++) {
		alllist.add(i,c.get(i).getText());
		}
		return alllist;
	
	}
	
	public void clickaddcategorybutton(String category) {
		ADDNAME_ELEMENT.sendKeys(category);
		ADDCATEGORYBUTTON_ELEMENT.click();
	}
	
	public boolean massageOfStatus() {
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(VALIDATEADDCATEGORY_ELEMENT));
			return true;
		} catch (Exception e) {
			
		} return false;
	}
	
	public List<String> dropdownlist() throws Exception {
		
		List<String> actualdropdownvalues = new ArrayList<String>();
		Select sel= new Select (CLICKDROPDOWNBUTTON_ELEMENT);
		List<WebElement> dd = sel.getOptions();
		for(int f= 0; f<dd.size(); f++) {
			actualdropdownvalues.add(f, dd.get(f).getText());
			Thread.sleep(3000);
		}
		return actualdropdownvalues;
		}
		
}
