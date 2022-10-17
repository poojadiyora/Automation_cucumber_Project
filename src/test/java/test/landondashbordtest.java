package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.addcategorypage;
import util.BrowserFactory;

public class landondashbordtest {
	WebDriver driver;
	addcategorypage name;

	Random ran = new Random();
	String categoryname = "pooja3" + ran.nextInt(50);
	String duplicatename = "puja" + ran.nextInt(50);

	@BeforeMethod
	public void runall() {
		driver = BrowserFactory.init();
		name = PageFactory.initElements(driver, addcategorypage.class);
	}

	@Test(priority = 1)

	public void validusershouldaddname() throws Exception {
		name.clickaddcategorybutton(categoryname);
		List<String> actual = name.categoryList();
		Assert.assertTrue(BrowserFactory.existeddata(categoryname, actual), "category not exist");
		 Thread.sleep(3000);
		name.clickaddcategorybutton(duplicatename);
		name.clickaddcategorybutton(duplicatename);
		 Thread.sleep(3000);
		Assert.assertTrue(name.massageOfStatus(), "duplicate category not created");
   Thread.sleep(3000);
//	}
//
//	@Test(priority = 2)
//	public void dropdownhaveallthemonths() throws Exception {
		List<String> months = new ArrayList<String>();
		months.add("None");
		months.add("Jan");
		months.add("Feb");
		months.add("Mar");
		months.add("Apr");
		months.add("May");
		months.add("Jun");
		months.add("Jul");
		months.add("Aug");
		months.add("Sep");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");

		List<String> dropdownlist = name.dropdownlist();
		Assert.assertTrue(BrowserFactory.matchdata(months, dropdownlist), "value not match");

	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
