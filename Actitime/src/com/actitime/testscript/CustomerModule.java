package com.actitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.generic.FileLib;
import com.actitime.pom.HomePage;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)
public class CustomerModule extends BaseClass{
	@Test
	public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException {
		FileLib f=new FileLib();
		String custName = f.getExcelData("CreateCustomer", 1, 2);
		String custDescription = f.getExcelData("CreateCustomer", 1, 3);
		HomePage h=new HomePage(driver);
		//click on task tab
		h.setTaskTab();
		TaskListPage t=new TaskListPage(driver);
		//click on add new button
		t.getAddNewBtn().click();
		t.getNewCustomerOption().click();
		t.getEnterCustNameTbx().sendKeys(custName);
		t.getCustDescrptTbx().sendKeys(custDescription);
		t.getSelectCustomerDD().click();
		t.getOurCompany().click();
		t.getCreateCustBtn().click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(t.getActualCustomerCreated(), custName));
		String actualtext = t.getActualCustomerCreated().getText();
		Assert.assertEquals(actualtext, custName);
		Reporter.log("Customer name ie"+custName +" successfully created",true);
	}
}
